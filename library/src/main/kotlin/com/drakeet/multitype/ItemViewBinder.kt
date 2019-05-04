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

package com.drakeet.multitype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/***
 * @author Drakeet Xu
 */
abstract class ItemViewBinder<T, VH : ViewHolder> {

  @Suppress("PropertyName")
  internal var _adapter: MultiTypeAdapter? = null

  /**
   * Gets the associated [MultiTypeAdapter].
   * @since v2.3.4
   */
  val adapter: MultiTypeAdapter
    get() {
      if (_adapter == null) {
        throw IllegalStateException(
          "This $this has not been attached to MultiTypeAdapter yet. " +
              "You should not call the method before registering the binder."
        )
      }
      return _adapter!!
    }

  /**
   * Gets or sets the items of the associated [MultiTypeAdapter].
   * @see MultiTypeAdapter.items
   * @since v4.0.0
   */
  var adapterItems: List<Any>
    get() = adapter.items
    set(value) {
      adapter.items = value
    }

  abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH

  /**
   * Called by MultiTypeAdapter to display the data with its view holder. This method should
   * update the contents of the [ViewHolder.itemView] to reflect the given item.
   *
   * If you need the position of an item later on (e.g. in a click listener), use
   * `ViewHolder#getAdapterPosition()` which will have the updated adapter position.
   *
   * Override `onBindViewHolder(ViewHolder, Object, List)` instead if your ItemViewBinder
   * can handle efficient partial bind.
   *
   * @param holder The ViewHolder which should be updated to represent the contents of the
   * given item in the items data set.
   * @param item The item within the MultiTypeAdapter's items data set.
   */
  abstract fun onBindViewHolder(holder: VH, item: T)

  /**
   * Called by MultiTypeAdapter to display the data with its view holder. This method should
   * update the contents of the [ViewHolder.itemView] to reflect the given item.
   *
   * If you need the position of an item later on (e.g. in a click listener), use
   * [ViewHolder.getAdapterPosition] which will have the updated adapter position.
   *
   * Partial bind vs full bind:
   *
   * The payloads parameter is a merge list from [MultiTypeAdapter.notifyItemChanged] [MultiTypeAdapter.notifyItemRangeChanged].
   * If the payloads list is not empty, the ViewHolder is currently bound to old data and
   * ItemViewBinder may run an efficient partial update using the payload info.
   * If the payload is empty, ItemViewBinder must run a full bind.
   * ItemViewBinder should not assume that the payload passed in notify methods will be
   * received by onBindViewHolder().  For example when the view is not attached to the screen,
   * the payload in notifyItemChange() will be simply dropped.
   *
   * This implementation calls the `onBindViewHolder(ViewHolder, Object)` by default.
   *
   * @param holder The ViewHolder which should be updated to represent the contents of the
   * given item in the items data set.
   * @param item The item within the MultiTypeAdapter's items data set.
   * @param payloads A non-null list of merged payloads. Can be empty list if requires full
   * update.
   * @since v2.5.0
   */
  open fun onBindViewHolder(holder: VH, item: T, payloads: List<Any>) {
    onBindViewHolder(holder, item)
  }

  /**
   * Get the adapter position of current item,
   * the internal position equals to [ViewHolder.getAdapterPosition].
   *
   * **NOTE**: Below v2.3.5 we may provide getPosition() method to get the position,
   * It exists BUG, and sometimes can not get the correct position,
   * it is recommended to immediately stop using it and use the new
   * `getPosition(ViewHolder)` instead.
   *
   * @param holder The ViewHolder to call holder.getAdapterPosition().
   * @return The adapter position.
   * @since v2.3.5. If below v2.3.5, use [ViewHolder.getAdapterPosition] instead.
   */
  fun getPosition(holder: ViewHolder): Int {
    return holder.adapterPosition
  }

  /**
   * Return the stable ID for the `item`. If [RecyclerView.Adapter.hasStableIds]
   * would return false this method should return [RecyclerView.NO_ID]. The default
   * implementation of this method returns [RecyclerView.NO_ID].
   *
   * @param item The item within the MultiTypeAdapter's items data set to query
   * @return the stable ID of the item
   * @see RecyclerView.Adapter.setHasStableIds
   * @since v3.2.0
   */
  @Suppress("UNUSED_PARAMETER")
  open fun getItemId(item: T): Long = RecyclerView.NO_ID

  /**
   * Called when a view created by this [ItemViewBinder] has been recycled.
   *
   * A view is recycled when a [LayoutManager] decides that it no longer
   * needs to be attached to its parent [RecyclerView]. This can be because it has
   * fallen out of visibility or a set of cached views represented by views still
   * attached to the parent RecyclerView. If an item view has large or expensive data
   * bound to it such as large bitmaps, this may be a good place to release those
   * resources.
   *
   * RecyclerView calls this method right before clearing ViewHolder's internal data and
   * sending it to RecycledViewPool.
   *
   * @param holder The ViewHolder for the view being recycled
   * @since v3.1.0
   */
  open fun onViewRecycled(holder: VH) {}

  /**
   * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
   * due to its transient state. Upon receiving this callback, Adapter can clear the
   * animation(s) that effect the View's transient state and return `true` so that
   * the View can be recycled. Keep in mind that the View in question is already removed from
   * the RecyclerView.
   *
   * In some cases, it is acceptable to recycle a View although it has transient state. Most
   * of the time, this is a case where the transient state will be cleared in
   * [.onBindViewHolder] call when View is rebound to a new item.
   * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
   * value of this method to decide whether the View should be recycled or not.
   *
   * Note that when all animations are created by [RecyclerView.ItemAnimator], you
   * should never receive this callback because RecyclerView keeps those Views as children
   * until their animations are complete. This callback is useful when children of the item
   * views create animations which may not be easy to implement using an [ ].
   *
   * You should *never* fix this issue by calling
   * `holder.itemView.setHasTransientState(false);` unless you've previously called
   * `holder.itemView.setHasTransientState(true);`. Each
   * `View.setHasTransientState(true)` call must be matched by a
   * `View.setHasTransientState(false)` call, otherwise, the state of the View
   * may become inconsistent. You should always prefer to end or cancel animations that are
   * triggering the transient state instead of handling it manually.
   *
   * @param holder The ViewHolder containing the View that could not be recycled due to its
   * transient state.
   * @return True if the View should be recycled, false otherwise. Note that if this method
   * returns `true`, RecyclerView *will ignore* the transient state of
   * the View and recycle it regardless. If this method returns `false`,
   * RecyclerView will check the View's transient state again before giving a final decision.
   * Default implementation returns false.
   * @since v3.1.0
   */
  open fun onFailedToRecycleView(holder: VH): Boolean {
    return false
  }

  /**
   * Called when a view created by this [ItemViewBinder] has been attached to a window.
   *
   * This can be used as a reasonable signal that the view is about to be seen
   * by the user. If the [ItemViewBinder] previously freed any resources in
   * [onViewDetachedFromWindow][.onViewDetachedFromWindow]
   * those resources should be restored here.
   *
   * @param holder Holder of the view being attached
   * @since v3.1.0
   */
  open fun onViewAttachedToWindow(holder: VH) {}

  /**
   * Called when a view created by this [ItemViewBinder] has been detached from its
   * window.
   *
   * Becoming detached from the window is not necessarily a permanent condition;
   * the consumer of an Adapter's views may choose to cache views offscreen while they
   * are not visible, attaching and detaching them as appropriate.
   *
   * @param holder Holder of the view being detached
   * @since v3.1.0
   */
  open fun onViewDetachedFromWindow(holder: VH) {}
}
