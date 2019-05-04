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

package com.drakeet.multitype.sample.payload

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import java.util.*

class TestPayloadActivity : MenuBaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView = findViewById<RecyclerView>(R.id.list)
    recyclerView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    val adapter = MultiTypeAdapter()
    recyclerView.adapter = adapter

    adapter.register(HeavyItemViewBinder())

    val items = ArrayList<Any>()
    for (i in 0..29) {
      items.add(HeavyItem("1000$i"))
    }
    adapter.items = items
    adapter.notifyDataSetChanged()

    Toast.makeText(this, "Try to click or long click items", Toast.LENGTH_SHORT).show()
  }
}
