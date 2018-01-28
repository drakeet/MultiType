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

package me.drakeet.multitype;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author drakeet
 */
public class ItemViewBinderTest {

  @Test
  public void shouldGetNonNullAdapter() {
    Exception exception = null;
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    Items empty = new Items();
    adapter.setItems(empty);

    TestItemViewBinder binder = new TestItemViewBinder();
    adapter.register(TestItem.class, binder);

    empty.add(new TestItem("ItemViewBinderTest"));
    try {
      binder.notifyTestItemAdded();
    } catch (Exception e) {
      e.printStackTrace();
      exception = e;
    }
    assertNull(exception);
  }


  @Test(expected = IllegalStateException.class)
  public void shouldThrowIllegalStateException() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    Items empty = new Items();
    adapter.setItems(empty);

    TestItemViewBinder binder = new TestItemViewBinder();

    empty.add(new TestItem("ItemViewBinderTest"));
    binder.notifyTestItemAdded();

    adapter.register(TestItem.class, binder);
  }


  private static class TestItemViewBinder extends me.drakeet.multitype.TestItemViewBinder {

    private void notifyTestItemAdded() {
      assertNotNull(getAdapter().toString());
    }
  }
}
