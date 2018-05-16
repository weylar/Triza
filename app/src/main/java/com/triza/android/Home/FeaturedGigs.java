package com.triza.android.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.triza.android.R;
import com.triza.android.Search.Search;

public class FeaturedGigs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feautured_gigs);
    }
    public void backPressed(View view){
        finish();
    }
    public void searchClick(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
