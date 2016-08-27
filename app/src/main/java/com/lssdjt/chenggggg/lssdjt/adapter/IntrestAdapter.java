package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.dataengine.JokeDataFactory;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.TextJokeBean;
import com.lssdjt.chenggggg.lssdjt.utils.CacheUtils;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class IntrestAdapter extends PagerAdapter {

    private String[] mTabs = new String[]{"段子", "趣图", "动图"};
    private Context mContext;
    private RecyclerView mRecyclerView;
    public static JokeDataFactory mJokeDataFatory;
    private SwipeRefreshLayout mRefreshLayout;
    private ArrayList<TextJokeBean.TextBean> mTextJokeList;
    private TextJokeRecyclerAdapter mTextJokeAdapter;
    private TextJokeBean moreData;
    private FloatingActionButton mFAB;


    public IntrestAdapter(Context context) {
        this.mContext = context;
        if (mJokeDataFatory == null) {
            mJokeDataFatory = new JokeDataFactory(context);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        String cache = null;
        cache = CacheUtils.getCache(Constant.TEXT_JOKE_URL, mContext);
        switch (position) {
            case 0:
                //选中再加载数据
                //先从网络加载
                TextJokeBean mNetData = mJokeDataFatory.getDataFromWeb();
                if (mNetData != null) {
                    mTextJokeList = mNetData.showapi_res_body.contentlist;
                    view = getTextJokeView(mTextJokeList);
                    container.addView(view);
                    return view;
                    //从缓存中加载
                } else if (cache != null) {
                    TextJokeBean mCacheData = mJokeDataFatory.proccessJokeJsonData(cache);
                    mTextJokeList = mCacheData.showapi_res_body.contentlist;
                    view = getTextJokeView(mTextJokeList);
                    container.addView(view);
                    return view;
                } else {
                    //没有数据返回默认界面
                    View defaultView = LayoutInflater.from(mContext).inflate(R.layout.default_layout, null, false);
                    container.addView(defaultView);
                    return defaultView;
                }

            case 1:

            case 2:


        }

        return view;
    }

    public View getTextJokeView(ArrayList<TextJokeBean.TextBean> list) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.textjoke_layout, null);
        mTextJokeAdapter = new TextJokeRecyclerAdapter(mContext, list);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TextJokeBean refreshData = mJokeDataFatory.getMoreData();
                if (refreshData != null) {
                    //更新数据
                    mTextJokeList.addAll(0, refreshData.showapi_res_body.contentlist);
                    mTextJokeAdapter.notifyDataSetChanged();
                }
                mRefreshLayout.setRefreshing(false);
            }
        });


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_layout);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mTextJokeAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItem = manager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mTextJokeAdapter.getItemCount()) {
                    //上拉加载更多
                    moreData = mJokeDataFatory.getMoreData();
                    if (moreData != null) {

                        mTextJokeList.addAll(mTextJokeAdapter.getItemCount() - 1, moreData.showapi_res_body.contentlist);
                        mTextJokeAdapter.notifyDataSetChanged();
                    } else {
                        Snackbar.make(mRecyclerView, "已经没有更多数据", Snackbar.LENGTH_SHORT).show();
                    }
                }

            }


        });


        return view;
    }


}
