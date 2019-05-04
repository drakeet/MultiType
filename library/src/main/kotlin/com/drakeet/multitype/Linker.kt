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

package com.drakeet.multitype

import androidx.annotation.IntRange

/**
 * An interface to link the items and binders by the array index.
 *
 * @author Drakeet Xu
 */
interface Linker<T> {

  /**
   * Returns the index of your registered binders for your item. The result should be in range of
   * `[0, one-to-multiple-binders.length)`.
   *
   * Note: The argument of [OneToManyFlow.to] is the
   * one-to-multiple-binders.
   *
   * @param position The position in items
   * @param item The data item
   * @return The index of your registered binders
   * @see OneToManyFlow.to
   * @see OneToManyEndpoint.withLinker
   */
  @IntRange(from = 0)
  fun index(position: Int, item: T): Int
}
