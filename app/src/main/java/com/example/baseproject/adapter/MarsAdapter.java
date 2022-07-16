package com.example.baseproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baseproject.ItemClickListener;
import com.example.baseproject.model.Mars;
import com.example.baseproject.R;

import java.util.List;

public class MarsAdapter extends RecyclerView.Adapter<MarsAdapter.ViewHowder> {
    private static final String TAG = "MarsAdapter";
    private List<Mars> mListMars;
    Context context;
    ItemClickListener itemClickListener;


    public MarsAdapter(Context context, List<Mars> mListMars, ItemClickListener itemClickListener) {
        this.mListMars = mListMars;
        this.context = context;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHowder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mars, parent, false);
        return new ViewHowder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHowder holder, int position) {
        Mars mars = mListMars.get(position);
        if (mars == null) {
            return;
        }

        holder.imgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClickMarsItem(mars);
            }
        });
        Uri uri = Uri.parse(mars.getImg_src()).buildUpon().scheme("https").build();
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .into(holder.imgItem);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: MarsAdapter" + mListMars);
        return this.mListMars.size();

    }

    public class ViewHowder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        LinearLayout linearLayout;

        public ViewHowder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgViewItem);
        }
    }


}
