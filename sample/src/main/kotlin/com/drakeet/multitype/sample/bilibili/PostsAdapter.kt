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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.sample.R

/**
 * @author Drakeet Xu
 */
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

  private var posts = emptyList<Post>()

  fun setPosts(posts: List<Post>) {
    this.posts = posts
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_post, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val post = posts[position]
    holder.cover.setImageResource(post.coverResId)
    holder.title.text = post.title
  }

  override fun getItemCount(): Int {
    return posts.size
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var cover: ImageView = itemView.findViewById(R.id.cover)
    var title: TextView = itemView.findViewById(R.id.title)

    init {
      itemView.setOnClickListener { v -> Toast.makeText(v.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show() }
    }
  }
}
