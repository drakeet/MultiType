package com.drakeet.multitype.sample.normal

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.dp

/**
 * @author Drakeet Xu
 */
class RichView(context: Context) : LinearLayout(context) {

  val imageView = AppCompatImageView(context).apply {
    addView(this, LayoutParams(72.dp, 72.dp))
  }

  val textView = AppCompatTextView(context).apply {
    setTextColor(Color.BLACK)
    addView(this, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
  }

  init {
    orientation = VERTICAL
    gravity = Gravity.CENTER
    setPadding(16.dp, 16.dp, 16.dp, 16.dp)
  }
}
