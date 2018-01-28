/*
 * Copyright 2016 drakeet. https://github.com/drakeet
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

package me.drakeet.multitype.sample.bilibili;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

  private List<Post> posts = Collections.emptyList();


  public void setPosts(@NonNull List<Post> posts) {
    this.posts = posts;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_horizontal_post, parent, false);
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Post post = posts.get(position);
    holder.cover.setImageResource(post.coverResId);
    holder.title.setText(post.title);
  }


  @Override
  public int getItemCount() {
    return posts.size();
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {

    @NonNull ImageView cover;
    @NonNull TextView title;


    ViewHolder(View itemView) {
      super(itemView);
      cover = itemView.findViewById(R.id.cover);
      title = itemView.findViewById(R.id.title);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Toast.makeText(v.getContext(), String.valueOf(getAdapterPosition()),
              Toast.LENGTH_SHORT).show();
        }
      });
    }
  }
}
