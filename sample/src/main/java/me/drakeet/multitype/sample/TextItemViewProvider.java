package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.drakeet.multitype.ItemType;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author drakeet
 */
public class TextItemViewProvider extends ItemViewProvider<TextItemContent> {

    private static class ViewHolder extends ItemViewProvider.ViewHolder {
        @NonNull final TextView text;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }


    @NonNull @Override protected View onCreateView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_text, parent, false);
        ViewHolder holder = new ViewHolder(root);
        root.setTag(holder);
        return root;
    }


    @Override
    protected void onBindView(
        @NonNull View view, @NonNull TextItemContent content, @NonNull ItemType itemType) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText("hello: " + content.text);
    }

}
