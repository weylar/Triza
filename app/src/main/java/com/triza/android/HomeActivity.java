package com.triza.android;

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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.triza.android.Categories.AddCategoryActivity;
import com.triza.android.Categories.CategoryActivity;
import com.triza.android.Favorites.FavoritesFragment;
import com.triza.android.Home.HomeFragment;
import com.triza.android.Profile.ProfileFragment;
import com.triza.android.Search.Search;

public class HomeActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragmentOld;
    Fragment fragmentNew;
    Context context = HomeActivity.this;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentOld = fragmentManager.findFragmentById(R.id.fragmentHolder);
                    fragmentNew = new HomeFragment();

                    if (fragmentOld != null) {
                        fragmentTransaction.remove(fragmentOld);
                    }
                    fragmentTransaction.add(R.id.fragmentHolder, fragmentNew).commit();

                    return true;
                case R.id.navigation_favorite:

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentOld = fragmentManager.findFragmentById(R.id.fragmentHolder);
                    fragmentNew = new FavoritesFragment();

                    if (fragmentOld != null) {
                        fragmentTransaction.remove(fragmentOld);
                    }
                    fragmentTransaction.add(R.id.fragmentHolder, fragmentNew).commit();
                    return true;
                case R.id.navigation_add:

                    return true;
                case R.id.navigation_chat:

                    return true;
                case R.id.navigation_profile:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentOld = fragmentManager.findFragmentById(R.id.fragmentHolder);
                    fragmentNew = new ProfileFragment();

                    if (fragmentOld != null) {
                        fragmentTransaction.remove(fragmentOld);
                    }
                    fragmentTransaction.add(R.id.fragmentHolder, fragmentNew).commit();
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
        displayHomeFragment(); //calling method that displays home fragment automatically


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

    /*This function displays home fragment automatically on oncreate */
    private void displayHomeFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentReference = fragmentManager.findFragmentById(R.id.fragmentHolder);
//        Check if layout is emty or not
        if (fragmentReference == null) {
            Fragment fragment = new HomeFragment();
            fragmentTransaction.add(R.id.fragmentHolder, fragment).commit();
        }

    }

}
