package com.triza.android.Categories;

public class Categories {
    String mCatTitle ;
    String mCatImageUrl;

    Categories(String catTitle, String catImageUrl){
        mCatTitle = catTitle;
        mCatImageUrl = catImageUrl;
    }

    public String getCatTitle(){
        return mCatTitle;
    }

    public String getCatImageUrl(){
        return mCatImageUrl;
    }
}
