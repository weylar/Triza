
package com.triza.android.Favorites;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.FavouritesAdapterVertical;
import com.triza.android.Home.HomeActivity;
import com.triza.android.R;
import com.triza.android.RecyclerItemTouchHelper;
import com.triza.android.Search.Search;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, ConfirmDeleteAllFav.DeleteAll {
    public  ImageView deleteAll, search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FavouritesAdapterVertical favouritesAdapterVertical;
    public LinearLayout favoriteLayout, emptyFavorites;
    public List<Favourites> favouriteGigs = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFavouritesDatabaseReference;
    private DatabaseReference mGigsDatabaseReference;
    private DataSnapshot favouritesDataSnapshots;

    private String user_id = "muib";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        favouriteGigs = HomeActivity.favouriteGigs;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment_layout, container, false);
        favoriteLayout = view.findViewById(R.id.favorite_layout);
        search = view.findViewById(R.id.ic_search_fav);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFavouritesDatabaseReference = mFirebaseDatabase.getReference().child("favourites");
        mGigsDatabaseReference = mFirebaseDatabase.getReference().child("gigs");
        emptyFavorites = view.findViewById(R.id.empty_favorites);
        deleteAll = view.findViewById(R.id.delete_all);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDeleteAllFav confirmDeleteAllFav = new ConfirmDeleteAllFav();
                confirmDeleteAllFav.setTargetFragment(FavoritesFragment.this, 1);
                confirmDeleteAllFav.show(getFragmentManager(), "123");


            }
        });

        if (favouriteGigs.size() > 0) {
            deleteAll.setVisibility(View.VISIBLE);
        }else{
            emptyFavorites.setVisibility(View.VISIBLE);
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSearch = new Intent(getActivity(), Search.class);
                startActivity(moveToSearch);
            }
        });
        recyclerView = view.findViewById(R.id.recycler_view_favorites);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //Layout manager in charge of horizontal recycler view
        favouritesAdapterVertical = new FavouritesAdapterVertical(getActivity(), favouriteGigs);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(favouritesAdapterVertical);


        /*This implement the swipe fuction of recycler viewer*/
        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback)
                .attachToRecyclerView(recyclerView);


        return view;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction,
                         int position) {
        if (viewHolder instanceof FavouritesAdapterVertical.MyViewHolder) {
            String itemName = FavouritesAdapterVertical.favList.get(viewHolder.getAdapterPosition()).getGig().getGigTitle();// a dummy way of getting the gig title to display on snackbar whenm swipped

            /*Backup of removed item for undo purpose*/
            final Favourites deletedFav = FavouritesAdapterVertical.favList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            //ref where filter_index = muilat_123-kbdhd-61yddn-
            mFavouritesDatabaseReference.orderByChild("filter_index").equalTo(user_id + "_" + deletedFav.getGigId())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot favGigSnapshot : dataSnapshot.getChildren()) {
                                    favGigSnapshot.getRef().removeValue();
                                }

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            /*This removes the item from the recycler view*/
            favouritesAdapterVertical.removeItem(viewHolder.getAdapterPosition(), emptyFavorites, deleteAll);


            Snackbar snackbar = Snackbar.make(favoriteLayout, itemName + "deleted from favorites!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //TODO:resave the item to db
                    mFavouritesDatabaseReference.push()
                            .setValue(new Favourites(user_id, deletedFav.getGigId(), user_id + "_" + deletedFav.getGigId()));


                    /*undo by restoring gig*/
                    favouritesAdapterVertical.restoreItem(deletedFav, deletedIndex, emptyFavorites, deleteAll);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }


    @Override
    public void deleteAll() {
        favouritesAdapterVertical.removeAllItem(emptyFavorites, deleteAll);

        mFavouritesDatabaseReference.orderByChild("userId").equalTo(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot favGigSnapshot : dataSnapshot.getChildren()) {
                    favGigSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

