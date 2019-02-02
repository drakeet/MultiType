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

package me.drakeet.multitype.sample.communication;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.normal.TextItem;

import static java.lang.String.valueOf;

/**
 * @author drakeet
 */
public class CommunicateWithBinderActivity extends MenuBaseActivity {

  private String aFieldValue = "aFieldValue of SimpleActivity";
  private MultiTypeAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    RecyclerView recyclerView = findViewById(R.id.list);

    List<Object> items = new ArrayList<>();
    adapter = new MultiTypeAdapter();
    adapter.register(TextItem.class, new TextItemWithOutsizeDataViewBinder(aFieldValue));
    recyclerView.setAdapter(adapter);

    for (int i = 0; i < 20; i++) {
      items.add(new TextItem(valueOf(i)));
    }
    adapter.setItems(items);
    adapter.notifyDataSetChanged();
  }
}
