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
 * @author Drakeet Xu
 */
internal class ClassLinkerBridge<T> private constructor(
  private val javaClassLinker: JavaClassLinker<T>,
  private val delegates: Array<ItemViewDelegate<T, *>>
) : Linker<T> {

  override fun index(position: Int, item: T): Int {
    val indexedClass = javaClassLinker.index(position, item)
    val index = delegates.indexOfFirst { it.javaClass == indexedClass }
    if (index != -1) return index
    throw IndexOutOfBoundsException(
      "The delegates'(${delegates.contentToString()}) you registered do not contain this ${indexedClass.name}."
    )
  }

  companion object {
    fun <T> toLinker(
      javaClassLinker: JavaClassLinker<T>,
      delegates: Array<ItemViewDelegate<T, *>>
    ): Linker<T> {
      return ClassLinkerBridge(javaClassLinker, delegates)
    }
  }
}
