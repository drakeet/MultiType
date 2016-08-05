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

package tellh.com.library_retro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author drakeet
 */
public class MultiTypeItemsAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<ItemViewBinder> typeItemBinders;

    public MultiTypeItemsAdapter(@NonNull List<ItemViewBinder> binders) {this.typeItemBinders = binders;}

    @Override public int getItemViewType(int position) {
        ItemViewBinder binder = typeItemBinders.get(position);
        return binder.getItemLayoutId();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(indexViewType, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ItemViewBinder binder = typeItemBinders.get(position);
        binder.onBindView(holder,position);
    }

    @Override
    public int getItemCount() {
        return typeItemBinders.size();
    }

}