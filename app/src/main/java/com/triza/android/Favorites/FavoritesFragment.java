
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.Gigs.Gigs;
import com.triza.android.Home.HomeFragment;
import com.triza.android.R;
import com.triza.android.Search.Search;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    ImageView  search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GigsAdapterVertical gigsAdapterVertical;
    TextView delte_all_textView;

    List<Gigs> favouriteGigs = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFavouritesDatabaseReference;
    private DataSnapshot favouritesDataSnapshots;

    private String user_id = "muilat";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment_layout, container, false);

        //search favourites first tgo see if the gig is already liked or not
        //Instanciate firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFavouritesDatabaseReference = mFirebaseDatabase.getReference().child("favourites");

        mFavouritesDatabaseReference.orderByChild("userId").equalTo(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favouritesDataSnapshots = dataSnapshot;
                for (DataSnapshot favGigSnapshot : dataSnapshot.getChildren()) {
                    Gigs gig = favGigSnapshot.getValue(Gigs.class);
                    favouriteGigs.add(gig);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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
        gigsAdapterVertical = new GigsAdapterVertical(getActivity(), HomeFragment.favouriteGigs);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(gigsAdapterVertical);


        delte_all_textView = view.findViewById(R.id.delete_all);

        delte_all_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFavouritesDatabaseReference.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(getActivity(), "All Gigs removed", Toast.LENGTH_SHORT).show();
                        favouriteGigs.clear();
                        recyclerView.swapAdapter(new GigsAdapterVertical(getActivity(), favouriteGigs), true);
                    }
                });
            }
        });





        return view;
    }

}
