package me.drakeet.multitype.sample.retro;

import android.support.annotation.NonNull;

import me.drakeet.multitype.sample.R;
import tellh.com.library_retro.ItemViewBinder;
import tellh.com.library_retro.RecyclerViewHolder;

/**
 * Created by tlh on 2016/8/5.
 */
public class RichItemBinder extends ItemViewBinder<RichItemBinder.RichItemBean>{
    public RichItemBinder(RichItemBean data) {
        super(data);
    }

    @Override
    protected void onBindView(RecyclerViewHolder holder, int pos) {
        holder.setText(R.id.text,data.text)
                .getImageView(R.id.image).setImageResource(data.imageResId);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_rich;
    }

    public static class RichItemBean{
        @NonNull
        String text;
        int imageResId;
        public RichItemBean(@NonNull String text, int imageResId) {
            this.text = text;
            this.imageResId = imageResId;
        }
    }

}
