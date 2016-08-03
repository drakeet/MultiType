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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * @author drakeet
 */
public class TypeItemsAdapter extends RecyclerView.Adapter<TypeItemsAdapter.ViewHolder> {

    private final List<TypeItem> typeItems;


    public TypeItemsAdapter(@NonNull List<TypeItem> typeItems) {this.typeItems = typeItems;}


    @Override public int getItemViewType(int position) {
        ItemContent content = typeItems.get(position).content;
        int index = ItemTypePool.getContents().indexOf(content.getClass());
        return index;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = ItemTypePool.getProviderByIndex(indexViewType).onCreateView(inflater, parent);
        ViewHolder holder = new ViewHolder(root);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        TypeItem typeItem = typeItems.get(position);
        ItemTypePool.getProviderByIndex(type).onBindView(holder.itemView, typeItem);
    }


    @Override public int getItemCount() {
        return typeItems.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}