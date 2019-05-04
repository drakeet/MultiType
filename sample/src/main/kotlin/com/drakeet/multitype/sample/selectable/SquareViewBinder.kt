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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
class SquareViewBinder(val selectedSet: MutableSet<Int>) : ItemViewBinder<Square, SquareViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_square, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Square) {
    holder.square = item
    holder.squareView.text = item.number.toString()
    holder.squareView.isSelected = item.isSelected
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val squareView: TextView = itemView.findViewById(R.id.square)
    lateinit var square: Square

    init {
      itemView.setOnClickListener {
        square.apply {
          squareView.isSelected = !isSelected
          this.isSelected = !isSelected
        }
        if (square.isSelected) {
          selectedSet.add(square.number)
        } else {
          selectedSet.remove(square.number)
        }
      }
    }
  }
}
