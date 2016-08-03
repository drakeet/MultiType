package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author drakeet
 */
public class ItemType {

    @NonNull public ItemContent content;
    @Nullable public String extra;


    public ItemType() {
    }


    public ItemType(@NonNull ItemContent content, String extra) {
        this.extra = extra;
        this.content = content;
    }


    @Override public String toString() {
        return "ItemType {" +
            "content=" + content +
            ", extra='" + extra + '\'' +
            '}';
    }
}
