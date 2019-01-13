package me.drakeet.multitype

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * More tests: /sample/src/androidTest
 *
 * @author drakeet
 */
class ArrayTypePoolTest {

  private var pool: ArrayTypePool? = null

  private inner class SubClass(text: String) : TestItem(text)

  private inner class RegisteredSubClass(text: String) : TestItem(text)

  @Before
  fun register() {
    pool = ArrayTypePool()
    pool!!.register(Type(TestItem::class.java, TestItemViewBinder(), DefaultLinker()))
    // pool.register(new Type<>(RegisteredSubClass.class, new TestItemViewBinder(), new DefaultLinker<TestItem>()));
  }

  @Test
  fun shouldIndexOfReturn0() {
    assertEquals(0, pool!!.firstIndexOf(TestItem::class.java).toLong())
  }

  @Test
  fun shouldIndexOfReturn0WithNonRegisterSubclass() {
    assertEquals(0, pool!!.firstIndexOf(SubClass::class.java).toLong())
  }

  @Test
  fun shouldIndexOfReturn1WithRegisterSubclass() {
    assertEquals(1, pool!!.firstIndexOf(RegisteredSubClass::class.java).toLong())
  }
}
