package com.example.baseproject.network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    private static final String TAG = "RetroInstance";
    public static String BASE_URL = "https://mars.udacity.com";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        Log.i(TAG, "getRetrofit: "+retrofit);
        return retrofit;
    }
}
