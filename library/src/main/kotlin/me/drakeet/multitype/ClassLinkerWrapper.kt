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

package me.drakeet.multitype

/**
 * @author drakeet
 */
internal class ClassLinkerWrapper<T> private constructor(
  private val classLinker: ClassLinker<T>,
  private val binders: Array<ItemViewBinder<T, *>>
) : Linker<T> {

  override fun index(position: Int, t: T): Int {
    val userIndexClass = classLinker.index(position, t)
    for (i in binders.indices) {
      if (binders[i].javaClass == userIndexClass) {
        return i
      }
    }
    throw IndexOutOfBoundsException(
      "${userIndexClass.name} is out of your registered binders'(${binders.contentToString()}) bounds."
    )
  }

  companion object {
    fun <T> wrap(
      classLinker: ClassLinker<T>,
      binders: Array<ItemViewBinder<T, *>>
    ): ClassLinkerWrapper<T> {
      return ClassLinkerWrapper(classLinker, binders)
    }
  }
}
