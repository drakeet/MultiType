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
 * A TypePool implemented by an ArrayList.
 *
 * @author Drakeet Xu
 */
open class MutableTypes constructor(
  open val initialCapacity: Int = 0,
  open val types: MutableList<Type<*>> = ArrayList(initialCapacity)
) : Types {

  override val size: Int get() = types.size

  override fun <T> register(type: Type<T>) {
    types.add(type)
  }

  override fun unregister(clazz: Class<*>): Boolean {
    return types.removeAll { it.clazz == clazz }
  }

  @Suppress("UNCHECKED_CAST")
  override fun <T> getType(index: Int): Type<T> = types[index] as Type<T>

  override fun firstIndexOf(clazz: Class<*>): Int {
    val index = types.indexOfFirst { it.clazz == clazz }
    if (index != -1) {
      return index
    }
    return types.indexOfFirst { it.clazz.isAssignableFrom(clazz) }
  }
}
