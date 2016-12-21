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

/**
 * A convenient version of the {@link FlatTypeAdapter} to flatten Class
 *
 * @author drakeet
 */
public abstract class FlatTypeClassAdapter implements FlatTypeAdapter {

    @NonNull @Override public abstract Class onFlattenClass(@NonNull Object item);


    @NonNull @Override public Object onFlattenItem(@NonNull Object item) {
        return item;
    }
}
