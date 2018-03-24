package me.drakeet.multitype.sample.loadmore;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypePool;
import me.drakeet.multitype.TypePool;

/**
 * @author BoBoMEe
 * @since 2018/3/18
 */

public class LoadMoreAdapter extends MultiTypeAdapter {

  private LoadMore mLoadMore;
  private int mLoadMorePos = -1;
  private static final String TAG = "LoadMoreAdapter";

  public LoadMoreAdapter() {
    this(Collections.emptyList());
  }

  public LoadMoreAdapter(@NonNull List<?> items) {
    this(items, new MultiTypePool());
  }

  public LoadMoreAdapter(@NonNull List<?> items, int initialCapacity) {
    this(items, new MultiTypePool(initialCapacity));
  }

  public LoadMoreAdapter(@NonNull List<?> items, @NonNull TypePool pool) {
    super(items, pool);
    mLoadMore = new LoadMore();

    register(LoadMore.class, new LoadMoreViewBinder());

    registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override public void onChanged() {
        super.onChanged();
        calculateLoadMorePos();
      }

      @Override public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
        calculateLoadMorePos();
      }

      @Override public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
        calculateLoadMorePos();
      }

      @Override public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        calculateLoadMorePos();
      }

      @Override public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
        calculateLoadMorePos();
      }

      @Override public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        calculateLoadMorePos();
      }
    });
  }

  public void setCanLoadMore(boolean loadMore) {
    if (loadMore) {
      mLoadMore = new LoadMore();
    } else {
      mLoadMore = null;
    }
    notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    int itemCount = super.getItemCount();
    if (mLoadMorePos != -1 && null != mLoadMore) {
      itemCount += 1;
    }
    return itemCount;
  }

  @Override public Object getItem(int position) {
    if (mLoadMorePos != -1 && null != mLoadMore && position == mLoadMorePos) {
      return mLoadMore;
    } else {
      return super.getItem(position);
    }
  }

  private void calculateLoadMorePos() {
    if (null != mLoadMore) {
      List<?> items = super.getItems();
      int size = items.size();
      if (size == 0) {
        mLoadMorePos = -1;
      } else {
        mLoadMorePos = size;
      }
    } else {
      mLoadMorePos = -1;
    }
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager == null) {
      Log.e(TAG, "Cannot setSpanSizeLookup on a null LayoutManager Object. "
          + "Call setLayoutManager with a non-null argument.");
      return;
    }

    if (layoutManager instanceof GridLayoutManager) {
      ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        @Override public int getSpanSize(int position) {
          return mLoadMorePos != -1 && null != mLoadMore && position == mLoadMorePos
              ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
        }
      });
    }
  }
}
