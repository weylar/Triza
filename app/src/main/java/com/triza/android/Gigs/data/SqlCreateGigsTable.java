package com.triza.android.Gigs.data;

public class SqlCreateGigsTable {
    public static final String SQL_CREATE_GIGS_TABLE = "CREATE TABLE " + GigsContract.GigsEntry.TABLE_NAME + "( " +
            GigsContract.GigsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GigsContract.GigsEntry.COLUMN_GIG_TITLE + " TEXT NOT NULL, " +
            GigsContract.GigsEntry.COLUMN_GIG_DESC + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
            GigsContract.GigsEntry.COLUMN_SUB_CAT_ID + " TEXT NOT NULL, " +
            GigsContract.GigsEntry.COLUMN_SEARCH_TAG + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_DATE_ADDED + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_DELIVERY_TIME + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_FAQ + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_FAQ_ANSWER + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_MIN_PRICE + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_PACKAGE_DESC + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_PACKAGE_NAME + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_GALLERY + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_DOCUMENT + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_EXTRA_FAST_DELIVERY + " TEXT , " +
            GigsContract.GigsEntry.COLUMN_ADDITIONAL_REVISION + " TEXT " +

            "); ";
}
