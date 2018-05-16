
package com.triza.android.Favorites;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.Home.HomeFragment;
import com.triza.android.R;
import com.triza.android.Search.Search;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    ImageView  search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GigsAdapterVertical gigsAdapterVertical;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*This is the dummy method of data directly from Homefragment*/
        HomeFragment.dummyData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment_layout, container, false);

        search = view.findViewById(R.id.ic_search_fav);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSearch = new Intent(getActivity(), Search.class);
                startActivity(moveToSearch);
            }
        });
        recyclerView = view.findViewById(R.id.recycler_view_favorites);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //Layout manager in charge of horizontal recycler view
        gigsAdapterVertical = new GigsAdapterVertical(getActivity(), HomeFragment.gigList);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(gigsAdapterVertical);



        return view;
    }

}
