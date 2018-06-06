package com.triza.android.Categories;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.CategoriesAdapter;
import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.Gigs.Gigs;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity
        implements  CategoryViewFragment.OnCategorySelectedInteractionListener,
        SubCategoryViewFragment.OnSubCategorySelectedInteractionListener,
        GigsListingFragment.OnFragmentInteractionListener{
    Context context = CategoryActivity.this;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
    private DatabaseReference mSubCategoriesDatabaseReference;
    private DatabaseReference mGigsDatabaseReference;


    CategoryViewFragment categoryViewFragment;
    SubCategoryViewFragment subCategoryViewFragment;
    GigsListingFragment gigsListingFragment;

    public static ArrayList<Gigs> gigList;

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragmentOld;
    int fragCount = 0;
    String[] frags = {"","Categories","",""};
    TextView fragName;


    public static CategoriesAdapter categoriesAdapter;
    public static ArrayAdapter<SubCategories> subCategoriesAdapter;
    public static GigsAdapterVertical gigsAdapterVertical;

//    public static List<SubCategories> subCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        Set toolbar as actionbar
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

//        GridView gridView = findViewById(R.id.cat_grid_list);


        //Instanciate firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("categories");
        mSubCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("sub_categories");
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child("gigs");

        ArrayList categories = new ArrayList();
        gigList = new ArrayList<>();

        categoriesAdapter = new CategoriesAdapter(this, categories);
        gigsAdapterVertical = new GigsAdapterVertical(this, categories);
        subCategoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

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

//        gridView.setAdapter(categoriesAdapter);

        fragName = findViewById(R.id.frag_name);


        // Check whether the activity is using the layout version with
        // the catFragmentHolder FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.catFragmentHolder) != null) {

            fragmentManager = getSupportFragmentManager();
            categoryViewFragment = new CategoryViewFragment();
            subCategoryViewFragment = new SubCategoryViewFragment();
            gigsListingFragment = new GigsListingFragment();

            /*This method sets the categoryViewFragment by activity launch*/
            setUpFragment(savedInstanceState, categoryViewFragment, fragName, "Categories");

        }
    }

    public void setUpFragment(Fragment fragmentNew, TextView fragName, String fragNameVal) {
        fragCount++;

        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentNew.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.catFragmentHolder, fragmentNew);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragName.setText(fragNameVal);


    }//without bundle

    //with bundle
    public void setUpFragment(Bundle savedInstanceState, Fragment fragmentNew, TextView fragName, String fragNameVal) {
        fragCount++;

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentOld = fragmentManager.findFragmentById(R.id.catFragmentHolder);


        fragmentNew.setArguments(getIntent().getExtras());

        if (fragmentOld != null) {
            fragmentTransaction.remove(fragmentOld);
        }
        fragmentTransaction.add(R.id.catFragmentHolder, fragmentNew).commit();

        fragName.setText(fragNameVal);


    } //with bundle

    //    On back pressed method
     public void BackPressed(View view){
        finish();
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
//        if (fragCount == 3) {
//
//            fragName.setText(frags[2]);
//        }

    }


    public void searchClick(View view){
        Intent moveToSearch = new Intent(context, AddCategoryActivity.class);
        startActivity(moveToSearch);

    }

    @Override
    public void onCategorySelectedInteraction(Categories category) {

        getSubCategories(category.getCatId());

        //start SubCategoryViewFragment
        setUpFragment(subCategoryViewFragment,fragName,category.getCatTitle());
        frags[2]= category.getCatTitle();


    }

    public void getSubCategories(String category_id) {
        final List<SubCategories> subCategoriesList = new ArrayList<>();
        mSubCategoriesDatabaseReference.orderByChild("catId").equalTo(category_id).addValueEventListener(new ValueEventListener() {
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

    public void getGigs(String sub_category_id){
        //fetch gigs from firebase
        mGigsDatabaseReference.orderByChild("subCatId").equalTo(sub_category_id).addValueEventListener(new ValueEventListener() {
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

    @Override
    public void onSubCategorySelectedInteraction(SubCategories subCategory) {
        //get Gigs
        getGigs(subCategory.getSubCatId());

        //start GigsListingFragment
        setUpFragment(gigsListingFragment,fragName,subCategory.getSubCatTitle());


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
