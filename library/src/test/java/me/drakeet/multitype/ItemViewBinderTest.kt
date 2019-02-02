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

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.*

/**
 * @author drakeet
 */
class ItemViewBinderTest {

  @Test
  fun shouldGetNonNullAdapter() {
    var exception: Exception? = null
    val adapter = MultiTypeAdapter()
    val empty = ArrayList<Any>()
    adapter.items = empty

    val binder = TestItemViewBinder()
    adapter.register(TestItem::class.java, binder)

    empty.add(TestItem("ItemViewBinderTest"))
    try {
      binder.notifyTestItemAdded()
    } catch (e: Exception) {
      e.printStackTrace()
      exception = e
    }

    assertNull(exception)
  }

  @Test(expected = IllegalStateException::class)
  fun shouldThrowIllegalStateException() {
    val adapter = MultiTypeAdapter()
    val empty = ArrayList<Any>()
    adapter.items = empty

    val binder = TestItemViewBinder()

    empty.add(TestItem("ItemViewBinderTest"))
    binder.notifyTestItemAdded()

    adapter.register(binder)
  }

  class TestItemViewBinder : me.drakeet.multitype.TestItemViewBinder() {
    fun notifyTestItemAdded() {
      assertNotNull(adapter.toString())
    }
  }
}
