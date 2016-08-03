# MultiType
An Android library to retrofit multiple item view types

Previously, when we need to develop a complex RecyclerView/ListView, it is a boring and troublesome work. 
We should override the `getItemViewType` of `RecyclerView.Adapter` and add some types, 
then we create some `ViewHolder` to relate the type, all of the process it is a very bad experience.
**And once we need to add a new type, we have to go to the original Adapter and modify some old codes**, so sad. 

Today, I create a new graceful way to easily develop the complex RecyclerView/ListView, with my MultiType library, 
no matter how complex and how frequently changing list, we could insert a new type without changing the old codes.

## Usage

#### Step 1. Create a class extends `ItemContent`, for example:

```java
public class TextItemContent extends ItemContent {

    @NonNull public String text;


    public TextItemContent(@NonNull String text) {
        this.text = text;
    }


    public TextItemContent(@NonNull byte[] data) {
        String json = new String(data);
        this.text = new Gson().fromJson(json, TextItemContent.class).text;
    }

    // If the content is savable, you should override the method
    @NonNull @Override public byte[] toBytes() {
        return new Gson().toJson(this).getBytes();
    }
}
```

#### Step 2. Create a class extends `ItemViewProvider<T extends ItemContent>`, for example: 

```java
public class TextItemViewProvider extends ItemViewProvider<TextItemContent> {

    private static class ViewHolder extends ItemViewProvider.ViewHolder {
        @NonNull final TextView text;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }


    @NonNull @Override protected View onCreateView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        ViewHolder holder = new ViewHolder(root);
        root.setTag(holder);
        return root;
    }


    @Override
    protected void onBindView(
        @NonNull View view, @NonNull TextItemContent content, @NonNull ItemType itemType) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText("hello: " + content.text);
    }
}
```

#### Step 3. You do not need to create a new class. I should add a `RecyclerView` and `List<ItemType>` to you `Activity`, for example: 

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = (RecyclerView) findViewById(R.id.list);
    
    itemTypeFactory = new ItemTypeFactory.Builder().build();
    ItemType textItemType = itemTypeFactory.newType(new TextItemContent("world"));
    ItemType imageItemType = itemTypeFactory.newType(new ImageItemContent(R.mipmap.ic_launcher));
    ItemType richItemType = itemTypeFactory.newType(new RichItemContent("小艾大人赛高", R.mipmap.avatar));
    
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
```

**You're good to go!** 

You could check the `sample` module for more details and after running it will look like: 

<img src="art/screenshot.png" width=270 height=486/>




