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
import org.junit.Before
import org.junit.Test

/**
 * More tests: /sample/src/androidTest
 *
 * @author drakeet
 */
class ArrayTypePoolTest {

  private lateinit var pool: ArrayTypePool

  private inner class SubClass(text: String) : TestItem(text)

  private inner class RegisteredSubClass(text: String) : TestItem(text)

  @Before
  fun register() {
    pool = ArrayTypePool()
    pool.register(Type(TestItem::class.java, TestItemViewBinder(), DefaultLinker()))
  }

  @Test
  fun shouldIndexOfReturn0() {
    assertEquals(0, pool.firstIndexOf(TestItem::class.java).toLong())
  }

  @Test
  fun shouldIndexOfReturn0WithNonRegisterSubclass() {
    assertEquals(0, pool.firstIndexOf(SubClass::class.java).toLong())
  }

  @Test
  fun shouldIndexOfReturn1WithRegisterSubclass() {
    assertEquals(1, pool.firstIndexOf(RegisteredSubClass::class.java).toLong())
  }
}
