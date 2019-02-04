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

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.robolectric.RobolectricTestRunner
import java.util.*

/**
 * @author drakeet
 */
@RunWith(RobolectricTestRunner::class)
class MultiTypeAdapterTest {

  @Mock
  private val parent: ViewGroup? = null
  @Mock
  private val context: Context? = null
  @Mock
  private val mockedItemViewBinder: TestItemViewBinder? = null
  @Mock
  private val inflater: LayoutInflater? = null

  private val itemViewBinder = TestItemViewBinder()

  @Before
  @Throws(Exception::class)
  fun setUp() {
    initMocks(this)
    whenever(parent!!.context).thenReturn(context)
    whenever(context!!.getSystemService(anyString())).thenReturn(inflater)
  }

  @Test
  fun shouldReturnOriginalItems() {
    val list = ArrayList<Any>()
    val adapter = MultiTypeAdapter(list)
    assertEquals(list, adapter.items)
  }

  @Test
  fun shouldReturnEmptyItemsWithDefaultConstructor() {
    val adapter = MultiTypeAdapter()
    assertTrue(adapter.items.isEmpty())
  }

  @Test
  fun shouldOverrideRegisteredBinder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class.java, itemViewBinder)
    assertEquals(1, adapter.typePool.size().toLong())
    assertEquals(itemViewBinder, adapter.typePool.getType<Any>(0).binder)

    val newBinder = TestItemViewBinder()
    adapter.register(TestItem::class.java, newBinder)
    assertEquals(newBinder, adapter.typePool.getType<Any>(0).binder)
  }

  @Test
  fun shouldNotOverrideRegisteredBinderWhenToMany() {
    val adapter = MultiTypeAdapter()
    val binder2 = TestItemViewBinder()
    adapter.register(TestItem::class.java)
      .to(itemViewBinder, binder2)
      .withLinker { _, _ -> -1 }
    assertEquals(TestItem::class.java, adapter.typePool.getType<Any>(0).clazz)
    assertEquals(TestItem::class.java, adapter.typePool.getType<Any>(1).clazz)
    assertEquals(itemViewBinder, adapter.typePool.getType<Any>(0).binder)
    assertEquals(binder2, adapter.typePool.getType<Any>(1).binder)
  }

  @Test
  fun testOnCreateViewHolder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class.java, mockedItemViewBinder!!)
    val item = TestItem("testOnCreateViewHolder")
    adapter.items = listOf(item)
    val type = adapter.getItemViewType(0)

    adapter.onCreateViewHolder(parent!!, type)

    verify(mockedItemViewBinder).onCreateViewHolder(inflater!!, parent)
  }

  @Test
  fun testOnBindViewHolder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class.java, mockedItemViewBinder!!)
    val item = TestItem("testOnCreateViewHolder")
    adapter.items = listOf(item)

    val holder = mock(TestItemViewBinder.ViewHolder::class.java)
    whenever(holder.itemViewType).thenReturn(adapter.getItemViewType(0))
    adapter.onBindViewHolder(holder, 0)
    verify(mockedItemViewBinder).onBindViewHolder(eq(holder), eq(item), anyList())

    val payloads = emptyList<Any>()
    adapter.onBindViewHolder(holder, 0, payloads)
    verify(mockedItemViewBinder, times(2)).onBindViewHolder(holder, item, payloads)
  }
}
