package com.triza.android.Gigs;

public class Gigs {
    private String gig_title;
    private String category_id;
    private String sub_cat_id;
    private String package_name;
    private String package_desc;
    private String search_tag;
    private String delivery_time;
    private String gig_desc;
    private String min_price;
    private long date_added;
    private String faq;
    private String faq_answer;
    private String gallery;
    private String document;
    private int extra_fast_delivery = 0;
    private int additional_revision = 0;

    public  Gigs(){}

    public Gigs(String gig_title, String gig_desc, String category_id, String sub_cat_id, String search_tag){
        this.gig_title = gig_title;
        this.gig_desc = gig_desc;
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
    public void setMinPrice(String min_price){
        this.min_price = min_price;
    }
    public void setExtraFastDelivery(int extra_fast_delivery){
        this.extra_fast_delivery = extra_fast_delivery;
    }
    public void setAdditionalRevision(int additional_revision){
        this.extra_fast_delivery = additional_revision;
    }
    public void setDateAdded(long date_added){
        this.date_added = date_added;
    }


    //get metholds

    public String getGig_title() {
        return gig_title;
    }
}
