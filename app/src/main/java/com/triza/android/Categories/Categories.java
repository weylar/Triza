package com.triza.android.Categories;

import android.net.Uri;

public class Categories {
    String catTitle ;
    String catDescription;
    String catImageUrl;
<<<<<<< HEAD
=======
    String catId;
    long catDateAdded;
    int noOfSubCategories;
>>>>>>> 235670ba79965fed05ce24f1363e1a34d39bf0a1

    public Categories(){}

    public Categories(String catTitle, String catDesc, String catImageUrl){
        this.catTitle = catTitle;
        this.catImageUrl = catImageUrl;
        this.catDescription = catDesc;
    }

<<<<<<< HEAD
    public String getCatTitle(){
        return catTitle;
=======
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
>>>>>>> 235670ba79965fed05ce24f1363e1a34d39bf0a1
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
<<<<<<< HEAD

=======
>>>>>>> 235670ba79965fed05ce24f1363e1a34d39bf0a1
    public String getCatDescription(){
        return catDescription;
    }
}
