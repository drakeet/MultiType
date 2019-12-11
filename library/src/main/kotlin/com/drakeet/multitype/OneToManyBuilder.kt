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
 * @author Drakeet Xu
 */
internal class OneToManyBuilder<T>(
  private val adapter: MultiTypeAdapter,
  private val clazz: Class<T>
) : OneToManyFlow<T>, OneToManyEndpoint<T> {

  private var delegates: Array<ItemViewDelegate<T, *>>? = null

  @SafeVarargs
  @CheckResult(suggest = "#withLinker(Linker)")
  override fun to(vararg delegates: ItemViewDelegate<T, *>) = apply {
    @Suppress("UNCHECKED_CAST")
    this.delegates = delegates as Array<ItemViewDelegate<T, *>>
  }

  @SafeVarargs
  @CheckResult(suggest = "#withLinker(Linker)")
  override fun to(vararg binders: ItemViewBinder<T, *>) = apply {
    @Suppress("UNCHECKED_CAST")
    this.delegates = binders as Array<ItemViewDelegate<T, *>>
  }

  override fun withLinker(linker: Linker<T>) {
    doRegister(linker)
  }

  override fun withJavaClassLinker(javaClassLinker: JavaClassLinker<T>) {
    withLinker(ClassLinkerBridge.toLinker(javaClassLinker, delegates!!))
  }

  private fun doRegister(linker: Linker<T>) {
    for (delegate in delegates!!) {
      adapter.register(Type(clazz, delegate, linker))
    }
  }
}
