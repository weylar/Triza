package com.triza.android.Favorites;

import com.triza.android.Gigs.Gigs;

public class Favourites {
    private String userId;
    private String gigId, favouriteId;

    private Gigs gig;
    //this is to ease query due to database realtime deficieny
    private String filter_index;




    public Favourites(String userId, String gigId, String filter_index, String favouriteId) {
        this.userId = userId;
        this.gigId = gigId;
        this.favouriteId = favouriteId;
        this.filter_index = filter_index;
    }
    public Favourites(String userId, String gigId, String filter_index) {
        this.userId = userId;
        this.gigId = gigId;
        this.filter_index = filter_index;
    }

    public Favourites() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGigId() {
        return gigId;
    }

    public void setGigId(String gigId) {
        this.gigId = gigId;
    }

    public String getFilter_index() {
        return filter_index;
    }

    public String getFavouriteId() {
        return favouriteId;
    }

    public Gigs getGig() {
        return gig;
    }


    public void setGig(Gigs gig) {
        this.gig = gig;
    }


    public void setFavouriteId(String favouriteId) {
        this.favouriteId = favouriteId;
    }
    public void setFilter_index(String filter_index) {
        this.filter_index = filter_index;
    }


}
