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
import android.util.Log;
import java.util.ArrayList;

/**
 * @author drakeet
 */
public final class MultiTypePool {

    private static final String TAG = MultiTypePool.class.getSimpleName();
    private static ArrayList<Class<? extends Item>> contents = new ArrayList<>();
    private static ArrayList<ItemViewProvider> providers = new ArrayList<>();


    public synchronized static void register(
        @NonNull Class<? extends Item> clazz,
        @NonNull ItemViewProvider provider) {
        if (!contents.contains(clazz)) {
            contents.add(clazz);
            providers.add(provider);
        } else {
            Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
                "It should not be added again otherwise it will override the original provider.");
            int index = contents.indexOf(clazz);
            providers.set(index, provider);
        }
    }


    @NonNull public static ArrayList<Class<? extends Item>> getContents() {
        return contents;
    }


    @NonNull public static ArrayList<ItemViewProvider> getProviders() {
        return providers;
    }


    @NonNull public static ItemViewProvider getProviderByIndex(int index) {
        return providers.get(index);
    }
}
