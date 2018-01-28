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
import java.nio.charset.Charset;
import me.drakeet.multitype.sample.Savable;

/**
 * @author drakeet
 */
public class TextItem implements Savable {

  public String text;


  public TextItem(@NonNull String text) {
    this.text = text;
  }


  public TextItem(@NonNull byte[] data) {
    init(data);
  }


  @Override
  public final void init(@NonNull byte[] data) {
    String json = new String(data, UTF_8);
    this.text = new Gson().fromJson(json, TextItem.class).text;
  }


  @Override
  public @NonNull byte[] toBytes() {
    return new Gson().toJson(this).getBytes(UTF_8);
  }


  @Override
  public @NonNull String describe() { return "Text"; }


  private static final Charset UTF_8 = Charset.forName("UTF-8");
}
