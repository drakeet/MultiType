package me.drakeet.multitype;

import android.support.annotation.NonNull;
import java.util.ArrayList;

/**
 * @author drakeet
 */
public final class ItemTypePool {

    private static ArrayList<Class<? extends ItemContent>> contents = new ArrayList<>();
    private static ArrayList<ItemViewProvider> providers = new ArrayList<>();


    public static void register(
        @NonNull Class<? extends ItemContent> itemContent, @NonNull ItemViewProvider provider) {
        contents.add(itemContent);
        providers.add(provider);
    }


    @NonNull public static ArrayList<Class<? extends ItemContent>> getContents() {
        return contents;
    }


    @NonNull public static ArrayList<ItemViewProvider> getProviders() {
        return providers;
    }


    @NonNull public static ItemViewProvider getProviderByIndex(int index) {
        return providers.get(index);
    }

}
