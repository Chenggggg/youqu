package com.lssdjt.chenggggg.lssdjt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class NewsCenterFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newscenter_layout,null);
        return view;
    }

    private void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
