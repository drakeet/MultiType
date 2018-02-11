/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.drakeet.multitype

import android.support.annotation.CheckResult
import kotlin.reflect.KClass

/**
 * @author drakeet
 */
fun <T : Any> MultiTypeAdapter.register(clazz: KClass<out T>, binder: ItemViewBinder<T, *>) {
  register(clazz.java, binder)
}


inline fun <reified T : Any> MultiTypeAdapter.register(binder: ItemViewBinder<T, *>) {
  register(T::class.java, binder)
}


@CheckResult
fun <T : Any> MultiTypeAdapter.register(clazz: KClass<out T>): OneToManyFlow<T> {
  return register(clazz.java)
}


fun <T : Any> TypePool.register(clazz: KClass<out T>, binder: ItemViewBinder<T, *>, linker: Linker<T>) {
  register(clazz.java, binder, linker)
}


fun <T : Any> TypePool.unregister(clazz: KClass<out T>): Boolean {
  return unregister(clazz.java)
}


fun <T : Any> TypePool.firstIndexOf(clazz: KClass<out T>): Int {
  return firstIndexOf(clazz.java)
}


fun <T> OneToManyEndpoint<T>.withKClassLinker(classLinker: KClassLinker<T>) {
  withClassLinker { position, t -> classLinker.index(position, t).java }
}


fun <T> OneToManyEndpoint<T>.withKClassLinker(classLinker: (position: Int, t: T) -> KClass<out ItemViewBinder<T, *>>) {
  withClassLinker { position, t -> classLinker(position, t).java }
}
