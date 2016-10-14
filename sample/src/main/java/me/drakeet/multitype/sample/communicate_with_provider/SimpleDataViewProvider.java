package me.drakeet.multitype.sample.communicate_with_provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class SimpleDataViewProvider
    extends ItemViewProvider<SimpleData, SimpleDataViewProvider.ViewHolder> {

    public String aValueFromOutside;


    public SimpleDataViewProvider() {}


    public SimpleDataViewProvider(String aValueFromOutside) {
        this.aValueFromOutside = aValueFromOutside;
    }


    @NonNull @Override protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_text, parent, false);
        return new ViewHolder(root);
    }


    @Override protected void onBindViewHolder(
        @NonNull ViewHolder holder, @NonNull SimpleData simpleData) {
        holder.setData(simpleData);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        String value;


        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(v.getContext(),
                        "item's value: " + value + ", aValueFromOutside: " + aValueFromOutside,
                        Toast.LENGTH_SHORT).show();
                }
            });
        }


        void setData(@NonNull final SimpleData simpleData) {
            textView.setText(simpleData.value);
            this.value = simpleData.value;
        }
    }
}
