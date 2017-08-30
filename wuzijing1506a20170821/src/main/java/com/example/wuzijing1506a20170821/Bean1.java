package com.example.wuzijing1506a20170821;

/**
 * Created by dell on 2017/8/21.
 */

public class Bean1 {
    private String title, image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bean1(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Bean1() {
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


}
