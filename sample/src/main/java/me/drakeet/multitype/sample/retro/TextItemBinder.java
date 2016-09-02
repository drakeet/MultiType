package me.drakeet.multitype.sample.retro;

import me.drakeet.multitype.sample.R;
import tellh.com.library_retro.ItemViewBinder;
import tellh.com.library_retro.RecyclerViewHolder;

/**
 * Created by tlh on 2016/8/5.
 */
public class TextItemBinder extends ItemViewBinder<TextItemBinder.TextItemBean> {
    public TextItemBinder(TextItemBean data) {
        super(data);
    }

    @Override
    protected void onBindView(RecyclerViewHolder holder, int pos) {
        holder.setText(R.id.text, data.text);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_text;
    }

    public static class TextItemBean {
        String text;

        public TextItemBean(String text) {
            this.text = text;
        }
    }
}
