# MultiType Releases

### Version 4.0.0-alpha1 - Feb 06, 2019

#### Features

- Add a new reified `MultiTypeAdapter#register(ItemViewBinder)`
- Add a new class `Type` for `TypePool` to hold data
- Add `withKotlinClassLinker` for `OneToManyEndpoint`
- Add `ItemViewBinder#adapterItems` to get or set the items of the associated `MultiTypeAdapter`
- Change `MultiTypeAdapter#items` from `List<*>` to `List<Any>`

#### Breaking Changes

- Change all protected methods of `ItemViewBinder` to public (#245)
- Change the `payloads` parameter of `ItemViewBinder#onBindViewHolder(holder, item, payloads)` to be of `List<Any>` type
- Change the `clazz` parameter of `MultiTypeAdapter#register(...)` from `Class<? extends T>` to `Class<T>`
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
