package com.example.stonks.cards;

import android.view.View;

public class NewsCard{
    private int mImgResource;
    private String mTitle;
    private String mDescrip;
    private String mDetails;

    public NewsCard(int imgResource, String title, String descrip, String details){
        mImgResource = imgResource;
        mTitle = title;
        mDescrip = descrip;
        mDetails = details;
    }

    public int getImageResource(){
        return mImgResource;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getDescrip(){
        return mDescrip;
    }

    public String getDetails(){
        return mDetails;
    }
}
