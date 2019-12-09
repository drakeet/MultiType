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

/**
 * @author Drakeet Xu
 */
class ItemViewDelegateTest {

  @Test
  fun shouldGetNonNullAdapter() {
    var exception: Exception? = null
    val adapter = MultiTypeAdapter()
    val empty = arrayListOf<Any>()
    adapter.items = empty

    val delegate = TestItemViewDelegate()
    adapter.register(TestItem::class.java, delegate)

    empty.add(TestItem("ItemViewDelegateTest"))
    try {
      delegate.notifyTestItemAdded()
    } catch (e: Exception) {
      e.printStackTrace()
      exception = e
    }

    assertThat(exception).isNull()
  }

  @Test(expected = IllegalStateException::class)
  fun shouldThrowIllegalStateException() {
    val adapter = MultiTypeAdapter()
    val empty = ArrayList<Any>()
    adapter.items = empty

    val delegate = TestItemViewDelegate()

    empty.add(TestItem("ItemViewDelegateTest"))
    delegate.notifyTestItemAdded()

    adapter.register(delegate)
  }

  class TestItemViewDelegate : com.drakeet.multitype.TestItemViewDelegate() {
    fun notifyTestItemAdded() {
      assertThat(adapter).isNotNull()
      assertThat(adapter.toString()).isNotNull()
    }
  }
}
