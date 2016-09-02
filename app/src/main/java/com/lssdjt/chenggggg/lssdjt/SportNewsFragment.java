package com.lssdjt.chenggggg.lssdjt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lssdjt.chenggggg.lssdjt.adapter.SportNewsAdapter;
import com.lssdjt.chenggggg.lssdjt.domain.SportNewsBean;
import com.lssdjt.chenggggg.lssdjt.dataengine.SportNewsDataFactory;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/9/1.
 */
public class SportNewsFragment extends android.support.v4.app.Fragment {

    private View mInflate;
    private XRecyclerView mXRecylerView;
    private final SportNewsDataFactory mFatory;
    private static final String TAG = "SportNewsFragment";
    private ArrayList<SportNewsBean.SportNews> mNewsList;
    private SportNewsBean moreData;
    private SportNewsAdapter mNewsAdapter;

    public SportNewsFragment() {
        mFatory = new SportNewsDataFactory(getContext());
        mNewsList = new ArrayList<SportNewsBean.SportNews>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.sport_news_layout, null, false);
        container.addView(mInflate);
        initView();
        initData();
        return mInflate;
    }

    private void initView() {
        mXRecylerView = (XRecyclerView) mInflate.findViewById(R.id.xrv_sport);
        LinearLayoutManager mImageManager = new LinearLayoutManager(getContext());
        mImageManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecylerView.setLayoutManager(mImageManager);

        mXRecylerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                moreData = mFatory.getNewestData();
                if (moreData == null || moreData.showapi_res_body.newslist.get(0).ctime.equals(mNewsList.get(0).ctime)) {
                    Toast.makeText(getContext(), "已经是最新的的数据", Toast.LENGTH_SHORT).show();
                    mXRecylerView.refreshComplete();
                } else {
                    ArrayList<SportNewsBean.SportNews> moreDataList = moreData.showapi_res_body.newslist;
                    mNewsList.addAll(0, moreDataList);
                    mNewsAdapter.notifyDataSetChanged();
                    mXRecylerView.refreshComplete();
                }
            }

            @Override
            public void onLoadMore() {
                moreData = mFatory.GetMoreData();
                if (moreData == null) {
                    Toast.makeText(getContext(), "没有更多的数据", Toast.LENGTH_SHORT).show();
                    mXRecylerView.setLoadingMoreEnabled(false);
                } else {
                    ArrayList<SportNewsBean.SportNews> moreDataList = moreData.showapi_res_body.newslist;
                    mNewsList.addAll(mNewsList.size(), moreDataList);
                    mNewsAdapter.notifyDataSetChanged();
                    mXRecylerView.loadMoreComplete();
                }
            }
        });


    }

    private void initData() {

        SportNewsBean mData = mFatory.getSportDataFromWeb();
        if (mData != null) {
            mNewsList = mData.showapi_res_body.newslist;
            Log.d(TAG, "initData: mData" + mNewsList);
        }


        mNewsAdapter = new SportNewsAdapter(getContext(), mNewsList);
        mXRecylerView.setAdapter(mNewsAdapter);
    }


}
