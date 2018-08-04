package me.drakeet.multitype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author drakeet
 */
@RunWith(RobolectricTestRunner.class)
public class MultiTypeAdapterTest {

  @Mock
  private ViewGroup parent;
  @Mock
  private Context context;
  @Mock
  private TestItemViewBinder mockedItemViewBinder;
  @Mock
  private LayoutInflater inflater;

  private TestItemViewBinder itemViewBinder = new TestItemViewBinder();


  @Before
  public void setUp() throws Exception {
    initMocks(this);
    when(parent.getContext()).thenReturn(context);
    when(context.getSystemService(anyString())).thenReturn(inflater);
  }


  @Test
  public void shouldReturnOriginalItems() {
    List list = new ArrayList();
    MultiTypeAdapter adapter = new MultiTypeAdapter(list);
    assertEquals(list, adapter.getItems());
  }


  @Test
  public void shouldReturnEmptyItemsWithDefaultConstructor() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    assertTrue(adapter.getItems().isEmpty());
  }


  @Test
  public void shouldOverrideRegisteredBinder() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.register(TestItem.class, itemViewBinder);
    assertEquals(1, adapter.getTypePool().size());
    assertEquals(itemViewBinder, adapter.getTypePool().getItemViewBinder(0));

    TestItemViewBinder newBinder = new TestItemViewBinder();
    adapter.register(TestItem.class, newBinder);
    assertEquals(newBinder, adapter.getTypePool().getItemViewBinder(0));
  }


  @Test
  public void shouldNotOverrideRegisteredBinderWhenToMany() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    TestItemViewBinder binder2 = new TestItemViewBinder();
    adapter.register(TestItem.class)
        .to(itemViewBinder, binder2)
        .withLinker(new Linker<TestItem>() {
          @Override
          public int index(int position, @NonNull TestItem testItem) {
            // ignored
            return -1;
          }
        });
    assertEquals(TestItem.class, adapter.getTypePool().getClass(0));
    assertEquals(TestItem.class, adapter.getTypePool().getClass(1));
    assertEquals(itemViewBinder, adapter.getTypePool().getItemViewBinder(0));
    assertEquals(binder2, adapter.getTypePool().getItemViewBinder(1));
  }


  @Test
  public void testOnCreateViewHolder() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.register(TestItem.class, mockedItemViewBinder);
    TestItem item = new TestItem("testOnCreateViewHolder");
    adapter.setItems(Collections.singletonList(item));
    int type = adapter.getItemViewType(0);

    adapter.onCreateViewHolder(parent, type);

    verify(mockedItemViewBinder).onCreateViewHolder(inflater, parent);
  }


  @Test
  @SuppressWarnings("deprecation")
  public void testOnBindViewHolder() {
    MultiTypeAdapter adapter = new MultiTypeAdapter();
    adapter.register(TestItem.class, mockedItemViewBinder);
    TestItem item = new TestItem("testOnCreateViewHolder");
    adapter.setItems(Collections.singletonList(item));

    TestItemViewBinder.ViewHolder holder = mock(TestItemViewBinder.ViewHolder.class);
    when(holder.getItemViewType()).thenReturn(adapter.getItemViewType(0));
    adapter.onBindViewHolder(holder, 0);
    verify(mockedItemViewBinder).onBindViewHolder(eq(holder), eq(item), anyList());

    List<Object> payloads = Collections.emptyList();
    adapter.onBindViewHolder(holder, 0, payloads);
    verify(mockedItemViewBinder, times(2)).onBindViewHolder(holder, item, payloads);
  }
}
