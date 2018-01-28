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
import java.util.List;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.weibo.content.SimpleImage;
import me.drakeet.multitype.sample.weibo.content.SimpleImageViewBinder;
import me.drakeet.multitype.sample.weibo.content.SimpleText;
import me.drakeet.multitype.sample.weibo.content.SimpleTextViewBinder;

import static me.drakeet.multitype.MultiTypeAsserts.assertAllRegistered;

/**
 * @author drakeet
 */
public class WeiboActivity extends MenuBaseActivity {

  private MultiTypeAdapter adapter;
  private Items items;

  /* @formatter:off */
  private static final String JSON_FROM_SERVICE =
      "[\n" +
          "    {\n" +
          "        \"content\":{\n" +
          "            \"text\":\"A simple text Weibo: JSON_FROM_SERVICE.\",\n" +
          "            \"content_type\":\"simple_text\"\n" +
          "        },\n" +
          "        \"createTime\":\"Just now\",\n" +
          "        \"user\":{\n" +
          "            \"avatar\":2130903040,\n" +
          "            \"name\":\"drakeet\"\n" +
          "        }\n" +
          "    },\n" +
          "    {\n" +
          "        \"content\":{\n" +
          "            \"resId\":2130837591,\n" +
          "            \"content_type\":\"simple_image\"\n" +
          "        },\n" +
          "        \"createTime\":\"Just now(JSON_FROM_SERVICE)\",\n" +
          "        \"user\":{\n" +
          "            \"avatar\":2130903040,\n" +
          "            \"name\":\"drakeet\"\n" +
          "        }\n" +
          "    }\n" +
          "]";
  /* @formatter:on */


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    RecyclerView recyclerView = findViewById(R.id.list);

    adapter = new MultiTypeAdapter();

    adapter.register(Weibo.class).to(
        new SimpleTextViewBinder(),
        new SimpleImageViewBinder()
    ).withLinker((position, weibo) -> {
      if (weibo.content instanceof SimpleText) {
        return 0;
      } else if (weibo.content instanceof SimpleImage) {
        return 1;
      }
      return 0;
    });

    recyclerView.setAdapter(adapter);

    items = new Items();

    User user = new User("drakeet", R.mipmap.avatar);
    SimpleText simpleText = new SimpleText("A simple text Weibo: Hello World.");
    SimpleImage simpleImage = new SimpleImage(R.drawable.img_10);
    for (int i = 0; i < 20; i++) {
      items.add(new Weibo(user, simpleText));
      items.add(new Weibo(user, simpleImage));
    }
    adapter.setItems(items);
    adapter.notifyDataSetChanged();

    assertAllRegistered(adapter, items);

    loadRemoteData();
  }


  private void loadRemoteData() {
    List<Weibo> weiboList = WeiboJsonParser.fromJson(JSON_FROM_SERVICE);
    // atomically
    items = new Items(items);
    items.addAll(0, weiboList);
    adapter.setItems(items);
    adapter.notifyDataSetChanged();
  }
}
