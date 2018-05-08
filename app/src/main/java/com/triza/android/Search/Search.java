package com.triza.android.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.triza.android.R;

public class Search extends AppCompatActivity {
    EditText editTextSearch;
    ListView recentSearch;
    RelativeLayout relativeLayout;
    LinearLayout emptySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        editTextSearch = findViewById(R.id.editText_search); //Referencing edittext
        recentSearch = findViewById(R.id.list_recent_search); //referencing listview object
        relativeLayout = findViewById(R.id.recent_search_header); //referencing recent search header
        emptySearch = findViewById(R.id.empty_search); //referencing the linearlayout empty view


//        i create a dummy array of strings to test the search history
        String[] value = new String[3];
        value[0] = "Weylar";
        value[1] = "Muilat";
        value[2] = "Triza";

//        Settingn up array to fire the list recent search
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, value);

//        fires the list view
        recentSearch.setAdapter(arrayAdapter);
    }
public void onClearRecentSearch(View view){
//        TODO: THIS SHOULD CLEAR ON CLOUD RECENT SEARCH
}
   public void onBackClick(View view){
        finish();
   }

   public void searchClick(View view){
//TODO: ACTION PENDING
   }
}
