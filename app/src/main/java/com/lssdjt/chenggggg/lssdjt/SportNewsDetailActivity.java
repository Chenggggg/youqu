package com.lssdjt.chenggggg.lssdjt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.dataengine.SportNewsDataFactory;
import com.lssdjt.chenggggg.lssdjt.domain.SportNewsDetail;

/**
 * Created by Chenggggg on 2016/9/2.
 */
public class SportNewsDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private String mURL;
    private String mTitle;
    private WebView mWebView;
    private Bundle mBundle;
    private static final String TAG = "SportNewsDetailActivity";
    private Handler mHandler;
    private SportNewsDetail mData;
    private BitmapTypeRequest<Object> background;
    private ImageView titleBackground;
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_detail_layout);

        mBundle = getIntent().getBundleExtra("BUNDLE");
        mURL = mBundle.getString("URL");
        mTitle = mBundle.getString("TITLE");
        imageURL = mBundle.getString("IMAGEURL");

        initView();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:

                        if (imageURL != null) {
                            Glide.with(SportNewsDetailActivity.this).load(imageURL).into(titleBackground);
                        }
                        String html =  mData.showapi_res_body.html.replaceAll("\\<p\\>\\<img .*?\\>", "");
                        mWebView.loadDataWithBaseURL(mURL, html, "text/html", "UTF-8", mURL);
                        break;
                }
            }
        };

        initData();
    }

    private void initData() {

        new Thread() {
            @Override
            public void run() {
                String s = SportNewsDataFactory.getSportNewsDataFactory().getSportParseData(mURL);
                mData = getSportNewsDetail(s);
                mHandler.sendEmptyMessage(0);
            }
        }.start();

    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle(mTitle);
        mWebView = (WebView) findViewById(R.id.wv);

        titleBackground = (ImageView) findViewById(R.id.iv);
    }


    public SportNewsDetail getSportNewsDetail(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, SportNewsDetail.class);
    }
}
