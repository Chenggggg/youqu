package com.lssdjt.chenggggg.lssdjt.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.dataengine.NewsCenterDataFactory;
import com.lssdjt.chenggggg.lssdjt.domain.NewsBean;

import java.util.List;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class newsDetailFragment extends android.support.v4.app.Fragment {


    private XRecyclerView mRecycleView;
    private String mChanelId;
    private NewsCenterDataFactory mFatocry;
    private NewsBean mData;
    private List<NewsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mContent;
    private mAdapter adapter;
    private View mInflate;
    private Context mContext;

    public newsDetailFragment(Context context, String channelid) {
        this.mContext = context;
        this.mChanelId = channelid;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.news_detail_layout, null);
        initView();
        initData();
        initAdapter();

        return mInflate;
    }

    public void initData() {
        mFatocry = NewsCenterDataFactory.getNewsCenterDataFactoryInstance();
        mData = mFatocry.getNewsData(mChanelId);
        if (mData != null) {
            mContent = mData.getShowapi_res_body().getPagebean().getContentlist();
        }
    }

    public void initView() {
        mRecycleView = (XRecyclerView) mInflate.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
    }

    public void initAdapter() {
        adapter = new mAdapter();
        mRecycleView.setAdapter(adapter);
    }

    private class mAdapter extends RecyclerView.Adapter<mAdapter.mHolder> {

        @Override
        public mAdapter.mHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_newsdetail, null);
            return new mHolder(view);
        }

        @Override
        public void onBindViewHolder(mAdapter.mHolder holder, int position) {
            holder.title.setText(mContent.get(position).getTitle());
            holder.time.setText(mContent.get(position).getPubDate());
            if (mContent.get(position).getImageurls().size() != 0) {
                Glide.with(mContext).load(mContent.get(position).getImageurls().get(0).getUrl()).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mImageView);
            }
        }

        @Override
        public int getItemCount() {
            return mContent.size();
        }

        public class mHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;
            TextView title, time;

            public mHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.item_time);
                title = (TextView) itemView.findViewById(R.id.item_title);
                mImageView = (ImageView) itemView.findViewById(R.id.left_image);
            }
        }
    }
}
