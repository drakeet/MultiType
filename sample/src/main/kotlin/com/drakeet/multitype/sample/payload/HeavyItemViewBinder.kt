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

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
internal class HeavyItemViewBinder : ItemViewBinder<HeavyItem, HeavyItemViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_heavy, parent, false))
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: ViewHolder, item: HeavyItem) {
    holder.firstText.text = item.text
    holder.endText.text = "currentTimeMillis: " + System.currentTimeMillis()
    holder.item = item
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: ViewHolder, item: HeavyItem, payloads: List<Any>) {
    if (payloads.isEmpty()) {
      super.onBindViewHolder(holder, item, payloads)
    } else {
      holder.firstText.text = "Just update the first text: " + payloads[0]
    }
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

    var firstText: TextView = itemView.findViewById(R.id.first_text)
    var endText: TextView = itemView.findViewById(R.id.end_text)
    var item: HeavyItem? = null

    init {
      itemView.setOnClickListener(this)
      itemView.setOnLongClickListener(this)
    }

    override fun onClick(v: View) {
      Toast.makeText(v.context, "Update with a payload", Toast.LENGTH_SHORT).show()
      adapter.notifyItemChanged(adapterPosition, "la la la (payload)")
    }

    override fun onLongClick(v: View): Boolean {
      Toast.makeText(v.context, "Full update", Toast.LENGTH_SHORT).show()
      item!!.text = "full full full"
      adapter.notifyItemChanged(adapterPosition)
      return true
    }
  }
}
