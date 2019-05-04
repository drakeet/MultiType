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

import kotlin.reflect.KClass

/**
 * End-operators for one-to-many.
 *
 * @author Drakeet Xu
 */
interface OneToManyEndpoint<T> {

  /**
   * Sets a linker to link the items and binders by array index.
   *
   * @param linker the row linker
   * @see Linker
   */
  fun withLinker(linker: Linker<T>)

  fun withLinker(linker: (position: Int, item: T) -> Int) {
    withLinker(object : Linker<T> {
      override fun index(position: Int, item: T): Int {
        return linker(position, item)
      }
    })
  }

  /**
   * Sets a class linker to link the items and binders by the class instance of binders.
   *
   * @param javaClassLinker the class linker
   * @see JavaClassLinker
   */
  fun withJavaClassLinker(javaClassLinker: JavaClassLinker<T>)

  private fun withJavaClassLinker(classLinker: (position: Int, item: T) -> Class<out ItemViewBinder<T, *>>) {
    withJavaClassLinker(object : JavaClassLinker<T> {
      override fun index(position: Int, item: T): Class<out ItemViewBinder<T, *>> {
        return classLinker(position, item)
      }
    })
  }

  fun withKotlinClassLinker(classLinker: KotlinClassLinker<T>) {
    withJavaClassLinker { position, item -> classLinker.index(position, item).java }
  }

  fun withKotlinClassLinker(classLinker: (position: Int, item: T) -> KClass<out ItemViewBinder<T, *>>) {
    withJavaClassLinker { position, item -> classLinker(position, item).java }
  }
}
