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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.weibo.ContentHolder
import com.drakeet.multitype.sample.weibo.WeiboFrameBinder

/**
 * @author Drakeet Xu
 */
class SimpleTextViewBinder : WeiboFrameBinder<SimpleText, SimpleTextViewBinder.ViewHolder>() {

  override fun onCreateContentViewHolder(inflater: LayoutInflater, parent: ViewGroup): ContentHolder {
    return ViewHolder(inflater.inflate(R.layout.item_weibo_simple_text, parent, false))
  }

  override fun onBindContentViewHolder(holder: ViewHolder, content: SimpleText) {
    holder.simpleText.text = content.text
  }

  class ViewHolder(itemView: View) : ContentHolder(itemView) {
    val simpleText: TextView = itemView.findViewById(R.id.simple_text)
  }
}
