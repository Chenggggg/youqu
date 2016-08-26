package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class TextJokeRecyclerAdapter extends RecyclerView.Adapter<TextJokeRecyclerAdapter.mViewHolder> {

    Context mContext;

    public TextJokeRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_textjoke,null,false);
        mViewHolder holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class mViewHolder extends RecyclerView.ViewHolder{

        public mViewHolder(View itemView) {
            super(itemView);
        }
    }
}
