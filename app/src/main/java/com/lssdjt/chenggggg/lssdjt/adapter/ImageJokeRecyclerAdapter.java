package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.domain.ImageJokeBean;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/8/27.
 */
public class ImageJokeRecyclerAdapter extends RecyclerView.Adapter<ImageJokeRecyclerAdapter.mImageJokeViewHolder> {

    private Context mContext;
    private ArrayList<ImageJokeBean.ImageJokeItem> mDataList;

    public ImageJokeRecyclerAdapter(Context context, ArrayList<ImageJokeBean.ImageJokeItem> mImageJoketData) {
        this.mContext = context;
        this.mDataList = mImageJoketData;
    }

    @Override
    public mImageJokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_imagejoke, parent, false);
        mImageJokeViewHolder holder = new mImageJokeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mImageJokeViewHolder holder, int position) {
        holder.mTextView.setText(mDataList.get(position).title);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class mImageJokeViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        public mImageJokeViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_joke_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_joke_image);
        }
    }
}
