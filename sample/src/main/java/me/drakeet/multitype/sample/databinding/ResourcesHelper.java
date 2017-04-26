package me.drakeet.multitype.sample.databinding;

import android.graphics.drawable.Drawable;

import me.drakeet.multitype.sample.App;

/**
 * Created by zhenyu on 2017/4/26.
 */

public class ResourcesHelper {

    public static Drawable getDrawable(int resId) {
        if (resId <= 0) {
            return null;
        }
        return App.getContext().getResources().getDrawable(resId);
    }
}
