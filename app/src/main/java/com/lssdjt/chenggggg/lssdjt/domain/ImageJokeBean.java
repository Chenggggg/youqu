package com.lssdjt.chenggggg.lssdjt.domain;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/8/27.
 */
public class ImageJokeBean {

    public String showapi_res_error;
    public ImageJoke showapi_res_body;

    public class ImageJoke{
        public int allNum;
        public int allPages;
        public int currentPage;

        @Override
        public String toString() {
            return "ImageJoke{" +
                    "allNum=" + allNum +
                    ", allPages=" + allPages +
                    ", currentPage=" + currentPage +
                    ", maxResult=" + maxResult +
                    ", contentlist=" + contentlist +
                    '}';
        }

        public int maxResult;
        public ArrayList<ImageBean> contentlist;

    }

    public class ImageBean {
        public String ct;
        public String id;
        public String img;

        @Override
        public String toString() {
            return "ImageBean{" +
                    "ct='" + ct + '\'' +
                    ", id='" + id + '\'' +
                    ", img='" + img + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        public String title;
    }
}
