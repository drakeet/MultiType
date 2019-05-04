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

package com.drakeet.multitype.sample.one2many

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.RecyclerViewMatcher.Companion.withRecyclerView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Drakeet Xu
 */
@RunWith(AndroidJUnit4::class)
class OneToManyTest {

  private val testTitle = "testTitle"

  private lateinit var items: List<Any>
  private lateinit var adapter: MultiTypeAdapter

  @get:Rule
  var rule = ActivityTestRule(
    OneDataToManyActivity::class.java
  )

  @Before
  fun setup() {
    items = rule.activity.adapter.items
    adapter = rule.activity.adapter
  }

  @Test
  @Throws(Throwable::class)
  fun shouldRefreshTypeChanged() {
    val originalFirst = items[0] as Data
    for (i in 0..1) {
      rule.runOnUiThread {
        originalFirst.type = Data.TYPE_2
        adapter.notifyItemChanged(0)
      }
      onView(
        withRecyclerView(R.id.list).atPositionOnView(0, android.R.id.title)
      )
        .check(matches(withHint("right")))
      SystemClock.sleep(2000)
      rule.runOnUiThread { rule.activity.recyclerView.smoothScrollToPosition(items.size - 1) }
      SystemClock.sleep(2000)
      rule.runOnUiThread {
        rule.activity.recyclerView.smoothScrollToPosition(0)
        originalFirst.type = Data.TYPE_1
        adapter.notifyItemChanged(0)
      }
      SystemClock.sleep(2000)
      onView(
        withRecyclerView(R.id.list).atPositionOnView(0, android.R.id.title)
      )
        .check(matches(withHint("left")))
      SystemClock.sleep(2000)
    }
  }
}
