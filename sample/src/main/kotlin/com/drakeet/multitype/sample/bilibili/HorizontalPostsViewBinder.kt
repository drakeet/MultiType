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

package com.drakeet.multitype.sample.bilibili

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
class HorizontalPostsViewBinder : ItemViewBinder<PostList, HorizontalPostsViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_horizontal_list, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: PostList) {
    holder.setPosts(item.posts)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter: PostsAdapter = PostsAdapter()
    private val recyclerView: RecyclerView = itemView.findViewById(R.id.post_list)

    init {
      val layoutManager = LinearLayoutManager(itemView.context)
      layoutManager.orientation = LinearLayoutManager.HORIZONTAL
      recyclerView.layoutManager = layoutManager
      LinearSnapHelper().attachToRecyclerView(recyclerView)
      recyclerView.adapter = adapter
    }

    fun setPosts(posts: List<Post>) {
      adapter.setPosts(posts)
      adapter.notifyDataSetChanged()
    }
  }
}
