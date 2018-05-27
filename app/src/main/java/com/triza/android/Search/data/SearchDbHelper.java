package com.triza.android.Search.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.triza.android.Search.data.SearchContract.SearchEntry;

public class SearchDbHelper extends SQLiteOpenHelper {

    // The name of the database
    private static final String DATABASE_NAME = "triza.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;


    // Constructor
    public SearchDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    /**
     * Called when the triza database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create searches table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE " + SearchContract.SearchEntry.TABLE_NAME + " (" +
                SearchEntry._ID + " INTEGER PRIMARY KEY, " +
                SearchEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                SearchEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(CREATE_TABLE);
    }


    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SearchEntry.TABLE_NAME);
        onCreate(db);
    }
}
