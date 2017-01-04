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

/***
 * @author drakeet
 */
public abstract class ItemViewProvider<T, V extends ViewHolder> {

    /* internal */ int position;
    /* internal */ RecyclerView.Adapter adapter;

    /* @formatter:off */

    @NonNull
    protected abstract V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull V holder, @NonNull T t);

    /* @formatter:on */


    /**
     * Get the adapter position of current item,
     * the internal position equals to {@link ViewHolder#getAdapterPosition()}.
     *
     * @return the adapter position
     * @since v2.3.0. If below v2.3.0, use {@link ViewHolder#getAdapterPosition()} instead.
     */
    protected final int getPosition() {
        return position;
    }


    /**
     * Get the RecyclerView.Adapter for sending notifications or getting item count, etc.
     *
     * @return The RecyclerView.Adapter this item is currently associated with.
     * @since v2.3.4
     */
    @NonNull protected final RecyclerView.Adapter getAdapter() {
        return adapter;
    }
}