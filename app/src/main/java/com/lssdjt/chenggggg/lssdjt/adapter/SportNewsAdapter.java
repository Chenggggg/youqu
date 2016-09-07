package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.domain.SportNewsBean;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/9/1.
 */
public class SportNewsAdapter extends RecyclerView.Adapter<SportNewsAdapter.mViewHolder> {

    private Context mContext;
    private ArrayList<SportNewsBean.SportNews> mNewsList;

    public SportNewsAdapter(Context context, ArrayList<SportNewsBean.SportNews> newsList) {
        this.mContext = context;
        this.mNewsList = newsList;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sport_news, parent, false);
        mViewHolder holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Glide.with(mContext).load(mNewsList.get(position).picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mImageView);
        holder.title.setText(mNewsList.get(position).title);
        holder.desc.setText(mNewsList.get(position).ctime + "    " + mNewsList.get(position).description);
    }

    @Override
    public int getItemCount() {
        if(mNewsList != null){
            return mNewsList.size();
        }
        return 0;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView title;
        TextView desc;

        public mViewHolder(View itemView) {
            super(itemView);
            desc = (TextView) itemView.findViewById(R.id.tv_content);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
