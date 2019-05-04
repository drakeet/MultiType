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

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.robolectric.RobolectricTestRunner
import java.util.*

/**
 * @author Drakeet Xu
 */
@RunWith(RobolectricTestRunner::class)
class MultiTypeAdapterTest {

  private val parent: ViewGroup = mock()
  private val context: Context = mock()
  private val mockedItemViewBinder: TestItemViewBinder = mock()
  private val inflater: LayoutInflater = mock()

  private val itemViewBinder = TestItemViewBinder()

  @Before
  @Throws(Exception::class)
  fun setUp() {
    whenever(parent.context).thenReturn(context)
    whenever(context.getSystemService(anyString())).thenReturn(inflater)
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
    assertThat(adapter.items).isEmpty()
  }

  @Test
  fun shouldOverrideRegisteredBinder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class, itemViewBinder)
    assertThat(adapter.types.size).isEqualTo(1)
    assertThat(itemViewBinder).isEqualTo(adapter.types.getType<Any>(0).binder)

    val newBinder = TestItemViewBinder()
    adapter.register(TestItem::class, newBinder)
    assertThat(newBinder).isEqualTo(adapter.types.getType<Any>(0).binder)
  }

  @Test
  fun shouldNotOverrideRegisteredBinderWhenToMany() {
    val adapter = MultiTypeAdapter()
    val binder2 = TestItemViewBinder()
    adapter.register(TestItem::class)
      .to(itemViewBinder, binder2)
      .withLinker { _, _ -> -1 }
    assertThat(adapter.types.getType<Any>(0).clazz).isEqualTo(TestItem::class.java)
    assertThat(adapter.types.getType<Any>(1).clazz).isEqualTo(TestItem::class.java)

    assertThat(itemViewBinder).isEqualTo(adapter.types.getType<Any>(0).binder)
    assertThat(binder2).isEqualTo(adapter.types.getType<Any>(1).binder)
  }

  @Test
  fun testOnCreateViewHolder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class, mockedItemViewBinder)
    val item = TestItem("testOnCreateViewHolder")
    adapter.items = listOf(item)
    val type = adapter.getItemViewType(0)

    adapter.onCreateViewHolder(parent, type)
    verify(mockedItemViewBinder).onCreateViewHolder(inflater, parent)
  }

  @Test
  fun testOnBindViewHolder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class, mockedItemViewBinder)
    val item = TestItem("testOnCreateViewHolder")
    adapter.items = listOf(item)

    val holder: TestItemViewBinder.ViewHolder = mock()
    whenever(holder.itemViewType).thenReturn(adapter.getItemViewType(0))
    adapter.onBindViewHolder(holder, 0)
    verify(mockedItemViewBinder).onBindViewHolder(eq(holder), eq(item), anyList())

    val payloads = emptyList<Any>()
    adapter.onBindViewHolder(holder, 0, payloads)
    verify(mockedItemViewBinder, times(2)).onBindViewHolder(holder, item, payloads)
  }
}
