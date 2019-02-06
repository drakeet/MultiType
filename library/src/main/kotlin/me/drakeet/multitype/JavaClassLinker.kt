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

package me.drakeet.multitype

/**
 * An interface to link the items and binders by the classes of binders.
 *
 * @author Drakeet Xu
 */
interface JavaClassLinker<T> {

  /**
   * Returns the class of your registered binders for your item.
   *
   * @param position The position in items
   * @param item The item
   * @return The index of your registered binders
   * @see OneToManyEndpoint.withJavaClassLinker
   */
  fun index(position: Int, item: T): Class<out ItemViewBinder<T, *>>
}
