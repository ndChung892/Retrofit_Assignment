package com.example.baseproject.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;


import com.example.baseproject.adapter.MarsAdapter;
import com.example.baseproject.ItemClickListener;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.model.Mars;
import com.example.baseproject.R;
import com.example.baseproject.viewmodel.MarsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RecyclerView rcvMars;
    private Button btnFilterAll, btnFilterRent, btnFilterBuy;
    private List<Mars> lMars;
    private MainActivity mainActivity = (MainActivity) this;
    private MarsViewModel mMarsViewModel;
    MarsAdapter mMarsAdapter;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        rcvMars = findViewById(R.id.rcvMars);
        mBinding.btnFilterAll.findViewById(R.id.btnFilterAll).setOnClickListener(this);
        mBinding.btnFilterBuy.findViewById(R.id.btnFilterBuy).setOnClickListener(this);
        mBinding.btnFilterRent.findViewById(R.id.btnFilterRent).setOnClickListener(this);
        setAdapter(type);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFilterAll:
                type = "";
                break;
            case R.id.btnFilterRent:
                type = "rent";
                break;
            case R.id.btnFilterBuy:
                type = "buy";
                break;
            default:
                break;
        }
        setAdapter(type);
    }

    private void setAdapter(String type) {
        LinearLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this, 2);
        rcvMars.setLayoutManager(layoutManager);
        mMarsViewModel = ViewModelProviders.of(this).get(MarsViewModel.class);
        mMarsViewModel.getMarsListObserver().observe(this, new Observer<List<Mars>>() {
            @Override
            public void onChanged(List<Mars> mListMars) {
                Log.d(TAG, "onChanged: " + mListMars);
                mMarsAdapter = new MarsAdapter(mainActivity, mListMars, new ItemClickListener() {
                    @Override
                    public void onClickMarsItem(Mars mars) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("MarsDetail", mars);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                rcvMars.setAdapter(mMarsAdapter);
            }
        });
        mMarsViewModel.initLiveData(type);
    }
}



