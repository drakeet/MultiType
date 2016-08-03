package me.drakeet.multitype;

import android.support.annotation.NonNull;

/**
 * @author drakeet
 */
public abstract class ItemContent implements Savable {

    protected ItemContent() {
    }


    public ItemContent(@NonNull byte[] data) {
    }


    @NonNull @Override public byte[] toBytes() {
        throw new IllegalAccessError("You should override the toBytes before you call it");
    }
}
