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
public class Items {

    private List<TypeItem> items;
    private TypeItemFactory factory;


    public Items() {
        this(new ArrayList<TypeItem>());
    }


    public Items(@NonNull final List<TypeItem> items) {
        this.items = items;
        factory = new TypeItemFactory.Builder().build();
    }


    @NonNull public List<? extends TypeItem> toList() {
        return items;
    }


    public boolean add(@NonNull final ItemContent content) {
        return items.add(factory.newItem(content));
    }


    public void add(int index, @NonNull ItemContent content) {
        items.add(index, factory.newItem(content));
    }


    @NonNull public TypeItem get(int index) {
        return items.get(index);
    }


    public TypeItem set(int index, @NonNull ItemContent content) {
        return items.set(index, factory.newItem(content));
    }


    public boolean contains(@NonNull final ItemContent content) {
        return items.contains(factory.newItem(content));
    }


    @NonNull public TypeItem remove(int index) {
        return items.remove(index);
    }


    public boolean remove(@NonNull final ItemContent content) {
        return items.remove(factory.newItem(content));
    }


    public int size() {
        return items.size();
    }


    public boolean isEmpty() {
        return items.isEmpty();
    }


    public void clear() {
        items.clear();
    }
}
