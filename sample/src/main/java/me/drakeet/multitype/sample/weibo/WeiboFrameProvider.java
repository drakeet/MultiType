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
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public abstract class WeiboFrameProvider
    <Content extends WeiboContent, SubViewHolder extends ContentHolder>
    extends ItemViewProvider<Weibo, WeiboFrameProvider.FrameHolder> {

    protected abstract ContentHolder onCreateContentViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindContentViewHolder(
        @NonNull SubViewHolder holder, @NonNull Content content);


    @NonNull @Override
    protected FrameHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_weibo_frame, parent, false);
        ContentHolder subViewHolder = onCreateContentViewHolder(inflater, parent);
        return new FrameHolder(root, subViewHolder);
    }


    @SuppressWarnings("unchecked")
    @Override protected void onBindViewHolder(
        @NonNull FrameHolder holder, @NonNull Weibo weibo) {
        final WeiboContent weiboContent = weibo.content;
        holder.avatar.setImageResource(weibo.user.avatar);
        holder.username.setText(weibo.user.name);
        holder.createTime.setText(weibo.createTime);
        onBindContentViewHolder((SubViewHolder) holder.subViewHolder, (Content) weibo.content);
    }


    protected static class FrameHolder extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView username;
        private FrameLayout container;
        private TextView createTime;
        private ContentHolder subViewHolder;


        protected FrameHolder(View itemView, ContentHolder subViewHolder) {
            super(itemView);
            avatar = (ImageView) findViewById(R.id.avatar);
            username = (TextView) findViewById(R.id.username);
            container = (FrameLayout) findViewById(R.id.container);
            createTime = (TextView) findViewById(R.id.create_time);
            container.addView(subViewHolder.itemView);
            this.subViewHolder = subViewHolder;
        }


        private View findViewById(int resId) {
            return itemView.findViewById(resId);
        }
    }
}