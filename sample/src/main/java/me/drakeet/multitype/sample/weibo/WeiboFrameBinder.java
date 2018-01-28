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

package me.drakeet.multitype.sample.weibo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * @author drakeet
 */
public abstract class WeiboFrameBinder
    <Content extends WeiboContent, SubViewHolder extends ContentHolder>
    extends ItemViewBinder<Weibo, WeiboFrameBinder.FrameHolder> {

  protected abstract ContentHolder onCreateContentViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

  protected abstract void onBindContentViewHolder(
      @NonNull SubViewHolder holder, @NonNull Content content);


  @Override
  protected @NonNull FrameHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_weibo_frame, parent, false);
    ContentHolder subViewHolder = onCreateContentViewHolder(inflater, parent);
    return new FrameHolder(root, subViewHolder, this);
  }


  @Override @SuppressWarnings("unchecked")
  protected void onBindViewHolder(@NonNull FrameHolder holder, @NonNull Weibo weibo) {
    holder.avatar.setImageResource(weibo.user.avatar);
    holder.username.setText(weibo.user.name);
    holder.createTime.setText(weibo.createTime);
    final WeiboContent weiboContent = weibo.content;
    onBindContentViewHolder((SubViewHolder) holder.subViewHolder, (Content) weiboContent);
  }


  static class FrameHolder extends RecyclerView.ViewHolder {

    private ImageView avatar;
    private TextView username;
    private FrameLayout container;
    private TextView createTime;
    private TextView close;
    private ContentHolder subViewHolder;


    FrameHolder(
        @NonNull View itemView,
        @NonNull final ContentHolder subViewHolder,
        @NonNull final WeiboFrameBinder binder) {

      super(itemView);
      avatar = (ImageView) findViewById(R.id.avatar);
      username = (TextView) findViewById(R.id.username);
      container = (FrameLayout) findViewById(R.id.container);
      createTime = (TextView) findViewById(R.id.create_time);
      close = (TextView) findViewById(R.id.close);
      container.addView(subViewHolder.itemView);
      this.subViewHolder = subViewHolder;
      this.subViewHolder.frameHolder = this;

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Toast.makeText(v.getContext(),
              "Position: " + getAdapterPosition(), LENGTH_SHORT).show();
        }
      });
      close.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          binder.getAdapter().getItems().remove(getAdapterPosition());
          binder.getAdapter().notifyItemRemoved(getAdapterPosition());
        }
      });
    }


    private View findViewById(int resId) {
      return itemView.findViewById(resId);
    }
  }
}
