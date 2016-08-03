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
public class TypeItem {

    @NonNull public ItemContent content;
    @Nullable public String extra;


    public TypeItem() {
    }


    public TypeItem(@NonNull ItemContent content, String extra) {
        this.extra = extra;
        this.content = content;
    }


    @Override public String toString() {
        return "ItemType {" +
            "content=" + content +
            ", extra='" + extra + '\'' +
            '}';
    }
}
