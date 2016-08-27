package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.TextJokeBean;
import com.show.api.ShowApiRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chenggggg on 2016/8/27.
 */
public class JokeDataFactory {

    private final String mDateString;
    private final Gson mGson;
    private TextJokeBean mJokeData;
    private static final String TAG = "JokeDataFactory";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
             switch (msg.what){
                 case 0:
                     break;
             }
        }
    };


    public JokeDataFactory() {

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        mDateString = formatter.format(currentTime);
        mGson = new Gson();
    }

    public TextJokeBean getDataFromWeb(){
        try {
            String result = new GetTextJokeSyncTask().execute().get();
            mJokeData = proccessJokeJsonData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJokeData;
    }

    public void getMoreData(){


    }

    public void refreshData(){

    }


    private class GetTextJokeSyncTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            final String res=new ShowApiRequest( "http://route.showapi.com/341-1", Constant.ShowAPI_ID, Constant.ShowAPI_SECRET)
                    .addTextPara("time", mDateString)
                    .addTextPara("page", "1")
                    .addTextPara("maxResult", "20")
                    .post();
            Log.d(TAG, "doInBackground: res = " + res);
            return res;
        }
    }


    public TextJokeBean proccessJokeJsonData(String json){
        TextJokeBean result = mGson.fromJson(json, TextJokeBean.class);
        return result;
    }


}
