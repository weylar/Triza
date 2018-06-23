package com.triza.android.Categories;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.CategoriesAdapter;
import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.Adapters.SubCatAdapter;
import com.triza.android.Gigs.Gigs;
import com.triza.android.R;
import com.triza.android.Search.Search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CategoryActivity extends AppCompatActivity implements CategoryViewFragment.OnCategorySelectedInteractionListener, SubCategoryViewFragment.OnSubCategorySelectedInteractionListener, GigsListingFragment.OnFragmentInteractionListener {
    public static final String CAT_ID = "catId";
    public static final String SUB_CAT_ID = "subCatId";
    public static final String CATEGORIES = "categories";
    public static final String SUB_CATEGORIES = "sub_categories";
    public static final String GIGS = "gigs";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
    private DatabaseReference mSubCategoriesDatabaseReference;
    private DatabaseReference mGigsDatabaseReference;
    private CategoryViewFragment categoryViewFragment;
    private SubCategoryViewFragment subCategoryViewFragment;
    private GigsListingFragment gigsListingFragment;
    public static ArrayList<Gigs> gigList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment fragmentOld;
    private Categories category;
    private int fragCount = 0;
    private String[] frags = {"", "Categories", "", ""};
    private TextView fragName;
    public static CategoriesAdapter categoriesAdapter;
    public static SubCatAdapter subCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child(CATEGORIES);
        mSubCategoriesDatabaseReference = mFirebaseDatabase.getReference().child(SUB_CATEGORIES);
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child(GIGS);
        gigList = new ArrayList<>();
        categoriesAdapter = new CategoriesAdapter(CategoryActivity.this, new ArrayList<Categories>());
        subCategoriesAdapter = new SubCatAdapter(this, new ArrayList<SubCategories>());
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
        fragName = findViewById(R.id.frag_name);
        if (findViewById(R.id.catFragmentHolder) != null) {
            fragmentManager = getSupportFragmentManager();
            categoryViewFragment = new CategoryViewFragment();
            subCategoryViewFragment = new SubCategoryViewFragment();
            gigsListingFragment = new GigsListingFragment();
            /*This method sets the categoryViewFragment by activity launch*/
            setUpFragment(savedInstanceState, categoryViewFragment, fragName, "Categories");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragCount -= 1;
        if (fragCount <= 0) {
            finish();
        }
        if (fragCount == 1) {

            fragName.setText(frags[1]);
        }
        if (fragCount == 2) {

            fragName.setText(frags[2]);
        }

    }

    public void searchClick(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    @Override
    public void onCategorySelectedInteraction(Categories category) {
        getSubCategories(category.getCatId());
        setUpFragment(subCategoryViewFragment, fragName, category.getCatTitle());
        frags[2] = category.getCatTitle();


    }

    @Override
    public void onSubCategorySelectedInteraction(SubCategories subCategory) {
        getGigs(subCategory.getSubCatId());
        setUpFragment(gigsListingFragment, fragName, subCategory.getSubCatTitle());


    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    private void getSubCategories(String category_id) {
        mSubCategoriesDatabaseReference.orderByChild(CAT_ID).equalTo(category_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                subCategoriesAdapter.clear();
                for (DataSnapshot subCatSnapshot : dataSnapshot.getChildren()) {
                    SubCategories subCategory = subCatSnapshot.getValue(SubCategories.class);
                    subCategoriesAdapter.add(subCategory);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void getGigs(String sub_category_id) {
        mGigsDatabaseReference.orderByChild(SUB_CAT_ID).equalTo(sub_category_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gigList.clear();
                for (DataSnapshot gigSnapshot : dataSnapshot.getChildren()) {
                    Gigs gig = gigSnapshot.getValue(Gigs.class);
                    gigList.add(gig);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setUpFragment(Fragment fragmentNew, TextView fragName, String fragNameVal) {
        fragCount++;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.catFragmentHolder, fragmentNew);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragName.setText(fragNameVal);


    }//without bundle

    //with bundle
    public void setUpFragment(Bundle savedInstanceState, Fragment fragmentNew, TextView fragName, String fragNameVal) {
        fragCount++;
        if (savedInstanceState != null) {
            return;
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentNew.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.catFragmentHolder, fragmentNew).commit();
        fragName.setText(fragNameVal);


    } //with bundle

    public void BackPressed(View view) {
        /*I wrote this to check for the instance of current inflated frag and decide the action on back pressed*/
        Fragment f = fragmentManager.findFragmentById(R.id.catFragmentHolder);
        if (f instanceof SubCategoryViewFragment) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }


    }


}
