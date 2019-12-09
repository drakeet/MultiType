package com.drakeet.multitype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * This is a compatible version of [ItemViewDelegate].
 * @see ItemViewDelegate
 * @author Drakeet Xu
 */
abstract class ItemViewBinder<T, VH : RecyclerView.ViewHolder> : ItemViewDelegate<T, VH>() {

  final override fun onCreateViewHolder(context: Context, parent: ViewGroup): VH {
    return onCreateViewHolder(LayoutInflater.from(context), parent)
  }

  abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH
}
