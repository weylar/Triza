package com.triza.android.Gigs;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.triza.android.Gigs.data.GigsContract;
import com.triza.android.R;

public class AddGigActivity extends AppCompatActivity implements AddGigOverviewFragment.OnAddGigOverviewListener, AddGigScopeFragment.OnSaveGigScopeListener{

    private Gigs mGig;
    private AddGigOverviewFragment addGigOverviewFragment;
    private AddGigScopeFragment addGigScopeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gig);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.newGigFragmentHolder) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of AddCategoryFragment
            addGigOverviewFragment = new AddGigOverviewFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            addGigOverviewFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'newCatFragmentHolder' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.newGigFragmentHolder, addGigOverviewFragment).commit();
        }

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
        if(uri != null) {
            //ifisertion is successfull continue to the next fragment
            addGigScopeFragment = new AddGigScopeFragment();
//        Bundle args = new Bundle();
//        args.putString(AddGigScopeFragment.ARG_CAT_TITLE, category.getCatTitle());
//        args.putString(AddGigScopeFragment.ARG_CAT_IMAGE_URL, mSelectedImageUrl.toString());//get the local image
//        addGigScopeFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.newGigFragmentHolder, addGigScopeFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        }

    }

    public void saveOverview(View view) {
        addGigOverviewFragment.onSaveOverviewButtonPressed();
    }

    @Override
    public void onSaveGigScope(Uri uri) {

    }
}
