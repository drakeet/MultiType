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

    public ItemContent content;
    @Nullable public String extra;


    public TypeItem() {
    }


    public TypeItem(@NonNull ItemContent content, @Nullable String extra) {
        this.extra = extra;
        this.content = content;
    }


    @Override public String toString() {
        return "TypeItem {" +
            "content=" + content +
            ", extra='" + extra + '\'' +
            '}';
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeItem typeItem = (TypeItem) o;
        if (!content.equals(typeItem.content)) return false;
        return extra != null ? extra.equals(typeItem.extra) : typeItem.extra == null;

    }


    @Override public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }
}
