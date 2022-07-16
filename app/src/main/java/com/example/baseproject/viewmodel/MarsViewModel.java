package com.example.baseproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseproject.network.Api;
import com.example.baseproject.model.Mars;
import com.example.baseproject.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarsViewModel extends ViewModel {
    private static final String TAG = "MarsViewModel";
    private MutableLiveData<List<Mars>> mListMarsLiveData;
    private List<Mars> mListMars;

    public MarsViewModel() {
        mListMarsLiveData = new MutableLiveData<>();
//        initLiveData();
    }

    public MutableLiveData<List<Mars>> getMarsListObserver() {
        return mListMarsLiveData;

    }

    public void initLiveData(String type) {
        Api api = RetroInstance.getRetrofit()
                .create(Api.class);
        Call<List<Mars>> call = api.getMars(type);
        new Thread(new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback<List<Mars>>() {
                    @Override
                    public void onResponse(Call<List<Mars>> call, Response<List<Mars>> response) {
                        Log.d(TAG, "onResponse: " + response.body().size());
                        mListMarsLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Mars>> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                        mListMarsLiveData.postValue(null);
                    }
                });
            }
        }).start();
    }

}
