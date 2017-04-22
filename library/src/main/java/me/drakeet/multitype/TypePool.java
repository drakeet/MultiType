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
import java.util.List;

/**
 * An ordered collection to hold the types, binders and linkers.
 *
 * @author drakeet
 */
public interface TypePool {

    /**
     * Registers a type class and its item view binder.
     *
     * @param clazz the class of a item
     * @param binder the item view binder
     * @param linker the linker to link the class and view binder
     * @param <T> the item data type
     */
    <T> void register(
        @NonNull Class<? extends T> clazz,
        @NonNull ItemViewBinder<T, ?> binder,
        @NonNull Linker<T> linker);

    /**
     * For getting index of the item class. If the subclass is already registered,
     * the registered mapping is used. If the subclass is not registered, then look
     * for its parent class if is registered, if the parent class is registered,
     * the subclass is regarded as the parent class.
     *
     * @param clazz the item class.
     * @return The index of the first occurrence of the specified class
     * in this pool, or -1 if this pool does not contain the class.
     */
    int firstIndexOf(@NonNull Class<?> clazz);

    /**
     * Gets the item classes.
     *
     * @return The item classes list
     */
    @NonNull
    List<Class<?>> getClasses();

    /**
     * Gets the item view binders list.
     *
     * @return the binders list
     */
    @NonNull
    List<ItemViewBinder<?, ?>> getItemViewBinders();

    /**
     * Gets the linkers list.
     *
     * @return the linkers list
     */
    @NonNull
    List<Linker<?>> getLinkers();
}
