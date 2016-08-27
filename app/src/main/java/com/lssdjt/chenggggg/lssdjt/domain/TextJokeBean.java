package com.lssdjt.chenggggg.lssdjt.domain;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/8/27.
 */
public class TextJokeBean {


    public int showapi_res_code;
    public TextJoke showapi_res_body;

    public class TextJoke{
        public int allNum;
        public int allPages;

        @Override
        public String toString() {
            return "TextJokeBean{" +
                    "allNum=" + allNum +
                    ", allPages=" + allPages +
                    ", contentlist=" + contentlist +
                    '}';
        }

        public ArrayList<TextBean> contentlist;

    }

    public class TextBean{

        public String ct;
        public String id;
        public String text;
        public String title;

        @Override
        public String toString() {
            return "TextBean{" +
                    "ct='" + ct + '\'' +
                    ", id='" + id + '\'' +
                    ", text='" + text + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}