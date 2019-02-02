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

package me.drakeet.multitype.sample.communication

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.sample.MenuBaseActivity
import me.drakeet.multitype.sample.R
import me.drakeet.multitype.sample.normal.TextItem
import java.util.*

/**
 * @author drakeet
 */
class CommunicateWithBinderActivity : MenuBaseActivity() {

  private val aFieldValue = "aFieldValue of SimpleActivity"
  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView = findViewById<RecyclerView>(R.id.list)

    val items = ArrayList<Any>()
    adapter.register(TextItemWithOutsizeDataViewBinder(aFieldValue))
    recyclerView.adapter = adapter

    for (i in 0..19) {
      items.add(TextItem(i.toString()))
    }
    adapter.items = items
    adapter.notifyDataSetChanged()
  }
}
