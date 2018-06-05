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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_grid_view, parent, false);
        }
        TextView catTitle = convertView.findViewById(R.id.cat_title);
        ImageView catImage = convertView.findViewById(R.id.cat_image);

        catTitle.setText(categories.getCatTitle());


            Glide.with(getContext())
                    .load(categories.getCatImageUrl())
                    .into(catImage);



        return convertView;


    }

//    // And here is when the "chooser" is popped up
//    // Normally is the same view, but you can customize it if you want
//    @Override
//    public View getDropDownView(int position, View convertView,
//                                ViewGroup parent) {
//        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
//        label.setTextColor(Color.BLACK);
//        Categories category = getItem(position);
//
//        label.setText(category.getCatTitle());
//
//        return label;
//    }
}
