package com.example.baseproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.baseproject.R;
import com.example.baseproject.viewmodel.DetailViewModel;
import com.example.baseproject.databinding.ActivityDetailBinding;
import com.example.baseproject.model.Mars;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Mars mars = (Mars) bundle.get("MarsDetail");
        Log.i(TAG, "onCreate: "+mars);
        detailBinding.setMars(mars);
        DetailViewModel detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailBinding.setLifecycleOwner(this);

    }
}