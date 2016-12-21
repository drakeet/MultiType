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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.FlatTypeClassAdapter;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;

public class OneDataToManyActivity extends MenuBaseActivity {

    RecyclerView recyclerView;
    MultiTypeAdapter adapter;
    Items items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        items = new Items();
        adapter = new MultiTypeAdapter(items);
        adapter.setFlatTypeAdapter(new FlatTypeClassAdapter() {
            @NonNull @Override public Class onFlattenClass(@NonNull Object item) {
                return ((Data) item).typeClass;
            }
        });
        adapter.register(Data.Type1.class, new DataType1ViewProvider());
        adapter.register(Data.Type2.class, new DataType2ViewProvider());

        List<Data> dataList = getDataFromService();
        // 如果 Data 们不会自动把 int type -> Class typeClass 的话，
        // 需要自行处理，示例如下：
        // for (Data data : dataList) {
        //     data.typeClass = Data.getTypeClass(data.type);
        // }
        items.addAll(dataList);
        recyclerView.setAdapter(adapter);
    }


    private List<Data> getDataFromService() {
        List<Data> list = new ArrayList<>();
        final int TYPE1 = 1, TYPE2 = 2;
        for (int i = 0; i < 30; i = i + 2) {
            list.add(new Data("title: " + i, TYPE1));
            list.add(new Data("title: " + i + 1, TYPE2));
        }
        return list;
    }
}
