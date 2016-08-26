package com.lssdjt.chenggggg.lssdjt.domain;

import java.util.ArrayList;

/**
 * Created by Chenggggg on 2016/8/26.
 */
public class HistoryEventListBean {

    public int error_code;
    public String reason;
    public ArrayList<hEvent> result;

    public class hEvent{
        @Override
        public String toString() {
            return "hEvent{" +
                    "day=" + day +
                    ", month=" + month +
                    ", year=" + year +
                    ", id=" + id +
                    ", des='" + des + '\'' +
                    ", lunar='" + lunar + '\'' +
                    ", title='" + title + '\'' +
                    ", pic='" + pic + '\'' +
                    '}';
        }

        public int day;
        public int month;
        public int year;
        public int id;
        public String des;
        public String lunar;
        public String title;
        public String pic;
    }
}
