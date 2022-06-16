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

package com.drakeet.multitype.sample.concat

import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.MenuBaseActivity
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.normal.*

/**
 * [ConcatAdapter] Implements
 * @author Lowae
 */
class ConcatActivity : MenuBaseActivity() {

  private val textAdapter = MultiTypeAdapter()
  private val imageAdapter = MultiTypeAdapter()
  private val otherAdapter = MultiTypeAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView = findViewById<RecyclerView>(R.id.list)

    textAdapter.register(TextItemViewBinder())
    imageAdapter.register(ImageItemViewBinder())
    otherAdapter.register(RichViewDelegate())

    textAdapter.items = (0 until 10).map {
      TextItem(it.toString())
    }
    imageAdapter.items = (0 until 10).map {
      ImageItem(R.mipmap.ic_launcher)
    }
    otherAdapter.items = (0 until 10).map {
      RichItem("小艾大人赛高 $it", R.drawable.img_11)
    }
    recyclerView.adapter = ConcatAdapter(textAdapter, imageAdapter, otherAdapter)
  }
}
