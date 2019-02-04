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

package me.drakeet.multitype.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.drakeet.multitype.ItemViewBinder


/**
 * A sample for @[ContentView] support
 *
 * @author drakeet
 */
abstract class InflatedItemViewBinder<T, VH : ViewHolder> : ItemViewBinder<T, VH>() {

  final override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
    val annotation = javaClass.getAnnotation(ContentView::class.java)
    if (annotation != null) {
      val layoutId = annotation.value
      if (layoutId != 0) {
        return this.onCreateViewHolder(inflater.inflate(layoutId, parent, false))
      }
    }
    throw IllegalStateException("You must annotate a @ContentView for this ${javaClass.name}")
  }

  abstract fun onCreateViewHolder(itemView: View): VH
}
