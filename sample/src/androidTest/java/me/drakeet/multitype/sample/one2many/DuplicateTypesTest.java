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

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import me.drakeet.multitype.Linker;
import me.drakeet.multitype.MultiTypeAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DuplicateTypesTest {

  private MultiTypeAdapter adapter;

  @Rule
  public ActivityTestRule<OneDataToManyActivity> rule =
      new ActivityTestRule<>(OneDataToManyActivity.class);


  @Before
  public void setup() throws Throwable {
    adapter = rule.getActivity().adapter;
  }


  private void resetRecyclerViewState() {
    // reset and clear the recycler view pool to recreate view holder
    rule.getActivity().recyclerView.setAdapter(adapter);
  }


  @Test
  public void shouldOneOverrideMany() throws Throwable {
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        resetRecyclerViewState();
        adapter.register(Data.class, new DataType1ViewBinder());
        adapter.notifyDataSetChanged();
      }
    });
    assertEquals(1, adapter.getTypePool().size());
    assertEquals(Data.class, adapter.getTypePool().getClass(0));
    assertEquals(DataType1ViewBinder.class,
        adapter.getTypePool().getItemViewBinder(0).getClass());
  }


  @Test
  public void shouldOneOverrideOne() throws Throwable {
    rule.runOnUiThread(new Runnable() {
      @Override public void run() {
        resetRecyclerViewState();
        adapter.register(Data.class, new DataType1ViewBinder());

        adapter.register(Data.class, new DataType2ViewBinder());
        adapter.notifyDataSetChanged();
      }
    });
    assertEquals(1, adapter.getTypePool().size());
    assertEquals(Data.class, adapter.getTypePool().getClass(0));
    assertEquals(DataType2ViewBinder.class,
        adapter.getTypePool().getItemViewBinder(0).getClass());
  }


  @Test
  public void shouldManyOverrideOne() throws Throwable {
    final Linker<Data> linker = (position, data) -> (data.type == Data.TYPE_1) ? 1 : 0;
    rule.runOnUiThread(() -> {
      resetRecyclerViewState();
      adapter.register(Data.class, new DataType1ViewBinder());

      adapter.register(Data.class).to(
          new DataType2ViewBinder(),
          new DataType1ViewBinder()
      ).withLinker(linker);
      adapter.notifyDataSetChanged();
    });
    assertEquals(2, adapter.getTypePool().size());

    assertEquals(Data.class, adapter.getTypePool().getClass(0));
    assertEquals(Data.class, adapter.getTypePool().getClass(1));

    assertEquals(DataType2ViewBinder.class,
        adapter.getTypePool().getItemViewBinder(0).getClass());
    assertEquals(DataType1ViewBinder.class,
        adapter.getTypePool().getItemViewBinder(1).getClass());

    assertSame(linker, adapter.getTypePool().getLinker(0));
    assertSame(linker, adapter.getTypePool().getLinker(1));
  }


  @Test
  public void shouldManyOverrideMany() throws Throwable {
    final Linker<Data> linker = (position, data) -> (data.type == Data.TYPE_1) ? 1 : 0;
    rule.runOnUiThread(() -> {
      resetRecyclerViewState();
      adapter.register(Data.class).to(
          new DataType2ViewBinder(),
          new DataType1ViewBinder()
      ).withLinker(linker);

      adapter.register(Data.class).to(
          new DataType1ViewBinder(),
          new DataType2ViewBinder()
      ).withLinker(linker);
      adapter.notifyDataSetChanged();
    });
    assertEquals(2, adapter.getTypePool().size());

    assertEquals(Data.class, adapter.getTypePool().getClass(0));
    assertEquals(Data.class, adapter.getTypePool().getClass(1));

    assertEquals(DataType1ViewBinder.class,
        adapter.getTypePool().getItemViewBinder(0).getClass());
    assertEquals(DataType2ViewBinder.class,
        adapter.getTypePool().getItemViewBinder(1).getClass());

    assertSame(linker, adapter.getTypePool().getLinker(0));
    assertSame(linker, adapter.getTypePool().getLinker(1));
  }
}
