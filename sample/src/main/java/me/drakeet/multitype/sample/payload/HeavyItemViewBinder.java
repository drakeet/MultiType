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

package me.drakeet.multitype.sample.payload;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
class HeavyItemViewBinder extends ItemViewBinder<HeavyItem, HeavyItemViewBinder.ViewHolder> {

  @Override
  protected @NonNull ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_heavy, parent, false);
    return new ViewHolder(root);
  }


  @Override @SuppressLint("SetTextI18n")
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HeavyItem heavyItem) {
    holder.firstText.setText(heavyItem.text);
    holder.endText.setText("currentTimeMillis: " + System.currentTimeMillis());
    holder.item = heavyItem;
  }


  @Override @SuppressLint("SetTextI18n")
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HeavyItem item, @NonNull List<Object> payloads) {
    if (payloads.isEmpty()) {
      super.onBindViewHolder(holder, item, payloads);
    } else {
      holder.firstText.setText("Just update the first text: " + payloads.get(0));
    }
  }


  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
      View.OnLongClickListener {

    TextView firstText;
    TextView endText;
    HeavyItem item;


    ViewHolder(View itemView) {
      super(itemView);
      firstText = itemView.findViewById(R.id.first_text);
      endText = itemView.findViewById(R.id.end_text);
      itemView.setOnClickListener(this);
      itemView.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
      Toast.makeText(v.getContext(), "Update with a payload", Toast.LENGTH_SHORT).show();
      getAdapter().notifyItemChanged(getAdapterPosition(), "la la la (payload)");
    }


    @Override
    public boolean onLongClick(View v) {
      Toast.makeText(v.getContext(), "Full update", Toast.LENGTH_SHORT).show();
      item.text = "full full full";
      getAdapter().notifyItemChanged(getAdapterPosition());
      return true;
    }
  }
}
