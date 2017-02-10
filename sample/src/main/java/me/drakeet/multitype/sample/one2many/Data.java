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

package me.drakeet.multitype.sample.one2many;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

/**
 * @author drakeet
 */
public class Data {

    // @formatter:off
    public abstract static class Type1 {}
    public abstract static class Type2 {}
    // @formatter:on

    @SerializedName("title") public String title;
    @SerializedName("type") public int type;

    public Class typeClass;


    public Data(@NonNull String title, int type) {
        this.title = title;
        this.type = type;
        this.typeClass = getTypeClass(type);
    }


    @Nullable public static Class getTypeClass(int type) {
        switch (type) {
            case 1:
                return Type1.class;
            case 2:
                return Type2.class;
            // ...
        }
        return null;
    }
}
