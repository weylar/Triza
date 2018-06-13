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
import com.triza.android.R;

import java.util.List;


public class CategoriesAdapter extends ArrayAdapter<Categories> {


    //the type is just so we can use the same adapter for both CATEGORIESACTIVITY and the Spinner
    public CategoriesAdapter(Activity context, List<Categories> categories) {
        super(context, 0, categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        Categories categories = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_list_view, parent, false);
        }
        TextView catTitle = convertView.findViewById(R.id.cat_title);
        TextView catDesc = convertView.findViewById(R.id.cat_desc);
        ImageView catImage = convertView.findViewById(R.id.cat_image);
        TextView gigCount = convertView.findViewById(R.id.gig_count);

        catTitle.setText(categories.getCatTitle());

        catDesc.setText(categories.getCatDescription());
        gigCount.setText(categories.getGigCount() + "");

        Glide.with(getContext())
                .load(R.drawable.programming_grey_50px)
                .into(catImage);



        return convertView;


    }


}
