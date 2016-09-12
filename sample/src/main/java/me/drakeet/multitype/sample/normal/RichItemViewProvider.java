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

package me.drakeet.multitype.sample.normal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.TypeItem;
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class RichItemViewProvider
    extends ItemViewProvider<RichItemContent, RichItemViewProvider.RichHolder> {

    static class RichHolder extends RecyclerView.ViewHolder {
        @NonNull final TextView text;
        @NonNull final ImageView image;


        RichHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


    @NonNull @Override
    protected RichHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_rich, parent, false);
        RichHolder holder = new RichHolder(root);
        return holder;
    }


    @Override
    protected void onBindViewHolder(
        @NonNull RichHolder holder,
        @NonNull RichItemContent richContent, @NonNull TypeItem typeItem) {
        holder.text.setText(richContent.text);
        holder.image.setImageResource(richContent.imageResId);
    }
}
