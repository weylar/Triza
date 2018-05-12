package com.triza.android.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class CategoriesContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.triza.android";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "categories" directory
    public static final String PATH_CATEGORIES = "categories";

    public static final class CategoriesEntry implements BaseColumns {

        // CategoriesEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORIES).build();

        public static final String TABLE_NAME = "categories";
        // COLUMN_CAT_TITLE -> cat_title
        public static final String COLUMN_CAT_TITLE = "cat_title";
        // COLUMN_CAT_DESCRIPTION -> catDescription
        public static final String COLUMN_CAT_DESCRIPTION = "catDescription";
        // COLUMN_PHOTO_URL -> photo_url
        public static final String COLUMN_PHOTO_URL = "photo_url";
        // COLUMN_UID -> photo_url
        public static final String COLUMN_CAT_UID = "uid";

    };
}