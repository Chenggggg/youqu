package com.lssdjt.chenggggg.lssdjt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lssdjt.chenggggg.lssdjt.R;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class newsDetailFragment extends android.support.v4.app.Fragment {


    private XRecyclerView mRecycleView;
    private int type;
    public newsDetailFragment() {}

    public newsDetailFragment(int i) {
        type = i;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_detail_layout,null);
        mRecycleView = (XRecyclerView)view.findViewById(R.id.rv);

        TextView text = new TextView(getContext());
        text.setText("fragment  " + type);
        return text;
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    private class mAdapter extends RecyclerView.Adapter<mAdapter.mHolder>{

        @Override
        public mAdapter.mHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_newsdetail,null);
            mHolder holder = new mHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(mAdapter.mHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class mHolder extends RecyclerView.ViewHolder{
            ImageView mImageView;
            TextView title,time;

            public mHolder(View itemView) {
                super(itemView);
                time = (TextView)itemView.findViewById(R.id.item_time);
                title = (TextView)itemView.findViewById(R.id.item_title);
                mImageView = (ImageView)itemView.findViewById(R.id.left_image);
            }
        }
    }
}
