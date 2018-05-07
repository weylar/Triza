package com.triza.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        Set toolbar as actionbar
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);
    }
    //    On back pressed method
    public void catBackPressed(View view){
        finish();
    }

    public void searchClick(View view){
//        TODO:MOVE TO SEARCH ACTIVITY

    }
}
