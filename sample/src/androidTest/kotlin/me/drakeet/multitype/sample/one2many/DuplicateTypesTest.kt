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

package me.drakeet.multitype.sample.one2many

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.drakeet.multitype.MultiTypeAdapter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DuplicateTypesTest {

  private lateinit var adapter: MultiTypeAdapter

  @Rule
  var rule = ActivityTestRule(OneDataToManyActivity::class.java)

  @Before
  @Throws(Throwable::class)
  fun setup() {
    adapter = rule.activity.adapter
  }

  private fun resetRecyclerViewState() {
    // reset and clear the recycler view pool to recreate view holder
    rule.activity.recyclerView.adapter = adapter
  }

  @Test
  @Throws(Throwable::class)
  fun shouldOneOverrideMany() {
    rule.runOnUiThread {
      resetRecyclerViewState()
      adapter.register(DataType1ViewBinder())
      adapter.notifyDataSetChanged()
    }
    assertEquals(1, adapter.typePool.size().toLong())
    assertEquals(Data::class.java, adapter.typePool.getType<Data>(0).clazz)
    assertEquals(
      DataType1ViewBinder::class.java,
      adapter.typePool.getType<Data>(0).binder::class.java
    )
  }

  @Test
  @Throws(Throwable::class)
  fun shouldOneOverrideOne() {
    rule.runOnUiThread {
      resetRecyclerViewState()
      adapter.register(DataType1ViewBinder())
      adapter.register(DataType2ViewBinder())
      adapter.notifyDataSetChanged()
    }
    assertEquals(1, adapter.typePool.size().toLong())
    assertEquals(Data::class.java, adapter.typePool.getType<Data>(0).clazz)
    assertEquals(
      DataType2ViewBinder::class.java,
      adapter.typePool.getType<Data>(0).binder::class.java
    )
  }

  @Test
  @Throws(Throwable::class)
  fun shouldManyOverrideOne() {
    val linker = { _: Int, data: Data -> if (data.type == Data.TYPE_1) 1 else 0 }
    rule.runOnUiThread {
      resetRecyclerViewState()
      adapter.register(DataType1ViewBinder())

      adapter.register(Data::class).to(
        DataType2ViewBinder(),
        DataType1ViewBinder()
      ).withLinker(linker)
      adapter.notifyDataSetChanged()
    }
    assertEquals(2, adapter.typePool.size().toLong())

    assertEquals(Data::class.java, adapter.typePool.getType<Data>(0).clazz)
    assertEquals(Data::class.java, adapter.typePool.getType<Data>(1).clazz)

    assertEquals(
      DataType2ViewBinder::class.java,
      adapter.typePool.getType<Data>(0).binder::class.java
    )
    assertEquals(
      DataType1ViewBinder::class.java,
      adapter.typePool.getType<Data>(1).binder::class.java
    )

    assertSame(linker, adapter.typePool.getType<Data>(0).linker)
    assertSame(linker, adapter.typePool.getType<Data>(1).linker)
  }

  @Test
  @Throws(Throwable::class)
  fun shouldManyOverrideMany() {
    val linker = { _: Int, data: Data -> if (data.type == Data.TYPE_1) 1 else 0 }
    rule.runOnUiThread {
      resetRecyclerViewState()
      adapter.register(Data::class).to(
        DataType2ViewBinder(),
        DataType1ViewBinder()
      ).withLinker(linker)

      adapter.register(Data::class).to(
        DataType1ViewBinder(),
        DataType2ViewBinder()
      ).withLinker(linker)
      adapter.notifyDataSetChanged()
    }
    assertEquals(2, adapter.typePool.size().toLong())

    assertEquals(Data::class.java, adapter.typePool.getType<Data>(0).clazz)
    assertEquals(Data::class.java, adapter.typePool.getType<Data>(1).clazz)

    assertEquals(
      DataType1ViewBinder::class.java,
      adapter.typePool.getType<Data>(0).binder::class.java
    )
    assertEquals(
      DataType2ViewBinder::class.java,
      adapter.typePool.getType<Data>(1).binder::class.java
    )

    assertSame(linker, adapter.typePool.getType<Data>(0).linker)
    assertSame(linker, adapter.typePool.getType<Data>(1).linker)
  }
}
