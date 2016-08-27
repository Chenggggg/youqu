package com.lssdjt.chenggggg.lssdjt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.adapter.IntrestAdapter;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class IntrestingFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTab;
    private View mView;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        return mView;
    }

    private void initView() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.intrested_main_layout,null,false);
        mViewPager = (ViewPager) mView.findViewById(R.id.vp_main);
        mTab = (TabLayout) mView.findViewById(R.id.tab_intrested);
        mTab.setTabGravity(TabLayout.GRAVITY_FILL);
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(new IntrestAdapter(getContext()));
        mTab.setupWithViewPager(mViewPager);

        mRefreshLayout = (SwipeRefreshLayout)mView.findViewById(R.id.swipe_refresh_layout);
    }

}
