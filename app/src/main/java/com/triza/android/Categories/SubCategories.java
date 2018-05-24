package com.triza.android.Categories;

public class SubCategories {
    String subCatTitle ;
    String catId;
    String subSubCatUid;
    int noOfServices;

    public SubCategories(){}

    public SubCategories(String subCatTitle, String catId) {
        this.subCatTitle = subCatTitle;
        this.catId = catId;
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

    @Override
    public String toString() {
        return this.subCatTitle;// What to display in the Spinner list.
    }


}
