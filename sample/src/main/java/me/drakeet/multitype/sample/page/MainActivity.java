package me.drakeet.multitype.sample.page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.multitype.ItemType;
import me.drakeet.multitype.ItemTypeFactory;
import me.drakeet.multitype.ItemTypePool;
import me.drakeet.multitype.ItemTypesAdapter;
import me.drakeet.multitype.sample.ImageItemContent;
import me.drakeet.multitype.sample.ImageItemViewProvider;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.RichItemContent;
import me.drakeet.multitype.sample.RichItemViewProvider;
import me.drakeet.multitype.sample.TextItemContent;
import me.drakeet.multitype.sample.TextItemViewProvider;

public class MainActivity extends AppCompatActivity {

    private ItemTypeFactory itemTypeFactory;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        // @formatter:off
        itemTypeFactory = new ItemTypeFactory.Builder().build();
        ItemType textItemType = itemTypeFactory.newType(new TextItemContent("world"));
        ItemType imageItemType = itemTypeFactory.newType(new ImageItemContent(R.mipmap.ic_launcher));
        ItemType richItemType = itemTypeFactory.newType(new RichItemContent("小艾大人赛高", R.mipmap.avatar));
        // @formatter:on
        List<ItemType> itemTypes = new ArrayList<>(80);
        for (int i = 0; i < 20; i++) {
            itemTypes.add(textItemType);
            itemTypes.add(imageItemType);
            itemTypes.add(richItemType);
        }

        ItemTypePool.register(TextItemContent.class, new TextItemViewProvider());
        ItemTypePool.register(ImageItemContent.class, new ImageItemViewProvider());
        ItemTypePool.register(RichItemContent.class, new RichItemViewProvider());

        recyclerView.setAdapter(new ItemTypesAdapter(itemTypes));
    }
}
