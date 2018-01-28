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
import android.widget.ImageView;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class ImageItemViewBinder extends ItemViewBinder<ImageItem, ImageItemViewBinder.ImageHolder> {

  class ImageHolder extends RecyclerView.ViewHolder {

    private @NonNull final ImageView image;


    ImageHolder(View itemView) {
      super(itemView);
      image = itemView.findViewById(R.id.image);
    }
  }


  @Override
  protected @NonNull ImageHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_image, parent, false);
    return new ImageHolder(root);
  }


  @Override
  protected void onBindViewHolder(@NonNull ImageHolder holder, @NonNull ImageItem imageContent) {
    holder.image.setImageResource(imageContent.resId);
  }
}
