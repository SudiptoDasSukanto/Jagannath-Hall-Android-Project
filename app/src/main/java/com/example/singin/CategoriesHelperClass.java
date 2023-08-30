package com.example.singin;

import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {

    GradientDrawable gradientDrawable ;
    int image;
    String title;

    public CategoriesHelperClass(GradientDrawable gradientDrawable, int image, String title) {
        this.gradientDrawable = gradientDrawable;
        this.image = image;
        this.title = title;
    }

    public GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
