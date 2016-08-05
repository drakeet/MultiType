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

/**
 * @author drakeet
 */
public class TypeItemFactory {

    @Nullable private final String extra;


    private TypeItemFactory(@Nullable String extra) {
        this.extra = extra;
    }


    public TypeItem newItem(@NonNull ItemContent content) {
        return new TypeItem(content, extra);
    }


    public static class Builder {

        @Nullable private String extra;


        /* optional */
        public Builder setExtra(@Nullable String extra) {
            if (extra == null) {
                throw new NullPointerException("extra may not be null");
            }
            this.extra = extra;
            return this;
        }


        public TypeItemFactory build() {
            return new TypeItemFactory(extra);
        }
    }
}
