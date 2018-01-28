package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author drakeet
 */
public class TestItemViewBinder extends ItemViewBinder<TestItem, TestItemViewBinder.ViewHolder> {

  @NonNull @Override
  protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return null;
  }


  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TestItem testItem) {

  }


  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
