package com.triza.android.Categories;

public class SubCategories {
    private String subCatTitle ;
    private String catId;
    private String subCatId;
    private int noOfGigs;
    private String imageUrl;

    public SubCategories(){}

    public SubCategories(String subCatTitle, String catId, int noOfGigs, String imageUrl) {
        this.subCatTitle = subCatTitle;
        this.catId = catId;
        this.noOfGigs = noOfGigs;
        this.imageUrl = imageUrl;
    }

    public SubCategories(String subCatTitle){
        this.subCatTitle = subCatTitle;
    }


    public String getSubCatTitle(){
        return subCatTitle;
    }

    public int getNoOfGigs(){
        return noOfGigs;
    }

    public String getCatId(){
        return catId;
    }
    public String getSubCatId(){
        return subCatId;
    }
    public void setCatId(String catUid){
        this.catId = catUid;
    }
    public void setSubCatId(String subCatId){
        this.subCatId = subCatId;
    }

    @Override
    public String toString() {
        return this.subCatTitle;// What to display in the Spinner list.
    }


}
