package com.triza.android.Adapters;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.triza.android.Gigs.AddGalleryGig.AddPictureClass;
import com.triza.android.R;

import java.util.List;


public class GridPictureAdapter extends ArrayAdapter<AddPictureClass> {



    //the type is just so we can use the same adapter for both CATEGORIESACTIVITY and the Spinner
    public GridPictureAdapter(Activity context, List<AddPictureClass> pictures) {
        super(context, 0, pictures);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        AddPictureClass addPictureClass = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_add_picture, parent, false);
        }

        ImageView picture = convertView.findViewById(R.id.picturesPlaceHolder);
        ImageView editIcon = convertView.findViewById(R.id.editIcon);


            Glide.with(getContext())
                    .load(addPictureClass.getUri())
                    .into(picture);
            Glide.with(getContext())
                    .load(R.drawable.ic_edit_black_24dp)
                    .into(editIcon);



        return convertView;


    }

}

