package com.triza.android.Gigs;

public class Gigs {
    String gigImageUrl;
    int gigNoReview = 0;
    double gigRating = 0.0;
    private String gigTitle;
    private String category_id;
    private String sub_cat_id;
    private String gig_id;
    private String package_name;
    private String package_desc;
    private String search_tag;
    private String delivery_time;
    private String gig_desc;
    private int min_price;
    private long date_added;
    private String faq;
    private String faq_answer;
    private String gallery;
    private String document;
    private boolean extra_fast_delivery = false;
    private boolean additional_revision = false;

    private String sellerId;



    public  Gigs(){}

    public Gigs(String gig_title, String category_id, String sub_cat_id, String search_tag) {
        this.gigTitle = gig_title;
        this.category_id = category_id;
        this.sub_cat_id = sub_cat_id;
        this.search_tag = search_tag;
    }

    public void setFaq(String faq){
        this.faq = faq;
    }
    public void setPackagDesc(String package_desc){
        this.package_desc = package_desc;
    }
    public void setPackageName(String package_name){
        this.package_name = package_name;
    }
    public void setFaqAnswer(String faq_answer){
        this.faq_answer = faq_answer;
    }
    public void setDeliveryTime(String delivery_time){
        this.delivery_time = delivery_time;
    }

    public void setExtraFastDelivery(boolean extra_fast_delivery) {
        this.extra_fast_delivery = extra_fast_delivery;
    }

    public void setAdditionalRevision(boolean additional_revision) {
        this.extra_fast_delivery = additional_revision;
    }

    public double getGigRating() {
        return gigRating;
    }
    public void setDateAdded(long date_added){
        this.date_added = date_added;
    }

        public int getGigNoReview() {
        return gigNoReview;
    }

    public void setGigNoReview(int gigNoReview) {
        this.gigNoReview = gigNoReview;
    }
    public String getSellerId() {
        return sellerId;
    }

    public void setSeller_id(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getGigTitle() {
        return gigTitle;
    }

    public String getCategoryId() {
        return category_id;
    }

    //TODO restructure gallery to take many pictures as well as document

    public String getGigId() {
        return gig_id;
    }



    //get metholds

    public void setGigId(String gig_id) {
        this.gig_id = gig_id;
    }

    public String getSubCatId() {
        return sub_cat_id;
    }

    public String getDocument() {
        return document;
    }

    public int getMinPrice() {
        return min_price;
    }

    public void setMinPrice(int min_price) {
        this.min_price = min_price;
    }

    public String getPackageDesc() {
        return package_desc;
    }

    public String getPackageName() {
        return package_name;
    }

    public String getFaq() {
        return faq;
    }

    public String getFaqAnswer() {
        return faq_answer;
    }

    public String getSearchTag() {
        return search_tag;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getDeliveryTime() {
        return delivery_time;
    }

    public long getDateAdded() {
        return date_added;
    }

    public String getGigDesc() {
        return gig_desc;
    }

    public String getGigImageUrl() {
        return gigImageUrl;
    }

    public void setGigImageUrl(String gigImageUrl) {
        this.gigImageUrl = gigImageUrl;
    }

}
