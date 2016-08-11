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
    compile 'me.drakeet.multitype:multitype:1.2'
}
```

## 使用

#### Step 1. 创建一个 class __implements__ `ItemContent`，它将是你的数据类型或 `Java bean`，示例：


```java
public class TextItemContent implements ItemContent, Savable {

    @NonNull public String text;

    public TextItemContent(@NonNull String text) {
        this.text = text;
    }

    public TextItemContent(@NonNull byte[] data) {
        init(data);
    }

    @Override public void init(@NonNull byte[] data) {
        String json = new String(data);
        this.text = new Gson().fromJson(json, TextItemContent.class).text;
    }

    @NonNull @Override public byte[] toBytes() {
        return new Gson().toJson(this).getBytes();
    }
}
```

#### Step 2. 创建一个 class 继承  `ItemViewProvider<C extends ItemContent, V extends ViewHolder>`，示例：


```java
public class TextItemViewProvider
    extends ItemViewProvider<TextItemContent, TextItemViewProvider.TextHolder> {

    static class TextHolder extends RecyclerView.ViewHolder {
        @NonNull final TextView text;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }


    @NonNull @Override
    protected TextHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_text, parent, false);
        TextHolder holder = new TextHolder(root);
        return holder;
    }


    @Override
    protected void onBindViewHolder(
        @NonNull TextHolder holder, @NonNull TextItemContent content, @NonNull TypeItem typeItem) {
        holder.text.setText("hello: " + content.text);
    }
}
```

#### Step 3. 好了，你不必再创建新的类文件了，只要往你 `Application` 中进行注册类型，同时在 `Activity` 中加入 `RecyclerView` 和 `List<TypeItem>` 即可，示例：


```java
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        MultiTypePool.register(TextItemContent.class, new TextItemViewProvider());
        MultiTypePool.register(ImageItemContent.class, new ImageItemViewProvider());
        MultiTypePool.register(RichItemContent.class, new RichItemViewProvider());
    }
}
```

```java
public class MainActivity extends AppCompatActivity {

    private TypeItemFactory factory;
    private RecyclerView recyclerView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        factory = new TypeItemFactory.Builder().build();
        TypeItem textItem = factory.newItem(new TextItemContent("world"));
        TypeItem imageItem = factory.newItem(new ImageItemContent(R.mipmap.ic_launcher));
        TypeItem richItem = factory.newItem(new RichItemContent("小艾大人赛高", R.mipmap.avatar));

        List<TypeItem> typeItems = new ArrayList<>(80);
        for (int i = 0; i < 20; i++) {
            typeItems.add(textItem);
            typeItems.add(imageItem);
            typeItems.add(richItem);
        }

        recyclerView.setAdapter(new MultiTypeAdapter(typeItems));
    }
}
```

**大功告成！**

你可以阅读源码项目中的 `sample` 模块获得更多信息和示例，当完整的示例代码运行起来，它是这样子的：

![](http://ww2.sinaimg.cn/large/86e2ff85gw1f6hj52rqg1j207i0dcq3c.jpg)

## Q&A (English Version later)

**Q: 为什么使用静态或者全局类型池？(Why we need static and single TypePool?)**

A: 我不反对局部或临时类型池的设计，你可以 fork 这个项目自行实现，它们对于内存更加友好（但也只是微小优势而已），但在我看来，全局类型池在多方面更好：
- 它能够**显式**连接 Type 和它的 Item View，能够在同一地方统一 register，这将避免分散，带来很好的直观性和可管理性；
- 一个应用不会有超级大量的类型定义，类型 class 和 provider 对象都是非常轻薄的对象，直接静态存于内存，并不会导致内存泄漏和大的内存占用问题，几乎可以忽略；
- 至于要不要支持 optional 的局部类型池参数，我也是不喜欢支持的，前面说了，这是没必要的，而且若是可选（optional）也会使用户疑惑：“到底要还是不要？”

因此我喜欢和坚持使用全局静态类型池，它不会带来什么问题，而且好处诸多，有人给我提交了使用反射的方法来自动获取类型连接，为了避免性能话题，我不喜欢反射，而且将类型连接变得复杂和不可见性未必是好事。我一直坚持的原则是：写简单的代码，写可读的代码，实现复杂的需求（你们看我的代码是不是感觉很自然而然而且可读性十分好？）

## 题外话

这个类库成品看来是挺精巧的，或者说轻巧，但一个人从无到有把它设计和创造出来，还是费了很多思考和多次推翻重构，其中有些点看起来可能自然而然，但是它在开发过程中可能都是一个小坎，如果没有找到合适的结构或设计，整体可能就不能搭建起来，使用也可能没那么简单和灵活。所以，要是有人感兴趣，之后可以分享一下开发过程中遇到的问题和思考，还是很有意思的 : )
