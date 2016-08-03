package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.ItemType;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author drakeet
 */
public class RichItemViewProvider extends ItemViewProvider<RichItemContent> {

    private static class ViewHolder extends ItemViewProvider.ViewHolder {
        @NonNull final TextView text;
        @NonNull final ImageView image;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


    @NonNull @Override protected View onCreateView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_rich, parent, false);
        ViewHolder holder = new ViewHolder(root);
        root.setTag(holder);
        return root;
    }


    @Override
    protected void onBindView(
        @NonNull View view, @NonNull RichItemContent richContent, @NonNull ItemType itemType) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(richContent.text);
        holder.image.setImageResource(richContent.imageResId);
    }
}
