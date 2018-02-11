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

package me.drakeet.multitype.sample.one2many;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;

import static me.drakeet.multitype.MultiTypeAsserts.assertAllRegistered;
import static me.drakeet.multitype.MultiTypeAsserts.assertHasTheSameAdapter;

/**
 * @author drakeet
 */
public class OneDataToManyActivity extends MenuBaseActivity {

  @VisibleForTesting RecyclerView recyclerView;
  @VisibleForTesting MultiTypeAdapter adapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    recyclerView = findViewById(R.id.list);
    adapter = new MultiTypeAdapter();

        /*
        adapter.register(Data.class).to(
            new DataType1ViewBinder(),
            new DataType2ViewBinder()
        ).withLinker((position, data) ->
            data.type == Data.TYPE_2 ? 1 : 0
        );
        */

    adapter.register(Data.class).to(
        new DataType1ViewBinder(),
        new DataType2ViewBinder()
    ).withClassLinker((position, data) -> {
      if (data.type == Data.TYPE_2) {
        return DataType2ViewBinder.class;
      } else {
        return DataType1ViewBinder.class;
      }
    });

    List<Data> dataList = getDataFromService();
    adapter.setItems(dataList);
    adapter.notifyDataSetChanged();
    assertAllRegistered(adapter, dataList);
    recyclerView.setAdapter(adapter);
    assertHasTheSameAdapter(recyclerView, adapter);
  }


  @VisibleForTesting
  List<Data> getDataFromService() {
    List<Data> list = new ArrayList<>();
    for (int i = 0; i < 30; i = i + 2) {
      list.add(new Data("title: " + i, Data.TYPE_1));
      list.add(new Data("title: " + i + 1, Data.TYPE_2));
    }
    return list;
  }
}
