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
public class GlobalMultiTypePool {

    private static MultiTypePool pool = new MultiTypePool();


    public static void register(@NonNull Class<?> clazz, @NonNull ItemViewBinder binder) {
        pool.register(clazz, binder);
    }


    public static int indexOf(@NonNull Class<?> clazz) {
        return pool.indexOf(clazz);
    }


    @NonNull
    public static List<Class<?>> getContents() {
        return pool.getContents();
    }


    @NonNull
    public static List<ItemViewBinder> getBinders() {
        return pool.getItemViewBinders();
    }


    @NonNull
    public static ItemViewBinder getBinderByIndex(int index) {
        return pool.getBinderByIndex(index);
    }


    @NonNull
    public static <T extends ItemViewBinder> T getBinderByClass(
        @NonNull Class<?> clazz) {
        return pool.getBinderByClass(clazz);
    }


    @NonNull
    public static MultiTypePool getPool() {
        return pool;
    }
}
