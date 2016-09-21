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

import me.drakeet.multitype.MultiTypePool;
import me.drakeet.multitype.sample.bilibili.CategoryItem;
import me.drakeet.multitype.sample.bilibili.CategoryItemViewProvider;
import me.drakeet.multitype.sample.bilibili.HorizontalItemViewProvider;
import me.drakeet.multitype.sample.bilibili.PostList;
import me.drakeet.multitype.sample.bilibili.PostRowItem;
import me.drakeet.multitype.sample.bilibili.PostRowItemViewProvider;
import me.drakeet.multitype.sample.normal.ImageItem;
import me.drakeet.multitype.sample.normal.ImageItemViewProvider;
import me.drakeet.multitype.sample.normal.RichItem;
import me.drakeet.multitype.sample.normal.RichItemViewProvider;
import me.drakeet.multitype.sample.normal.TextItem;
import me.drakeet.multitype.sample.normal.TextItemViewProvider;

/**
 * @author drakeet
 */
class MultiTypeInstaller {

    static void start() {
        MultiTypePool.register(TextItem.class, new TextItemViewProvider());
        MultiTypePool.register(ImageItem.class, new ImageItemViewProvider());
        MultiTypePool.register(RichItem.class, new RichItemViewProvider());
        MultiTypePool.register(CategoryItem.class, new CategoryItemViewProvider());
        MultiTypePool.register(PostRowItem.class, new PostRowItemViewProvider());
        MultiTypePool.register(PostList.class, new HorizontalItemViewProvider());
    }
}
