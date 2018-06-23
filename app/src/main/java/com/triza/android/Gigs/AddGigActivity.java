package com.triza.android.Gigs;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.triza.android.Gigs.AddGigDescription.AddGigDescriptionFragment;
import com.triza.android.Gigs.AddGigGallery.AddGigGalleryFragment;
import com.triza.android.Gigs.AddGigOverview.AddGigOverviewFragment;
import com.triza.android.Gigs.AddGigScope.AddGigScopeFragment;
import com.triza.android.Home.HomeActivity;
import com.triza.android.R;


public class AddGigActivity extends AppCompatActivity /*implements OnDataSentListener*/ {


    private FrameLayout one, two, three, four;
    private Button buttonSend;
    private View oneFocus, twoFocus, threeFocus, fourFocus, oneFocusee, twoFocusee, threeFocusee, fourFocusee;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment fragmentOverview;
    private Fragment fragmentScope;
    private Fragment fragmentDescription;
    private Fragment fragmentGallery;
    private int fragCount;
    private TextView fragName;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGigsDatabaseReference;


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
        buttonSend = findViewById(R.id.sendBtn);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child("gigs");

        // Check whether the activity is using the layout version with the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.newGigFragmentHolder) != null) {
            fragmentManager = getSupportFragmentManager();
            fragmentOverview = new AddGigOverviewFragment();
            fragmentScope = new AddGigScopeFragment();
            fragmentDescription = new AddGigDescriptionFragment();
            fragmentGallery = new AddGigGalleryFragment();

            /*This populates the overview fragment by default in oncreate*/
            fragCount = 1;
            setUpFragment(fragmentOverview, oneFocus, oneFocusee, fragName, getString(R.string.overview));

        }
    }

    public void onSaveAndContinueButton(View view) {
        fragCount += 1;
        if (fragCount == 2) {
            setUpFragment(fragmentScope, twoFocus, twoFocusee, fragName, getString(R.string.scope_pricing));
        } else if (fragCount == 3) {
            setUpFragment(fragmentDescription, threeFocus, threeFocusee, fragName, getString(R.string.description));
        } else if (fragCount == 4) {
            setUpFragment(fragmentGallery, fourFocus, fourFocusee, fragName, getString(R.string.gallery));
            buttonSend.setText(R.string.post_gig);
            buttonSend.setEnabled(false);

        }


    }


    public void onCancel(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void help(View v){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.addDefaultShareMenuItem();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse("https://triza.com/help"));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragCount > 0) {
            fragCount -= 1;
        }
        if (fragCount == 0) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if (fragCount == 1) {
            oneFocus.setVisibility(View.VISIBLE);
            oneFocusee.setVisibility(View.VISIBLE);
            twoFocus.setVisibility(View.GONE);
            twoFocusee.setVisibility(View.GONE);
            threeFocus.setVisibility(View.GONE);
            threeFocusee.setVisibility(View.GONE);
            fourFocus.setVisibility(View.GONE);
            fourFocusee.setVisibility(View.GONE);
            fragName.setText(getString(R.string.overview));
            buttonSend.setText(R.string.continue_button);
            buttonSend.setEnabled(true);
        } else if (fragCount == 2) {
            oneFocus.setVisibility(View.VISIBLE);
            oneFocusee.setVisibility(View.VISIBLE);
            twoFocus.setVisibility(View.VISIBLE);
            twoFocusee.setVisibility(View.VISIBLE);
            threeFocus.setVisibility(View.GONE);
            threeFocusee.setVisibility(View.GONE);
            fourFocus.setVisibility(View.GONE);
            fourFocusee.setVisibility(View.GONE);
            fragName.setText(R.string.scope_pricing);
            buttonSend.setText(R.string.continue_button);
            buttonSend.setEnabled(true);
        } else if (fragCount == 3) {
            oneFocus.setVisibility(View.VISIBLE);
            oneFocusee.setVisibility(View.VISIBLE);
            twoFocus.setVisibility(View.VISIBLE);
            twoFocusee.setVisibility(View.VISIBLE);
            threeFocus.setVisibility(View.VISIBLE);
            threeFocusee.setVisibility(View.VISIBLE);
            fourFocus.setVisibility(View.GONE);
            fourFocusee.setVisibility(View.GONE);
            fragName.setText(R.string.description);
            buttonSend.setText(R.string.continue_button);
            buttonSend.setEnabled(true);

        }

    }


    private void setUpFragment(Fragment fragmentNew, View viewYellow, View viewGreen, TextView fragName, String fragNameVal) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.newGigFragmentHolder, fragmentNew);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        viewYellow.setVisibility(View.VISIBLE);
        viewGreen.setVisibility(View.VISIBLE);
        fragName.setText(fragNameVal);


    }


    /** @Override public void onDataSent(byte[] data) {
    Bundle bundle = new Bundle();
    bundle.putByteArray(BITMAP, data);
    new AddGigGalleryFragment().setArguments(bundle);
    }**/
}
