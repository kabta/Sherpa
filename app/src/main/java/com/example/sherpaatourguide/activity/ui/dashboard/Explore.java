package com.example.sherpaatourguide.activity.ui.dashboard;

import android.widget.ImageView;

public class Explore {

    private String browse, text1, text2;
    private int image1, image2;


    public Explore(String browse, String text1, String text2, int image1, int image2) {
        this.browse = browse;
        this.text1 = text1;
        this.text2 = text2;
        this.image1 = image1;
        this.image2 = image2;
    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String browse) {

        this.browse = browse;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public int getImage1() {
        return image1;
    }
    public void setImage1(int image1){
        this.image1= image1;
    }
    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getImage2() {
        return image1;
    }
    public void setImage2(int image2){
        this.image2= image2;
    }
}
