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

package me.drakeet.multitype.sample.weibo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.weibo.content.SimpleImage;
import me.drakeet.multitype.sample.weibo.content.SimpleImageViewProvider;
import me.drakeet.multitype.sample.weibo.content.SimpleText;
import me.drakeet.multitype.sample.weibo.content.SimpleTextViewProvider;

import static me.drakeet.multitype.MultiTypeAsserts.assertAllRegistered;

/**
 * @author drakeet
 */
public class WeiboActivity extends MenuBaseActivity {

    private WeiboAdapter adapter;
    private Items items;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        items = new Items();
        /* WeiboAdapter! */
        adapter = new WeiboAdapter(items);
        adapter.register(SimpleText.class, new SimpleTextViewProvider());
        adapter.register(SimpleImage.class, new SimpleImageViewProvider());

        User user = new User("drakeet", R.mipmap.avatar);
        SimpleText simpleText = new SimpleText("A simple text Weibo: Hello World.");
        SimpleImage simpleImage = new SimpleImage(R.drawable.img_10);
        for (int i = 0; i < 20; i++) {
            items.add(new Weibo(user, simpleText));
            items.add(new Weibo(user, simpleImage));
        }

        assertAllRegistered(adapter, items);
        recyclerView.setAdapter(adapter);
    }
}
