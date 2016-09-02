package me.drakeet.multitype.sample.retro;

import me.drakeet.multitype.sample.R;
import tellh.com.library_retro.ItemViewBinder;
import tellh.com.library_retro.RecyclerViewHolder;

/**
 * Created by tlh on 2016/8/5.
 */
public class ImageItemBinder extends ItemViewBinder<ImageItemBinder.ImageItemBean> {

    public ImageItemBinder(ImageItemBean data) {
        super(data);
    }

    @Override
    protected void onBindView(RecyclerViewHolder holder, int pos) {
        holder.getImageView(R.id.image).setImageResource(data.resId);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_image;
    }

    public static class ImageItemBean{
        int resId;

        public ImageItemBean(int resId) {
            this.resId = resId;
        }
    }
}
