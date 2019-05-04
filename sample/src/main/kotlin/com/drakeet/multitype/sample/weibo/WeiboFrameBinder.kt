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

package com.drakeet.multitype.sample.weibo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
abstract class WeiboFrameBinder<Content : WeiboContent, SubViewHolder : ContentHolder> : ItemViewBinder<Weibo, WeiboFrameBinder.FrameHolder>() {

  protected abstract fun onCreateContentViewHolder(inflater: LayoutInflater, parent: ViewGroup): ContentHolder

  protected abstract fun onBindContentViewHolder(holder: SubViewHolder, content: Content)

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): FrameHolder {
    val root = inflater.inflate(R.layout.item_weibo_frame, parent, false)
    val subViewHolder = onCreateContentViewHolder(inflater, parent)
    return FrameHolder(root, subViewHolder, this)
  }

  override fun onBindViewHolder(holder: FrameHolder, item: Weibo) {
    holder.avatar.setImageResource(item.user.avatar)
    holder.username.text = item.user.name
    holder.createTime.text = item.createTime
    val weiboContent = item.content
    @Suppress("UNCHECKED_CAST")
    onBindContentViewHolder(holder.subViewHolder as SubViewHolder, weiboContent as Content)
  }

  class FrameHolder(itemView: View, val subViewHolder: ContentHolder, binder: WeiboFrameBinder<*, *>) : RecyclerView.ViewHolder(itemView) {

    val avatar: ImageView = itemView.findViewById(R.id.avatar)
    val username: TextView = itemView.findViewById(R.id.username)
    val createTime: TextView = itemView.findViewById(R.id.create_time)
    private val container: FrameLayout = itemView.findViewById(R.id.container)
    private val close: TextView = itemView.findViewById(R.id.close)

    init {
      container.addView(subViewHolder.itemView)
      this.subViewHolder.parent = this

      itemView.setOnClickListener { v -> Toast.makeText(v.context, "Position: $adapterPosition", LENGTH_SHORT).show() }
      close.setOnClickListener {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
          binder.adapter.items.toMutableList()
            .apply {
              removeAt(position)
              binder.adapter.items = this
            }
          binder.adapter.notifyItemRemoved(position)
        }
      }
    }
  }
}
