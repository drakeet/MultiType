# MultiType
An Android library to create multiple item types list views easily and flexibly.

[![Build Status](https://travis-ci.org/drakeet/MultiType.svg?branch=3.x)](https://travis-ci.org/drakeet/MultiType)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/drakeet/MultiType/blob/master/LICENSE)
![maven-central](https://img.shields.io/maven-central/v/me.drakeet.multitype/multitype.svg)
![jetbrains-plugin](https://img.shields.io/jetbrains/plugin/v/9202-a8translate.svg)

English Version | [《Android 复杂的列表视图新写法 · 详解篇》](https://github.com/drakeet/Effective-MultiType/blob/master/README.md)

Previously, when we need to develop a complex RecyclerView / ListView, it is a difficult and 
troublesome work. We should override the `getItemViewType()` of `RecyclerView.Adapter` , add some 
types, and create some `ViewHolder`s relating to those types. 

Once we need to add a new item type, we have to go to the original adapter file and modify some old codes carefully, 
and these adapter classes will get more complicated.

Today, I created a new intuitive and flexible way to easily create complex RecyclerViews, 
**with the MultiType library, we could insert a new item type without changing any old adapter codes 
and make them more readable.**

## Getting started

In your `build.gradle`:

```groovy
dependencies {
    implementation 'me.drakeet.multitype:multitype:3.4.4'
}
```

_Note: MultiType does not support RecyclerView below version 23.0.0._

Optional: Extension for Kotlin:

```groovy
implementation 'me.drakeet.multitype:multitype-kotlin:3.4.4'
```

## Usage

#### Step 1. Create a class, It would be your `data model`/`Java bean`, for example:

```java
public class TextItem {

    public final @NonNull String text;

    public TextItem(@NonNull String text) {
        this.text = text;
    }
}
```

#### Step 2. Create a class extends `ItemViewBinder<T, VH extends ViewHolder>`, for example:

```java
public class TextItemViewBinder extends ItemViewBinder<TextItem, TextItemViewBinder.TextHolder> {

    static class TextHolder extends RecyclerView.ViewHolder {
    
        private @NonNull final TextView text;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    @NonNull @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_text, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem textItem) {
        holder.text.setText("hello: " + textItem.text);
        Log.d("demo", "position: " + getPosition(holder));
        Log.d("demo", "adapter: " + getAdapter());
    }
}
```

#### Step 3. Just `register` your types and setup your `RecyclerView` and `List<Object>` in your `Activity`, for example:

```java
public class SampleActivity extends AppCompatActivity {

    private MultiTypeAdapter adapter;
    private Items items;

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        adapter = new MultiTypeAdapter();
        adapter.register(TextItem.class, new TextItemViewBinder());
        adapter.register(ImageItem.class, new ImageItemViewBinder());
        adapter.register(RichItem.class, new RichItemViewBinder());
        recyclerView.setAdapter(adapter);

        TextItem textItem = new TextItem("world");
        ImageItem imageItem = new ImageItem(R.mipmap.ic_launcher);
        RichItem richItem = new RichItem("小艾大人赛高", R.mipmap.avatar);

        items = new Items();
        for (int i = 0; i < 20; i++) {
            items.add(textItem);
            items.add(imageItem);
            items.add(richItem);
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
```

**That's all, you're good to go!**



## Advanced usage 

**One to many**:  

```java
adapter.register(Data.class).to(
    new DataType1ViewBinder(),
    new DataType2ViewBinder()
).withLinker((position, data) ->
    data.type == Data.TYPE_2 ? 1 : 0
);
```

```java
adapter.register(Data.class).to(
    new DataType1ViewBinder(),
    new DataType2ViewBinder()
).withClassLinker((position, data) -> {
    if (data.type == Data.TYPE_2) {
        return DataType2ViewBinder.class;
    } else {
        return DataType1ViewBinder.class;
    }
});
```

**More methods that you can override from [ItemViewBinder](library/src/main/java/me/drakeet/multitype/ItemViewBinder.java)**: 

```java
protected long getItemId(@NonNull T item)
protected void onViewRecycled(@NonNull VH holder)
protected boolean onFailedToRecycleView(@NonNull VH holder)
protected void onViewAttachedToWindow(@NonNull VH holder)
protected void onViewDetachedFromWindow(@NonNull VH holder)
```

**Kotlin**:  

MultiTypeAdapter  
- Added `register(binder: ItemViewBinder<T, *>)`
- Added `register(clazz: KClass<out T>, binder: ItemViewBinder<T, *>)`
- Added `register(clazz: KClass<out T>): OneToManyFlow<T>`

TypePool  
- Added `register(clazz: KClass<out T>, binder: ItemViewBinder<T, *>, linker: Linker<T>)`
- Added `unregister(clazz: KClass<out T>)`
- Added `firstIndexOf(clazz: KClass<out T>)`

OneToManyEndpoint  
- Added `withKClassLinker(classLinker: KClassLinker<T>)`
- Added `withKClassLinker(classLinker: (position: Int, t: T) -> KClass<out ItemViewBinder<T, *>>)`

## Wiki

<a href="https://github.com/drakeet/MultiType/wiki/Android-MultiType-3.0"><img src="http://ww4.sinaimg.cn/large/86e2ff85gw1f9iswm098sj21kw064mzk.jpg" width=640/></a>

## Android Studio Plugin

- **[drakeet/MultiTypeTemplates](https://github.com/drakeet/MultiTypeTemplates)**

 An intellij idea plugin for Android to generate `MultiType` `Item` and `ItemViewBinder` easily.

<img src="http://ww4.sinaimg.cn/large/86e2ff85gw1f8yj0sejd6j21340ben1s.jpg" width=640/>

## Change Log

https://github.com/drakeet/MultiType/releases

## Sample screenshots

You could check the `sample` module for more details and after running it will look like:

<img src="art/screenshot-normal.png" width=216/> <img src="art/screenshot-bilibili.png" width=216/> <img src="art/screenshot-multigrid.png" width=216/>

<img src="http://ww3.sinaimg.cn/large/86e2ff85jw1f9a7tek74lj21401z414s.jpg" width=216/> <img src="http://ww1.sinaimg.cn/mw1024/86e2ff85jw1f9a7z4yqlkj21401z4n8r.jpg" width=216/>


License
-------

    Copyright 2016-2018 drakeet.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
