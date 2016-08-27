package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.TextJokeBean;
import com.lssdjt.chenggggg.lssdjt.utils.CacheUtils;
import com.show.api.ShowApiRequest;

/**
 * Created by Chenggggg on 2016/8/27.
 */
public class JokeDataFactory {

    private final String mDateString;
    private final Gson mGson;
    private TextJokeBean mJokeData;
    private static final String TAG = "JokeDataFactory";
    private TextJokeBean mNewJokeData;
    public static int page = 1;
    private Context mContext;

    public JokeDataFactory(Context context) {
        this.mContext = context;
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        mDateString = formatter.format(currentTime);
        mDateString = "2015-08-27";
        mGson = new Gson();
    }

    public TextJokeBean getDataFromWeb() {
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mJokeData = proccessJokeJsonData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJokeData;
    }

    public TextJokeBean getMoreData() {
        page = page + 1;
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mNewJokeData = proccessJokeJsonData(result);
            if (mNewJokeData == null || mNewJokeData.showapi_res_body.allPages == page) {
                return null;
            }
            //写入缓存
            CacheUtils.setCache(Constant.TEXT_JOKE_URL, result, mContext);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewJokeData;

    }

    public TextJokeBean refreshData() {
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mNewJokeData = proccessJokeJsonData(result);
            if (mNewJokeData.showapi_res_body.allNum == mJokeData.showapi_res_body.allNum) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewJokeData;
    }


    private class GetTextJokeSyncTask extends AsyncTask<String, Void, String> {
        
        String currentPage = String.valueOf(page);

        @Override
        protected String doInBackground(String... voids) {
            String res = new ShowApiRequest(Constant.TEXT_JOKE_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("time", mDateString)
                    .addTextPara("page", currentPage)
                    .addTextPara("maxResult", "20")
                    .post();
            res = res.replaceAll("<p>", "");
            res = res.replaceAll("</p>", "");
            Log.d(TAG, "doInBackground: res = " + res + "currentPage" + currentPage);
            return res;
        }
    }


    public TextJokeBean proccessJokeJsonData(String json) {
        TextJokeBean result = mGson.fromJson(json, TextJokeBean.class);
        return result;
    }

}
