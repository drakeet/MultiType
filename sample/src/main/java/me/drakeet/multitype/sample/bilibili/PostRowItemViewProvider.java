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
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.TypeItem;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class PostRowItemViewProvider
    extends ItemViewProvider<PostRowItemContent, PostRowItemViewProvider.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_post_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull ViewHolder holder,
        @NonNull PostRowItemContent content, @NonNull TypeItem typeItem) {
        holder.setData(content);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        View left, right;
        private ImageView leftCover, rightCover;
        private TextView leftTitle, rightTitle;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            left = itemView.findViewById(R.id.left);
            right = itemView.findViewById(R.id.right);

            leftCover = (ImageView) left.findViewById(R.id.cover);
            rightCover = (ImageView) right.findViewById(R.id.cover);
            leftTitle = (TextView) left.findViewById(R.id.title);
            rightTitle = (TextView) right.findViewById(R.id.title);
        }


        void setData(@NonNull final PostRowItemContent content) {
            leftCover.setImageResource(content.posts[0].coverResId);
            rightCover.setImageResource(content.posts[1].coverResId);
            leftTitle.setText(content.posts[0].title);
            rightTitle.setText(content.posts[1].title);
        }
    }
}
