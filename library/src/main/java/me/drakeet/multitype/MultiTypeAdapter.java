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
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.ViewHolder> {

    private final List<? extends TypeItem> typeItems;
    private LayoutInflater inflater;


    public MultiTypeAdapter(@NonNull List<? extends TypeItem> typeItems) {
        this.typeItems = typeItems;
    }


    @Override public int getItemViewType(int position) {
        Log.e("volley", "getItemViewType");
        ItemContent content = typeItems.get(position).content;
        return MultiTypePool.getContents().indexOf(content.getClass());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View view = MultiTypePool.getProviderByIndex(indexViewType).onCreateViewHolder(inflater, parent);
        
        return new ViewHolder(view);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("volley", "onBindViewHolder");
        int type = getItemViewType(position);
        TypeItem typeItem = typeItems.get(position);
        MultiTypePool.getProviderByIndex(type).onBindView(holder, holder.itemView, typeItem);
    }

    @Override public int getItemCount() {
        Log.e("volley", "getItemCount");
        return typeItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> views;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.views = new SparseArray<>();
        }

        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }
    }
}