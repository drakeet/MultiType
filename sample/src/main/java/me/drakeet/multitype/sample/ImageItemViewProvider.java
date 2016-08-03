package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import me.drakeet.multitype.ItemType;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author drakeet
 */
public class ImageItemViewProvider extends ItemViewProvider<ImageItemContent> {

    private class ViewHolder extends ItemViewProvider.ViewHolder {
        @NonNull private final ImageView image;


        ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


    @NonNull @Override protected View onCreateView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_image, parent, false);
        ViewHolder holder = new ViewHolder(root);
        root.setTag(holder);
        return root;
    }


    @Override
    protected void onBindView(
        @NonNull View view,
        @NonNull ImageItemContent imageContent, @NonNull ItemType itemType) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.image.setImageResource(imageContent.resId);
    }
}
