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
import android.view.View;
import android.view.ViewGroup;

/**
 * @author drakeet
 */
public abstract class ItemViewProvider<T extends ItemContent> {

    @NonNull protected abstract View onCreateView(@NonNull ViewGroup parent);

    protected abstract void onBindView(@NonNull View view, @NonNull T t, @NonNull TypeItem typeItem);


    public final void onBindView(@NonNull View view, @NonNull TypeItem data) {
        this.onBindView(view, (T) data.content, data);
    }


    public static class ViewHolder {
        @NonNull final View itemView;


        public ViewHolder(@NonNull View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
    }
}
