package com.triza.android.Gigs;


import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.triza.android.Gigs.data.GigsContract;
import com.triza.android.HomeActivity;
import com.triza.android.R;

public class AddGigActivity extends AppCompatActivity implements AddGigOverviewFragment.OnAddGigOverviewListener, AddGigScopeFragment.OnSaveGigScopeListener {

    private Gigs mGig;
    private AddGigOverviewFragment addGigOverviewFragment;
    private AddGigScopeFragment addGigScopeFragment;
    FrameLayout one, two, three, four;
    View oneFocus, twoFocus, threeFocus, fourFocus;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragmentOld;
    Fragment fragmentOverview;
    Fragment fragmentScope;
    int fragCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gig);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        oneFocus = findViewById(R.id.one_focus);
        twoFocus = findViewById(R.id.two_focus);
        threeFocus = findViewById(R.id.three_focus);
        fourFocus = findViewById(R.id.four_focus);

        fragmentManager = getSupportFragmentManager();
        fragmentOverview = new AddGigOverviewFragment();
        fragmentScope = new AddGigScopeFragment();


        /*This method sets my overview fragment by activity launch*/
        setUpFragment(savedInstanceState, fragmentOverview, oneFocus);

    }


    @Override
    public void onAddGigOverview(String gig_title, String gig_desc, String category_id, String sub_cat_id, String search_tag) {
        mGig = new Gigs(gig_title, gig_desc, category_id, sub_cat_id, search_tag);

        // Insert new gig data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();
        // Put the data into the ContentValues
        contentValues.put(GigsContract.GigsEntry.COLUMN_GIG_TITLE, gig_title);
        contentValues.put(GigsContract.GigsEntry.COLUMN_GIG_DESC, gig_desc);
        contentValues.put(GigsContract.GigsEntry.COLUMN_CATEGORY_ID, category_id);
        contentValues.put(GigsContract.GigsEntry.COLUMN_SUB_CAT_ID, sub_cat_id);
        contentValues.put(GigsContract.GigsEntry.COLUMN_SEARCH_TAG, search_tag);

        // Insert the content values via a ContentResolver
        Uri uri = getContentResolver().insert(GigsContract.GigsEntry.CONTENT_URI, contentValues);

        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if (uri != null) {
            //ifisertion is successfull continue to the next fragment

//        Bundle args = new Bundle();
//        args.putString(AddGigScopeFragment.ARG_CAT_TITLE, category.getCatTitle());
//        args.putString(AddGigScopeFragment.ARG_CAT_IMAGE_URL, mSelectedImageUrl.toString());//get the local image
//        addGigScopeFragment.setArguments(args);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentOld = fragmentManager.findFragmentById(R.id.fragmentHolder);
            fragmentScope = new AddGigScopeFragment();

            if (fragmentOld != null) {
                fragmentTransaction.remove(fragmentOld);
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.add(R.id.fragmentHolder, fragmentScope).commit();

            fragmentTransaction.addToBackStack(null);


        }

    }

    public void onSaveAndContinueButton(View view) {
        fragCount += 1;

        if (fragCount == 1) {
            setUpFragment(fragmentScope, twoFocus);
        } else if (fragCount == 2) {
            Toast.makeText(this, "im two now", Toast.LENGTH_SHORT).show();
        } else if (fragCount == 3) {
            Toast.makeText(this, "im three now", Toast.LENGTH_SHORT).show();
        }

        //addGigOverviewFragment.onSaveOverviewButtonPressed();
    }


    @Override
    public void onSaveGigScope(Uri uri) {

    }

    public void backPressed(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void setUpFragment(Bundle savedInstanceState, Fragment fragmentNew, View view) {
        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentOld = fragmentManager.findFragmentById(R.id.newGigFragmentHolder);
        fragmentNew.setArguments(getIntent().getExtras());

        if (fragmentOld != null) {
            fragmentTransaction.remove(fragmentOld);
        }
        fragmentTransaction.add(R.id.newGigFragmentHolder, fragmentNew).commit();

        view.setVisibility(View.VISIBLE);


    } //with bundle

    /*Lol, my first trial of method overridden. Calling the same method name with diff parameters*/
    public void setUpFragment(Fragment fragmentNew, View view1) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentNew.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.newGigFragmentHolder, fragmentNew).commit();
        fragmentTransaction.addToBackStack(null);
        view1.setVisibility(View.VISIBLE);


    }//without bundle

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragCount -=1;
        if (fragCount < 0){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
