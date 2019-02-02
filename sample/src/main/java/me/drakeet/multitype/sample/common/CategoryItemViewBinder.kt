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

package me.drakeet.multitype.sample.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.sample.R

/**
 * @author drakeet
 */
class CategoryItemViewBinder : ItemViewBinder<Category, CategoryItemViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_category, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, category: Category) {
    holder.title.text = category.title
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
  }
}
