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

package me.drakeet.multitype.sample.bilibili;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.common.Category;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static me.drakeet.multitype.sample.RecyclerViewMatcher.withRecyclerView;

/**
 * @author drakeet
 */
@RunWith(AndroidJUnit4.class)
public class BilibiliActivityDataSetADCTest {

  private final String testTitle = "testTitle";

  private List<Object> items;
  private MultiTypeAdapter adapter;

  @Rule
  public ActivityTestRule<BilibiliActivity> rule = new ActivityTestRule<>(BilibiliActivity.class);


  @Before
  public void setup() {
    items = rule.getActivity().items;
    adapter = rule.getActivity().adapter;
  }


  @Test
  public void shouldNotFailWhenAddSingleTop() throws Throwable {
    final Object originalFirst = items.get(0);
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        items.add(originalFirst);
        adapter.notifyItemInserted(0);
      }
    });
  }


  @Test
  public void shouldNotFailWhenAddMultiTop() throws Throwable {
    final Object originalFirst = items.get(0);
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        for (int i = 0; i < 30; i++) {
          items.add(originalFirst);
        }
        adapter.notifyItemRangeInserted(0, 29);
      }
    });
  }


  @Test
  public void shouldNotFailWhenDeleteFirst() throws Throwable {
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        items.remove(0);
        adapter.notifyItemRemoved(0);
      }
    });
  }


  @Test
  public void shouldNotFailWhenDeleteEnd() throws Throwable {
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        final int endIndex = items.size() - 1;
        items.remove(endIndex);
        adapter.notifyItemRemoved(endIndex);
      }
    });
  }


  @Test
  public void shouldNotFailWhenDeleteAll() throws Throwable {
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        items.clear();
        adapter.notifyDataSetChanged();
      }
    });
  }


  @Test
  public void shouldNotFailWhenChangeFirst() throws Throwable {
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        Category category = (Category) items.get(0);
        category.title = testTitle;
        adapter.notifyItemChanged(0);
      }
    });

    onView(withRecyclerView(R.id.list)
        .atPositionOnView(0, R.id.title))
        .check(matches(withText(testTitle)));
  }
}
