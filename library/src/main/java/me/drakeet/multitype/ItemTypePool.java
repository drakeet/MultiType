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
public final class ItemTypePool {

    private static ArrayList<Class<? extends ItemContent>> contents = new ArrayList<>();
    private static ArrayList<ItemViewProvider> providers = new ArrayList<>();


    public synchronized static void register(
        @NonNull Class<? extends ItemContent> itemContent, @NonNull ItemViewProvider provider) {
        if (!contents.contains(itemContent)) {
            contents.add(itemContent);
            providers.add(provider);
        } else {
            throw new IllegalArgumentException(
                "You have registered the " + itemContent.getSimpleName() +
                    " type. It should not be added again.");
        }
    }


    @NonNull public static ArrayList<Class<? extends ItemContent>> getContents() {
        return contents;
    }


    @NonNull public static ArrayList<ItemViewProvider> getProviders() {
        return providers;
    }


    @NonNull public static ItemViewProvider getProviderByIndex(int index) {
        return providers.get(index);
    }

}
