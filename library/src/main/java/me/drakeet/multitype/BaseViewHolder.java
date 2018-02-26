package me.drakeet.multitype;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author YoungTr
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

  private final SparseArray<View> mViews;

  public BaseViewHolder(View itemView) {
    super(itemView);

    mViews = new SparseArray<>();
  }


  @SuppressWarnings("unchecked")
  public <T extends View> T getView(int resId) {
    View view = mViews.get(resId);
    //如果该View没有缓存过，则查找View并缓存
    if (view == null) {
      view = itemView.findViewById(resId);
      mViews.put(resId, view);
    }
    if (view == null) {
      throw new NullPointerException("View is null");
    }
    return (T) view;
  }

  /**
   * Will set the text of a TextView.
   *
   * @param viewId The view id.
   * @param value  The text to put in the text view.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setText(@IdRes int viewId, CharSequence value) {
    TextView view = getView(viewId);
    view.setText(value);
    return this;
  }

  public BaseViewHolder setText(@IdRes int viewId, @StringRes int strId) {
    TextView view = getView(viewId);
    view.setText(strId);
    return this;
  }

  /**
   * Will set the image of an ImageView from a resource id.
   *
   * @param viewId     The view id.
   * @param imageResId The image resource id.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
    ImageView view = getView(viewId);
    view.setImageResource(imageResId);
    return this;
  }

  /**
   * Will set background color of a view.
   *
   * @param viewId The view id.
   * @param color  A color, not a resource id.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
    View view = getView(viewId);
    view.setBackgroundColor(color);
    return this;
  }

  /**
   * Will set background of a view.
   *
   * @param viewId        The view id.
   * @param backgroundRes A resource to use as a background.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
    View view = getView(viewId);
    view.setBackgroundResource(backgroundRes);
    return this;
  }

  /**
   * Will set text color of a TextView.
   *
   * @param viewId    The view id.
   * @param textColor The text color (not a resource id).
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
    TextView view = getView(viewId);
    view.setTextColor(textColor);
    return this;
  }


  /**
   * Will set the image of an ImageView from a drawable.
   *
   * @param viewId   The view id.
   * @param drawable The image drawable.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
    ImageView view = getView(viewId);
    view.setImageDrawable(drawable);
    return this;
  }

  /**
   * Add an action to set the image of an image view. Can be called multiple times.
   */
  public BaseViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
    ImageView view = getView(viewId);
    view.setImageBitmap(bitmap);
    return this;
  }


  /**
   * Set a view visibility to VISIBLE (true) or GONE (false).
   *
   * @param viewId  The view id.
   * @param visible True for VISIBLE, false for GONE.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setGone(@IdRes int viewId, boolean visible) {
    View view = getView(viewId);
    view.setVisibility(visible ? View.VISIBLE : View.GONE);
    return this;
  }

  /**
   * Set a view visibility to VISIBLE (true) or INVISIBLE (false).
   *
   * @param viewId  The view id.
   * @param visible True for VISIBLE, false for INVISIBLE.
   * @return The BaseViewHolder for chaining.
   */
  public BaseViewHolder setVisible(@IdRes int viewId, boolean visible) {
    View view = getView(viewId);
    view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    return this;
  }

}
