package com.triza.android.Categories;

import android.net.Uri;

public class Categories {
    String catTitle ;
    String catDescription;
    String catImageUrl;

    public Categories(){}

    public Categories(String catTitle, String catDesc, String catImageUrl){
        this.catTitle = catTitle;
        this.catImageUrl = catImageUrl;
        this.catDescription = catDesc;
    }

    public String getCatTitle(){
        return catTitle;
    }

    public String getCatImageUrl(){
        return catImageUrl;
    }

    public String getCatDescription(){
        return catDescription;
    }
}
