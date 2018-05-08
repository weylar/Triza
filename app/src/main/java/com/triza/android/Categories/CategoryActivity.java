package com.triza.android.Categories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.triza.android.R;
import com.triza.android.Search.Search;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Context context = CategoryActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        Set toolbar as actionbar
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        GridView gridView = findViewById(R.id.cat_grid_list);

//        This is a dummy hardcoded value to test!!!
        Categories category1 = new Categories("Graphics & Design", "");
        Categories category2 = new Categories("Programming & Tech", "");
        ArrayList categories = new ArrayList();
        categories.add(category1);
        categories.add(category2);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, categories);
        gridView.setAdapter(categoriesAdapter);
    }
    //    On back pressed method
    public void catBackPressed(View view){
        finish();
    }

    public void searchClick(View view){
        Intent moveToSearch = new Intent(context, Search.class);
        startActivity(moveToSearch);

    }
}
