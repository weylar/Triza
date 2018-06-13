package com.triza.android.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.HomeActivity;
import com.triza.android.R;
import com.triza.android.Search.Search;

public class FeaturedGigs extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GigsAdapterVertical gigsAdapterVertical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feautured_gigs);

        recyclerView = findViewById(R.id.recycler_view_featured_vertical);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //Layout manager in charge of horizontal recycler view
        gigsAdapterVertical = new GigsAdapterVertical(FeaturedGigs.this, HomeActivity.gigList);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(gigsAdapterVertical);

    }
    public void backPressed(View view){
        finish();
    }
    public void searchClick(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
