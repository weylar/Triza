
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.FavouritesAdapterVertical;
import com.triza.android.Dialogs.ConfirmDeleteAllFav;
import com.triza.android.Gigs.Gigs;
import com.triza.android.HomeActivity;
import com.triza.android.R;
import com.triza.android.RecyclerItemClickListeners;
import com.triza.android.RecyclerItemTouchHelper;
import com.triza.android.Search.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoritesFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, ConfirmDeleteAllFav.DeleteAll{
    ImageView  search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FavouritesAdapterVertical favouritesAdapterVertical;
    public static LinearLayout favoriteLayout, emptyFavorites;
    public static TextView deleteAll;

    List<Gigs> favouriteGigs = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFavouritesDatabaseReference;
    private DataSnapshot favouritesDataSnapshots;

    private String user_id = "muilat";

//    //needed to backup the id of the swap-removed fav
//    String favId;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the favourite gigs loaded from the HomeActivity
        favouriteGigs = HomeActivity.favouriteGigs;
        //Instanciate firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFavouritesDatabaseReference = mFirebaseDatabase.getReference().child("favourites");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment_layout, container, false);

        emptyFavorites = view.findViewById(R.id.empty_favorites);

        deleteAll = view.findViewById(R.id.delete_all);




        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDeleteAllFav dialog = new ConfirmDeleteAllFav();

                /*this help to taget this particular fragment in  the main activity*/
                dialog.setTargetFragment(FavoritesFragment.this, 0);

                dialog.show(getFragmentManager(), "123");
                dialog.show(getFragmentManager(), "123");
            }
        });

        favoriteLayout = view.findViewById(R.id.favorite_layout);



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
        favouritesAdapterVertical = new FavouritesAdapterVertical(getActivity(), favouriteGigs);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(favouritesAdapterVertical);

//        /*This set my custom clickclass to recyclerview*/
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListeners(getActivity(), recyclerView, new RecyclerItemClickListeners.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //TODO: go to gig details, remove the snackbar
//                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
//
//            }
//        }));

        /*This implement the swipe fuction of recycler viewer*/
        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        /*Condition to check if to display empty fav or recycler fav*/
        //hide delete_all if there is no favouriteGigs
        if (favouriteGigs == null){
            deleteAll.setVisibility(View.GONE);
            emptyFavorites.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof FavouritesAdapterVertical.MyViewHolder){
            String itemName = FavouritesAdapterVertical.gigsList.get(viewHolder.getAdapterPosition()).getGigTitle();// a dummy way of getting the gig title to display on snackbar whenm swipped

        /*Backup of removed item for undo purpose*/
        final Gigs deletedGigs = FavouritesAdapterVertical.gigsList.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        //ref where filter_index = muilat_123-kbdhd-61yddn-
        mFavouritesDatabaseReference.orderByChild("filter_index").equalTo(user_id + "_" + deletedGigs.getGigId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            mFavouritesDatabaseReference.removeValue();
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
                mFavouritesDatabaseReference.push().setValue(new Favourites(user_id, deletedGigs.getGigId(), user_id + "_" + deletedGigs.getGigId()));


                /*undo by restoring gig*/
                favouritesAdapterVertical.restoreItem(deletedGigs, deletedIndex);
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
                    Favourites favGig = favGigSnapshot.getValue(Favourites.class);
//                    favouriteGigs.add(gig);
                    Map<String, Object> map = new HashMap<>();
                    map.put("/" + favGig.getFavouriteId()+ "/",null);
                    mFavouritesDatabaseReference.updateChildren(map);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
