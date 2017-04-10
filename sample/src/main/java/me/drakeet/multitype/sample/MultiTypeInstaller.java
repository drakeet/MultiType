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

package me.drakeet.multitype.sample;

import me.drakeet.multitype.GlobalMultiTypePool;
import me.drakeet.multitype.sample.common.Category;
import me.drakeet.multitype.sample.common.CategoryItemViewBinder;
import me.drakeet.multitype.sample.normal.ImageItem;
import me.drakeet.multitype.sample.normal.ImageItemViewBinder;
import me.drakeet.multitype.sample.normal.TextItem;
import me.drakeet.multitype.sample.normal.TextItemViewBinder;

/**
 * @author drakeet
 */
class MultiTypeInstaller {

    static void start() {
        GlobalMultiTypePool.register(TextItem.class, new TextItemViewBinder());
        GlobalMultiTypePool.register(ImageItem.class, new ImageItemViewBinder());
        GlobalMultiTypePool.register(Category.class, new CategoryItemViewBinder());
    }
}