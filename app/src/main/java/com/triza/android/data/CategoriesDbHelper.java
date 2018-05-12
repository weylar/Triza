package com.triza.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.triza.android.data.CategoriesContract.CategoriesEntry;

public class CategoriesDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "triza.db";
    static final int DATABASE_VERSION = 1;

    public CategoriesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CategoriesContract.CategoriesEntry.TABLE_NAME + "( "+
                CategoriesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CategoriesEntry.COLUMN_CAT_TITLE + " TEXT NOT NULL, " +
                CategoriesEntry.COLUMN_CAT_DESCRIPTION + " TEXT NOT NULL, " +
                CategoriesEntry.COLUMN_CAT_UID + " TEXT NOT NULL, " +
                CategoriesEntry.COLUMN_PHOTO_URL + " TEXT NOT NULL " +
                "); ";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesEntry.TABLE_NAME);
        onCreate(db);
    }
}
