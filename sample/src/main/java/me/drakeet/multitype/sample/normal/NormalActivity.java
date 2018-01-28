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

package me.drakeet.multitype.sample.normal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class NormalActivity extends MenuBaseActivity {

  private MultiTypeAdapter adapter;
  private List<Object> items;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    RecyclerView recyclerView = findViewById(R.id.list);

    adapter = new MultiTypeAdapter();
    adapter.register(TextItem.class, new TextItemViewBinder());
    adapter.register(ImageItem.class, new ImageItemViewBinder());
    adapter.register(RichItem.class, new RichItemViewBinder());
    recyclerView.setAdapter(adapter);

    TextItem textItem = new TextItem("world");
    ImageItem imageItem = new ImageItem(R.mipmap.ic_launcher);
    RichItem richItem = new RichItem("小艾大人赛高", R.mipmap.avatar);

    items = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      items.add(textItem);
      items.add(imageItem);
      items.add(richItem);
    }
    adapter.setItems(items);
    adapter.notifyDataSetChanged();
  }
}
