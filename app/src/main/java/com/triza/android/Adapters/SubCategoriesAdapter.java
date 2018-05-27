package com.triza.android.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.triza.android.Categories.SubCategories;

import java.util.List;


public class SubCategoriesAdapter extends ArrayAdapter<SubCategories> {

    public static final String SUB_CATEGORY_VIEW = "sub-category-view";
    public static final String SPINNER_VIEW = "spinner-view";
    String type;

    //the type is just so we can use the same adapter for both CATEGORIESACTIVITY and the Spinner
    public SubCategoriesAdapter(Activity context, List<SubCategories> sub_categories, String type) {
        super(context, 0, sub_categories);
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        SubCategories sub_categories = getItem(position);

        if (type == SUB_CATEGORY_VIEW) {
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_grid_view, parent, false);
//            }
//
//            TextView catTitle = convertView.findViewById(R.id.cat_title);
//            ImageView catImage = convertView.findViewById(R.id.cat_image);
//
//            catTitle.setText(categories.getCatTitle());
//
//
//            Glide.with(getContext())
//                    .load(categories.getCatImageUrl())
//                    .into(catImage);
//
//            return convertView;


        } else if (type == SPINNER_VIEW) {
            // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
            TextView label = (TextView) super.getView(position, convertView, parent);
            label.setTextColor(Color.BLACK);
            SubCategories sub_category = getItem(position);
            label.setText(sub_category.getSubCatTitle());

            // And finally return your dynamic (or custom) view for each spinner item
            return label;
        }

        return null;


    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        SubCategories sub_category = getItem(position);

        label.setText(sub_category.getSubCatTitle());

        return label;
    }
}
