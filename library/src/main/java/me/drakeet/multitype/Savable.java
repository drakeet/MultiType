package me.drakeet.multitype;

import android.support.annotation.NonNull;

/**
 * @author drakeet
 */
public interface Savable {

    @NonNull byte[] toBytes();
}
