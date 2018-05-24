package com.triza.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.triza.android.Gigs.data.GigsContract.GigsEntry;
import com.triza.android.Gigs.data.SqlCreateGigsTable;
import com.triza.android.Search.data.SearchContract.SearchEntry;
import com.triza.android.Search.data.SqlCreateSearchTable;

public class TrizaDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "triza.db";
    static final int DATABASE_VERSION = 1;

    public TrizaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GIGS_TABLE = SqlCreateGigsTable.SQL_CREATE_GIGS_TABLE;
        final String SQL_CREATE_SEARCHES_TABLE = SqlCreateSearchTable.SQL_CREATE_SEARCH_TABLE;
        db.execSQL(SQL_CREATE_GIGS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        db.execSQL("DROP TABLE IF EXISTS " + GigsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchEntry.TABLE_NAME);
        onCreate(db);
    }
}
