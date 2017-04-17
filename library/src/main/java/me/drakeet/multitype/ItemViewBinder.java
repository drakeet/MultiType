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
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

/***
 * @author drakeet
 */
public abstract class ItemViewBinder<T, VH extends ViewHolder> {

    /* internal */ MultiTypeAdapter adapter;


    @NonNull
    protected abstract VH onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    /**
     * Called by MultiTypeAdapter to display the data with its view holder. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the given item.
     * <p>
     * If you need the position of an item later on (e.g. in a click listener), use
     * {@code ViewHolder#getAdapterPosition()} which will have the updated adapter position.
     *
     * Override {@code onBindViewHolder(ViewHolder, Object, List)} instead if your ItemViewBinder
     * can handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * given item in the items data set.
     * @param item The item within the MultiTypeAdapter's items data set.
     */
    protected abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item);


    /**
     * Called by MultiTypeAdapter to display the data with its view holder. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the given item.
     * <p>
     * If you need the position of an item later on (e.g. in a click listener), use
     * {@link ViewHolder#getAdapterPosition()} which will have the updated adapter position.
     * <p>
     * Partial bind vs full bind:
     * <p>
     * The payloads parameter is a merge list from {@link MultiTypeAdapter#notifyItemChanged(int,
     * Object)} {@link MultiTypeAdapter#notifyItemRangeChanged(int, int, Object)}.
     * If the payloads list is not empty, the ViewHolder is currently bound to old data and
     * ItemViewBinder may run an efficient partial update using the payload info.
     * If the payload is empty, ItemViewBinder must run a full bind.
     * ItemViewBinder should not assume that the payload passed in notify methods will be
     * received by onBindViewHolder().  For example when the view is not attached to the screen,
     * the payload in notifyItemChange() will be simply dropped.
     *
     * This implementation calls the {@code onBindViewHolder(ViewHolder, Object)} by default.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * given item in the items data set.
     * @param item The item within the MultiTypeAdapter's items data set.
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full
     * update.
     * @since v2.5.0
     */
    protected void onBindViewHolder(
        @NonNull VH holder, @NonNull T item, @NonNull List<Object> payloads) {
        onBindViewHolder(holder, item);
    }


    /**
     * Get the adapter position of current item,
     * the internal position equals to {@link ViewHolder#getAdapterPosition()}.
     * <p><b>NOTE</b>: Below v2.3.5 we may provide getPosition() method to get the position,
     * It exists BUG, and sometimes can not get the correct position,
     * it is recommended to immediately stop using it and use the new
     * {@code getPosition(ViewHolder)} instead.</p>
     *
     * @param holder The ViewHolder to call holder.getAdapterPosition().
     * @return The adapter position.
     * @since v2.3.5. If below v2.3.5, use {@link ViewHolder#getAdapterPosition()} instead.
     */
    protected final int getPosition(@NonNull final ViewHolder holder) {
        return holder.getAdapterPosition();
    }


    /**
     * Get the {@link MultiTypeAdapter} for sending notifications or getting item count, etc.
     * <p>
     * Note that if you need to change the item's parent items, you could call this method
     * to get the {@link MultiTypeAdapter}, and call {@link MultiTypeAdapter#getItems()} to get
     * a list that can not be added any new item, so that you should copy the items and just use
     * {@link MultiTypeAdapter#setItems(List)} to replace the original items list and update the
     * views.
     * </p>
     *
     * @return The MultiTypeAdapter this item is currently associated with.
     * @since v2.3.4
     */
    @NonNull
    protected final MultiTypeAdapter getAdapter() {
        return adapter;
    }
}