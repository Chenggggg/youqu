package com.lssdjt.chenggggg.lssdjt.fragment;

import android.content.Context;
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
import com.lssdjt.chenggggg.lssdjt.dataengine.NewsCenterDataFactory;
import com.lssdjt.chenggggg.lssdjt.domain.NewsChanelBean;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class NewsCenterFragment extends Fragment {

    private Context mContext;
    private View mInflate;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private ArrayList<Fragment> mFragments;
    private NewsCenterDataFactory mFactory;
    private static final String TAG = "NewsCenterFragment";
    private NewsChanelBean mChanelData;

    public NewsCenterFragment(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.newscenter_layout, null);
        initData();

        initView();
        initFragment(mContext);
        initAdapter();
        return mInflate;
    }


    private void initAdapter() {
        mViewPager.setAdapter(new mViewPagerAdapter(getFragmentManager()));
        mTab.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mViewPager = (ViewPager) mInflate.findViewById(R.id.vp_photocenter);
        mTab = (TabLayout) mInflate.findViewById(R.id.tab_photocenter);
        mTab.setTabGravity(TabLayout.GRAVITY_FILL);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment(Context context) {

        mFragments = new ArrayList<Fragment>();
        if(mChanelData != null){
            for (int i = 0; i < 13; i++) {  //13
                mFragments.add(new newsDetailFragment(mContext, mChanelData.getShowapi_res_body().getChannelList().get(i).getChannelId()));
            }
        }
    }

    private void initData() {
        mFactory = NewsCenterDataFactory.getNewsCenterDataFactoryInstance();
        mChanelData = mFactory.getNewsChannelData();
    }

    private class mViewPagerAdapter extends FragmentPagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return mChanelData.getShowapi_res_body().getChannelList().get(position).getName();
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

        public void getNewsData() {

        }
    }

}
