/*
 * Copyright 2017 CaMnter. https://github.com/CaMnter
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

package com.camnter.multitype.databinding.sample.normal;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.camnter.multitype.databinding.sample.normal.binder.CategoryItemBindingBinder;
import com.camnter.multitype.databinding.sample.normal.binder.ImageItemBindingBinder;
import com.camnter.multitype.databinding.sample.normal.binder.RichItemBindingBinder;
import com.camnter.multitype.databinding.sample.normal.binder.TextItemBindingBinder;
import com.camnter.multitype.databinding.sample.normal.vm.NormalCollaborator;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.common.Category;
import me.drakeet.multitype.sample.databinding.ActivityBindingNormalListBinding;
import me.drakeet.multitype.sample.normal.ImageItem;
import me.drakeet.multitype.sample.normal.RichItem;
import me.drakeet.multitype.sample.normal.TextItem;

/**
 * @author CaMnter
 */

public class DataBindingNormalActivity extends MenuBaseActivity
    implements NormalCollaborator.Listener {

    private MultiTypeAdapter adapter;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBindingNormalListBinding binding = DataBindingUtil.setContentView(this,
            R.layout.activity_binding_normal_list);

        final TextItemBindingBinder textBinder = new TextItemBindingBinder();
        final ImageItemBindingBinder imageBinder = new ImageItemBindingBinder();
        final CategoryItemBindingBinder categoryBinder = new CategoryItemBindingBinder();
        final RichItemBindingBinder richBinder = new RichItemBindingBinder();

        this.adapter = new MultiTypeAdapter();
        adapter.register(TextItem.class, textBinder);
        adapter.register(ImageItem.class, imageBinder);
        adapter.register(Category.class, categoryBinder);
        adapter.register(RichItem.class, richBinder);

        /*
         * Each Binder by setting the VHandler complete custom functions (such as click events)
         */
        final NormalCollaborator collaborator = new NormalCollaborator();
        collaborator.setListener(this);
        textBinder.setCollaborator(collaborator);
        imageBinder.setCollaborator(collaborator);
        categoryBinder.setCollaborator(collaborator);
        richBinder.setCollaborator(collaborator);

        /*
         *  binding variable
         *  R.layout.activity_binding_normal_list:
         *
         *  <variable
         *      name="adapter"
         *      type="me.drakeet.multitype.MultiTypeAdapter"/>
         *
         *   <variable
         *      name="viewModel"
         *      type="com.camnter.multitype.databinding.sample.normal.vm.NormalViewModel"/>
         */
        binding.setAdapter(adapter);
        binding.setViewModel(collaborator);

        collaborator.query();
    }


    @Override public void querySuccess(List<Object> item) {
        adapter.setItems(item);
        adapter.notifyDataSetChanged();
    }

}
