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
import java.util.ArrayList;
import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypePool implements TypePool {

    private final String TAG = MultiTypePool.class.getSimpleName();

    @NonNull private final List<Class<?>> contents;
    @NonNull private final List<ItemViewBinder<?, ?>> binders;
    @NonNull private final List<Linker<?>> linkers;


    public MultiTypePool() {
        this.contents = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.linkers = new ArrayList<>();
    }


    public MultiTypePool(int initialCapacity) {
        this.contents = new ArrayList<>(initialCapacity);
        this.binders = new ArrayList<>(initialCapacity);
        this.linkers = new ArrayList<>(initialCapacity);
    }


    public MultiTypePool(
        @NonNull List<Class<?>> contents,
        @NonNull List<ItemViewBinder<?, ?>> binders,
        @NonNull List<Linker<?>> linkers) {
        this.contents = contents;
        this.binders = binders;
        this.linkers = linkers;
    }


    @Override
    public synchronized <T> void register(
        @NonNull Class<? extends T> clazz,
        @NonNull ItemViewBinder<T, ?> binder,
        @NonNull Linker<T> linker) {
        contents.add(clazz);
        binders.add(binder);
        linkers.add(linker);
    }


    @Override
    public int firstIndexOf(@NonNull final Class<?> clazz) {
        int index = contents.indexOf(clazz);
        if (index != -1) {
            return index;
        }
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        return -1;
    }


    @NonNull @Override
    public List<Class<?>> getContents() {
        return contents;
    }


    @NonNull @Override
    public List<ItemViewBinder<?, ?>> getItemViewBinders() {
        return binders;
    }


    @NonNull
    public List<Linker<?>> getLinkers() {
        return linkers;
    }
}
