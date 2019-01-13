package me.drakeet.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author drakeet
 */
open class TestItemViewBinder : ItemViewBinder<TestItem, TestItemViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    throw NotImplementedError()
  }

  override fun onBindViewHolder(holder: ViewHolder, item: TestItem) {}

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
