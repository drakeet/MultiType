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

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Drakeet Xu
 */
@RunWith(JUnit4::class)
class MultiTypeTest {

  private val adapter = MultiTypeAdapter()
  private val simpleLinker = object : Linker<String> {
    override fun index(position: Int, item: String): Int = 0
  }

  @Test
  fun shouldEqualToRegisteredKClass() {
    adapter.register(String::class, StringViewBinder())
    assertThat(adapter.types.getType<String>(0).clazz).isEqualTo(String::class.java)
  }

  @Test
  fun shouldEqualToRegisteredKClass_Reified() {
    adapter.register(StringViewBinder())
    assertThat(adapter.types.getType<String>(0).clazz).isEqualTo(String::class.java)
  }

  @Test
  fun shouldEqualToRegisteredOneToManyKClass() {
    adapter.register(String::class)
      .to(StringViewBinder())
      .withLinker(simpleLinker)
    assertThat(adapter.types.getType<String>(0).clazz).isEqualTo(String::class.java)
  }

  @Test
  fun shouldEqualToRegisteredKClass_TypePool() {
    adapter.types.register(Type(String::class.java, StringViewBinder(), simpleLinker))
    assertThat(adapter.types.getType<String>(0).clazz).isEqualTo(String::class.java)
  }

  @Test
  fun shouldUnregisterKClass_TypePool() {
    adapter.types.register(Type(String::class.java, StringViewBinder(), simpleLinker))
    assertThat(adapter.types.unregister(String::class.java)).isTrue()
  }

  @Test
  fun shouldEqualToRegisteredFirstKClass_TypePool() {
    adapter.types.register(Type(String::class.java, StringViewBinder(), simpleLinker))
    assertThat(adapter.types.firstIndexOf(String::class.java)).isEqualTo(0)
  }
}
