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
import org.junit.Before
import org.junit.Test

/**
 * @author Drakeet Xu
 */
class MutableTypesTest {

  private lateinit var types: MutableTypes

  private inner class SubClass(text: String) : TestItem(text)

  private inner class RegisteredSubClass(text: String) : TestItem(text)

  @Before
  fun register() {
    types = MutableTypes()
    types.register(Type(TestItem::class.java, TestItemViewDelegate(), DefaultLinker()))
    types.register(Type(RegisteredSubClass::class.java, TestItemViewDelegate(), DefaultLinker()))
  }

  @Test
  fun testFirstIndexOf() {
    assertThat(types.firstIndexOf(TestItem::class.java)).isEqualTo(0)
    assertThat(types.firstIndexOf(SubClass::class.java)).isEqualTo(0)
    assertThat(types.firstIndexOf(RegisteredSubClass::class.java)).isEqualTo(1)
  }
}
