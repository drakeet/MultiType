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

import androidx.recyclerview.widget.RecyclerView
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.check
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import kotlin.reflect.KClass

/**
 * @author Drakeet Xu
 */
class OneToManyBuilderTest {

  private class Data

  private val adapter: MultiTypeAdapter = mock()

  private val oneToManyBuilder = OneToManyBuilder(adapter, Data::class.java)

  @Test
  fun testWithLinker() {
    oneToManyBuilder.to(mock(), mock())
    oneToManyBuilder.withLinker(object : Linker<Data> {
      override fun index(position: Int, item: Data): Int {
        return position
      }
    })
    verify(adapter, times(2)).register(check<Type<Data>> {
      assertThat(it.clazz).isEqualTo(Data::class.java)
      assertThat(it.linker.index(1, Data())).isEqualTo(1)
    })

    oneToManyBuilder.withLinker { position, item -> position }
    verify(adapter, times(4)).register(check<Type<Data>> {
      assertThat(it.clazz).isEqualTo(Data::class.java)
      assertThat(it.linker.index(2, Data())).isEqualTo(2)
    })
  }

  private abstract class DataItemViewDelegate : ItemViewDelegate<Data, RecyclerView.ViewHolder>()

  @Test
  fun testWithJavaClassLinker() {
    val itemViewDelegate1 = mock<ItemViewDelegate<Data, RecyclerView.ViewHolder>>()
    val itemViewDelegate2 = mock<DataItemViewDelegate>()
    oneToManyBuilder.to(itemViewDelegate1, itemViewDelegate2)
    oneToManyBuilder.withJavaClassLinker(object : JavaClassLinker<Data> {
      override fun index(position: Int, item: Data): Class<out ItemViewDelegate<Data, *>> {
        return if (position == 3) itemViewDelegate1.javaClass else itemViewDelegate2.javaClass
      }
    })
    verify(adapter, times(2)).register(check<Type<Data>> {
      assertThat(it.clazz).isEqualTo(Data::class.java)
      assertThat(it.linker.index(3, Data())).isEqualTo(0)
      assertThat(it.linker.index(5, Data())).isEqualTo(1)
    })

    oneToManyBuilder.withKotlinClassLinker { position, item ->
      if (position == 3) itemViewDelegate1::class else itemViewDelegate2::class
    }
    verify(adapter, times(4)).register(check<Type<Data>> {
      assertThat(it.clazz).isEqualTo(Data::class.java)
      assertThat(it.linker.index(3, Data())).isEqualTo(0)
      assertThat(it.linker.index(5, Data())).isEqualTo(1)
    })
    oneToManyBuilder.withKotlinClassLinker(object : KotlinClassLinker<Data> {
      override fun index(position: Int, item: Data): KClass<out ItemViewDelegate<Data, *>> {
        return if (position == 3) itemViewDelegate1::class else itemViewDelegate2::class
      }
    })
    verify(adapter, times(6)).register(check<Type<Data>> {
      assertThat(it.clazz).isEqualTo(Data::class.java)
      assertThat(it.linker.index(3, Data())).isEqualTo(0)
      assertThat(it.linker.index(5, Data())).isEqualTo(1)
    })
  }
}
