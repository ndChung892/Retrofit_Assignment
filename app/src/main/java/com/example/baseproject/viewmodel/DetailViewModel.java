package com.example.baseproject.viewmodel;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

public class DetailViewModel extends ViewModel {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        Uri uri =Uri.parse(imageUrl).buildUpon().scheme("https").build();
        Glide.with(view.getContext())
                .load(uri)
                .centerCrop()
                .into(view);
    }

}
