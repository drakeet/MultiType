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

package me.drakeet.multitype.sample

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author drakeet
 */
class ContentViewActivity : MenuBaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView: RecyclerView = findViewById(R.id.list)
    val adapter = MultiTypeAdapter()
    adapter.register(InflatedTextViewBinder())
    adapter.items = listOf(InflatedText(), InflatedText())
    recyclerView.adapter = adapter
  }

  class InflatedText

  @ContentView(R.layout.item_inflated_something)
  class InflatedTextViewBinder : InflatedItemViewBinder<InflatedText, InflatedTextViewHolder>() {

    override fun onCreateViewHolder(itemView: View) = InflatedTextViewHolder(itemView)

    override fun onBindViewHolder(holder: InflatedTextViewHolder, item: InflatedText) {
      // ...
    }
  }

  class InflatedTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
