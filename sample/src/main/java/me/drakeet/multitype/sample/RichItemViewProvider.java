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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.TypeItem;

/**
 * @author drakeet
 */
public class RichItemViewProvider
    extends ItemViewProvider<RichItemContent> {

    @Override
    protected int getLayoutResId() {
        return R.layout.item_rich;
    }

    protected void onBindView(MultiTypeAdapter.ViewHolder holder, 
        @NonNull View view, @NonNull RichItemContent richContent, @NonNull TypeItem typeItem) {
        ((TextView) holder.getView(R.id.text)).setText(richContent.text);
        ((ImageView) holder.getView(R.id.image)).setImageResource(richContent.imageResId);
    }
}
