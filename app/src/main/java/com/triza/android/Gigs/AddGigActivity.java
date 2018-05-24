package com.triza.android.Gigs;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.triza.android.Gigs.data.GigsContract;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

public class AddGigActivity extends AppCompatActivity implements AddGigOverviewFragment.OnAddGigOverviewListener, AddGigScopeFragment.OnSaveGigScopeListener{

    private Gigs mGig;
    private AddGigOverviewFragment addGigOverviewFragment;
    private AddGigScopeFragment addGigScopeFragment;

    //firebase variable
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGigsDatabaseReference;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mGigsImageStorageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gig);


        List<Gigs> gigs = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child("gigs");

//
//        mGig = new Gigs("Professional website development in a week","LCxil933ovd-LgNarZV","gdjd-she344","Professional website wedDevelopment");
//        mGig.setAdditionalRevision(true);
//        mGig.setDeliveryTime("One week");
//        mGig.setExtraFastDelivery(true);
//        mGig.setFaq("is the site dynamic");
//        mGig.setFaqAnswer("it is professional,dynamicaland elastic");
//        mGig.setMinPrice(5000);
//        mGig.setPackageName("Web Development");
//        mGig.setPackagDesc("Developing websites that meets the standard of the ever demanding technology");
//        mGig.setGigImageUrl("http://firebasestorage.googleapis.com/v0/b/triza2-87859.appsot.com/o/categories_images%2Fcategory_20-24-2018%2014%3A164?alt=media&token=c4c46d2d-6430-48bf-bd49-29ba360e5c33");
//        gigs.add(mGig);
//
//        mGig = new Gigs("Professional website development in a week","LCxil933ovd-LgNarZV","LCxil9lbGylRxJQ5SMc","Professional website wedDevelopment");
//        mGig.setAdditionalRevision(true);
//        mGig.setDeliveryTime("One week");
//        mGig.setExtraFastDelivery(true);
//        mGig.setFaq("is the site dynamic");
//        mGig.setFaqAnswer("it is professional,dynamical and elastic");
//        mGig.setMinPrice(5000);
//        mGig.setPackageName("Web Development");
//        mGig.setPackagDesc("Developing websites that meets the standard of the ever demanding technology");
//        mGig.setGigImageUrl("http://firebasestorage.googleapis.com/v0/b/triza2-87859.appsot.com/o/categories_images%2Fcategory_20-24-2018%2014%3A164?alt=media&token=c4c46d2d-6430-48bf-bd49-29ba360e5c33");
//        gigs.add(mGig);
//
//        mGig = new Gigs("Awesome website development from scratch","LDBmCWxBwjcjQGjitnF","LDBMCXBAH5YbUBGN6n4","Scratch website wedDevelopment");
//        mGig.setAdditionalRevision(false);
//        mGig.setDeliveryTime("One week");
//        mGig.setExtraFastDelivery(true);
//        mGig.setFaq("is the site dynamic");
//        mGig.setFaqAnswer("it is professional,dynamicaland elastic");
//        mGig.setMinPrice(100);
//        mGig.setPackageName("Web Development");
//        mGig.setPackagDesc("Developing websites that meets the standard of the ever demanding technology");
//        mGig.setGigImageUrl("http://firebasestorage.googleapis.com/v0/b/triza2-87859.appsot.com/o/categories_images%2Fcategory_20-24-2018%2014%3A164?alt=media&token=c4c46d2d-6430-48bf-bd49-29ba360e5c33");
//        gigs.add(mGig);
//
//        for (int i = 0 ; i< gigs.size(); i++){
//            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
//            f.setLenient(false);
//            try {
//                gigs.get(i).setDateAdded(f.parse(f.format(new Date())).getTime());
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            //add empty data and get the pushId/key
//            String gigId = mGigsDatabaseReference.push().getKey();
//            gigs.get(i).setGigId(gigId);
//            //creat a child using the created/gotten (pushId) key of the empty data created
//            mGigsDatabaseReference.child(gigId).setValue(gigs.get(i));
//
//
//
//
//        }



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
    public void onAddGigOverview(String gig_title, String category_id, String sub_cat_id, String search_tag) {
        mGig = new Gigs(gig_title, category_id, sub_cat_id, search_tag);

        // Insert new gig data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();
        // Put the data into the ContentValues
        contentValues.put(GigsContract.GigsEntry.COLUMN_GIG_TITLE, gig_title);
        //contentValues.put(GigsContract.GigsEntry.COLUMN_GIG_DESC, gig_desc);
        contentValues.put(GigsContract.GigsEntry.COLUMN_CATEGORY_ID, category_id);
        contentValues.put(GigsContract.GigsEntry.COLUMN_SUB_CAT_ID, sub_cat_id);
        contentValues.put(GigsContract.GigsEntry.COLUMN_SEARCH_TAG, search_tag);

        // Insert the content values via a ContentResolver
        //Uri uri = getContentResolver().insert(GigsContract.GigsEntry.CONTENT_URI, contentValues);

        //if(uri != null) {
            //ifisertion is successfull continue to the next fragment
            addGigScopeFragment = new AddGigScopeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.newGigFragmentHolder, addGigScopeFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        //}

    }

    public void saveOverview(View view) {
        addGigOverviewFragment.onSaveOverviewButtonPressed();
    }

    @Override
    public void onSaveGigScope(Uri uri) {

    }
}
