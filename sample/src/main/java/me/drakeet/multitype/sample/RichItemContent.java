package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import me.drakeet.multitype.ItemContent;

/**
 * @author drakeet
 */
public class RichItemContent extends ItemContent {

    @NonNull public String text;
    public int imageResId;


    public RichItemContent(@NonNull String text, int imageResId) {
        this.text = text;
        this.imageResId = imageResId;
    }


    @NonNull @Override public byte[] toBytes() {
        // I do not want to save the content so that I just pass the method
        return new byte[0];
    }
}
