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
import com.google.gson.Gson;
import me.drakeet.multitype.sample.Savable;

/**
 * @author drakeet
 */
public class TextItem implements Savable {

    @NonNull public String text;


    public TextItem(@NonNull String text) {
        this.text = text;
    }


    public TextItem(@NonNull byte[] data) {
        init(data);
    }


    @Override public void init(@NonNull byte[] data) {
        String json = new String(data);
        this.text = new Gson().fromJson(json, TextItem.class).text;
    }


    @NonNull @Override public byte[] toBytes() {
        return new Gson().toJson(this).getBytes();
    }


    @NonNull @Override public String describe() { return "Text";}
}