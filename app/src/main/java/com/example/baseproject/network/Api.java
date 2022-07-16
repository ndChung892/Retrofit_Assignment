package com.example.baseproject.network;

import com.example.baseproject.model.Mars;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("realestate")
    Call<List<Mars>> getMars(@Query("filter") String type);


}
