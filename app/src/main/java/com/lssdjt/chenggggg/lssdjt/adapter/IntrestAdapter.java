package com.lssdjt.chenggggg.lssdjt.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;
import com.lssdjt.chenggggg.lssdjt.dataengine.JokeDataFactory;
import com.lssdjt.chenggggg.lssdjt.domain.TextJokeBean;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class IntrestAdapter extends PagerAdapter {

    private String[] mTabs = new String[]{"段子","趣图","动图"};
    private Context mContext;
    private RecyclerView mRecyclerView;
    public static JokeDataFactory mJokeDataFatory;


    public IntrestAdapter(Context context) {
        this.mContext = context;
        if (mJokeDataFatory == null) {
            mJokeDataFatory = new JokeDataFactory();
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
        switch (position) {
            case 0:
                //选中再加载数据
                TextJokeBean mData = mJokeDataFatory.getDataFromWeb();
                if(mData != null){
                    view = getTextJokeView(mData);
                    container.addView(view);
                    return view;
                }else{
                    return null;
                }

            case 1:

            case 2:



        }

        return view;
    }



    public View getTextJokeView(TextJokeBean bean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.textjoke_layout,null);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_layout);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new TextJokeRecyclerAdapter(mContext,bean));
        return view;
    }


}
