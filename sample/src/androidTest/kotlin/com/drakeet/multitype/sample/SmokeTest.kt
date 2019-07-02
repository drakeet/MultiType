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

package com.drakeet.multitype.sample

import android.view.View
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.drakeet.multitype.sample.bilibili.BilibiliActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Drakeet Xu
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class SmokeTest {

  @get:Rule
  var rule = ActivityTestRule(BilibiliActivity::class.java)

  private val menus = arrayOf(
    "NormalActivity",
    "MultiSelectableActivity",
    "communicate with binder",
    "BilibiliActivity",
    "WeiboActivity",
    "OneDataToManyActivity",
    "TestPayloadActivity",
    "MoreApisPlayground"
  )

  @Test
  fun smokeTest() {
    Espresso.closeSoftKeyboard()
    menus.forEach {
      openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
      onView(allOf<View>(withId(R.id.title), withText(it), isDisplayed())).perform(click())
    }
  }
}
