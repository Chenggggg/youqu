package com.lssdjt.chenggggg.lssdjt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public SportNewsFragment() {
        mFatory = new SportNewsDataFactory();
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
    }

    private void initData() {

        SportNewsBean mData = mFatory.getSportDataFromWeb();
        if (mData != null){
            mNewsList = mData.showapi_res_body.newslist;
            Log.d(TAG, "initData: mData" + mNewsList);
        }


        SportNewsAdapter mSportNewsAdapter = new SportNewsAdapter(getContext(),mNewsList);
        mXRecylerView.setAdapter(mSportNewsAdapter);
    }


}
