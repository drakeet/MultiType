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

package com.drakeet.multitype.sample.weibo.content

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.weibo.ContentHolder
import com.drakeet.multitype.sample.weibo.WeiboFrameBinder

/**
 * @author Drakeet Xu
 */
class SimpleImageViewBinder : WeiboFrameBinder<SimpleImage, SimpleImageViewBinder.ViewHolder>() {

  override fun onCreateContentViewHolder(inflater: LayoutInflater, parent: ViewGroup): ContentHolder {
    return ViewHolder(inflater.inflate(R.layout.item_weibo_simple_image, parent, false))
  }

  override fun onBindContentViewHolder(holder: ViewHolder, content: SimpleImage) {
    Log.d("weibo", "getAdapterPosition: " + holder.adapterPosition)
    Log.d("weibo", "getLayoutPosition: " + holder.layoutPosition)
    Log.d("weibo", "getOldPosition: " + holder.oldPosition)
    Log.d("weibo", "isRecyclable: " + holder.isRecyclable)
    holder.simpleImage.setImageResource(content.resId)
  }

  class ViewHolder(itemView: View) : ContentHolder(itemView) {
    val simpleImage: ImageView = itemView.findViewById(R.id.simple_image)
  }
}
