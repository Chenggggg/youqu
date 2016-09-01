package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.ImageJokeBean;
import com.lssdjt.chenggggg.lssdjt.domain.TextJokeBean;
import com.lssdjt.chenggggg.lssdjt.utils.CacheUtils;
import com.show.api.ShowApiRequest;

/**
 * Created by Chenggggg on 2016/8/27.
 */
public class JokeDataFactory {

    private final String mDateString;
    private final Gson mGson;
    private TextJokeBean mTextJokeData;
    private static final String TAG = "JokeDataFactory";
    private TextJokeBean mNewTextJokeData;
    public static int TextPage = 1;
    public static int ImagePage = 1;
    private Context mContext;
    private ImageJokeBean mImageJokeData;
    private ImageJokeBean mNewImageJokeData;
    private int moreImagePage;

    public JokeDataFactory(Context context) {
        this.mContext = context;
        mDateString = "2015-08-27";
        mGson = new Gson();
    }

    public TextJokeBean getTextJokeDataFromWeb() {
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mTextJokeData = proccessTextJokeJsonData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mTextJokeData;
    }

    public TextJokeBean getMoreTextJokeData() {
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mNewTextJokeData = proccessTextJokeJsonData(result);
            if (mNewTextJokeData == null || mNewTextJokeData.showapi_res_body.allPages == TextPage) {
                return null;
            }
            //写入缓存
            CacheUtils.setCache(Constant.TEXT_JOKE_URL, result, mContext);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewTextJokeData;
    }

    public TextJokeBean refreshData() {
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mNewTextJokeData = proccessTextJokeJsonData(result);
            if (mNewTextJokeData.showapi_res_body.allNum == mTextJokeData.showapi_res_body.allNum) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewTextJokeData;
    }


    private class GetTextJokeSyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            TextPage++;
            String currentPage = String.valueOf(TextPage);
            String res = new ShowApiRequest(Constant.TEXT_JOKE_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("time", mDateString)
                    .addTextPara("page", currentPage)
                    .addTextPara("maxResult", "20")
                    .post();
            res = res.replaceAll("<p>", "");
            res = res.replaceAll("</p>", "");
            res = res.replaceAll("\r\n", "");
            res = res.replaceAll("\t", "");
            Log.d(TAG, "doInBackground: res = " + res + "currentPage" + currentPage);
            return res;
        }
    }


    public TextJokeBean proccessTextJokeJsonData(String json) {
        TextJokeBean result = mGson.fromJson(json, TextJokeBean.class);
        return result;
    }

    public ImageJokeBean getImageJokeDataFromWeb(int page) {
        try {
            String result = new GetImageJokeSyncTask().execute(String.valueOf(page)).get();
            mImageJokeData = proccessImageJokeJsonData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mImageJokeData;
    }

    private class GetImageJokeSyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... prams) {
            ImagePage++;
            String currentPage = String.valueOf(TextPage);
            String res = new ShowApiRequest(Constant.IMAGE_JOKE_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("time", mDateString)
                    .addTextPara("page", currentPage)
                    .addTextPara("maxResult", "20")
                    .post();
            Log.d(TAG, "doInBackground: res = " + res + "currentPage" + currentPage);
            return res;
        }
    }

    public ImageJokeBean proccessImageJokeJsonData(String json) {
        ImageJokeBean result = mGson.fromJson(json, ImageJokeBean.class);
        return result;
    }


    public ImageJokeBean getMoreImageJokeData() {
        try {
            String result = new GetImageJokeSyncTask().execute().get();
            mNewImageJokeData = proccessImageJokeJsonData(result);
            if (mNewImageJokeData == null || mNewImageJokeData.showapi_res_body.currentPage == moreImagePage) {
                Log.d(TAG, "getMoreImageJokeData: 没有更多数据");
                return null;
            }else {
                //写入缓存
                CacheUtils.setCache(Constant.IMAGE_JOKE_URL, result, mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewImageJokeData;
    }


}
