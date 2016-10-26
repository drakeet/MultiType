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
import android.util.Log;
import java.util.ArrayList;

/**
 * @author drakeet
 */
public final class MultiTypePool implements TypePool {

    private final String TAG = MultiTypePool.class.getSimpleName();
    private ArrayList<Class<? extends Item>> contents;
    private ArrayList<ItemViewProvider> providers;


    public MultiTypePool() {
        this.contents = new ArrayList<>();
        this.providers = new ArrayList<>();
    }


    public void register(
        @NonNull Class<? extends Item> clazz,
        @NonNull ItemViewProvider provider) {
        if (!contents.contains(clazz)) {
            contents.add(clazz);
            providers.add(provider);
        } else {
            int index = contents.indexOf(clazz);
            providers.set(index, provider);
            Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
                "It will override the original provider.");
        }
    }


    @Override public int indexOf(@NonNull final Class<? extends Item> clazz) {
        int index = contents.indexOf(clazz);
        if (index >= 0) {
            return index;
        }
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        return index;
    }


    @NonNull @Override public ArrayList<Class<? extends Item>> getContents() {
        return contents;
    }


    @NonNull @Override public ArrayList<ItemViewProvider> getProviders() {
        return providers;
    }


    @NonNull @Override public ItemViewProvider getProviderByIndex(int index) {
        return providers.get(index);
    }


    @SuppressWarnings("unchecked") @NonNull @Override
    public <T extends ItemViewProvider> T getProviderByClass(
        @NonNull final Class<? extends Item> clazz) {
        return (T) getProviderByIndex(indexOf(clazz));
    }
}
