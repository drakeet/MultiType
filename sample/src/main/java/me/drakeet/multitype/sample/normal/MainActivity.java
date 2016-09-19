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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        /* One way:

        TypeItemFactory factory = new TypeItemFactory.Builder().build();
        TypeItem textItem = factory.newItem(new TextItemContent("world"));
        TypeItem imageItem = factory.newItem(new ImageItemContent(R.mipmap.ic_launcher));
        TypeItem richItem = factory.newItem(new RichItemContent("小艾大人赛高", R.mipmap.avatar));
        */

        /* Another way: */
        Items items = new Items();
        TextItemContent textItem = new TextItemContent("world");
        ImageItemContent imageItem = new ImageItemContent(R.mipmap.ic_launcher);
        RichItemContent richItem = new RichItemContent("小艾大人赛高", R.mipmap.avatar);

        for (int i = 0; i < 20; i++) {
            items.add(textItem);
            items.add(imageItem);
            items.add(richItem);
        }

        recyclerView.setAdapter(new MultiTypeAdapter(items.toList()));
    }
}
