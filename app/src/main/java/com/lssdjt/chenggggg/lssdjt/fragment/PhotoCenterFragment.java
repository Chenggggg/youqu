package com.lssdjt.chenggggg.lssdjt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class PhotoCenterFragment extends android.support.v4.app.Fragment {

    private View mInflate;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private ArrayList<Fragment> mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.photo_center_layout,null);
        return mInflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initFragment();
        initAdapter();
    }

    private void initAdapter() {
        mViewPager.setAdapter(new mViewPagerAdapter(getFragmentManager()));
        mTab.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mViewPager = (ViewPager)mInflate.findViewById(R.id.vp_photocenter);
        mTab = (TabLayout)mInflate.findViewById(R.id.tab_photocenter);
    }

    private void initFragment(){

        mFragments = new ArrayList<Fragment>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(new newsDetailFragment(i));
        }

    }

    private class mViewPagerAdapter extends FragmentPagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        public mViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
