package com.drakeet.multitype

import android.util.Log
import androidx.annotation.CheckResult
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface MultiTypeDelegate {

  val types: Types

  fun <T> register(type: Type<T>) {
    types.register(type)
  }

  /**
   * Registers a type class and its item view delegate. If you have registered the class,
   * it will override the original delegate(s). Note that the method is non-thread-safe
   * so that you should not use it in concurrent operation.
   *
   * Note that the method should not be called after
   * [RecyclerView.setAdapter], or you have to call the setAdapter
   * again.
   *
   * @param clazz the class of a item
   * @param delegate the item view delegate
   * @param T the item data type
   * */
  fun <T> register(clazz: Class<T>, delegate: ItemViewDelegate<T, *>) {
    unregisterAllTypesIfNeeded(clazz)
    register(Type(clazz, delegate, DefaultLinker()))
  }

  /**
   * Registers a type class to multiple item view delegates. If you have registered the
   * class, it will override the original delegate(s). Note that the method is non-thread-safe
   * so that you should not use it in concurrent operation.
   *
   * Note that the method should not be called after
   * [RecyclerView.setAdapter], or you have to call the setAdapter again.
   *
   * @param clazz the class of a item
   * @param <T> the item data type
   * @return [OneToManyFlow] for setting the delegates
   * @see [register]
   */
  @CheckResult
  fun <T> register(clazz: Class<T>): OneToManyFlow<T> {
    unregisterAllTypesIfNeeded(clazz)
    return OneToManyBuilder(this, clazz)
  }

  /**
   * Registers all of the contents in the specified [Types]. If you have registered a
   * class, it will override the original delegate(s). Note that the method is non-thread-safe
   * so that you should not use it in concurrent operation.
   *
   * Note that the method should not be called after
   * [RecyclerView.setAdapter], or you have to call the setAdapter
   * again.
   *
   * @param types a [Types] containing contents to be added to this adapter inner [Types]
   * @see [register]
   * @see [register]
   */
  fun registerAll(types: Types) {
    val size = types.size
    for (i in 0 until size) {
      val type = types.getType<Any>(i)
      unregisterAllTypesIfNeeded(type.clazz)
      register(type)
    }
  }

  @Throws(DelegateNotFoundException::class)
  fun indexInTypesOf(position: Int, item: Any): Int {
    val index = types.firstIndexOf(item.javaClass)
    if (index != -1) {
      val linker = types.getType<Any>(index).linker
      return index + linker.index(position, item)
    }
    throw DelegateNotFoundException(item.javaClass)
  }

  fun getOutDelegate(itemViewType: Int): ItemViewDelegate<Any, *> {
    return types.getType<Any>(itemViewType).delegate
  }

  fun getOutDelegateByViewHolder(holder: ViewHolder): ItemViewDelegate<Any, ViewHolder> {
    @Suppress("UNCHECKED_CAST")
    return getOutDelegate(holder.itemViewType) as ItemViewDelegate<Any, ViewHolder>
  }
}

private fun MultiTypeDelegate.unregisterAllTypesIfNeeded(clazz: Class<*>) {
  if (types.unregister(clazz)) {
    Log.w(TAG, "The type ${clazz.simpleName} you originally registered is now overwritten.")
  }
}

private const val TAG = "MultiTypeAdapter"