package me.drakeet.multitype;

import android.support.annotation.NonNull;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * More tests: {@literal /sample/src/androidTest}
 *
 * @author drakeet
 */
public class MultiTypePoolTest {

  private MultiTypePool pool;


  private class SubClass extends TestItem {
    public SubClass(@NonNull String text) {
      super(text);
    }
  }


  private class RegisteredSubClass extends TestItem {
    public RegisteredSubClass(@NonNull String text) {
      super(text);
    }
  }


  @Before
  public void register() {
    pool = new MultiTypePool();
    pool.register(TestItem.class, new TestItemViewBinder(), new DefaultLinker<TestItem>());
    pool.register(RegisteredSubClass.class, new TestItemViewBinder(),
        new DefaultLinker<TestItem>());
  }


  @Test
  public void shouldIndexOfReturn0() {
    assertEquals(0, pool.firstIndexOf(TestItem.class));
  }


  @Test
  public void shouldIndexOfReturn0WithNonRegisterSubclass() {
    assertEquals(0, pool.firstIndexOf(SubClass.class));
  }


  @Test
  public void shouldIndexOfReturn1WithRegisterSubclass() {
    assertEquals(1, pool.firstIndexOf(RegisteredSubClass.class));
  }
}