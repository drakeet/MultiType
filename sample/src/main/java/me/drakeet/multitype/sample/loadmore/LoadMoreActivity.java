package me.drakeet.multitype.sample.loadmore;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.normal.ImageItem;
import me.drakeet.multitype.sample.normal.ImageItemViewBinder;
import me.drakeet.multitype.sample.normal.RichItem;
import me.drakeet.multitype.sample.normal.RichItemViewBinder;
import me.drakeet.multitype.sample.normal.TextItem;
import me.drakeet.multitype.sample.normal.TextItemViewBinder;

/**
 * @author drakeet
 */
public class LoadMoreActivity extends MenuBaseActivity implements LoadMoreDelegate.LoadMoreSubject {

  private LoadMoreAdapter adapter;
  private List<Object> items;
  private LoadMoreDelegate loadMoreDelegate;
  private AtomicInteger loadingCount;
  private int currpage = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    RecyclerView recyclerView = findViewById(R.id.list);
    loadingCount = new AtomicInteger(0);
    loadMoreDelegate = new LoadMoreDelegate(this);
    loadMoreDelegate.attach(recyclerView);

    adapter = new LoadMoreAdapter();
    adapter.register(TextItem.class, new TextItemViewBinder());
    adapter.register(ImageItem.class, new ImageItemViewBinder());
    adapter.register(RichItem.class, new RichItemViewBinder());
    recyclerView.setAdapter(adapter);

    items = new ArrayList<>();
    prepareData(true);
    adapter.setItems(items);
    adapter.notifyDataSetChanged();
  }

  @Override public boolean isLoading() {
    return loadingCount.get() > 0;
  }

  @Override public void onLoadMore() {
    loadData(false);
  }

  private void loadData(boolean b) {
    notifyLoadingStarted();

    new Thread() {

      @Override public void run() {
        super.run();
        if (currpage < 3) {
          prepareData(b);
          currpage++;
        }
      }
    }.start();
  }

  private void prepareData(boolean b) {
    TextItem textItem = new TextItem("world");
    ImageItem imageItem = new ImageItem(R.mipmap.ic_launcher);
    RichItem richItem = new RichItem("小艾大人赛高", R.mipmap.avatar);
    Items temp = b ? new Items() : new Items(items);
    for (int i = 0; i < 10; i++) {
      temp.add(textItem);
      temp.add(imageItem);
      temp.add(richItem);
    }
    items = temp;

    new Handler(getMainLooper()).postDelayed(() -> {
      adapter.setCanLoadMore(currpage < 3);
      adapter.setItems(temp);
      adapter.notifyDataSetChanged();
      notifyLoadingFinished();
    }, 800);
  }

  protected void notifyLoadingStarted() {
    loadingCount.getAndIncrement();
  }

  protected void notifyLoadingFinished() {
    loadingCount.decrementAndGet();
  }
}
