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

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import com.drakeet.multitype.ViewDelegate
import com.drakeet.multitype.sample.dp

/**
 * @author Drakeet Xu
 */
class RichViewDelegate : ViewDelegate<RichItem, RichView>() {

  override fun onCreateView(context: Context): RichView {
    return RichView(context).apply { layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT) }
  }

  override fun onBindView(view: RichView, item: RichItem) {
    view.imageView.setImageResource(item.imageResId)
    view.textView.text = item.text
    // Or bind the data in the RichView by calling view.setRichItem(item)
  }
}
