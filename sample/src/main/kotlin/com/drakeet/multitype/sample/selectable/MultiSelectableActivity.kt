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

package com.drakeet.multitype.sample.selectable

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.common.Category
import com.drakeet.multitype.sample.common.CategoryItemViewBinder
import java.util.*

class MultiSelectableActivity : MenuBaseActivity() {

  var items: MutableList<Any> = ArrayList()
  var adapter = MultiTypeAdapter()
  private lateinit var fab: Button
  private lateinit var selectedSet: TreeSet<Int>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_multi_selectable)
    val recyclerView = findViewById<RecyclerView>(R.id.list)
    val layoutManager = GridLayoutManager(this, SPAN_COUNT)
    layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return if (items[position] is Category) SPAN_COUNT else 1
      }
    }

    selectedSet = TreeSet()

    recyclerView.layoutManager = layoutManager
    adapter.register(CategoryItemViewBinder())
    adapter.register(SquareViewBinder(selectedSet))

    loadData()
    recyclerView.adapter = adapter

    setupFAB()
  }

  private fun loadData() {
    val spacialCategory = Category("特别篇")
    items.add(spacialCategory)
    for (i in 0..6) {
      items.add(Square(i + 1))
    }
    val currentCategory = Category("本篇")
    items.add(currentCategory)
    for (i in 0..999) {
      items.add(Square(i + 1))
    }
    adapter.items = items
    adapter.notifyDataSetChanged()
  }

  private fun setupFAB() {
    fab = findViewById(R.id.fab)
    fab.setOnClickListener { v ->
      val content = StringBuilder()
      for (number in selectedSet) {
        content.append(number).append(" ")
      }
      Toast.makeText(v.context, "Selected items: $content", Toast.LENGTH_SHORT).show()
    }
  }

  companion object {
    private const val SPAN_COUNT = 5
  }
}
