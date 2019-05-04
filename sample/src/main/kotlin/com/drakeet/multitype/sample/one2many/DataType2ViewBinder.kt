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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * Note: Data - DataType2ViewBinder
 *
 * @author Drakeet Xu
 */
class DataType2ViewBinder : ItemViewBinder<Data, DataType2ViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_data_type2, parent, false))
  }

  override fun onBindViewHolder(holder: DataType2ViewBinder.ViewHolder, item: Data) {
    holder.setTitle(item.title)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var titleView: TextView = itemView.findViewById(android.R.id.title)

    fun setTitle(title: String) {
      titleView.text = title
    }
  }
}
