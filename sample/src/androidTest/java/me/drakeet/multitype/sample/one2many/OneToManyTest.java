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

package me.drakeet.multitype.sample.one2many;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.R;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static me.drakeet.multitype.sample.RecyclerViewMatcher.withRecyclerView;

/**
 * @author drakeet
 */
@RunWith(AndroidJUnit4.class)
public class OneToManyTest {

  private final String testTitle = "testTitle";

  private List<?> items;
  private MultiTypeAdapter adapter;

  @Rule
  public ActivityTestRule<OneDataToManyActivity> rule = new ActivityTestRule<>(
      OneDataToManyActivity.class);


  @Before
  public void setup() {
    items = rule.getActivity().adapter.getItems();
    adapter = rule.getActivity().adapter;
  }


  @Test
  public void shouldRefreshTypeChanged() throws Throwable {
    final Data originalFirst = (Data) items.get(0);
    for (int i = 0; i < 2; i++) {
      rule.runOnUiThread(new Runnable() {
        @Override public void run() {
          originalFirst.type = Data.TYPE_2;
          adapter.notifyItemChanged(0);
        }
      });
      onView(withRecyclerView(R.id.list)
          .atPositionOnView(0, android.R.id.title))
          .check(matches(withHint("right")));
      SystemClock.sleep(2000);
      rule.runOnUiThread(new Runnable() {
        @Override public void run() {
          rule.getActivity().recyclerView.smoothScrollToPosition(items.size() - 1);
        }
      });
      SystemClock.sleep(2000);
      rule.runOnUiThread(new Runnable() {
        @Override public void run() {
          rule.getActivity().recyclerView.smoothScrollToPosition(0);
          originalFirst.type = Data.TYPE_1;
          adapter.notifyItemChanged(0);
        }
      });
      SystemClock.sleep(2000);
      onView(withRecyclerView(R.id.list)
          .atPositionOnView(0, android.R.id.title))
          .check(matches(withHint("left")));
      SystemClock.sleep(2000);
    }
  }
}
