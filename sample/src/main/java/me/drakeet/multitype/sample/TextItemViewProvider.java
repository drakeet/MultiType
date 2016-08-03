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

package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.drakeet.multitype.ItemType;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author drakeet
 */
public class TextItemViewProvider extends ItemViewProvider<TextItemContent> {

    private static class ViewHolder extends ItemViewProvider.ViewHolder {
        @NonNull final TextView text;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }


    @NonNull @Override protected View onCreateView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_text, parent, false);
        ViewHolder holder = new ViewHolder(root);
        root.setTag(holder);
        return root;
    }


    @Override
    protected void onBindView(
        @NonNull View view, @NonNull TextItemContent content, @NonNull ItemType itemType) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText("hello: " + content.text);
    }

}
