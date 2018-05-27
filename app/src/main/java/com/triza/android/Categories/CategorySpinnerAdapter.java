package com.triza.android.Categories;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategorySpinnerAdapter extends ArrayAdapter<Categories> {
    private Context context; //sent context
    private List<Categories> categories = new ArrayList<Categories>();

    public CategorySpinnerAdapter(Context context, int textViewResourceId, List<Categories> categories) {
        super(context, textViewResourceId, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }


    //the magic
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        Categories category = getItem(position);
        label.setText(category.getCatTitle());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        Categories category = getItem(position);

        label.setText(category.getCatTitle());

        return label;
    }
}
