package me.drakeet.multitype.databinding;

import android.databinding.ViewDataBinding;

/**
 * Created by zhenyu on 16/12/13.
 */

public class SimpleItemViewBindingTemplate<T, VDB extends ViewDataBinding> extends ItemViewBindingTemplate<T, VDB> {

    private int mItemLayoutId;
    private int mVariableId;

    public SimpleItemViewBindingTemplate(int itemLayoutId, int variableId) {
        this.mItemLayoutId = itemLayoutId;
        this.mVariableId = variableId;
    }

    @Override
    protected int getItemLayoutId() {
        return mItemLayoutId;
    }

    @Override
    protected int getVariableId() {
        return mVariableId;
    }

}
