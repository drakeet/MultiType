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

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
class TextItemViewBinder : ItemViewBinder<TextItem, TextItemViewBinder.TextHolder>() {

  private var lastShownAnimationPosition: Int = 0

  class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val text: TextView = itemView.findViewById(R.id.text)
  }

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): TextHolder {
    return TextHolder(inflater.inflate(R.layout.item_text, parent, false))
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: TextHolder, item: TextItem) {
    holder.text.text = "hello: " + item.text
    // should show animation, ref: https://github.com/drakeet/MultiType/issues/149
    setAnimation(holder.itemView, holder.adapterPosition)
  }

  private fun setAnimation(viewToAnimate: View, position: Int) {
    if (position > lastShownAnimationPosition) {
      viewToAnimate.startAnimation(AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left))
      lastShownAnimationPosition = position
    }
  }

  override fun onViewDetachedFromWindow(holder: TextHolder) {
    holder.itemView.clearAnimation()
  }
}
