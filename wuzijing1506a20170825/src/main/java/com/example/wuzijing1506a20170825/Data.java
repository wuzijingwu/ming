package com.example.wuzijing1506a20170825;

/**
 * Created by dell on 2017/8/25.
 */

public class Data {
    private String news_title;

    public Data(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public Data() {
    }

    @Override
    public String toString() {
        return "Data{" +
                "news_title='" + news_title + '\'' +
                '}';
    }
}
