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

package com.drakeet.multitype.sample.bilibili

import android.os.Bundle
import android.view.Menu
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.common.Category
import com.drakeet.multitype.sample.common.CategoryItemViewBinder
import java.util.*

/**
 * @author Drakeet Xu
 */
class BilibiliActivity : MenuBaseActivity() {

  @VisibleForTesting
  internal lateinit var items: MutableList<Any>
  @VisibleForTesting
  internal lateinit var adapter: MultiTypeAdapter

  private class JsonData {

    private val post00 = Post(R.drawable.img_00, PREFIX + "post00")
    private val post01 = Post(R.drawable.img_01, PREFIX + "post01")
    private val post10 = Post(R.drawable.img_10, PREFIX + "post10")
    private val post11 = Post(R.drawable.img_11, PREFIX + "post11")

    internal var category0 = Category("title0")
    internal var postArray = arrayOf(post00, post01, post10, post11)

    internal var postList: MutableList<Post> = ArrayList()

    init {
      postList.add(post00)
      postList.add(post00)
      postList.add(post00)
      postList.add(post00)
      postList.add(post00)
      postList.add(post00)
    }

    companion object {
      private const val PREFIX = "这是一条长长的达到两行的标题文字"
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)

    adapter = MultiTypeAdapter()
    adapter.register(CategoryItemViewBinder())

    adapter.register(PostViewBinder())
    adapter.register(HorizontalPostsViewBinder())

    val recyclerView = findViewById<RecyclerView>(R.id.list)

    val layoutManager = GridLayoutManager(this, SPAN_COUNT)
    val spanSizeLookup = object : SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        val item = items[position]
        return if (item is PostList || item is Category) SPAN_COUNT else 1
      }
    }
    layoutManager.spanSizeLookup = spanSizeLookup
    recyclerView.layoutManager = layoutManager
    val space = resources.getDimensionPixelSize(R.dimen.normal_space)
    recyclerView.addItemDecoration(PostItemDecoration(space, spanSizeLookup))

    recyclerView.adapter = adapter

    val data = JsonData()
    items = ArrayList()
    for (i in 0..9) {
      /* You also could use Category as your CategoryItemContent directly */
      items.add(data.category0)
      items.add(data.postArray[0])
      items.add(data.postArray[1])
      items.add(data.postArray[2])
      items.add(data.postArray[3])
      items.add(data.postArray[0])
      items.add(data.postArray[1])
      items.add(PostList(data.postList))
    }
    adapter.items = items
    adapter.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_main, menu)
    return true
  }

  companion object {
    private const val SPAN_COUNT = 2
  }
}
