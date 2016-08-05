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

import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.retro.ImageItemBinder;
import me.drakeet.multitype.sample.retro.RichItemBinder;
import me.drakeet.multitype.sample.retro.TextItemBinder;
import tellh.com.library_retro.ItemViewBinder;
import tellh.com.library_retro.MultiTypeItemsAdapter;

public class RetroMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        List<ItemViewBinder> binders=new ArrayList<>();

        // @formatter:on
        for (int i = 0; i < 20; i++) {
            binders.add(new TextItemBinder(new TextItemBinder.TextItemBean("world")));
            binders.add(new ImageItemBinder(new ImageItemBinder.ImageItemBean(R.mipmap.ic_launcher)));
            binders.add(new RichItemBinder(new RichItemBinder.RichItemBean("小艾大人赛高", R.mipmap.avatar)));
        }

        recyclerView.setAdapter(new MultiTypeItemsAdapter(binders));
    }
}
