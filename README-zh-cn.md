# MultiType
Android 复杂的多类型列表视图新写法，清晰、灵活、模块开发、插件化思想

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/drakeet/MultiType/blob/master/LICENSE) ![maven-central] (https://img.shields.io/maven-central/v/me.drakeet.multitype/multitype.svg)

中文版 | [英文版](https://github.com/drakeet/MultiType)

GitHub: [https://github.com/drakeet/MultiType](https://github.com/drakeet/MultiType)

这几天晚上回家开始设计我的 TimeMachine 的消息池系统，并抽取出来开源成一个全新的类库：MultiType! 从前，我们写一个复杂的、多 item view types 的列表视图，经常要做一堆繁琐的工作，而且不小心的话代码还堆积严重：我们需要覆写  `RecyclerView.Adapter` 的  `getItemViewType` 方法，并新增一些 type 整形常量，而且 `ViewHolder` 继承、泛型传递、转型也比较糟糕，毕竟 `Adapter` 只能接受一个泛型……十分麻烦导致过于复杂的页面经常会使用 ScrollView 来实现，一次性加载，而且失去了复用性。

而且，一旦我们需要新增一些新的 item view types，就得去修改 `Adapter` 旧的代码，步骤繁多，侵入较强。

现在好了，只要三步，不需要修改旧代码，只要无脑往池子里插入新的 type，会自动连接、分发数据和事件，新增再多的 item types 都能轻松搞定，支持 RV、复用，代码模块开发，清晰而灵活。若要说为什么这么灵活？ 因为它本来就是为 IM 视图开发的，想想 IM 的消息类型可能有多少种而且新增频繁。

## 接入

在你的 `build.gradle`:

```groovy
dependencies {
    compile 'me.drakeet.multitype:multitype:2.3.4'
}
```

## 使用

#### Step 1. 创建一个 class，它将是你的数据类型或 `Java bean`，示例：


```java
public class TextItem {

    @NonNull public String text;

    public TextItem(@NonNull final String text) {
        this.text = text;
    }
}
```

#### Step 2. 创建一个 class 继承  `ItemViewProvider<T, V extends ViewHolder>`，示例：


```java
public class TextItemViewProvider
    extends ItemViewProvider<TextItem, TextItemViewProvider.TextHolder> {

    static class TextHolder extends RecyclerView.ViewHolder {
        @NonNull private final TextView text;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    @NonNull @Override
    protected TextHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_text, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem textItem) {
        holder.text.setText("hello: " + textItem.text);
        Log.d("demo", "position: " + getPosition());
    }
}
```

#### Step 3. 好了，你不必再创建新的类文件了，在 `Activity` 中加入 `RecyclerView` 和 `List<Object>` 并注册你都类型即可，示例：


```java
public class NormalActivity extends AppCompatActivity {

    private MultiTypeAdapter adapter;
    private Items items;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        items = new Items();
        adapter = new MultiTypeAdapter(items);
        adapter.register(TextItem.class, new TextItemViewProvider());
        adapter.register(ImageItem.class, new ImageItemViewProvider());
        adapter.register(RichItem.class, new RichItemViewProvider());

        TextItem textItem = new TextItem("world");
        ImageItem imageItem = new ImageItem(R.mipmap.ic_launcher);
        RichItem richItem = new RichItem("小艾大人赛高", R.mipmap.avatar);

        for (int i = 0; i < 20; i++) {
            items.add(textItem);
            items.add(imageItem);
            items.add(richItem);
        }

        recyclerView.setAdapter(adapter);
    }
}
```

**大功告成！**

你可以阅读源码项目中的 `sample` 模块获得更多信息和示例，当完整的示例代码运行起来，它是这样子的：

<img src="art/screenshot-normal.png" width=270 height=486/> <img src="art/screenshot-bilibili.png" width=270 height=486/> <img src="art/screenshot-multigrid.png" width=270 height=486/>

And it has been used in [drakeet/TimeMachine](http://github.com/drakeet/TimeMachine), you could check the `Message extends TypeItem` to learn **how to custom TypeItem** and it is recommended to read the `MessageViewProvider`, they are all great guide:

[<img src="http://ww3.sinaimg.cn/large/86e2ff85gw1f55jnr2zjij20bx0bx0v3.jpg" width=256 height=256/>](http://github.com/drakeet/TimeMachine)

## Change logs & Releases

https://github.com/drakeet/MultiType/releases
