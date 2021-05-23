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

package com.drakeet.multitype.sample.normal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.ViewDelegate
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import java.util.*

/**
 * @author Drakeet Xu
 */
class NormalActivity : MenuBaseActivity() {

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView = findViewById<RecyclerView>(R.id.list)

    adapter.register(TextItemViewBinder())
    adapter.register(ImageItemViewBinder())
    // ✨✨✨
    adapter.register(RichViewDelegate())

    // Test https://github.com/drakeet/MultiType/issues/302
    // Kotlin will reify and inline this to Integer.class
    adapter.register(Int::class, IntViewDelegate())

    recyclerView.adapter = adapter

    val textItem = TextItem("world")
    val imageItem = ImageItem(R.mipmap.ic_launcher)
    val richItem = RichItem("小艾大人赛高", R.drawable.img_11)

    for (i in 0..19) {
      items.add(textItem)
      items.add(imageItem)
      items.add(richItem)
    }
    items.add(Integer.valueOf(999))
    items.add(666)
    items.add(333)
    adapter.items = items
    adapter.notifyDataSetChanged()
  }

  private class IntViewDelegate : ViewDelegate<Int, TextView>() {

    override fun onCreateView(context: Context): TextView {
      return TextView(context)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindView(view: TextView, item: Int) {
      view.text = "IntViewItem: $item"
    }
  }
}
