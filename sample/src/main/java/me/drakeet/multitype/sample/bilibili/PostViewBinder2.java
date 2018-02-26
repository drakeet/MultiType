package me.drakeet.multitype.sample.bilibili;

import android.support.annotation.NonNull;

import me.drakeet.multitype.BaseItemViewBinder;
import me.drakeet.multitype.BaseViewHolder;
import me.drakeet.multitype.sample.R;

/**
 * @author YoungTr
 */

public class PostViewBinder2 extends BaseItemViewBinder<Post> {

  public PostViewBinder2(int layoutId) {
    super(layoutId);
  }

  @Override
  protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Post item) {
    holder.setText(R.id.title, item.title);
    holder.setImageResource(R.id.cover, item.coverResId);
  }
}
