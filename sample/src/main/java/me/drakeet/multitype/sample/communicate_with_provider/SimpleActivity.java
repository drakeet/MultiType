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

package me.drakeet.multitype.sample.communicate_with_provider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypePool;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;

import static java.lang.String.valueOf;

/**
 * @author drakeet
 */
public class SimpleActivity extends MenuBaseActivity {

    private String aFieldValue = "aFieldValue of SimpleActivity";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        /* If you have register the type:
         * int index = MultiTypePool.indexOf(SimpleData.class);
         * ((SimpleDataViewProvider) MultiTypePool.getProviderByIndex(index)).aValueFromOutside
         *     = aFieldValue;
         * Or:
         */
        MultiTypePool.register(SimpleData.class, new SimpleDataViewProvider(aFieldValue));

        Items items = new Items();
        for (int i = 0; i < 20; i++) {
            items.add(new SimpleData(valueOf(i)));
        }

        recyclerView.setAdapter(new MultiTypeAdapter(items));
    }
}
