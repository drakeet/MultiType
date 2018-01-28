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

package me.drakeet.multitype.sample.weibo;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author drakeet
 */
public class ContentHolder {

  WeiboFrameBinder.FrameHolder frameHolder;

  public final View itemView;


  public ContentHolder(@NonNull final View itemView) {
    this.itemView = itemView;
  }


  public @NonNull WeiboFrameBinder.FrameHolder getParent() {
    return frameHolder;
  }


  public final int getAdapterPosition() {
    return getParent().getAdapterPosition();
  }


  public final int getLayoutPosition() {
    return getParent().getLayoutPosition();
  }


  public final int getOldPosition() {
    return getParent().getOldPosition();
  }


  public final boolean isRecyclable() {
    return getParent().isRecyclable();
  }


  public final void setIsRecyclable(boolean recyclable) {
    getParent().setIsRecyclable(recyclable);
  }
}
