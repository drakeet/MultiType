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

package com.drakeet.multitype.sample.normal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
class ImageItemViewBinder : ItemViewBinder<ImageItem, ImageItemViewBinder.ImageHolder>() {

  inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)

  }

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ImageHolder {
    return ImageHolder(inflater.inflate(R.layout.item_image, parent, false))
  }

  override fun onBindViewHolder(holder: ImageHolder, item: ImageItem) {
    holder.image.setImageResource(item.resId)
  }
}
