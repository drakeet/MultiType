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

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author drakeet
 */
@RunWith(JUnit4::class)
class MultiTypeTest {

  private val adapter = MultiTypeAdapter()
  private val simpleLinker = object : Linker<String> {
    override fun index(position: Int, t: String): Int = 0
  }

  @Test
  fun shouldEqualToRegisteredKClass() {
    adapter.register(String::class, StringViewBinder())
    assertEquals(adapter.typePool.getType<String>(0).clazz, String::class.java)
  }

  @Test
  fun shouldEqualToRegisteredKClass_Reified() {
    adapter.register(StringViewBinder())
    assertEquals(adapter.typePool.getType<String>(0).clazz, String::class.java)
  }

  @Test
  fun shouldEqualToRegisteredOneToManyKClass() {
    adapter.register(String::class)
      .to(StringViewBinder())
      .withLinker(simpleLinker)
    assertEquals(adapter.typePool.getType<String>(0).clazz, String::class.java)
  }

  @Test
  fun shouldEqualToRegisteredKClass_TypePool() {
    adapter.typePool.register(Type<String>(String::class.java, StringViewBinder(), simpleLinker))
    assertEquals(adapter.typePool.getType<String>(0).clazz, String::class.java)
  }

  @Test
  fun shouldUnregisterKClass_TypePool() {
    adapter.typePool.register(Type<String>(String::class.java, StringViewBinder(), simpleLinker))
    assertTrue(adapter.typePool.unregister(String::class.java))
  }

  @Test
  fun shouldEqualToRegisteredFirstKClass_TypePool() {
    adapter.typePool.register(Type<String>(String::class.java, StringViewBinder(), simpleLinker))
    assertEquals(adapter.typePool.firstIndexOf(String::class.java), 0)
  }
}
