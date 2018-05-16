package com.triza.android.Gigs.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.triza.android.Gigs.data.GigsContract.GigsEntry;

public class GigsDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "triza.db";
    static final int DATABASE_VERSION = 1;

    public GigsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GIGS_TABLE = "CREATE TABLE " + GigsEntry.TABLE_NAME + "( "+
                GigsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                GigsEntry.COLUMN_GIG_TITLE + " TEXT NOT NULL, " +
                GigsEntry.COLUMN_GIG_DESC + " TEXT NOT NULL, " +
                GigsEntry.COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
                GigsEntry.COLUMN_SUB_CAT_ID + " TEXT NOT NULL, " +
                GigsEntry.COLUMN_SEARCH_TAG + " TEXT , " +
                GigsEntry.COLUMN_DATE_ADDED + " TEXT , " +
                GigsEntry.COLUMN_DELIVERY_TIME + " TEXT , " +
                GigsEntry.COLUMN_FAQ + " TEXT , " +
                GigsEntry.COLUMN_FAQ_ANSWER + " TEXT , " +
                GigsEntry.COLUMN_MIN_PRICE + " TEXT , " +
                GigsEntry.COLUMN_PACKAGE_DESC + " TEXT , " +
                GigsEntry.COLUMN_PACKAGE_NAME + " TEXT , " +
                GigsEntry.COLUMN_GALLERY + " TEXT , " +
                GigsEntry.COLUMN_DOCUMENT + " TEXT , " +
                GigsEntry.COLUMN_EXTRA_FAST_DELIVERY + " TEXT , " +
                GigsEntry.COLUMN_ADDITIONAL_REVISION + " TEXT " +

                "); ";

        db.execSQL(SQL_CREATE_GIGS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        db.execSQL("DROP TABLE IF EXISTS " + GigsEntry.TABLE_NAME);
        onCreate(db);
    }
}
