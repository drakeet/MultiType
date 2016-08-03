package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static java.util.Objects.requireNonNull;

/**
 * @author drakeet
 */
public class ItemTypeFactory {

    @Nullable private final String extra;


    private ItemTypeFactory(@Nullable String extra) {
        this.extra = extra;
    }


    public ItemType newType(@NonNull ItemContent content) {
        return new ItemType(content, extra);
    }


    public static class Builder {

        @Nullable private String extra;


        /* optional */
        public Builder setExtra(@Nullable String extra) {
            this.extra = requireNonNull(extra);
            return this;
        }


        public ItemTypeFactory build() {
            return new ItemTypeFactory(extra);
        }
    }
}
