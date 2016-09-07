package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.NewsBean;
import com.lssdjt.chenggggg.lssdjt.domain.NewsChanelBean;
import com.show.api.ShowApiRequest;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class NewsCenterDataFactory {

    private final Gson mGson;
    private static Context mContext;
    private String result_chanel;
    private NewsChanelBean mChanelData;
    private static NewsCenterDataFactory mNewsCenterDataFactory;
    private String channelId;
    private String result_data;
    private NewsBean mNewsData;
    private static final String TAG = "NewsCenterDataFactory";

    private NewsCenterDataFactory(Context context) {
        this.mContext = context;
        mGson = new Gson();
    }

    public static NewsCenterDataFactory getNewsCenterDataFactoryInstance() {
        if (mNewsCenterDataFactory == null) {
            mNewsCenterDataFactory = new NewsCenterDataFactory(mContext);
            return mNewsCenterDataFactory;
        }
        return mNewsCenterDataFactory;
    }


    public NewsChanelBean getNewsChannelData() {
        try {
            result_chanel = new getNewsChannelTask().execute().get();
            mChanelData = mGson.fromJson(result_chanel, NewsChanelBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mChanelData;
    }

    public NewsBean getNewsData(String id) {
        try {
            result_data = new getNewsDataTask().execute(id).get();
            mNewsData = mGson.fromJson(result_data, NewsBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewsData;
    }

    private class getNewsChannelTask extends AsyncTask<Void, Void, String> {
        /**
         * 获取新闻频道数据，返回处理后的json
         *
         * @param strings
         * @return
         */
        @Override
        protected String doInBackground(Void... strings) {
            return new ShowApiRequest(Constant.NEWS_CENTER_CHANEL_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .post();
        }
    }


    private class getNewsDataTask extends AsyncTask<String, Void, String> {
        /**
         * 获取新闻频道数据，返回处理后的json
         *
         * @param strings
         * @return
         */
        @Override
        protected String doInBackground(String... strings) {
            channelId = strings[0];
            Log.d(TAG, "doInBackground: chanelID = " + channelId);
            String res = new ShowApiRequest(Constant.NEWS_CENTER_NEWS_DETAIL_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("channelId", channelId)
                    .addTextPara("channelName", "")
                    .addTextPara("title", "")
                    .addTextPara("page", "1")
                    .addTextPara("needContent", "0")
                    .addTextPara("needHtml", "0")
                    .addTextPara("needAllList", "0")
                    .addTextPara("maxResult", "20")
                    .post();
            return res;
        }
    }


}
