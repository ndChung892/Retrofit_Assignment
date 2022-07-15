package com.example.baseproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

import com.example.baseproject.adapter.MarsAdapter;
import com.example.baseproject.ItemClickListener;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.model.Mars;
import com.example.baseproject.R;
import com.example.baseproject.viewmodel.MarsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private RecyclerView rcvMars;
	private Button btnFilter;
	private Spinner spnType;
	private List<Mars> lMars;
	private MainActivity mainActivity= (MainActivity) this;

	private MarsViewModel mMarsViewModel;
	MarsAdapter mMarsAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);

		ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(mBinding.getRoot());
		rcvMars = findViewById(R.id.rcvMars);
		btnFilter = findViewById(R.id.btnFilter);
		spnType = findViewById(R.id.spnFilter);

		LinearLayoutManager layoutManager;
		layoutManager=new GridLayoutManager(this, 2);
		rcvMars.setLayoutManager(layoutManager);
		mMarsViewModel = ViewModelProviders.of(this).get(MarsViewModel.class);
		mMarsViewModel.getMarsListObserver().observe(this, new Observer<List<Mars>>() {
			@Override
			public void onChanged(List<Mars> mListMars) {
				Log.d(TAG, "onChanged: "+mListMars);
//				if(mListMars != null){
//					mMarsAdapter.setMarsList(mListMars);
//				}
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

		mMarsViewModel.initLiveData();

//		mMarsViewModel = new ViewModelProvider(this).get(MarsViewModel.class);
//		mMarsViewModel.getListMarsLiveData().observe(this, new Observer<List<Mars>>() {
//			@Override
//			public void onChanged(List<Mars> mListMars) {
//				mMarsAdapter= new MarsAdapter(mListMars, new ItemClickListener() {
//					@Override
//					public void onClickMarsItem(Mars mars) {
//						Log.i(TAG, "onClickMarsItem: "+mars);
////						Toast.makeText(MainActivity.this, "hahahahahaahahah", Toast.LENGTH_SHORT).show();
//						Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//						Bundle bundle = new Bundle();
//						bundle.putSerializable("MarsDetail", mars);
//						intent.putExtras(bundle);
//						startActivity(intent);
//						rcvMars.setAdapter(mMarsAdapter);
//					}
//				});
//
//			}
//		});

	}
}