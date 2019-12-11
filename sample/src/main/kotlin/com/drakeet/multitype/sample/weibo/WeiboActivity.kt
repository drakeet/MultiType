/*
 * Copyright (c) 2016-present. Drakeet Xu
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

package com.drakeet.multitype.sample.weibo

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.weibo.content.SimpleImage
import com.drakeet.multitype.sample.weibo.content.SimpleImageViewBinder
import com.drakeet.multitype.sample.weibo.content.SimpleText
import com.drakeet.multitype.sample.weibo.content.SimpleTextViewBinder
import java.util.*

/**
 * @author Drakeet Xu
 */
class WeiboActivity : MenuBaseActivity() {

  private lateinit var adapter: MultiTypeAdapter
  private lateinit var items: MutableList<Any>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView = findViewById<RecyclerView>(R.id.list)

    adapter = MultiTypeAdapter()

    adapter.register(Weibo::class).to(
      SimpleTextViewBinder(),
      SimpleImageViewBinder()
    ).withLinker { _, weibo ->
      when (weibo.content) {
        is SimpleText -> 0
        is SimpleImage -> 1
        else -> 0
      }
    }

    recyclerView.adapter = adapter

    items = ArrayList()

    val user = User("drakeet", R.drawable.avatar_drakeet)
    val simpleText = SimpleText("A simple text Weibo: Hello World.")
    val simpleImage = SimpleImage(R.drawable.img_10)
    for (i in 0..19) {
      items.add(Weibo(user, simpleText))
      items.add(Weibo(user, simpleImage))
    }
    adapter.items = items
    adapter.notifyDataSetChanged()

    loadRemoteData()
  }

  private fun loadRemoteData() {
    val weiboList = WeiboJsonParser.fromJson(
      JSON_FROM_SERVICE
        .replace("\$avatar", "" + R.drawable.avatar_drakeet)
        .replace("\$content", "" + R.drawable.img_00)
    )
    items = ArrayList(items)
    items.addAll(0, weiboList)
    adapter.items = items
    adapter.notifyDataSetChanged()
  }

  companion object {
    private const val JSON_FROM_SERVICE = """[
      {
          "content":{
              "text":"A simple text Weibo: JSON_FROM_SERVICE.",
              "content_type":"simple_text"
          },
          "createTime":"Just now",
          "user":{
              "avatar":${"$"}avatar,
              "name":"drakeet"
          }
      },
      {
          "content":{
              "resId":${"$"}content,
              "content_type":"simple_image"
          },
          "createTime":"Just now(JSON_FROM_SERVICE)",
          "user":{
              "avatar":${"$"}avatar,
              "name":"drakeet"
          }
      }
    ]"""
  }
}
