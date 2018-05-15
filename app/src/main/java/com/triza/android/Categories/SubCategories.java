package com.triza.android.Categories;

import android.net.Uri;

public class SubCategories {
    String subCatTitle ;
    String catId;
    String subSubCatUid;
    int noOfServices;

    public SubCategories(){}

    public SubCategories(String subCatTitle, String catUid){
        this.subCatTitle = subCatTitle;
        this.catId = catUid;
    }

    public SubCategories(String subCatTitle){
        this.subCatTitle = subCatTitle;
    }


    public String getSubCatTitle(){
        return subCatTitle;
    }

    public String getCatId(){
        return catId;
    }
    public void setNoOfServices(int noOfServices){
        this.noOfServices = noOfServices;
    }
    public void setCatId(String catUid){
        this.catId = catUid;
    }

}
