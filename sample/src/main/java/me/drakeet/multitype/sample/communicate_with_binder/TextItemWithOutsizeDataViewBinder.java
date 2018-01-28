package me.drakeet.multitype.sample.communicate_with_binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.normal.TextItem;

/**
 * @author drakeet
 */
public class TextItemWithOutsizeDataViewBinder
    extends ItemViewBinder<TextItem, TextItemWithOutsizeDataViewBinder.ViewHolder> {

  public String aValueFromOutside;


  public TextItemWithOutsizeDataViewBinder() {}


  public TextItemWithOutsizeDataViewBinder(String aValueFromOutside) {
    this.aValueFromOutside = aValueFromOutside;
  }


  @Override
  protected @NonNull ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_text, parent, false);
    return new ViewHolder(root);
  }


  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TextItem textItem) {
    holder.setData(textItem);
  }


  class ViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    String value;


    ViewHolder(View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.text);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Toast.makeText(v.getContext(),
              "item's value: " + value + ", aValueFromOutside: " + aValueFromOutside,
              Toast.LENGTH_SHORT).show();
        }
      });
    }


    void setData(@NonNull final TextItem textItem) {
      textView.setText(textItem.text);
      this.value = textItem.text;
    }
  }
}
