package com.example.stonks.cards;

public class JobsCard {
    private String mTitle;
    private String mDetails;
    private String mStonks;

    public JobsCard(String title, String details, String stonks){
        mTitle = title;
        mDetails = details;
        mStonks = stonks;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getDetails(){
        return mDetails;
    }

    public String getStonks(){
        return mStonks;
    }
}
