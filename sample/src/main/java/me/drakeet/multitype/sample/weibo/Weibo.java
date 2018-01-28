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

package me.drakeet.multitype.sample.weibo;

import android.support.annotation.NonNull;

/**
 * @author drakeet
 */
public class Weibo {

  public @NonNull User user;
  public @NonNull WeiboContent content;
  public @NonNull String createTime;
  /* ... id, counts, etc. */


  public Weibo(@NonNull User user, @NonNull WeiboContent content) {
    this.user = user;
    this.content = content;
    this.createTime = "Just now";
  }


  @Override
  public String toString() {
    return "content: " + content.getClass().getSimpleName();
  }
}
