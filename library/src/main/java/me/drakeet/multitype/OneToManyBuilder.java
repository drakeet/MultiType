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

/**
 * @author drakeet
 */
public class OneToManyBuilder<T> implements OneToManyFlow<T>, OneToManyEndpoint<T> {

    @NonNull private final MultiTypeAdapter adapter;
    @NonNull private final Class<T> clazz;
    private ItemViewBinder<T, ?>[] binders;


    OneToManyBuilder(@NonNull MultiTypeAdapter adapter, @NonNull Class<T> clazz) {
        this.clazz = clazz;
        this.adapter = adapter;
    }


    @Override @NonNull @CheckResult
    public OneToManyEndpoint<T> to(@NonNull ItemViewBinder[] binders) {
        // TODO: 2017/4/2
        this.binders = binders;
        return this;
    }


    @Override
    public void withLinker(@NonNull Linker<T> linker) {
        for (ItemViewBinder<T, ?> binder : binders) {
            adapter.getTypePool().register(clazz, binder, linker);
        }
    }
}
