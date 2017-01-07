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

    @NonNull protected final List<?> items;
    @NonNull protected TypePool delegate;
    @Nullable protected LayoutInflater inflater;
    @Nullable private FlatTypeAdapter providedFlatTypeAdapter;


    public MultiTypeAdapter(@NonNull List<?> items) {
        this(items, new MultiTypePool(), null);
    }


    public MultiTypeAdapter(@NonNull List<?> items, int initialCapacity) {
        this(items, new MultiTypePool(initialCapacity), null);
    }


    public MultiTypeAdapter(@NonNull List<?> items, TypePool pool) {
        this(items, pool, null);
    }


    public MultiTypeAdapter(
        @NonNull List<?> items, @NonNull TypePool delegate,
        @Nullable FlatTypeAdapter providedFlatTypeAdapter) {
        this.items = items;
        this.delegate = delegate;
        this.providedFlatTypeAdapter = providedFlatTypeAdapter;
    }


    @SuppressWarnings("unchecked") @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        return indexOf(flattenClass(item));
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return getProviderByIndex(indexViewType).onCreateViewHolder(inflater, parent);
    }


    @SuppressWarnings("unchecked") @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object item = items.get(position);
        ItemViewProvider provider = getProviderByClass(flattenClass(item));
        provider.adapter = MultiTypeAdapter.this;
        provider.onBindViewHolder(holder, flattenItem(item));
    }


    @Override public int getItemCount() {
        return items.size();
    }


    @Override
    public void register(@NonNull Class<?> clazz, @NonNull ItemViewProvider provider) {
        delegate.register(clazz, provider);
    }


    public void registerAll(@NonNull final MultiTypePool pool) {
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


    @NonNull @SuppressWarnings("deprecation")
    Class flattenClass(@NonNull final Object item) {
        if (providedFlatTypeAdapter != null) {
            return providedFlatTypeAdapter.onFlattenClass(item);
        }
        return onFlattenClass(item);
    }


    @NonNull @SuppressWarnings("deprecation")
    Object flattenItem(@NonNull final Object item) {
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
