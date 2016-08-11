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

package me.drakeet.multitype.sample.page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.TypeItem;
import me.drakeet.multitype.TypeItemFactory;
import me.drakeet.multitype.sample.ImageItemContent;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.RichItemContent;
import me.drakeet.multitype.sample.TextItemContent;

public class MainActivity extends AppCompatActivity {

    private TypeItemFactory factory;
    private RecyclerView recyclerView;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        factory = new TypeItemFactory.Builder().build();
        TypeItem textItem = factory.newItem(new TextItemContent("world"));
        TypeItem imageItem = factory.newItem(new ImageItemContent(R.mipmap.ic_launcher));
        TypeItem richItem = factory.newItem(new RichItemContent("小艾大人赛高", R.mipmap.avatar));

        List<TypeItem> typeItems = new ArrayList<>(80);
        for (int i = 0; i < 20; i++) {
            typeItems.add(textItem);
            typeItems.add(imageItem);
            typeItems.add(richItem);
        }

        recyclerView.setAdapter(new MultiTypeAdapter(typeItems));
    }
}
