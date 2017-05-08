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

package com.camnter.multitype.databinding.sample.normal.vm;

import android.databinding.ObservableBoolean;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.camnter.multitype.databinding.DataBindingViewModel;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.normal.ImageItem;
import me.drakeet.multitype.sample.normal.RichItem;
import me.drakeet.multitype.sample.normal.TextItem;

/**
 * @author CaMnter
 */

public class NormalViewModel extends DataBindingViewModel {

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private Listener listener;


    public void setListener(@NonNull final Listener listener) {
        this.listener = listener;
    }


    public void query() {
        // Pretend to network requests
        this.dataLoading.set(true);
        this.mainHandler.postDelayed(new Runnable() {
            @Override public void run() {
                TextItem textItem = new TextItem("Save you form anything");
                ImageItem imageItem = new ImageItem(R.mipmap.ic_camnter_avatar);
                RichItem richItem = new RichItem("CaMnter", R.mipmap.ic_camnter_avatar);

                final List<Object> items;
                items = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    items.add(textItem);
                    items.add(imageItem);
                    items.add(richItem);
                }

                // success
                dataLoading.set(false);
                if (listener != null) {
                    listener.querySuccess(items);
                }
            }
        }, 3000L);
    }


    public interface Listener {
        void querySuccess(List<Object> item);
    }

}
