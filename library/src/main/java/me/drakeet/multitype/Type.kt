package me.drakeet.multitype

import androidx.recyclerview.widget.RecyclerView

/**
 * @author drakeet
 */
data class Type<T>(
  val clazz: Class<T>,
  val binder: ItemViewBinder<T, out RecyclerView.ViewHolder>,
  val linker: Linker<T>
)
