package com.triza.android.Home;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.HomeActivity;
import com.triza.android.R;
import com.triza.android.RecyclerItemClickListeners;
import com.triza.android.Search.Search;

public class TrendingGigs extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GigsAdapterVertical gigsAdapterVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_gigs);

        recyclerView = findViewById(R.id.recycler_view_trending_vertical);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //Layout manager in charge of horizontal recycler view
        gigsAdapterVertical = new GigsAdapterVertical(TrendingGigs.this, HomeActivity.gigList);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(gigsAdapterVertical);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListeners(TrendingGigs.this, recyclerView, new RecyclerItemClickListeners.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();

            }
        }));
    }
    public void backPressed(View view){
        finish();
    }
    public void searchClick(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
