package com.triza.android.Search;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.triza.android.Home.HomeActivity;
import com.triza.android.R;
import com.triza.android.Search.data.SearchContract.SearchEntry;
import com.triza.android.Search.data.SearchDbHelper;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    EditText editTextSearch;
    ListView recentSearch;
    RelativeLayout relativeLayout;
    LinearLayout emptySearch;
    SearchView searchView;
    View searchPlateView;
    TextView clear_history_textView;

    ArrayAdapter<String> arrayAdapter;
    List<String> searches = new ArrayList<>();
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        clear_history_textView = findViewById(R.id.clear_history);

        // Create a DB helper (this will create the DB if run for the first time)
        SearchDbHelper dbHelper = new SearchDbHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding user searches
        mDb = dbHelper.getWritableDatabase();

//        editTextSearch = findViewById(R.id.editText_search); //Referencing edittext
        searchView = findViewById(R.id.searchView);
        final EditText searchPlate = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchPlate.setHint("What are you looking for?");
        searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //some operation
                searchPlateView.requestFocus();
            }
        });
        // use this method for search process
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //save to db
                long returned_id = addNewSearch(query);

                //TODO query firebase


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        recentSearch = findViewById(R.id.list_recent_search); //referencing listview object
        relativeLayout = findViewById(R.id.recent_search_header); //referencing recent search header
        emptySearch = findViewById(R.id.empty_search); //referencing the linearlayout empty view


        //list the search history
        searches = getAllSearches();

//        Setting up array to fire the list recent search
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, searches);

//        fires the list view
        recentSearch.setAdapter(arrayAdapter);

        recentSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_search = searches.get(i);
                searchView.setQuery(selected_search, true);

            }
        });


        if (searches.isEmpty()) {
            clear_history_textView.setVisibility(View.INVISIBLE);
        } else {
            clear_history_textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Adds a new search to the mDb with the current timestamp
     *
     * @param title user's search
     * @return id of new record added
     */
    private long addNewSearch(String title) {
        //limit to 5 recent searches
        //if recent searches is up to 5, delete the first seach before adding
        if (searches.size() >= 5) {
            mDb.delete(SearchEntry.TABLE_NAME, SearchEntry.COLUMN_TITLE + " = \"" + searches.get(0).toString() + "\"", null);
        }


        if (searches.contains(title)) {
            //searching for the same query, so just return
            return 0;
        }
        ContentValues cv = new ContentValues();
        cv.put(SearchEntry.COLUMN_TITLE, title);

        long result = mDb.insert(SearchEntry.TABLE_NAME, null, cv);

        resetAdapter();

        return result;
    }

    /**
     * Query the mDb and get all guests from the waitlist table
     *
     * @return Cursor containing the list of guests
     */
    private List<String> getAllSearches() {
        List<String> s = new ArrayList<>();
        Cursor cursor = mDb.query(
                SearchEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                SearchEntry.COLUMN_TIMESTAMP
        );

        while (cursor.moveToNext()) {
            s.add(cursor.getString(cursor.getColumnIndex(SearchEntry.COLUMN_TITLE)));
        }


        return s;
    }

    public void onClearRecentSearch(View view) {
//        TODO: THIS SHOULD CLEAR ON DB RECENT SEARCH


        mDb.delete(SearchEntry.TABLE_NAME, null, null);
        Toast.makeText(Search.this, "Search History cleared!", Toast.LENGTH_SHORT).show();
        resetAdapter();


    }

    private void resetAdapter() {
        arrayAdapter.clear();
        searches = getAllSearches();
        arrayAdapter.addAll(searches);
        recentSearch.setAdapter(arrayAdapter);

        if (searches.isEmpty()) {
            clear_history_textView.setVisibility(View.INVISIBLE);
        } else {
            clear_history_textView.setVisibility(View.VISIBLE);
        }
    }

    public void onBackClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void searchClick(View view) {
//TODO: ACTION PENDING
    }
}
