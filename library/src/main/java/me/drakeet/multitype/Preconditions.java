package me.drakeet.multitype;

import android.support.annotation.NonNull;

/**
 * @author drakeet
 */
@SuppressWarnings("WeakerAccess")
public final class Preconditions {

    @SuppressWarnings("ConstantConditions")
    public static @NonNull <T> T checkNotNull(@NonNull final T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return object;
    }


    private Preconditions() {}
}
