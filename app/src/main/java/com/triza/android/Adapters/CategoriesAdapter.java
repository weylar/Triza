package com.triza.android.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triza.android.Categories.Categories;
import com.triza.android.R;

import org.w3c.dom.Text;

import java.util.List;


public class CategoriesAdapter extends ArrayAdapter<Categories> {

    public static final String CATEGORY_VIEW = "category-view";
    public static final String SPINNER_VIEW = "spinner-view";
    String type;

    //the type is just so we can use the same adapter for both CATEGORIESACTIVITY and the Spinner
    public CategoriesAdapter(Activity context, List<Categories> categories, String type) {
        super(context, 0, categories);
        this.type = type;
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
        if (type == CATEGORY_VIEW) {


            Glide.with(getContext())
                    .load(R.drawable.audio_black_50px)
                    .into(catImage);


        } else if (type == SPINNER_VIEW) {
            catImage.setVisibility(View.GONE);
            catTitle.setTextColor(Color.BLACK);


        }

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
