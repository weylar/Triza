package com.triza.android.Adapters;
/*Created by Aminu Idris on 13/05/18*/

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Favorites.Favourites;
import com.triza.android.Gigs.Gigs;
import com.triza.android.R;

import java.util.List;


public class GigsAdapterVertical extends RecyclerView.Adapter<GigsAdapterVertical.MyViewHolder> {

    private Context mContext;
    private List<Gigs> gigsList;

    DataSnapshot favouritesDataSnapShot;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFavouritesDatabaseReference;
    private String user_id = "muilat";


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView gigTitle, gigRating, gigNoReview, gigPrice;
        public ImageView gigImage, gigFavorite, gigOption;

        public MyViewHolder(View view) {
            super(view);
            gigTitle = view.findViewById(R.id.gig_title);
            gigRating = view.findViewById(R.id.gig_rating);
            gigNoReview = view.findViewById(R.id.gig_no_review);
            gigPrice = view.findViewById(R.id.gig_price);
            gigImage = view.findViewById(R.id.gig_image);
            gigFavorite = view.findViewById(R.id.gig_favorite);
            gigOption = view.findViewById(R.id.gig_option);
        }
    }


    public GigsAdapterVertical(Context mContext, List<Gigs> gigsList) {
        this.mContext = mContext;
        this.gigsList = gigsList;
    }

    @Override
    public GigsAdapterVertical.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gig_vertical, parent, false);

        return new GigsAdapterVertical.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GigsAdapterVertical.MyViewHolder holder, int position) {
        final Gigs gigs = gigsList.get(position);// Gets the item position
        holder.gigTitle.setText(gigs.getGigTitle());
        holder.gigRating.setText(gigs.getGigRating() + "");
        holder.gigNoReview.setText("(" + gigs.getGigNoReview() + " reviews)");
        holder.gigPrice.setText("Min Price: " + gigs.getMinPrice());
        holder.gigOption.setImageResource(R.drawable.ic_more_vert_black_30dp);


        //create favourites
        //search favourites first tgo see if the gig is already liked or not
        //Instanciate firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFavouritesDatabaseReference = mFirebaseDatabase.getReference().child("favourites");

        //TODO: replace the "muilat" in equalTo to loggedin user id
        mFavouritesDatabaseReference.orderByChild("filter_index").equalTo(user_id + "_" + gigs.getGigId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    favouritesDataSnapShot = dataSnapshot;
                    holder.gigFavorite.setImageResource(R.drawable.ic_favorite_accent_25dp);

                } else {
                    holder.gigFavorite.setImageResource(R.drawable.ic_favorite_border_black_25dp);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        // loading image using Glide library
        Glide.with(mContext).load(gigs.getGigImageUrl()).into(holder.gigImage);

        holder.gigOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.gigOption);
            }
        });

        holder.gigFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (favouritesDataSnapShot != null) {
                    //user already add the gigs to favourites, so delete it from favourites
                    mFavouritesDatabaseReference.removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            holder.gigFavorite.setImageResource(R.drawable.ic_favorite_border_black_25dp);
                            Toast.makeText(mContext, "Gig removed from favourites", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //user yet to add this gig, so add to favourites
                    mFavouritesDatabaseReference.push().setValue(new Favourites(user_id, gigs.getGigId(), user_id + "_" + gigs.getGigId()));
                    holder.gigFavorite.setImageResource(R.drawable.ic_favorite_accent_25dp);
                    Toast.makeText(mContext, "Gig added to favourites", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.gig_more, popup.getMenu());
        popup.setOnMenuItemClickListener(new GigsAdapterVertical.MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.seller_profile:
                    Toast.makeText(mContext, "Seller's profle", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.share_gig:
                    Toast.makeText(mContext, "Share gig", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.report_gig:
                    Toast.makeText(mContext, "Report gig", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }

    }

    @Override
    public int getItemCount() {
        return gigsList.size();
    }

}
