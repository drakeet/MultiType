package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author drakeet
 */
public abstract class ItemViewProvider<T extends ItemContent> {

    @NonNull protected abstract View onCreateView(@NonNull ViewGroup parent);

    protected abstract void onBindView(@NonNull View view, @NonNull T t, @NonNull ItemType itemType);


    public final void onBindView(@NonNull View view, @NonNull ItemType data) {
        this.onBindView(view, (T) data.content, data);
    }


    public static class ViewHolder {
        @NonNull final View itemView;


        public ViewHolder(@NonNull View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
    }
}
