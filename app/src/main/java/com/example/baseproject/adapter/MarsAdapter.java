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
        this.context=context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHowder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mars, parent, false);
        return new ViewHowder(view);
//        ItemMarsBinding mItemMarsBinding = ItemMarsBinding.inflate(inflater, parent, false);
//        return new ViewHowder(mItemMarsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHowder holder, int position) {
        Mars mars = mListMars.get(position);
        if (mars == null) {
            return;
        }
//        holder.mItemMarsBinding.setMars(mars);
//        holder.mItemMarsBinding.executePendingBindings();
//        holder.imgItem.setImageResource(mars.getImg_src());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClickMarsItem(mars);
            }
        });
//        holder.mItemMarsBinding.itemRoot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                itemClickListener.onClickMarsItem(mars);
//            }
//        });
//        holder.bind(mMars);
//        holder.imgItem.setImageResource(mars.getImg_src());
        Uri uri = Uri.parse(mars.getImg_src()).buildUpon().scheme("https").build();
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .into(holder.imgItem);


    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: MarsAdapter"+mListMars);
        return this.mListMars.size();

    }

    public class ViewHowder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        LinearLayout linearLayout;
        public ViewHowder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgViewItem);
            linearLayout = itemView.findViewById(R.id.imgViewItem);


        }
    }


//    public class ViewHowder extends RecyclerView.ViewHolder {
//        //        private ImageView imgMars;
//        ItemMarsBinding mItemMarsBinding;
//
//        public ViewHowder(@NonNull ItemMarsBinding mItemMarsBinding) {
//            super(mItemMarsBinding.getRoot());
//            this.mItemMarsBinding = mItemMarsBinding;
//
//
////            imgMars = itemView.findViewById(R.id.imgViewItem);
//        }
//
//        public void bind(Mars mars) {
////            imgMars.setImageResource(mMars.getImg_src());
//        }
//    }

}
