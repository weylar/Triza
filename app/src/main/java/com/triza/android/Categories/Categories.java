package com.triza.android.Categories;

import android.net.Uri;

public class Categories {
    String catTitle ;
    String catDescription;
    String catImageUrl;
    String catId;
    long catDateAdded;
    int noOfSubCategories;

    public Categories(){}

    public Categories(String catTitle, String catDesc, String catImageUrl){
        this.catTitle = catTitle;
        this.catImageUrl = catImageUrl;
        this.catDescription = catDesc;
    }

    public Categories(String catTitle, String catDesc){
        this.catTitle = catTitle;
        this.catDescription = catDesc;
    }

    public void setCatImageUrl(String catImageUrl){
        this.catImageUrl = catImageUrl;
    }
    public void setCatId(String catId){
        this.catId = catId;
    }
    public void setCatDateAdded(long catDateAdded){
        this.catDateAdded = catDateAdded;
    }
    public void setNoOfSubCategories(int noOfSubCategories){
        this.noOfSubCategories = noOfSubCategories;
    }

    public String getCatTitle(){
        return catTitle;
    }
    public long getCatDateAdded(){
        return catDateAdded;
    }
    public String getCatId(){
        return catId;
    }
    public int getNoOfSubCategories(){
        return noOfSubCategories;
    }
    public String getCatImageUrl(){
        return catImageUrl;
    }
    public String getCatDescription(){
        return catDescription;
    }
}
