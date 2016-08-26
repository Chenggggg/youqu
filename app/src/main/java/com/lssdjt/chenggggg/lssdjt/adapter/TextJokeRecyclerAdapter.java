package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_textjoke,parent,false);
        mViewHolder holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        holder.mTextView.setText("今天真特么冷啊！！~！~@！@#");
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public mViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_joke_title);
        }
    }
}
