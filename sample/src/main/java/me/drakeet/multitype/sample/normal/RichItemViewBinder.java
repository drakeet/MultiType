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
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class RichItemViewBinder extends ItemViewBinder<RichItem, RichItemViewBinder.RichHolder> {

  static class RichHolder extends RecyclerView.ViewHolder {

    @NonNull final TextView text;
    @NonNull final ImageView image;


    RichHolder(@NonNull View itemView) {
      super(itemView);
      this.text = itemView.findViewById(R.id.text);
      this.image = itemView.findViewById(R.id.image);
    }
  }


  @Override
  protected @NonNull RichHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_rich, parent, false);
    return new RichHolder(root);
  }


  @Override
  protected void onBindViewHolder(@NonNull RichHolder holder, @NonNull RichItem richContent) {
    holder.text.setText(richContent.text);
    holder.image.setImageResource(richContent.imageResId);
  }
}
