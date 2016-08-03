package me.drakeet.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * @author drakeet
 */
public class ItemTypesAdapter extends RecyclerView.Adapter<ItemTypesAdapter.ViewHolder> {

    private final List<ItemType> itemTypes;


    public ItemTypesAdapter(@NonNull List<ItemType> itemTypes) {this.itemTypes = itemTypes;}


    @Override public int getItemViewType(int position) {
        ItemContent content = itemTypes.get(position).content;
        int index = ItemTypePool.getContents().indexOf(content.getClass());
        return index;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        View root = ItemTypePool.getProviderByIndex(indexViewType).onCreateView(parent);
        ViewHolder holder = new ViewHolder(root);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        ItemType itemType = itemTypes.get(position);
        ItemTypePool.getProviderByIndex(type).onBindView(holder.itemView, itemType);
    }


    @Override public int getItemCount() {
        return itemTypes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}