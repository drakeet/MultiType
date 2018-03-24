package me.drakeet.multitype.sample.loadmore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * Created BoBoMEe rc on 2018/1/30.
 * Description:
 */

public class LoadMoreViewBinder extends ItemViewBinder<LoadMore, LoadMoreViewBinder.ViewHolder> {
  private static final String TAG = "LoadMoreViewBinder";

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View view = inflater.inflate(R.layout.item_load_more, parent, false);
    return new ViewHolder(view);
  }

  @Override protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LoadMore item) {

  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }

  @Override protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
    super.onViewAttachedToWindow(holder);

    final ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
    if (layoutParams == null) {
      Log.e(TAG,
          " onViewAttacedToWindow layoutParams is a null object , Call setLayoutManager with a non-null argument.");
      return;
    }
    if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
      ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
    }
  }
}
