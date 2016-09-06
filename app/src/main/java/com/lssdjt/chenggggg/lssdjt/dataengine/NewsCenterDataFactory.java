package com.lssdjt.chenggggg.lssdjt.dataengine;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.lssdjt.chenggggg.lssdjt.domain.Constant;
import com.lssdjt.chenggggg.lssdjt.domain.NewsChanelBean;
import com.show.api.ShowApiRequest;

/**
 * Created by Chenggggg on 2016/9/6.
 */
public class NewsCenterDataFactory {

    private final Gson mGson;
    private Context mContext;
    private String result;
    private NewsChanelBean mChanelData;

    public NewsCenterDataFactory(Context context) {
        this.mContext = context;
        mGson = new Gson();
    }


    public NewsChanelBean getNewsChannelData(){
        try {
            result = new getNewsChannelTask().execute().get();
            mChanelData = mGson.fromJson(result, NewsChanelBean.class);
        }  catch (Exception e) {
            e.printStackTrace();
        }
       return mChanelData;
    }

    private class getNewsChannelTask extends AsyncTask<Void,Void,String>{
        /**
         * 获取新闻频道数据，返回处理后的json
         * @param strings
         * @return
         */
        @Override
        protected String doInBackground(Void... strings) {
            return new ShowApiRequest(Constant.NEWS_CENTER_URL, Constant.ShowAPI_ID,Constant.ShowAPI_SECRET)
                    .post();
        }
    }
}
