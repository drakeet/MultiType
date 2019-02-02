package me.drakeet.multitype.sample.communication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.sample.R
import me.drakeet.multitype.sample.normal.TextItem

/**
 * @author drakeet
 */
class TextItemWithOutsizeDataViewBinder(var aValueFromOutside: String) : ItemViewBinder<TextItem, TextItemWithOutsizeDataViewBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_text, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: TextItem) {
    holder.setData(item)
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textView: TextView = itemView.findViewById(R.id.text)
    private lateinit var value: String

    init {
      textView.setOnClickListener { v ->
        Toast.makeText(
          v.context,
          "item's value: $value, aValueFromOutside: $aValueFromOutside", Toast.LENGTH_SHORT
        ).show()
      }
    }

    fun setData(textItem: TextItem) {
      textView.text = textItem.text
      this.value = textItem.text
    }
  }
}
