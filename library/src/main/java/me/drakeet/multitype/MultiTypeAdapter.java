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
import java.util.ArrayList;
import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder>
    implements FlatTypeAdapter, TypePool {

    protected final List<? extends Item> items;
    protected LayoutInflater inflater;
    protected TypePool delegate;


    public MultiTypeAdapter(@NonNull List<? extends Item> items) {
        this.delegate = new MultiTypePool();
        this.items = items;
    }


    public MultiTypeAdapter(@NonNull List<? extends Item> items, TypePool pool) {
        this.delegate = pool;
        this.items = items;
    }


    @NonNull @Override public Class onFlattenClass(@NonNull final Item item) {
        return item.getClass();
    }


    @NonNull @Override public Item onFlattenItem(@NonNull final Item item) {
        return item;
    }


    @SuppressWarnings("unchecked") @Override
    public int getItemViewType(int position) {
        Item item = items.get(position);
        return indexOf(onFlattenClass(item));
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return getProviderByIndex(indexViewType).onCreateViewHolder(inflater, parent);
    }


    @SuppressWarnings("unchecked") @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        getProviderByClass(onFlattenClass(item)).onBindViewHolder(holder, onFlattenItem(item));
    }


    @Override public int getItemCount() {
        return items.size();
    }


    public void applyGlobalMultiTypePool() {
        for (int i = 0; i < GlobalMultiTypePool.getContents().size(); i++) {
            final Class<? extends Item> clazz = GlobalMultiTypePool.getContents().get(i);
            final ItemViewProvider provider = GlobalMultiTypePool.getProviders().get(i);
            if (!this.getContents().contains(clazz)) {
                this.register(clazz, provider);
            }
        }
    }


    public void registerAll(@NonNull final MultiTypePool pool) {
        for (int i = 0; i < pool.getContents().size(); i++) {
            delegate.register(pool.getContents().get(i), pool.getProviders().get(i));
        }
    }


    @Override
    public void register(@NonNull Class<? extends Item> clazz, @NonNull ItemViewProvider provider) {
        delegate.register(clazz, provider);
    }


    @Override public int indexOf(@NonNull Class<? extends Item> clazz)
        throws ProviderNotFoundException {
        int index = delegate.indexOf(clazz);
        if (index >= 0) {
            return index;
        }
        throw new ProviderNotFoundException(clazz);
    }


    @NonNull @Override public ArrayList<Class<? extends Item>> getContents() {
        return delegate.getContents();
    }


    @NonNull @Override public ArrayList<ItemViewProvider> getProviders() {
        return delegate.getProviders();
    }


    @NonNull @Override public ItemViewProvider getProviderByIndex(int index) {
        return delegate.getProviderByIndex(index);
    }


    @NonNull @Override
    public <T extends ItemViewProvider> T getProviderByClass(
        @NonNull Class<? extends Item> clazz) {
        return delegate.getProviderByClass(clazz);
    }
}
