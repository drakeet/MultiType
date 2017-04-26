package me.drakeet.multitype.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by zhenyu on 16/12/13.
 */

public abstract class ItemViewBindingTemplate<T, VDB extends ViewDataBinding> extends ItemViewBinder<T, BindingHolder<VDB>> {

    @Override
    protected BindingHolder<VDB> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(getItemLayoutId(), parent, false);
        ViewDataBinding viewDataBinding = DataBindingUtil.bind(itemView);
        BindingHolder<VDB> bindingHolder = new BindingHolder(itemView, viewDataBinding);
        return bindingHolder;
    }

    @Override
    protected void onBindViewHolder(BindingHolder<VDB> holder, T item) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(getVariableId(), item);
        viewDataBinding.executePendingBindings();
    }

    protected abstract int getItemLayoutId();

    protected abstract int getVariableId();

}
