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

/**
 * @author drakeet
 */
public class GlobalMultiTypePool {

    private static MultiTypePool pool = new MultiTypePool();


    public static void register(@NonNull Class<?> clazz, @NonNull ItemViewProvider provider) {
        pool.register(clazz, provider);
    }


    public static int indexOf(@NonNull Class<?> clazz) {
        return pool.indexOf(clazz);
    }


    @NonNull public static ArrayList<Class<?>> getContents() {
        return pool.getContents();
    }


    @NonNull public static ArrayList<ItemViewProvider> getProviders() {
        return pool.getProviders();
    }


    @NonNull public static ItemViewProvider getProviderByIndex(int index) {
        return pool.getProviderByIndex(index);
    }


    @NonNull public static <T extends ItemViewProvider> T getProviderByClass(
        @NonNull Class<?> clazz) {
        return pool.getProviderByClass(clazz);
    }


    @NonNull public static MultiTypePool getPool() {
        return pool;
    }
}
