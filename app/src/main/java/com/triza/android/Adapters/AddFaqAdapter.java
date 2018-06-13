package com.triza.android.Adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.triza.android.Gigs.AddDescriptionFragment.AddFaqClass;
import com.triza.android.R;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;


public class AddFaqAdapter extends ArrayAdapter<AddFaqClass> {


    public AddFaqAdapter(Activity context, List<AddFaqClass> addFaqClasses) {
        super(context, 0, addFaqClasses);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Categories object from the ArrayAdapter at the appropriate position
        AddFaqClass addFaqClass = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_faq, parent, false);
        }
        final ExpandableTextView question = convertView.findViewById(R.id.tv_question);
        final ImageView answers = convertView.findViewById(R.id.img_question);

        /*Set text from class together*/
        question.setText("Q.  " + addFaqClass.getQuestion() + "\nA.  " + addFaqClass.getAnswer());

        /*Enable interpolar on  text view*/
        question.setInterpolator(new OvershootInterpolator());

        /*Toogle image on click*/
        answers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.isExpanded()) {
                    question.collapse();
                    answers.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    question.expand();
                    answers.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }
            }
        });


        return convertView;


    }

}

