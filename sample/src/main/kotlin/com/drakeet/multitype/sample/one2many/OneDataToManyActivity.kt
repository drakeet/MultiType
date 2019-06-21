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

package com.drakeet.multitype.sample.one2many

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import java.util.*

/**
 * @author Drakeet Xu
 */
class OneDataToManyActivity : MenuBaseActivity() {

  @VisibleForTesting
  lateinit var recyclerView: RecyclerView
  @VisibleForTesting
  lateinit var adapter: MultiTypeAdapter

  private val dataFromService: List<Data>
    @VisibleForTesting
    get() {
      val list = ArrayList<Data>()
      var i = 0
      while (i < 30) {
        list.add(Data("title: $i", Data.TYPE_1))
        list.add(Data("title: ${ i + 1 }", Data.TYPE_2))
        i += 2
      }
      return list
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    recyclerView = findViewById(R.id.list)
    adapter = MultiTypeAdapter()

    adapter.register(Data::class).to(
      DataType1ViewBinder(),
      DataType2ViewBinder()
    ).withKotlinClassLinker { _, data ->
      when (data.type) {
        Data.TYPE_2 -> DataType2ViewBinder::class
        else -> DataType1ViewBinder::class
      }
    }

    adapter.items = dataFromService
    adapter.notifyDataSetChanged()
    recyclerView.adapter = adapter
  }
}
