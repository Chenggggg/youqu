package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.domain.TextJokeBean;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class TextJokeRecyclerAdapter extends RecyclerView.Adapter<TextJokeRecyclerAdapter.mViewHolder> {

    Context mContext;
    public ArrayList<TextJokeBean.TextBean> mBean;

    public TextJokeRecyclerAdapter(Context context, ArrayList<TextJokeBean.TextBean> bean) {
        this.mContext = context;
        this.mBean = bean;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_textjoke,parent,false);
        mViewHolder holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        holder.mText.setText(mBean.get(position).text);
        holder.mTitle.setText(mBean.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        ExpandableTextView mText;
        public mViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_joke_title);
            mText = (ExpandableTextView) itemView.findViewById(R.id.tv_joke_content);

        }
    }
}
