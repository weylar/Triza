package com.triza.android.Gigs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.triza.android.Gigs.AddDescriptionFragment.AddGigDescriptionFragment;
import com.triza.android.Gigs.AddGalleryGig.AddGigGalleryFragment;
import com.triza.android.Gigs.AddGigScope.AddGigScopeFragment;
import com.triza.android.HomeActivity;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

public class AddGigActivity extends AppCompatActivity {

    private Gigs mGig;
    private AddGigOverviewFragment addGigOverviewFragment;
    private AddGigScopeFragment addGigScopeFragment;
    private FrameLayout one, two, three, four;
    private View oneFocus, twoFocus, threeFocus, fourFocus, oneFocusee, twoFocusee, threeFocusee, fourFocusee;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment fragmentOld;
    private Fragment fragmentOverview;
    private Fragment fragmentScope;
    private Fragment fragmentDescription;
    Fragment fragmentGallery;
    private int fragCount = 0;
    private TextView fragName;

    //firebase variable
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGigsDatabaseReference;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mGigsImageStorageReference;

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
        fragName = findViewById(R.id.frag_name);
        oneFocusee = findViewById(R.id.one_focusee);
        twoFocusee = findViewById(R.id.two_focusee);
        threeFocusee = findViewById(R.id.three_focusee);
        fourFocusee = findViewById(R.id.four_focusee);

        List<Gigs> gigs = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child("gigs");


        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.newGigFragmentHolder) != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentOverview = new AddGigOverviewFragment();
            fragmentScope = new AddGigScopeFragment();
            fragmentDescription = new AddGigDescriptionFragment();
            fragmentGallery = new AddGigGalleryFragment();


            /*This method sets my overview fragment by activity launch*/
            setUpFragment(savedInstanceState, fragmentOverview, oneFocus, oneFocusee, fragName, "Overview");

        }
    }


    public void onSaveAndContinueButton(View view) {
        if (fragCount == 3) {
            return;
        } else {
            fragCount += 1; //This increments by 1
        }
        if (fragCount == 1) {
            setUpFragment(fragmentScope, twoFocus, twoFocusee, fragName, "Scope & Pricing");
        } else if (fragCount == 2) {
            setUpFragment(fragmentDescription, threeFocus, threeFocusee, fragName, "Description");
        } else if (fragCount == 3) {
            setUpFragment(fragmentGallery, fourFocus, fourFocusee, fragName, "Gallery");
        }

        //addGigOverviewFragment.onSaveOverviewButtonPressed();
    }


    public void backPressed(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUpFragment(Bundle savedInstanceState, Fragment fragmentNew, View viewYellow, View viewBlue, TextView fragName, String fragNameVal) {
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

        viewYellow.setVisibility(View.VISIBLE);
        viewBlue.setVisibility(View.VISIBLE);
        fragName.setText(fragNameVal);


    } //with bundle

    /*Lol, my first trial of method overridden. Calling the same method name with diff parameters*/
    private void setUpFragment(Fragment fragmentNew, View viewYellow, View viewBlue, TextView fragName, String fragNameVal) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentNew.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.newGigFragmentHolder, fragmentNew).commit();
        fragmentTransaction.addToBackStack(null);
        viewYellow.setVisibility(View.VISIBLE);
        viewBlue.setVisibility(View.VISIBLE);
        fragName.setText(fragNameVal);


    }//without bundle

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragCount -= 1;
        if (fragCount < 0) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        if (fragCount == 0) {
            oneFocus.setVisibility(View.VISIBLE);
            twoFocus.setVisibility(View.GONE);
            twoFocusee.setVisibility(View.GONE);
            threeFocus.setVisibility(View.GONE);
            threeFocusee.setVisibility(View.GONE);
            fourFocus.setVisibility(View.GONE);
            fourFocusee.setVisibility(View.GONE);
            fragName.setText("Overview");
        }
        if (fragCount == 1) {
            oneFocus.setVisibility(View.VISIBLE);
            twoFocus.setVisibility(View.VISIBLE);
            threeFocus.setVisibility(View.GONE);
            threeFocusee.setVisibility(View.GONE);
            fourFocus.setVisibility(View.GONE);
            fourFocusee.setVisibility(View.GONE);
            fragName.setText("Scope & Pricing");
        }
        if (fragCount == 2) {
            oneFocus.setVisibility(View.VISIBLE);
            twoFocus.setVisibility(View.VISIBLE);
            threeFocus.setVisibility(View.VISIBLE);
            fourFocus.setVisibility(View.GONE);
            fourFocusee.setVisibility(View.GONE);
            fragName.setText("Description");

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
