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
    private TextJokeBean mNewJokeData;
    public static int TextPage = 1;
    public static int ImagePage = 1;
    private Context mContext;
    private ImageJokeBean mImageJokeData;

    public JokeDataFactory(Context context) {
        this.mContext = context;
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        mDateString = formatter.format(currentTime);
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
        TextPage = TextPage + 1;
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mNewJokeData = proccessTextJokeJsonData(result);
            if (mNewJokeData == null || mNewJokeData.showapi_res_body.allPages == TextPage) {
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
            mNewJokeData = proccessTextJokeJsonData(result);
            if (mNewJokeData.showapi_res_body.allNum == mTextJokeData.showapi_res_body.allNum) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNewJokeData;
    }


    private class GetTextJokeSyncTask extends AsyncTask<String, Void, String> {

        String currentPage = String.valueOf(TextPage);

        @Override
        protected String doInBackground(String... voids) {
            String res = new ShowApiRequest(Constant.TEXT_JOKE_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("time", mDateString)
                    .addTextPara("TextPage", currentPage)
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




    public ImageJokeBean getImageJokeDataFromWeb() {
        try {
            String result = new GetImageJokeSyncTask().execute().get();
            mImageJokeData = proccessImageJokeJsonData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mImageJokeData;
    }

    private class GetImageJokeSyncTask extends AsyncTask<String, Void, String> {

        String currentPage = String.valueOf(ImagePage);

        @Override
        protected String doInBackground(String... voids) {
            String res = new ShowApiRequest(Constant.IMAGE_JOKE_URL, Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("time", mDateString)
                    .addTextPara("TextPage", currentPage)
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


}
