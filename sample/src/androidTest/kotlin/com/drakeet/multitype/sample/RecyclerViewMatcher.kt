/*
 * Copyright (c) 2016-present. Drakeet Xu
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

package com.drakeet.multitype.sample

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * @author dannyroa
 * @author Drakeet Xu
 */
class RecyclerViewMatcher private constructor(private val recyclerViewId: Int) {

  fun atPosition(position: Int): Matcher<View> {
    return atPositionOnView(position, -1)
  }

  fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      var resources: Resources? = null
      var childView: View? = null

      override fun describeTo(description: Description) {
        var idDescription = Integer.toString(recyclerViewId)
        if (this.resources != null) {
          try {
            idDescription = this.resources!!.getResourceName(recyclerViewId)
          } catch (var4: Resources.NotFoundException) {
            idDescription = String.format(
              "%s (resource name not found)",
              recyclerViewId
            )
          }

        }

        description.appendText("with id: $idDescription")
      }

      public override fun matchesSafely(view: View): Boolean {

        this.resources = view.resources

        if (childView == null) {
          val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
          if (recyclerView != null && recyclerView.id == recyclerViewId) {
            childView = recyclerView.findViewHolderForAdapterPosition(
              position
            )!!.itemView
          } else {
            return false
          }
        }

        if (targetViewId == -1) {
          return view === childView
        } else {
          val targetView = childView!!.findViewById<View>(targetViewId)
          return view === targetView
        }
      }
    }
  }

  companion object {
    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
      return RecyclerViewMatcher(recyclerViewId)
    }
  }
}
