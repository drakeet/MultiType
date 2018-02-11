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

import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author drakeet
 */
@RunWith(JUnit4::class)
class MultiTypeTest {

  private val adapter = MultiTypeAdapter()
  private val simpleLinker = Linker<String> { _, _ -> 0 }


  @Test
  fun shouldEqualToRegisteredKClass() {
    adapter.register(String::class, StringViewBinder())
    Assert.assertEquals(adapter.typePool.getClass(0), String::class.java)
  }


  @Test
  fun shouldEqualToRegisteredKClass_Reified() {
    adapter.register(StringViewBinder())
    Assert.assertEquals(adapter.typePool.getClass(0), String::class.java)
  }


  @Test
  fun shouldEqualToRegisteredOneToManyKClass() {
    adapter.register(String::class)
        .to(StringViewBinder())
        .withLinker(simpleLinker)
    Assert.assertEquals(adapter.typePool.getClass(0), String::class.java)
  }


  @Test
  fun shouldEqualToRegisteredKClass_TypePool() {
    adapter.typePool.register(String::class, StringViewBinder(), simpleLinker)
    Assert.assertEquals(adapter.typePool.getClass(0), String::class.java)
  }


  @Test
  fun shouldUnregisterKClass_TypePool() {
    adapter.typePool.register(String::class, StringViewBinder(), simpleLinker)
    Assert.assertTrue(adapter.typePool.unregister(String::class))
  }


  @Test
  fun shouldEqualToRegisteredFirstKClass_TypePool() {
    adapter.typePool.register(String::class, StringViewBinder(), simpleLinker)
    Assert.assertEquals(adapter.typePool.firstIndexOf(String::class), 0)
  }
}
