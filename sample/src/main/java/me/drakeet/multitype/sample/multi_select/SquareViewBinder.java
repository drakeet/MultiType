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

package me.drakeet.multitype.sample.multi_select;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Set;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

import static java.lang.String.valueOf;

/**
 * @author drakeet
 */
public class SquareViewBinder extends ItemViewBinder<Square, SquareViewBinder.ViewHolder> {

    private final Set<Integer> selectedSet;


    public SquareViewBinder(Set<Integer> selectedSet) { this.selectedSet = selectedSet; }


    @NonNull @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_square, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Square square) {
        holder.square = square;
        holder.squareView.setText(valueOf(square.number));
        holder.squareView.setSelected(square.isSelected);
    }


    public Set<Integer> getSelectedSet() {
        return selectedSet;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView squareView;
        private Square square;


        ViewHolder(final View itemView) {
            super(itemView);
            squareView = (TextView) itemView.findViewById(R.id.square);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    itemView.setSelected(square.isSelected = !square.isSelected);
                    if (square.isSelected) {
                        selectedSet.add(square.number);
                    } else {
                        selectedSet.remove(square.number);
                    }
                }
            });
        }
    }
}
