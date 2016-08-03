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
import me.drakeet.multitype.TypeItem;
import me.drakeet.multitype.TypeItemFactory;
import me.drakeet.multitype.ItemTypePool;
import me.drakeet.multitype.ItemTypesAdapter;
import me.drakeet.multitype.sample.ImageItemContent;
import me.drakeet.multitype.sample.ImageItemViewProvider;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.RichItemContent;
import me.drakeet.multitype.sample.RichItemViewProvider;
import me.drakeet.multitype.sample.TextItemContent;
import me.drakeet.multitype.sample.TextItemViewProvider;

public class MainActivity extends AppCompatActivity {

    private TypeItemFactory itemFactory;
    private RecyclerView recyclerView;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = (RecyclerView) findViewById(R.id.list);
    // @formatter:off
    itemFactory = new TypeItemFactory.Builder().build();
    TypeItem textItem = itemFactory.newItem(new TextItemContent("world"));
    TypeItem imageItem = itemFactory.newItem(new ImageItemContent(R.mipmap.ic_launcher));
    TypeItem richItem = itemFactory.newItem(new RichItemContent("小艾大人赛高", R.mipmap.avatar));
    // @formatter:on
    List<TypeItem> typeItems = new ArrayList<>(80);
    for (int i = 0; i < 20; i++) {
        typeItems.add(textItem);
        typeItems.add(imageItem);
        typeItems.add(richItem);
    }

    ItemTypePool.register(TextItemContent.class, new TextItemViewProvider());
    ItemTypePool.register(ImageItemContent.class, new ImageItemViewProvider());
    ItemTypePool.register(RichItemContent.class, new RichItemViewProvider());

    recyclerView.setAdapter(new ItemTypesAdapter(typeItems));
}
}
