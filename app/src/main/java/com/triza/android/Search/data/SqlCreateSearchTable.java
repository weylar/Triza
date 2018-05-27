package com.triza.android.Search.data;

public class SqlCreateSearchTable {
    // Create searches table (careful to follow SQL formatting rules)
    public static final String SQL_CREATE_SEARCH_TABLE = "CREATE TABLE " + SearchContract.SearchEntry.TABLE_NAME + " (" +
            SearchContract.SearchEntry._ID + " INTEGER PRIMARY KEY, " +
            SearchContract.SearchEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            SearchContract.SearchEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ");";
}
