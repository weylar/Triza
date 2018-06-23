package com.triza.android.Home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Admin.AddCategoryActivity;
import com.triza.android.Categories.CategoryActivity;
import com.triza.android.Favorites.FavoritesFragment;
import com.triza.android.Favorites.Favourites;
import com.triza.android.Gigs.AddGigActivity;
import com.triza.android.Gigs.Gigs;
import com.triza.android.Profile.ProfileFragment;
import com.triza.android.R;
import com.triza.android.Search.Search;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    final String HOME_FRAGMENT = "home-fragment";
    Fragment fragmentNew;
    Context context = HomeActivity.this;
    final String FAVOURITES_FRAGMENT = "favourites-fragment";
    final String PROFILE_FRAGMENT = "profile-fragment";
    Fragment fragmentReference;


    public static ArrayList<Gigs> gigList;
    public static ArrayList<Favourites> favouriteGigs;
    private String user_id = "muib";



    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGigsDatabaseReference;
    private DatabaseReference mFavouritesDatabaseReference;
    private DataSnapshot favouritesDataSnapshots;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    displaySelectedFragment(HOME_FRAGMENT);

                    return true;
                case R.id.navigation_favorite:

                    displaySelectedFragment(FAVOURITES_FRAGMENT);

                    return true;
                case R.id.navigation_add:
                    Intent intent = new Intent(context, AddGigActivity.class);
                    startActivity(intent);

                    return true;
                case R.id.navigation_chat:

                    return true;
                case R.id.navigation_profile:
                    displaySelectedFragment(PROFILE_FRAGMENT);

                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager = getFragmentManager(); //Initializing the fragment manaager in onCreate


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Instanciate firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child("gigs");
        mFavouritesDatabaseReference = mFirebaseDatabase.getReference().child("favourites");

        gigList = new ArrayList<>(); //Ths is where i instanciated my custom class and recycler adapter
        favouriteGigs = new ArrayList<>();


        //fetch gigs from firebase
        mGigsDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot gigSnapshot : dataSnapshot.getChildren()) {
                    Gigs gig = gigSnapshot.getValue(Gigs.class);
                    gigList.add(gig);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mFavouritesDatabaseReference.orderByChild("userId").equalTo(user_id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        favouriteGigs.clear();//clear the list before adding all the new data
                        for (DataSnapshot favGigSnapshot : dataSnapshot.getChildren()) {
                            final Favourites favGig = favGigSnapshot.getValue(Favourites.class);

                            //get the gig of the fav
                            mGigsDatabaseReference.orderByChild("gigId").equalTo(favGig.getGigId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1) {

                                    if (dataSnapshot1.exists()) {
                                        for (DataSnapshot gigSnapshot : dataSnapshot1.getChildren()) {

                                            Gigs gig = gigSnapshot.getValue(Gigs.class);
                                            favGig.setGig(gig);

                                            //add favGig to the list of favouriteGigs
                                            favouriteGigs.add(favGig);
                                        }
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




    }

    @Override
    protected void onResume() {
        super.onResume();
        displaySelectedFragment(HOME_FRAGMENT); //calling method that displays home fragment automatically

    }

    /*This function displays home fragment automatically on oncreate */
    private void displaySelectedFragment(String fragmentKind) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentReference = fragmentManager.findFragmentById(R.id.fragmentHolder);
        switch (fragmentKind) {
            case HOME_FRAGMENT:
                fragmentNew = new HomeFragment();
                break;
            case FAVOURITES_FRAGMENT:
                fragmentNew = new FavoritesFragment();
                break;
            case PROFILE_FRAGMENT:
                fragmentNew = new ProfileFragment();
                break;
            default:
                fragmentNew = new Fragment();
                break;
        }
        fragmentTransaction.replace(R.id.fragmentHolder, fragmentNew);
        fragmentTransaction.commit();


    }


    //    On category click method
    public void categoriesClick(View view) {
        Intent intent = new Intent(context, CategoryActivity.class);
        startActivity(intent);
    }

    //    On search click method
    public void searchClick(View view) {
        Intent moveToSearch = new Intent(context, Search.class);
        startActivity(moveToSearch);
    }

    //    On preference click method
    public void preferenceClick(View view) {
        Snackbar snackbar = Snackbar.make(view, "You clicked prefernce icon", Snackbar.LENGTH_LONG);
        snackbar.show();
//        TODO: MOVE TO PREFERENCE ACTIVITY
    }

    //handle the temp addcategory image/button on d tool bar
    public void addCategory(View view) {
        Intent addCategoryIntent = new Intent(context, AddCategoryActivity.class);
        startActivity(addCategoryIntent);
    }
}
