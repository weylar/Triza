package com.triza.android.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triza.android.Gigs.CustomOffersClass;
import com.triza.android.R;

import java.util.List;


public class CustomOfferAdapter extends ArrayAdapter<CustomOffersClass> {


    public CustomOfferAdapter(Activity context, List<CustomOffersClass> customOffersClasses) {
        super(context, 0, customOffersClasses);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        CustomOffersClass customOffersClass = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_offer, parent, false);
        }
        CheckBox customText = convertView.findViewById(R.id.checked_custom);


        customText.setText(customOffersClass.getCustomText());
        customText.setChecked(true);


        return convertView;


    }

}
