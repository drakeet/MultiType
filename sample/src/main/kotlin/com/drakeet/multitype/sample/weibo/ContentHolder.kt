/*
 * Copyright (c) 2016-present. Drakeet Xu
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

package com.drakeet.multitype.sample.weibo

import android.view.View

/**
 * @author Drakeet Xu
 */
open class ContentHolder(val itemView: View) {

  lateinit var parent: WeiboFrameBinder.FrameHolder

  val adapterPosition: Int
    get() = parent.adapterPosition

  val layoutPosition: Int
    get() = parent.layoutPosition

  val oldPosition: Int
    get() = parent.oldPosition

  var isRecyclable: Boolean
    get() = parent.isRecyclable
    set(recyclable) = parent.setIsRecyclable(recyclable)
}
