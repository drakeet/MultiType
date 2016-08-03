package me.drakeet.multitype.sample;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import me.drakeet.multitype.ItemContent;

/**
 * @author drakeet
 */
public class TextItemContent extends ItemContent {

    @NonNull public String text;


    public TextItemContent(@NonNull String text) {
        this.text = text;
    }


    public TextItemContent(@NonNull byte[] data) {
        String json = new String(data);
        this.text = new Gson().fromJson(json, TextItemContent.class).text;
    }


    @NonNull @Override public byte[] toBytes() {
        return new Gson().toJson(this).getBytes();
    }
}