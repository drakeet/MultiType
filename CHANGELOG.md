# MultiType Releases

### Version 4.2.0 - Dec 24, 2019

- Add `ViewDelegate` that does not require `ViewHolder` (#296)

### Version 4.1.1 - Dec 11, 2019

- Fix binary incompatibility about `ItemViewBinder` (#294)

### Version 4.1.0 - Dec 9, 2019

- Add `ItemViewDelegate` & `onCreateViewHolder(context, _)` (#292)
- Kotlin 1.3.61

### Version 4.0.0 - Sep 7, 2019

- Migrate to com.drakeet group (#267)
- Kotlin 1.3.50
- AndroidX Annotation 1.1.0
- Fix duplicate library_release.kotlin_module (#284)

### Version 4.0.0-alpha3 - Mar 16, 2019

- Change `TypePool` to `Types`
- Rename `ArrayTypePool` to `MutableTypes`
- Open `MultiTypeAdapter` & `MutableTypes`

### Version 4.0.0-alpha2 - Feb 06, 2019

This migrates MultiType to Kotlin ([#253](https://github.com/drakeet/MultiType/pull/253))

#### Features

- Add a new reified `MultiTypeAdapter#register(ItemViewBinder)`
- Add a new class `Type` for `TypePool` to hold data
- Add `withKotlinClassLinker` for `OneToManyEndpoint`
- Add `ItemViewBinder#adapterItems` to get or set the items of the associated `MultiTypeAdapter`
- Change `MultiTypeAdapter#items` from `List<*>` to `List<Any>`

#### Breaking Changes

- Change all protected methods of `ItemViewBinder` to public ([#245](https://github.com/drakeet/MultiType/issues/245))
- Change the `payloads` parameter of `ItemViewBinder#onBindViewHolder(holder, item, payloads)` to be of `List<Any>` type
- Change the `clazz` parameter of `MultiTypeAdapter#register(...)` from `Class<? extends T>` to `Class<T>`
- ~~Change `MultiTypeAdapter` to `final`~~
- Remove `Items` class
- Remove `Preconditions` class
- Rename `MultiTypePool` to `ArrayTypePool`
- Rename `KClassLinker` to `KotlinClassLinker`
- Rename `ClassLinker` to `JavaClassLinker`
- Rename `OneToManyEndpoint#withKClassLinker(...)` method to `withKotlinClassLinker`
- Rename `OneToManyEndpoint#withClassLinker(...)` method to `withJavaClassLinker`

### Version 3.5.0 - Sep 22, 2018

- Migrate to AndroidX
- Rename Kotlin extension artifact to -ktx

### Version 3.4.4 - Feb 17, 2018

Some minor changes: 
- Disable `BuildConfig` generation
- Change some dependencies of libraries from `implementation` to `api`
- Improve the tip of `BinderNotFoundException`
