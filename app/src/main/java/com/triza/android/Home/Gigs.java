package com.triza.android.Home;
/*Created by weylar on 13/05/18*/
public class Gigs {
    String gigImageUrl, gigTitle;
    int  gigNoReview, price;
    double gigRating;
    boolean isFav;

    public Gigs(){}
    public Gigs(String gigImageUrl, String gigTitle, double gigRating, int gigNoReview, int price, boolean isFav){

        this.gigImageUrl = gigImageUrl;
        this.gigTitle = gigTitle;
        this.gigRating = gigRating;
        this.gigNoReview = gigNoReview;
        this.price = price;
        this.isFav = isFav;
    }

    public String getGigImageUrl() {
        return gigImageUrl;
    }

    public String getGigTitle() {
        return gigTitle;
    }

    public double getGigRating() {
        return gigRating;
    }

    public int getGigNoReview() {
        return gigNoReview;
    }

    public int getPrice() {
        return price;
    }

    public boolean isFav() {
        return isFav;
    }
}

