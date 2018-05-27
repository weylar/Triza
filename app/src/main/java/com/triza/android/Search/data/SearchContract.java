package com.triza.android.Search.data;

import android.provider.BaseColumns;

public class SearchContract {

    private SearchContract() {
    }

    public static final class SearchEntry implements BaseColumns {
        // Searches table and column names
        public static final String TABLE_NAME = "searches";

        // Since SearchEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}
