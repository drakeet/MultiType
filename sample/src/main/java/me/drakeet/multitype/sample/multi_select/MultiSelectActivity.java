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

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.TreeSet;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.common.Category;

import static me.drakeet.multitype.MultiTypeAsserts.assertAllRegistered;

public class MultiSelectActivity extends MenuBaseActivity {

    private final static int SPAN_COUNT = 5;
    Items items = new Items();
    MultiTypeAdapter adapter;
    Button fab;
    private TreeSet<Integer> selectedSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_select);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (items.get(position) instanceof Category) ? SPAN_COUNT : 1;
            }
        });

        loadData();
        selectedSet = new TreeSet<>();

        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter(items);
        adapter.applyGlobalMultiTypePool();
        adapter.register(Square.class, new SquareViewProvider(selectedSet));

        assertAllRegistered(adapter, items);
        recyclerView.setAdapter(adapter);

        setupFAB();
    }


    private void loadData() {
        Category spacialCategory = new Category("特别篇");
        items.add(spacialCategory);
        for (int i = 0; i < 7; i++) {
            items.add(new Square(i + 1));
        }
        Category currentCategory = new Category("本篇");
        items.add(currentCategory);
        for (int i = 0; i < 1000; i++) {
            items.add(new Square(i + 1));
        }
    }


    private void setupFAB() {
        fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                StringBuilder content = new StringBuilder();
                for (Integer number : selectedSet) {
                    content.append(number).append(" ");
                }
                Toast.makeText(v.getContext(),
                    "Selected items: " + content, Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }
}
