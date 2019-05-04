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

package com.drakeet.multitype.sample.bilibili

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.RecyclerViewMatcher.Companion.withRecyclerView
import com.drakeet.multitype.sample.common.Category
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Drakeet Xu
 */
@RunWith(AndroidJUnit4::class)
class BilibiliActivityDataSetADCTest {

  private val testTitle = "testTitle"

  private lateinit var items: MutableList<Any>
  private lateinit var adapter: MultiTypeAdapter

  @get:Rule
  var rule = ActivityTestRule(BilibiliActivity::class.java)

  @Before
  fun setup() {
    items = rule.activity.items
    adapter = rule.activity.adapter
  }

  @Test
  @Throws(Throwable::class)
  fun shouldNotFailWhenAddSingleTop() {
    val originalFirst = items[0]
    rule.runOnUiThread {
      items.add(originalFirst)
      adapter.notifyItemInserted(0)
    }
  }

  @Test
  @Throws(Throwable::class)
  fun shouldNotFailWhenAddMultiTop() {
    val originalFirst = items[0]
    rule.runOnUiThread {
      for (i in 0..29) {
        items.add(originalFirst)
      }
      adapter.notifyItemRangeInserted(0, 29)
    }
  }

  @Test
  @Throws(Throwable::class)
  fun shouldNotFailWhenDeleteFirst() {
    rule.runOnUiThread {
      items.removeAt(0)
      adapter.notifyItemRemoved(0)
    }
  }

  @Test
  @Throws(Throwable::class)
  fun shouldNotFailWhenDeleteEnd() {
    rule.runOnUiThread {
      val endIndex = items.size - 1
      items.removeAt(endIndex)
      adapter.notifyItemRemoved(endIndex)
    }
  }

  @Test
  @Throws(Throwable::class)
  fun shouldNotFailWhenDeleteAll() {
    rule.runOnUiThread {
      items.clear()
      adapter.notifyDataSetChanged()
    }
  }

  @Test
  @Throws(Throwable::class)
  fun shouldNotFailWhenChangeFirst() {
    rule.runOnUiThread {
      val category = items[0] as Category
      category.title = testTitle
      adapter.notifyItemChanged(0)
    }

    onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.title))
      .check(matches(withText(testTitle)))
  }
}
