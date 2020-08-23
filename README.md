# MultiType
Easier and more flexible to create multiple types for Android RecyclerView.

[![Build Status](https://travis-ci.org/drakeet/MultiType.svg?branch=3.x)](https://travis-ci.org/drakeet/MultiType)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/drakeet/MultiType/blob/master/LICENSE)
![maven-central](https://img.shields.io/maven-central/v/com.drakeet.multitype/multitype.svg)
![jetbrains-plugin](https://img.shields.io/jetbrains/plugin/v/9202-a8translate.svg)

Previously, when we need to develop a complex RecyclerView / ListView, it is difficult and
troublesome work. We should override the `getItemViewType()` of `RecyclerView.Adapter` , add some types, and create some `ViewHolder`s relating to those types. Once we need to add a new item type, we have to go to the original adapter file and modify some old codes carefully, 
and these adapter classes will get more complicated.

Nowadays, I created a new intuitive and flexible way to easily create complex RecyclerViews,
**with the MultiType library, we could insert a new item type without changing any old adapter codes and make them more readable.**

## Getting started

In your `build.gradle`:

_MultiType has been rebuilt based on [AndroidX](https://developer.android.com/jetpack/androidx/). If you are still using the android support library, please use `me.drakeet.multitype:multitype:3.4.4` and `me.drakeet.multitype:multitype-kotlin:3.4.4`._

_In addition, since 4.0.0 we have migrated to fully build with Kotlin. If you don't want to use Kotlin, you can use the last stable version `me.drakeet.multitype:multitype:3.5.0` and see [3.x](https://github.com/drakeet/MultiType/tree/3.x)._

```groovy
dependencies {
  implementation 'com.drakeet.multitype:multitype:4.2.0'
}
```

## Usage

#### Step 1. Create a Kotlin `class` or `data class`, for example:

```kotlin
data class Foo(
  val value: String
)
```

#### Step 2. Create a class extends `ItemViewDelegate<T, VH : ViewHolder>`, for example:

```kotlin
class FooViewDelegate: ItemViewDelegate<Foo, FooViewDelegate.ViewHolder>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    // If you want a LayoutInflater parameter instead of a Context,
    // you can use ItemViewBinder as the parent of this class.
    return ViewHolder(FooView(context))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Foo) {
    holder.fooView.text = item.value
    Log.d("ItemViewDelegate API", "position: ${getPosition(holder)}")
    Log.d("ItemViewDelegate API", "items: $adapterItems")
    Log.d("ItemViewDelegate API", "adapter: $adapter")
    Log.d("More", "Context: ${holder.itemView.context}")
  }

  class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    val fooView: TextView = itemView.findViewById(R.id.foo)
  }
}
```

##### Or if you are using a custom View instead of XML layout, you can use `ViewDelegate`:

> The `ViewDelegate` is a simple `ItemViewDelegate` that does not require to declare and provide a `RecyclerView.ViewHolder`.

```kotlin
class FooViewDelegate : ViewDelegate<Foo, FooView>() {

  override fun onCreateView(context: Context): FooView {
    return FooView(context).apply { layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT) }
  }

  override fun onBindView(view: FooView, item: Foo) {
    view.imageView.setImageResource(item.imageResId)
    view.textView.text = item.text
    // Or bind the data in the FooView by calling view.setFoo(item)
  }
}
```

(See [`RichViewDelegate`](sample/src/main/kotlin/com/drakeet/multitype/sample/normal/RichViewDelegate.kt) & [`RichView`](sample/src/main/kotlin/com/drakeet/multitype/sample/normal/RichView.kt) examples for more details)

#### Step 3. `register` your types and setup your `RecyclerView`, for example:

```kotlin
class SampleActivity : AppCompatActivity() {

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    val recyclerView = findViewById<RecyclerView>(R.id.list)

    adapter.register(TextItemViewDelegate())
    adapter.register(ImageItemViewDelegate())
    adapter.register(RichItemViewDelegate())
    recyclerView.adapter = adapter

    val textItem = TextItem("world")
    val imageItem = ImageItem(R.mipmap.ic_launcher)
    val richItem = RichItem("小艾大人赛高", R.drawable.img_11)

    for (i in 0..19) {
      items.add(textItem)
      items.add(imageItem)
      items.add(richItem)
    }
    adapter.items = items
    adapter.notifyDataSetChanged()
  }
}
```

**That's all, you're good to go!**

## Advanced usage 

**One to many**:  

```kotlin
adapter.register(Data::class).to(
  DataType1ViewDelegate(),
  DataType2ViewDelegate()
).withKotlinClassLinker { _, data ->
  when (data.type) {
    Data.TYPE_2 -> DataType2ViewDelegate::class
    else -> DataType1ViewDelegate::class
  }
}
```

See `OneDataToManyActivity`, `OneToManyFlow` and `OneToManyEndpoint` for more details.

**More methods that you can override from [ItemViewDelegate](library/src/main/kotlin/me/drakeet/multitype/ItemViewDelegate.kt)**:

```kotlin
open fun onBindViewHolder(holder: VH, item: T, payloads: List<Any>)
open fun getItemId(item: T): Long
open fun onViewRecycled(holder: VH)
open fun onFailedToRecycleView(holder: VH): Boolean
open fun onViewAttachedToWindow(holder: VH)
open fun onViewDetachedFromWindow(holder: VH)
```

## Android Studio Plugin

- **[drakeet/MultiTypeTemplates](https://github.com/drakeet/MultiTypeTemplates)**

 An intellij idea plugin for Android to generate `MultiType` `Item` and `ItemViewDelegate` easily.

<img src="http://ww4.sinaimg.cn/large/86e2ff85gw1f8yj0sejd6j21340ben1s.jpg" width=640/>

## Screenshots

Pages created with MultiType:

<img src="https://i.loli.net/2018/06/05/5b16aa7d5968b.png" width=216/><img src="https://i.loli.net/2018/06/05/5b16aa7d83aec.png" width=216/><img src="https://i.loli.net/2018/06/05/5b16aa7fbbc87.png" width=216/>

<img src="https://i.loli.net/2018/06/05/5b16aa83af0f7.png" width=216/><img src="https://i.loli.net/2018/06/05/5b16aa843e488.png" width=216/><img src="https://i.loli.net/2018/06/05/5b16aa86c52e7.png" width=216/>

<img src="https://i.loli.net/2017/10/20/59e95e4c78f5b.png" width=270/> <img src="https://i.loli.net/2017/10/20/59e95e4c8243c.png" width=270/>

License
-------

    Copyright (c) 2016-present. Drakeet Xu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
