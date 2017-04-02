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
 * @author drakeet
 */
public interface TypePool {

    <T> void register(
        @NonNull Class<? extends T> clazz,
        @NonNull ItemViewBinder<T, ?> list,
        @NonNull Linker<T> linker);

    /**
     * For getting index of the item class.
     * If the subclass is already registered, the registered mapping is used.
     * If the subclass is not registered, then look for the parent class is
     * registered, if the parent class is registered,
     * the subclass is regarded as the parent class.
     *
     * @param clazz the item class.
     * @return the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    int firstIndexOf(@NonNull Class<?> clazz);

    /**
     * Get the item classes. Its contents are not duplicated.
     *
     * @return the item classes list
     */
    @NonNull
    List<Class<?>> getContents();

    @NonNull
    List<ItemViewBinder<?, ?>> getItemViewBinders();

    @NonNull
    List<Linker<?>> getLinkers();
}
