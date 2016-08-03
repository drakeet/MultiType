package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import me.drakeet.multitype.ItemContent;

/**
 * @author drakeet
 */
public class ImageItemContent extends ItemContent {

    public final int resId;


    public ImageItemContent(int resId) {this.resId = resId;}


    public ImageItemContent(@NonNull byte[] data) {
        resId = -1;
        // pass
    }


    @NonNull @Override public byte[] toBytes() {
        return new byte[0];
    }
}
