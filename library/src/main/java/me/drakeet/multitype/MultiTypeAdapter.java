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

package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> implements FlatTypeAdapter {

    private final List<? extends Item> items;
    private LayoutInflater inflater;


    public MultiTypeAdapter(@NonNull List<? extends Item> items) {
        this.items = items;
    }


    @NonNull @Override public Class onFlattenClass(@NonNull final Item item) {
        return item.getClass();
    }


    @NonNull @Override public Item onFlattenItem(@NonNull final Item item) {
        return item;
    }


    @Override public int getItemViewType(int position) {
        Item item = items.get(position);
        return MultiTypePool.getContents().indexOf(onFlattenClass(item));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return MultiTypePool.getProviderByIndex(indexViewType).onCreateViewHolder(inflater, parent);
    }


    @SuppressWarnings("unchecked") @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Item item = items.get(position);
        MultiTypePool.getProviderByIndex(type).onBindViewHolder(holder, onFlattenItem(item));
    }


    @Override public int getItemCount() {
        return items.size();
    }
}