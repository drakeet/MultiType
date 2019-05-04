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

package com.drakeet.multitype.sample.communication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.normal.TextItem

/**
 * @author Drakeet Xu
 */
class TextItemWithOutsizeDataViewBinder(var aValueFromOutside: String) : ItemViewBinder<TextItem, TextItemWithOutsizeDataViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_text, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: TextItem) {
    holder.setData(item)
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textView: TextView = itemView.findViewById(R.id.text)
    private lateinit var value: String

    init {
      textView.setOnClickListener { v ->
        Toast.makeText(
          v.context,
          "item's value: $value, aValueFromOutside: $aValueFromOutside", Toast.LENGTH_SHORT
        ).show()
      }
    }

    fun setData(textItem: TextItem) {
      textView.text = textItem.text
      this.value = textItem.text
    }
  }
}
