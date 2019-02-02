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

import kotlin.reflect.KClass

/**
 * End-operators for one-to-many.
 *
 * @author drakeet
 */
interface OneToManyEndpoint<T> {

  /**
   * Sets a linker to link the items and binders by array index.
   *
   * @param linker the row linker
   * @see Linker
   */
  fun withLinker(linker: Linker<T>)

  fun withLinker(linker: (position: Int, t: T) -> Int) {
    withLinker(object : Linker<T> {
      override fun index(position: Int, t: T): Int {
        return linker.invoke(position, t)
      }
    })
  }

  /**
   * Sets a class linker to link the items and binders by the class instance of binders.
   *
   * @param classLinker the class linker
   * @see ClassLinker
   */
  fun withJavaClassLinker(classLinker: ClassLinker<T>)

  fun withKotlinClassLinker(classLinker: KotlinClassLinker<T>) {
    withJavaClassLinker { position, t -> classLinker.index(position, t).java }
  }

  fun withKotlinClassLinker(classLinker: (position: Int, t: T) -> KClass<out ItemViewBinder<T, *>>) {
    withJavaClassLinker { position, t -> classLinker(position, t).java }
  }

  private fun withJavaClassLinker(classLinker: (position: Int, t: T) -> Class<out ItemViewBinder<T, *>>) {
    withJavaClassLinker(object : ClassLinker<T> {
      override fun index(position: Int, t: T): Class<out ItemViewBinder<T, *>> {
        return classLinker.invoke(position, t)
      }
    })
  }
}
