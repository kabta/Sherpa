package com.example.sherpaatourguide.activity.ui.dashboard;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.widget.ImageView;

public class Explore {
    private String categoryTitle;
    private int categoryImg;
    private String categoryDetails;



    public Explore() {

    }

    public Explore(String categoryTitle, int categoryImg, String categoryDetails) {
        this.categoryTitle = categoryTitle;
        this.categoryImg = categoryImg;
        this.categoryDetails = categoryDetails;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(int categoryImg) {
        this.categoryImg = categoryImg;
    }
    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails;
    }
}
