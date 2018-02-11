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

package me.drakeet.multitype.kotlin

import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.OneToManyFlow
import kotlin.reflect.KClass

/**
 * @author drakeet
 */
fun <T : Any> MultiTypeAdapter.register(clazz: KClass<out T>, binder: ItemViewBinder<T, *>) {
  register(clazz.java, binder)
}


fun <T : Any> MultiTypeAdapter.register(clazz: KClass<out T>): OneToManyFlow<T> {
  return register(clazz.java)
}

