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

package me.drakeet.multitype.sample.bilibili;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author drakeet
 */
public class PostItemDecoration extends RecyclerView.ItemDecoration {

  private int space;
  private @NonNull SpanSizeLookup spanSizeLookup;


  public PostItemDecoration(int space, @NonNull SpanSizeLookup spanSizeLookup) {
    this.space = space;
    this.spanSizeLookup = spanSizeLookup;
  }


  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int position = parent.getChildLayoutPosition(view);
    if (spanSizeLookup.getSpanSize(position) == 1) {
      outRect.left = space;
      if (position % 2 == 0) {
        outRect.right = space;
      }
    }
  }
}
