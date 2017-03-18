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
import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypePool implements TypePool {

    private final String TAG = MultiTypePool.class.getSimpleName();

    @NonNull private final List<Class<?>> contents;
    @NonNull private final List<ItemViewBinder> binders;


    public MultiTypePool() {
        this.contents = new ArrayList<>();
        this.binders = new ArrayList<>();
    }


    public MultiTypePool(int initialCapacity) {
        this.contents = new ArrayList<>(initialCapacity);
        this.binders = new ArrayList<>(initialCapacity);
    }


    public MultiTypePool(@NonNull List<Class<?>> contents, @NonNull List<ItemViewBinder> binders) {
        this.contents = contents;
        this.binders = binders;
    }


    public void register(@NonNull Class<?> clazz, @NonNull ItemViewBinder binder) {
        if (!contents.contains(clazz)) {
            contents.add(clazz);
            binders.add(binder);
        } else {
            int index = contents.indexOf(clazz);
            binders.set(index, binder);
            Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
                "It will override the original binder.");
        }
    }


    @Override
    public int indexOf(@NonNull final Class<?> clazz) {
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


    @NonNull @Override
    public List<Class<?>> getContents() {
        return contents;
    }


    @NonNull @Override
    public List<ItemViewBinder> getItemViewBinders() {
        return binders;
    }


    @NonNull @Override
    public ItemViewBinder getBinderByIndex(int index) {
        return binders.get(index);
    }


    @NonNull @Override @SuppressWarnings("unchecked")
    public <T extends ItemViewBinder> T getBinderByClass(@NonNull final Class<?> clazz) {
        return (T) getBinderByIndex(indexOf(clazz));
    }
}
