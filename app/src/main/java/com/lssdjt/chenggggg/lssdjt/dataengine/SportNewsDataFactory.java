package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.SportNewsBean;
import com.show.api.ShowApiRequest;

/**
 * Created by Chenggggg on 2016/9/1.
 */
public class SportNewsDataFactory {

    private int Page = 1;
    private static final String TAG = "SportNewsDataFactory";
    private final Gson mGson;
    private SportNewsBean mData;

    public SportNewsDataFactory() {
        mGson = new Gson();
    }

    private class GetSportDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            Page++;
            String currentPage = String.valueOf(Page);
            final String res = new ShowApiRequest("http://route.showapi.com/196-1", Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("num", "20")
                    .addTextPara("page", currentPage)
                    .post();
            Log.d(TAG, "doInBackground: res = " + res + "currentPage" + currentPage);
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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


}
