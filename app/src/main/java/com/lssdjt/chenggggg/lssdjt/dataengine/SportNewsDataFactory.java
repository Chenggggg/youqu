package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.SportNewsBean;
import com.lssdjt.chenggggg.lssdjt.utils.CacheUtils;
import com.show.api.ShowApiRequest;

/**
 * Created by Chenggggg on 2016/9/1.
 */
public class SportNewsDataFactory {

    private int Page = 1;
    private static final String TAG = "SportNewsDataFactory";
    private final Gson mGson;
    private SportNewsBean mData;
    private SportNewsBean mMoreSportData;
    private Context mContext;
    private SportNewsBean mNewestData;


    public SportNewsDataFactory(Context context) {
        this.mContext = context;
        mGson = new Gson();
    }

    private class GetSportDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            Page++;
            String currentPage = String.valueOf(Page);
            final String res = new ShowApiRequest(Constant.SPORT_NEWS_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("num", "20")
                    .addTextPara("page", currentPage)
                    .post();
            Log.d(TAG, "doInBackground: res = " + res + "currentPage" + currentPage);
            return res;
        }
    }


    public SportNewsBean getSportDataFromWeb() {
        try {
            String result =  new GetSportDataAsyncTask().execute().get();
            mData = processJsonData(result);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return mData;
    }

    public SportNewsBean processJsonData(String json){
        SportNewsBean mData =  mGson.fromJson(json,SportNewsBean.class);
        return mData;
    }

    public SportNewsBean getNewestData(){
        try {
            String result = new GetNewestSportDataAsyncTask().execute().get();
            mNewestData = processJsonData(result);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return mNewestData;
    }

    public SportNewsBean GetMoreData(){
        try {
            String result = new GetSportDataAsyncTask().execute().get();
            mMoreSportData = processJsonData(result);
            if (mMoreSportData == null || mMoreSportData.showapi_res_body.newslist.get(2).ctime.equals(mData.showapi_res_body.newslist.get(2).ctime)) {
                Log.d(TAG, "GetMoreData: 没有更多数据");
                return null;
            }else {
                //写入缓存
                CacheUtils.setCache(Constant.SPORT_NEWS_URL, result, mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mMoreSportData;
    }

    private class GetNewestSportDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            final String res = new ShowApiRequest(Constant.SPORT_NEWS_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("num", "20")
                    .addTextPara("page", "2")
                    .post();
            return res;
        }
    }

}
