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

package me.drakeet.multitype.sample.normal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class TextItemViewBinder extends ItemViewBinder<TextItem, TextItemViewBinder.TextHolder> {

  private int lastShownAnimationPosition;


  static class TextHolder extends RecyclerView.ViewHolder {

    private @NonNull final TextView text;


    TextHolder(@NonNull View itemView) {
      super(itemView);
      this.text = itemView.findViewById(R.id.text);
    }
  }


  @Override
  protected @NonNull TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_text, parent, false);
    return new TextHolder(root);
  }


  @Override
  protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem textItem) {
    holder.text.setText("hello: " + textItem.text);

    // should show animation, ref: https://github.com/drakeet/MultiType/issues/149
    setAnimation(holder.itemView, holder.getAdapterPosition());
  }


  private void setAnimation(@NonNull View viewToAnimate, int position) {
    if (position > lastShownAnimationPosition) {
      Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(),
          android.R.anim.slide_in_left);
      viewToAnimate.startAnimation(animation);
      lastShownAnimationPosition = position;
    }
  }


  @Override
  public void onViewDetachedFromWindow(@NonNull TextHolder holder) {
    holder.itemView.clearAnimation();
  }
}
