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

/**
 * An ordered collection to hold the types, binders and linkers.
 *
 * @author Drakeet Xu
 */
interface Types {

  /**
   * Returns the size of the [Types].
   */
  val size: Int

  fun <T> register(type: Type<T>)

  /**
   * Unregister all types indexed by the specified class.
   *
   * @param clazz the main class of a [Type]
   * @return true if any types are unregistered from this [Types]
   */
  fun unregister(clazz: Class<*>): Boolean

  /**
   * Gets the type at the specified index.
   *
   * @param index the type index
   * @return the [Type] at the specified index
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  fun <T> getType(index: Int): Type<T>

  /**
   * For getting index of the type class. If the subclass is already registered,
   * the registered mapping is used. If the subclass is not registered, then look
   * for its parent class if is registered, if the parent class is registered,
   * the subclass is regarded as the parent class.
   *
   * @param clazz the type class.
   * @return The index of the first occurrence of the specified class in this [Types],
   * or -1 if this [Types] does not contain the class.
   */
  fun firstIndexOf(clazz: Class<*>): Int
}
