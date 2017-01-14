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

package me.drakeet.multitype.sample.weibo;

import android.support.annotation.NonNull;
import java.util.List;
import me.drakeet.multitype.Item;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.TypePool;

/**
 * @author drakeet
 */
public class WeiboAdapter extends MultiTypeAdapter {

    public WeiboAdapter() { super(); }


    public WeiboAdapter(@NonNull List<?> items) {
        super(items);
    }


    public WeiboAdapter(@NonNull List<? extends Item> items, TypePool pool) {
        super(items, pool);
    }


    @NonNull @Override public Class onFlattenClass(@NonNull Object item) {
        return ((Weibo) item).content.getClass();
    }
}
