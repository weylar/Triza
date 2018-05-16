package com.triza.android.Categories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.triza.android.Adapters.CategoriesAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.triza.android.R;
import com.triza.android.Search.Search;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Context context = CategoryActivity.this;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
    private ChildEventListener mChildEventListener;


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

        categoriesAdapter = new CategoriesAdapter(this, categories);


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //TODO: add to db
                Categories category = dataSnapshot.getValue(Categories.class);
                categoriesAdapter.add(category);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mCategoriesDatabaseReference.addChildEventListener(mChildEventListener);

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
