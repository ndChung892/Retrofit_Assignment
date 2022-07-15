package com.example.baseproject.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class Mars implements Serializable {
    public long price;
    public String id;
    public String type;
    public String img_src;

    public Mars() {
    }

    public Mars(long price, String id, String type, String img_src) {
        this.price = price;
        this.id = id;
        this.type = type;
        this.img_src = img_src;
    }

    public String getPrice() {
        return price+"";
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    @BindingAdapter("android:loadImage")
    public void loadImage(ImageView imageView, String imageUrl){
        Glide.with(imageView)
                .load(imageUrl)
                .into(imageView);
    }

    @Override
    public String toString() {
        return "Mars{" +
                "price=" + price +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", img_src=" + img_src +
                '}';
    }
}
