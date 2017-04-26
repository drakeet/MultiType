package me.drakeet.multitype.databinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhenyu on 16/12/8.
 */

public class BindingHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private VDB viewDataBinding;

    public BindingHolder(View itemView) {
        super(itemView);
    }

    public BindingHolder(View itemView, VDB viewDataBinding) {
        super(itemView);
        this.viewDataBinding = viewDataBinding;
    }

    public void setViewDataBinding(VDB viewDataBinding) {
        this.viewDataBinding = viewDataBinding;
    }

    public VDB getViewDataBinding() {
        return viewDataBinding;
    }
}

