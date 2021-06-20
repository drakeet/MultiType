package com.drakeet.multitype

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * This is a simple [ItemViewDelegate] that does not require to declare and provide a [RecyclerView.ViewHolder].
 * @since v4.2.0
 * @author Drakeet Xu
 */
abstract class ViewDelegate<T, V : View> : ItemViewDelegate<T, ViewDelegate.Holder<V>>() {

  protected val View.holder: Holder<V> get() {
    return holder(this) ?: throw IllegalAccessException("The view holder property can only be called after onCreateView()!")
  }

  protected val View.layoutPosition: Int get() = holder.layoutPosition

  protected val View.absoluteAdapterPosition: Int get() = holder.absoluteAdapterPosition

  protected val View.bindingAdapterPosition: Int get() = holder.bindingAdapterPosition

  abstract fun onCreateView(context: Context): V

  abstract fun onBindView(view: V, item: T)

  // Override this function if you need a parent ViewGroup
  open fun onCreateView(context: Context, parent: ViewGroup): V {
    return onCreateView(context)
  }

  // Override this function if you need a ViewHolder or positions
  open fun onBindView(holder: Holder<V>, view: V, item: T) {
    view.setTag(R.id.tagViewHolder, holder)
    onBindView(view, item)
  }

  protected fun getRecyclerLayoutParams(view: View): RecyclerView.LayoutParams {
    return (view.layoutParams as RecyclerView.LayoutParams)
  }

  private fun holder(view: View): Holder<V>? {
    @Suppress("UNCHECKED_CAST")
    return view.getTag(R.id.tagViewHolder) as? Holder<V>
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): Holder<V> {
    return Holder(onCreateView(context, parent))
  }

  override fun onBindViewHolder(holder: Holder<V>, item: T) = onBindView(holder, holder.view, item)

  class Holder<V : View>(val view: V) : RecyclerView.ViewHolder(view)
}
