package com.triza.android.Categories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.CategoriesAdapter;
import com.triza.android.R;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Context context = CategoryActivity.this;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;


    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        Set toolbar as actionbar
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        GridView gridView = findViewById(R.id.cat_grid_list);


        //Instanciate firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("categories");

          ArrayList categories = new ArrayList();

        categoriesAdapter = new CategoriesAdapter(this, categories, CategoriesAdapter.CATEGORY_VIEW);

        mCategoriesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    Categories category = categorySnapshot.getValue(Categories.class);
                    categoriesAdapter.add(category);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        gridView.setAdapter(categoriesAdapter);
    }
    //    On back pressed method
     public void BackPressed(View view){
        finish();
    }

    public void searchClick(View view){
        Intent moveToSearch = new Intent(context, AddCategoryActivity.class);
        startActivity(moveToSearch);

    }
}
