package com.example.baseproject.network;

import com.example.baseproject.model.Mars;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://mars.udacity.com/";
//    Gson gson = new GsonBuilder()
//            .create();
//
//    Api api = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(Api.class);
    @GET("realestate")
    Call<List<Mars>> getMars();

}
