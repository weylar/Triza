package com.triza.android.Gigs.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class GigsContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.triza.android";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "categories" directory
    public static final String PATH_GIGS = "gigs";

    public static final class GigsEntry implements BaseColumns {

        // CategoriesEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GIGS).build();

        public static final String TABLE_NAME = "gigs";
        // COLUMN_GIG_TITLE -> gig_title
        public static final String COLUMN_GIG_TITLE = "gig_title";
        // COLUMN_GIG_CAT_ID -> gig_cat_id
        public static final String COLUMN_CATEGORY_ID = "category_id";
        // COLUMN_SUB_CAT_ID -> sub_cat_id
        public static final String COLUMN_SUB_CAT_ID = "sub_cat_id";
        // COLUMN_PACKAGE_NAME -> package_name
        public static final String COLUMN_PACKAGE_NAME = "package_name";
        // COLUMN_PACKAGE_DESC -> package_desc
        public static final String COLUMN_PACKAGE_DESC = "package_desc";
        // COLUMN_SEARCH_TAG -> search_tag
        public static final String COLUMN_SEARCH_TAG = "search_tag";
        // COLUMN_GDELIVERY_TIME -> delivery_time
        public static final String COLUMN_DELIVERY_TIME = "delivery_time";
        // COLUMN_GIG_DESC -> gig_desc
        public static final String COLUMN_GIG_DESC = "gig_desc";
        // COLUMN_MIN_PRICE -> min_price
        public static final String COLUMN_MIN_PRICE = "min_price";
        // COLUMN_DATE_ADDED -> date_added
        public static final String COLUMN_DATE_ADDED = "date_added";
        // COLUMN_FAQ -> faq
        public static final String COLUMN_FAQ = "faq";
        // COLUMN_FAQ_ANSWER -> faq_answer
        public static final String COLUMN_FAQ_ANSWER = "faq_answer";
        // COLUMN_GALLERY -> gallery
        public static final String COLUMN_GALLERY = "gallery";

        public static final String COLUMN_DOCUMENT = "document";
        public static final String COLUMN_ADDITIONAL_REVISION = "additional_revision";
        public static final String COLUMN_EXTRA_FAST_DELIVERY = "extra_fast_delivery";



    };
}