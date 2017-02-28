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
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder>
    implements FlatTypeAdapter, TypePool {

    @Nullable protected List<?> items;
    @NonNull protected TypePool delegate;
    @Nullable protected LayoutInflater inflater;
    @Nullable private FlatTypeAdapter providedFlatTypeAdapter;


    public MultiTypeAdapter() {
        this(null);
    }


    public MultiTypeAdapter(@Nullable List<?> items) {
        this(items, new MultiTypePool(), null);
    }


    public MultiTypeAdapter(@Nullable List<?> items, int initialCapacity) {
        this(items, new MultiTypePool(initialCapacity), /* providedFlatTypeAdapter: */ null);
    }


    public MultiTypeAdapter(@Nullable List<?> items, @NonNull TypePool pool) {
        this(items, pool, /* providedFlatTypeAdapter: */ null);
    }


    public MultiTypeAdapter(
        @Nullable List<?> items, @NonNull TypePool delegate,
        @Nullable FlatTypeAdapter providedFlatTypeAdapter) {
        this.items = items;
        this.delegate = delegate;
        this.providedFlatTypeAdapter = providedFlatTypeAdapter;
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


    @SuppressWarnings("unchecked")
    @Override public int getItemViewType(int position) {
        assert items != null;
        Object item = items.get(position);
        return indexOf(flattenClass(item));
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemViewProvider provider = getProviderByIndex(indexViewType);
        provider.adapter = MultiTypeAdapter.this;
        return provider.onCreateViewHolder(inflater, parent);
    }


    @SuppressWarnings("unchecked")
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        assert items != null;
        Object item = items.get(position);
        ItemViewProvider provider = getProviderByClass(flattenClass(item));
        provider.onBindViewHolder(holder, flattenItem(item));
    }


    @Override public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    @Override public void register(@NonNull Class<?> clazz, @NonNull ItemViewProvider provider) {
        delegate.register(clazz, provider);
    }


    public void registerAll(@NonNull final TypePool pool) {
        for (int i = 0; i < pool.getContents().size(); i++) {
            delegate.register(pool.getContents().get(i), pool.getProviders().get(i));
        }
    }


    public void applyGlobalMultiTypePool() {
        for (int i = 0; i < GlobalMultiTypePool.getContents().size(); i++) {
            final Class<?> clazz = GlobalMultiTypePool.getContents().get(i);
            final ItemViewProvider provider = GlobalMultiTypePool.getProviders().get(i);
            if (!this.getContents().contains(clazz)) {
                this.register(clazz, provider);
            }
        }
    }


    @Override public int indexOf(@NonNull Class<?> clazz)
        throws ProviderNotFoundException {
        int index = delegate.indexOf(clazz);
        if (index >= 0) {
            return index;
        }
        throw new ProviderNotFoundException(clazz);
    }


    /**
     * Set the FlatTypeAdapter to instead of the default inner FlatTypeAdapter of
     * MultiTypeAdapter.
     * <p>Note: You could use {@link FlatTypeClassAdapter} and {@link FlatTypeItemAdapter}
     * to create a special FlatTypeAdapter conveniently.</p>
     *
     * @param flatTypeAdapter the FlatTypeAdapter
     * @since v2.3.2
     */
    public void setFlatTypeAdapter(@NonNull FlatTypeAdapter flatTypeAdapter) {
        this.providedFlatTypeAdapter = flatTypeAdapter;
    }


    @SuppressWarnings("deprecation")
    @NonNull Class flattenClass(@NonNull final Object item) {
        if (providedFlatTypeAdapter != null) {
            return providedFlatTypeAdapter.onFlattenClass(item);
        }
        return onFlattenClass(item);
    }


    @SuppressWarnings("deprecation")
    @NonNull Object flattenItem(@NonNull final Object item) {
        if (providedFlatTypeAdapter != null) {
            return providedFlatTypeAdapter.onFlattenItem(item);
        }
        return onFlattenItem(item);
    }


    /**
     * @deprecated Use {@link MultiTypeAdapter#setFlatTypeAdapter(FlatTypeAdapter)} instead.
     * The method may be removed next time.
     */
    @NonNull @Override public Class onFlattenClass(@NonNull final Object item) {
        return item.getClass();
    }


    /**
     * @deprecated Use {@link MultiTypeAdapter#setFlatTypeAdapter(FlatTypeAdapter)} instead.
     * The method may be removed next time.
     */
    @NonNull @Override public Object onFlattenItem(@NonNull final Object item) {
        return item;
    }


    /**
     * Set the TypePool to hold the types and view providers.
     *
     * @param typePool The TypePool implementation
     */
    public void setTypePool(@NonNull TypePool typePool) {
        this.delegate = typePool;
    }


    @NonNull @Override public ArrayList<Class<?>> getContents() {
        return delegate.getContents();
    }


    @NonNull @Override public ArrayList<ItemViewProvider> getProviders() {
        return delegate.getProviders();
    }


    @NonNull @Override public ItemViewProvider getProviderByIndex(int index) {
        return delegate.getProviderByIndex(index);
    }


    @NonNull @Override
    public <T extends ItemViewProvider> T getProviderByClass(@NonNull Class<?> clazz) {
        return delegate.getProviderByClass(clazz);
    }
}
