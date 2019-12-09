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
  private val mockedItemViewDelegate: TestItemViewDelegate = mock()

  private val itemViewDelegate = TestItemViewDelegate()

  @Before
  @Throws(Exception::class)
  fun setUp() {
    whenever(parent.context).thenReturn(context)
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
  fun shouldOverrideRegisteredDelegate() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class, itemViewDelegate)
    assertThat(adapter.types.size).isEqualTo(1)
    assertThat(itemViewDelegate).isEqualTo(adapter.types.getType<Any>(0).delegate)

    val newDelegate = TestItemViewDelegate()
    adapter.register(TestItem::class, newDelegate)
    assertThat(newDelegate).isEqualTo(adapter.types.getType<Any>(0).delegate)
  }

  @Test
  fun shouldNotOverrideRegisteredDelegateWhenToMany() {
    val adapter = MultiTypeAdapter()
    val delegate2 = TestItemViewDelegate()
    adapter.register(TestItem::class)
      .to(itemViewDelegate, delegate2)
      .withLinker { _, _ -> -1 }
    assertThat(adapter.types.getType<Any>(0).clazz).isEqualTo(TestItem::class.java)
    assertThat(adapter.types.getType<Any>(1).clazz).isEqualTo(TestItem::class.java)

    assertThat(itemViewDelegate).isEqualTo(adapter.types.getType<Any>(0).delegate)
    assertThat(delegate2).isEqualTo(adapter.types.getType<Any>(1).delegate)
  }

  @Test
  fun testOnCreateViewHolder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class, mockedItemViewDelegate)
    val item = TestItem("testOnCreateViewHolder")
    adapter.items = listOf(item)
    val type = adapter.getItemViewType(0)

    adapter.onCreateViewHolder(parent, type)
    verify(mockedItemViewDelegate).onCreateViewHolder(context, parent)
  }

  @Test
  fun testOnBindViewHolder() {
    val adapter = MultiTypeAdapter()
    adapter.register(TestItem::class, mockedItemViewDelegate)
    val item = TestItem("testOnCreateViewHolder")
    adapter.items = listOf(item)

    val holder: TestItemViewDelegate.ViewHolder = mock()
    whenever(holder.itemViewType).thenReturn(adapter.getItemViewType(0))
    adapter.onBindViewHolder(holder, 0)
    verify(mockedItemViewDelegate).onBindViewHolder(eq(holder), eq(item), anyList())

    val payloads = emptyList<Any>()
    adapter.onBindViewHolder(holder, 0, payloads)
    verify(mockedItemViewDelegate, times(2)).onBindViewHolder(holder, item, payloads)
  }
}
