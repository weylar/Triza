
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

import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.Dialogs.ConfirmDeleteAllFav;
import com.triza.android.Home.Gigs;
import com.triza.android.Home.HomeFragment;
import com.triza.android.R;
import com.triza.android.RecyclerItemClickListeners;
import com.triza.android.RecyclerItemTouchHelper;
import com.triza.android.Search.Search;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, ConfirmDeleteAllFav.DeleteAll{
    ImageView  search;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GigsAdapterVertical gigsAdapterVertical;
    LinearLayout favoriteLayout;
    TextView deleteAll;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment_layout, container, false);

        deleteAll = view.findViewById(R.id.delete_all);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDeleteAllFav dialog = new ConfirmDeleteAllFav();

                /*this help to taget this particular fragment in  the main activity*/
                dialog.setTargetFragment(FavoritesFragment.this, 0);

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
        gigsAdapterVertical = new GigsAdapterVertical(getActivity(), HomeFragment.gigList);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(gigsAdapterVertical);

        /*This set my custom clickclass to recyclerview*/
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListeners(getActivity(), recyclerView, new RecyclerItemClickListeners.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();

            }
        }));

        /*This implement the swipe fuction of recycler viewer*/
        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);


        return view;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof GigsAdapterVertical.MyViewHolder){
            String itemName = GigsAdapterVertical.gigsList.get(viewHolder.getAdapterPosition()).getGigTitle();// a dummy way of getting the gig title to display on snackbar whenm swipped

        /*Backup of removed item for undo purpose*/
        final Gigs deletedGigs = GigsAdapterVertical.gigsList.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        /*This removes the item from the recycler view*/
        gigsAdapterVertical.removeItem(viewHolder.getAdapterPosition());


        Snackbar snackbar = Snackbar.make(favoriteLayout, itemName + "deleted from favorites!", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*undo by restoring gig*/
                gigsAdapterVertical.restoreItem(deletedGigs, deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
        }
    }

    @Override
    public void deleteAll() {
        /*This removes the all item from the recycler view*/
        gigsAdapterVertical.removeAllItem();
    }
}
