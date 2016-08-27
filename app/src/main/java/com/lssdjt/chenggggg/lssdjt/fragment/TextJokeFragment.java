package com.lssdjt.chenggggg.lssdjt.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lssdjt.chenggggg.lssdjt.R;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class TextJokeFragment extends Fragment{

    private RecyclerView mRecyclerView;

    public TextJokeFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.textjoke_layout,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_layout);
        initRecyclerView();
        return mRecyclerView;
    }

    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.setAdapter(new TextJokeRecyclerAdapter(getContext()));
    }


}
