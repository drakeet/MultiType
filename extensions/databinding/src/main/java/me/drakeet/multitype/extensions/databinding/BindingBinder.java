/*
 * Copyright 2017 CaMnter. https://github.com/CaMnter
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

package me.drakeet.multitype.extensions.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.List;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author CaMnter
 */

public abstract class BindingBinder<T>
    extends ItemViewBinder<T, BindingViewHolder> {

    @Nullable
    private WeakReference<Collaborator> collaborator;


    public void setCollaborator(@NonNull final Collaborator collaborator) {
        this.collaborator = new WeakReference<>(collaborator);
    }


    protected abstract int getItemLayoutId();


    @NonNull @Override
    protected BindingViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        final int layoutId = this.getItemLayoutId();
        if (layoutId == 0) {
            throw new LayoutIdNotFoundException(this.getClass());
        }
        return new BindingViewHolder<>(
            DataBindingUtil.inflate(inflater, layoutId, parent, false));
    }


    @Override
    protected void onBindViewHolder(
        @NonNull BindingViewHolder holder, @NonNull T item) {
        final ViewDataBinding binding = holder.getBinding();
        binding.setVariable(me.drakeet.multitype.extensions.databinding.BR.position,
            holder.getAdapterPosition());
        binding.setVariable(me.drakeet.multitype.extensions.databinding.BR.itemValue, item);
        if (this.collaborator != null) {
            binding.setVariable(me.drakeet.multitype.extensions.databinding.BR.collaborator,
                this.collaborator.get());
        }
        binding.executePendingBindings();
    }


    @Override
    protected void onBindViewHolder(
        @NonNull BindingViewHolder holder, @NonNull T item, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, item, payloads);
    }


    public interface Collaborator {
    }

}
