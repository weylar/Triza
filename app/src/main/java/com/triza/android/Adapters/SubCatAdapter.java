package com.triza.android.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triza.android.Categories.Categories;
import com.triza.android.Categories.SubCategories;
import com.triza.android.R;

import java.util.List;

public class SubCatAdapter extends ArrayAdapter<SubCategories> {

    public SubCatAdapter(Activity context, List<SubCategories> subCategories) {
        super(context, 0, subCategories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        SubCategories subCategories = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sub_cat_adapter_view, parent, false);
        }
        TextView subCatTitle = convertView.findViewById(R.id.sub_cat_title);
        TextView catDesc = convertView.findViewById(R.id.sub_cat_gig_count);
        ImageView subCatImage = convertView.findViewById(R.id.sub_cat_image);


        subCatTitle.setText(subCategories.getSubCatTitle());

        catDesc.setText(subCategories.getNoOfGigs() + "");
        subCatImage.setImageResource(R.drawable.e);

        //Glide.with(getContext())
          //      .load(R.drawable.e)
            //    .into(subCatImage);

        return convertView;


    }

}
