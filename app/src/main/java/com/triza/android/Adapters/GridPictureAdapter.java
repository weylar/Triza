package com.triza.android.Adapters;


import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.triza.android.Gigs.AddGigGallery.AddGigGalleryFragment;
import com.triza.android.Gigs.AddGigGallery.AddPictureClass;
import com.triza.android.R;

import java.util.List;


public class GridPictureAdapter extends ArrayAdapter<AddPictureClass> {
    private AddPictureClass addPictureClass;
    private AddGigGalleryFragment addGigGalleryFragment;
    private List<AddPictureClass> list;
    private LinearLayout emptyLinear;

    public GridPictureAdapter(Activity context, List<AddPictureClass> list, LinearLayout emptyLinear) {
        super(context, 0, list);
        this.list = list;
        this.emptyLinear = emptyLinear;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        addPictureClass = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_add_picture, parent, false);
        }


        final ImageView picture = convertView.findViewById(R.id.picturesPlaceHolder);
        ImageView editIcon = convertView.findViewById(R.id.editIcon);

        picture.setImageBitmap(addPictureClass.getBitmap());

        Glide.with(getContext())
                .load(R.drawable.ic_cancel_black_24dp)
                .into(editIcon);

        /*Removes image on edit icon click*/
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGigGalleryFragment = new AddGigGalleryFragment();
                final AddPictureClass removedPicture = list.remove(position);
                notifyDataSetChanged();

                /*Add images back to its position on undo snack click*/
                Snackbar.make(view, R.string.image_removed, Snackbar.LENGTH_SHORT).setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        list.add(position, removedPicture);
                        notifyDataSetChanged();
                    }
                }).setActionTextColor(Color.YELLOW).show();

                changeEmptyLinearState(emptyLinear);
            }


        });
        return convertView;
    }

    /*Method that shows empty if there is no image to display*/
    private void changeEmptyLinearState(LinearLayout emptyLinear) {
        if (getCount() > 0) {
            emptyLinear.setVisibility(View.GONE);
        } else {
            emptyLinear.setVisibility(View.VISIBLE);
        }
    }

}

