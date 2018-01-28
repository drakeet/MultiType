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

package me.drakeet.multitype.sample;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import me.drakeet.multitype.sample.bilibili.BilibiliActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * @author drakeet
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class SmokeTest {

  @Rule
  public ActivityTestRule<BilibiliActivity> rule = new ActivityTestRule<>(BilibiliActivity.class);


  @Test
  public void smokeTest() {
    Espresso.closeSoftKeyboard();

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(
        allOf(withId(R.id.title), withText("NormalActivity"), isDisplayed())
    ).perform(click());

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(
        allOf(withId(R.id.title), withText("MultiSelectableActivity"), isDisplayed())
    ).perform(click());

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(
        allOf(withId(R.id.title), withText("communicate with binder"), isDisplayed())
    ).perform(click());

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(
        allOf(withId(R.id.title), withText("WeiboActivity"), isDisplayed())
    ).perform(click());

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(
        allOf(withId(R.id.title), withText("OneDataToManyActivity"), isDisplayed())
    ).perform(click());

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(
        allOf(withId(R.id.title), withText("TestPayloadActivity"), isDisplayed())
    ).perform(click());
  }
}
