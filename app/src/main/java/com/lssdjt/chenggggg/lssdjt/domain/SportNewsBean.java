package com.lssdjt.chenggggg.lssdjt.domain;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/9/1.
 */
public class SportNewsBean {

    public SportNewsBody showapi_res_body;

    public class SportNewsBody {
        public int code;
        public String msg;
        public ArrayList<SportNews> newslist;
    }

    public class SportNews {

        @Override
        public String toString() {
            return "SportNews{" +
                    "ctime='" + ctime + '\'' +
                    ", description='" + description + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String ctime;
        public String description;
        public String picUrl;
        public String title;
        public String url;
    }
}
