package com.example.login_register_f.bean;

import java.util.List;

public  class Essay{

        /**
         * _id : 5f8f8f1a808d6d2fe6b56f6b
         * author : Jenly
         * category : GanHuo
         * createdAt : 2020-10-21 09:30:02
         * desc : 一个支持可拖动多边形，可拖动多边形的角改变其形状的任意多边形控件
         * images : ["https://gank.io/images/2757977dade04560ac5ffa823681ed10"]
         * likeCounts : 0
         * publishedAt : 2020-10-21 09:30:02
         * stars : 1
         * title : 一个支持可拖动多边形，可拖动多边形的角改变其形状的任意多边形控件
         * type : Android
         * url : https://github.com/jenly1314/DragPolygonView
         * views : 16
         */

            private String _id;
            private String author;
            private String desc;
            private String publishedAt;
            private String title;
            private String url;


    public Essay() {
    }

    public Essay(String author, String title, String date) {
        this.author = author;
        this.title = title;
        this.publishedAt = date;
    }
            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;

            }


            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;

            }


            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;

            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;

            }


            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;

            }


            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;

            }

    @Override
    public String toString() {
        return "Essay{" +
                "_id='" + _id + '\'' +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}

