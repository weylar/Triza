package com.triza.android.Categories;

public class Categories {
    private String catTitle;
    private String catDescription;
    private String catImageUrl;
    private String catId;
    private long catDateAdded;
    private int noOfSubCategories;
    private int gigCount;

    public Categories() {
    }

    public Categories(String catTitle, String catDesc, String catImageUrl, int gigCount) {
        this.catTitle = catTitle;
        this.catImageUrl = catImageUrl;
        this.catDescription = catDesc;
        this.gigCount = gigCount;
    }


    public Categories(String catTitle, String catDesc) {
        this.catTitle = catTitle;
        this.catDescription = catDesc;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public long getCatDateAdded() {
        return catDateAdded;
    }

    public void setCatDateAdded(long catDateAdded) {
        this.catDateAdded = catDateAdded;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getNoOfSubCategories() {
        return noOfSubCategories;
    }

    public void setNoOfSubCategories(int noOfSubCategories) {
        this.noOfSubCategories = noOfSubCategories;
    }

    public String getCatImageUrl() {
        return catImageUrl;
    }

    public void setCatImageUrl(String catImageUrl) {
        this.catImageUrl = catImageUrl;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public int getGigCount() {
        return gigCount;
    }

    @Override
    public String toString() {
        return this.catTitle;// What to display in the Spinner list.
    }
}
