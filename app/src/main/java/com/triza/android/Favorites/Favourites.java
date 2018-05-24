package com.triza.android.Favorites;

public class Favourites {
    private String userId;
    private String gigId;

    //this is to ease query due to database realtime deficieny
    private String filter_index;


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

    public void setFilter_index(String filter_index) {
        this.filter_index = filter_index;
    }
}
