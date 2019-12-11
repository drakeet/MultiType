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

import androidx.annotation.CheckResult

/**
 * Process and flow operators for one-to-many.
 *
 * @author Drakeet Xu
 */
interface OneToManyFlow<T> {

  /**
   * Sets some item view delegates to the item type.
   *
   * @param delegates the item view delegates
   * @return end flow operator
   */
  @CheckResult
  fun to(vararg delegates: ItemViewDelegate<T, *>): OneToManyEndpoint<T>

  @CheckResult
  fun to(vararg delegates: ItemViewBinder<T, *>): OneToManyEndpoint<T>
}
