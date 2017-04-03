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

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = "MultiTypeAdapter";

    @Nullable private List<?> items;
    @NonNull private TypePool typePool;
    @Nullable protected LayoutInflater inflater;


    public MultiTypeAdapter() {
        this(null);
    }


    public MultiTypeAdapter(@Nullable List<?> items) {
        this(items, new MultiTypePool());
    }


    public MultiTypeAdapter(@Nullable List<?> items, int initialCapacity) {
        this(items, new MultiTypePool(initialCapacity));
    }


    public MultiTypeAdapter(@Nullable List<?> items, @NonNull TypePool pool) {
        this.items = items;
        this.typePool = pool;
    }


    public final <T> void register(
        @NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        checkAndRemoveAllTypesIfNeed(clazz);
        typePool.register(clazz, binder, new DefaultLinker<T>());
    }


    @CheckResult
    public final <T> OneToManyFlow<T> register(@NonNull Class<T> clazz) {
        checkAndRemoveAllTypesIfNeed(clazz);
        return new OneToManyBuilder<>(this, clazz);
    }


    public final void registerAll(@NonNull final TypePool pool) {
        for (int i = 0; i < pool.getContents().size(); i++) {
            registerFromTypePoolContent(
                pool.getContents().get(i),
                pool.getItemViewBinders().get(i),
                pool.getLinkers().get(i)
            );
        }
    }


    /** A safe register method base on the TypePool's safety. */
    @SuppressWarnings("unchecked")
    private void registerFromTypePoolContent(
        @NonNull Class clazz, @NonNull ItemViewBinder itemViewBinder, @NonNull Linker linker) {
        checkAndRemoveAllTypesIfNeed(clazz);
        typePool.register(clazz, itemViewBinder, linker);
    }


    /**
     * Update the items atomically and safely.
     * It is recommended to use this method to update the data.
     * <p>e.g. {@code adapter.setItems(new Items(changedItems));}</p>
     *
     * <p>Note: If you want to refresh the list views, you should
     * call {@link RecyclerView.Adapter#notifyDataSetChanged()} by yourself.</p>
     *
     * @param items The <b>new</b> items list.
     * @since v2.4.1
     */
    public void setItems(@Nullable List<?> items) {
        this.items = items;
    }


    /**
     * Set the TypePool to hold the types and view binders.
     *
     * @param typePool The TypePool implementation
     */
    public void setTypePool(@NonNull TypePool typePool) {
        this.typePool = typePool;
    }


    @Override
    public final int getItemViewType(int position) {
        assert items != null;
        Object item = items.get(position);
        return indexInTypesOf(item);
    }


    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemViewBinder<?, ?> binder = typePool.getItemViewBinders().get(indexViewType);
        binder.adapter = this;
        assert inflater != null;
        return binder.onCreateViewHolder(inflater, parent);
    }


    /**
     * This method is deprecated and unused. You should not call this method.
     * <p>
     * If you need to call the binding, use {@link RecyclerView.Adapter#onBindViewHolder(ViewHolder,
     * int, List)} instead.
     * <p/>
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @throws IllegalAccessError By default.
     * @deprecated Call {@link RecyclerView.Adapter#onBindViewHolder(ViewHolder, int, List)}
     * instead.
     */
    @Override @Deprecated
    public final void onBindViewHolder(ViewHolder holder, int position) {
        throw new IllegalAccessError("You should not call this method. " +
            "Call RecyclerView.Adapter#onBindViewHolder(holder, position, payloads) instead.");
    }


    @Override @SuppressWarnings("unchecked")
    public final void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        assert items != null;
        Object item = items.get(position);
        ItemViewBinder binder = typePool.getItemViewBinders().get(holder.getItemViewType());
        binder.onBindViewHolder(holder, item, payloads);
    }


    @Override
    public final int getItemCount() {
        return items == null ? 0 : items.size();
    }


    int indexInTypesOf(@NonNull Object item) throws BinderNotFoundException {
        int index = typePool.firstIndexOf(item.getClass());
        if (index != -1) {
            @SuppressWarnings("unchecked")
            Linker<Object> linker = (Linker<Object>) typePool.getLinkers().get(index);
            return index + linker.index(item);
        }
        throw new BinderNotFoundException(item.getClass());
    }


    @Nullable
    public List<?> getItems() { return items; }


    @NonNull
    public TypePool getTypePool() {
        return typePool;
    }


    private void checkAndRemoveAllTypesIfNeed(@NonNull Class<?> clazz) {
        if (!typePool.getContents().contains(clazz)) {
            return;
        }
        Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
            "It will override the original binder(s).");
        for (; ; ) {
            int index = typePool.getContents().indexOf(clazz);
            if (index != -1) {
                typePool.getContents().remove(index);
                typePool.getItemViewBinders().remove(index);
                typePool.getLinkers().remove(index);
            } else {
                break;
            }
        }
    }


    <T> void registerWithoutChecking(
        @NonNull Class<? extends T> clazz,
        @NonNull ItemViewBinder<T, ?> binder,
        @NonNull Linker<T> linker) {
        typePool.register(clazz, binder, linker);
    }
}
