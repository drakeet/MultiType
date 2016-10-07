package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author drakeet
 */
public class MultiTypePoolTest {

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
        MultiTypePool.getContents().clear();
        MultiTypePool.register(TestItem.class, new TestItemViewProvider());
        MultiTypePool.register(RegisteredSubClass.class, new TestItemViewProvider());
    }


    @Test
    public void shouldIndexOfReturn0() {
        assertEquals(0, MultiTypePool.indexOf(TestItem.class));
    }


    @Test
    public void shouldIndexOfReturn0WithNonRegisterSubclass() {
        assertEquals(0, MultiTypePool.indexOf(SubClass.class));
    }


    @Test
    public void shouldIndexOfReturn1WithRegisterSubclass() {
        assertEquals(1, MultiTypePool.indexOf(RegisteredSubClass.class));
    }

}