/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.drakeet.multitype.sample.bilibili;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.common.Category;
import me.drakeet.multitype.sample.common.CategoryItemViewBinder;

/**
 * @author drakeet
 */
public class BilibiliActivity extends MenuBaseActivity {

  private static final int SPAN_COUNT = 2;
  @VisibleForTesting List<Object> items;
  @VisibleForTesting MultiTypeAdapter adapter;


  private static class JsonData {

    private static final String PREFIX = "这是一条长长的达到两行的标题文字";

    private Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
    private Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
    private Post post10 = new Post(R.drawable.img_10, PREFIX + "post10");
    private Post post11 = new Post(R.drawable.img_11, PREFIX + "post11");

    Category category0 = new Category("title0");
    Post[] postArray = { post00, post01, post10, post11 };

    List<Post> postList = new ArrayList<>();


    {
      postList.add(post00);
      postList.add(post00);
      postList.add(post00);
      postList.add(post00);
      postList.add(post00);
      postList.add(post00);
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);

    adapter = new MultiTypeAdapter();
    adapter.register(Category.class, new CategoryItemViewBinder());

    adapter.register(Post.class, new PostViewBinder());
    adapter.register(PostList.class, new HorizontalPostsViewBinder());

    RecyclerView recyclerView = findViewById(R.id.list);

    final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
    SpanSizeLookup spanSizeLookup = new SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        Object item = items.get(position);
        return (item instanceof PostList || item instanceof Category) ? SPAN_COUNT : 1;
      }
    };
    layoutManager.setSpanSizeLookup(spanSizeLookup);
    recyclerView.setLayoutManager(layoutManager);
    int space = getResources().getDimensionPixelSize(R.dimen.normal_space);
    recyclerView.addItemDecoration(new PostItemDecoration(space, spanSizeLookup));

    recyclerView.setAdapter(adapter);

    JsonData data = new JsonData();
    items = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      /* You also could use Category as your CategoryItemContent directly */
      items.add(data.category0);
      items.add(data.postArray[0]);
      items.add(data.postArray[1]);
      items.add(data.postArray[2]);
      items.add(data.postArray[3]);
      items.add(data.postArray[0]);
      items.add(data.postArray[1]);
      items.add(new PostList(data.postList));
    }
    adapter.setItems(items);
    adapter.notifyDataSetChanged();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }
}
