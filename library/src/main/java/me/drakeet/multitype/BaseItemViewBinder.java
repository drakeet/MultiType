package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author YoungTr
 */

public abstract class BaseItemViewBinder<T> extends ItemViewBinder<T, BaseViewHolder> {

  private int mLayoutId;

  public BaseItemViewBinder(int layoutId) {
    this.mLayoutId = layoutId;
  }

  @NonNull
  @Override
  protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new BaseViewHolder(inflater.inflate(mLayoutId, parent, false));
  }
}
