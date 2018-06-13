package com.triza.android.Gigs.AddDescriptionFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.triza.android.Adapters.AddFaqAdapter;
import com.triza.android.Gigs.AddDescriptionFragment.AddFaqClass;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

/*Created by weylar on 23/05/18*/

public class AddGigDescriptionFragment extends Fragment {
    EditText gigDesc, addQuestion, addAnswer;
    TextView charCount, addFaq;
    Button cancelFaqButton, addFaqButton;
    RelativeLayout relAddFaq;
    LinearLayout linearLayout;
    int trackButtonState = 1;


    public AddGigDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_gig_desc_frag, container, false);
        gigDesc = view.findViewById(R.id.gig_desc_editText);
        charCount = view.findViewById(R.id.desc_text_count);
        addFaq = view.findViewById(R.id.tv_add_faq);
        addQuestion = view.findViewById(R.id.ed_add_faq_question);
        addAnswer = view.findViewById(R.id.ed_add_faq_answer);
        addFaqButton = view.findViewById(R.id.bt_add_faq);
        cancelFaqButton = view.findViewById(R.id.bt_cancel_faq);
        relAddFaq = view.findViewById(R.id.rel_add_faq);
        linearLayout = view.findViewById(R.id.linear_faq);

        charCount.setText(0 + "/120 max");
        charCount.setTextColor(Color.rgb(0, 0, 0));



        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String titleListener = gigDesc.getText().toString();

                if (titleListener.length() <= 110) {
                    charCount.setText(titleListener.length() + "/120 max");
                    charCount.setTextColor(Color.rgb(0, 0, 0));

                }

                if (titleListener.length() > 110) {
                    charCount.setText(titleListener.length() + "/120 max");
                    charCount.setTextColor(Color.rgb(0, 150, 136));
                }
                if (titleListener.length() > 120) {
                    charCount.setText(120 - titleListener.length() + "/120 max");
                    charCount.setTextColor(Color.parseColor("RED"));

                }

            }
        };
        gigDesc.addTextChangedListener(textWatcher);

        addFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFaqButton.setText(R.string.add);
                cancelFaqButton.setText(R.string.cancel);
                addQuestion.getText().clear();
                addAnswer.getText().clear();
                relAddFaq.setVisibility(View.VISIBLE);
                addFaq.setVisibility(View.GONE);
            }
        });

        /*Add button should remove view and add a list to the offer*/
        addFaqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relAddFaq.setVisibility(View.GONE);
                addFaq.setVisibility(View.VISIBLE);

                /*Get edit text inputs and set on the new linear layout*/
                List list = new ArrayList();
                final AddFaqClass addFaqClass = new AddFaqClass(addQuestion.getText().toString().trim(), addAnswer.getText().toString().trim());
                list.add(addFaqClass);
                AddFaqAdapter addFaqAdapter = new AddFaqAdapter(getActivity(), list);
                for ( int i = 0; i < addFaqAdapter.getCount(); i++) {
                    View item = addFaqAdapter.getView(i, null, null);
                    /*Set click listener to update entry*/
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            relAddFaq.setVisibility(View.VISIBLE);
                            addQuestion.setText(addFaqClass.getQuestion());
                            addFaqButton.setText(R.string.update);
                            cancelFaqButton.setText(R.string.delete);
                            addAnswer.setText(addFaqClass.getAnswer());
                            linearLayout.removeView(view);
                        }
                    });

                    /*Add view*/
                    linearLayout.addView(item);
                }

                /*Clears both edit text*/
                addQuestion.getText().clear();
                addAnswer.getText().clear();


            }
        });

        /*Cancel button should remove view only*/
        cancelFaqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relAddFaq.setVisibility(View.GONE);
                addFaq.setVisibility(View.VISIBLE);
            }
        });

        /*Disabled the add button on create*/
        disableButton(addFaqButton);

        /*Watcher watches my text inputs and enable or disable add button appropriately*/
        TextWatcher textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (addQuestion.getText().toString().length() == 0 || addAnswer.getText().toString().length() == 0) {
                    if (trackButtonState == 1) {
                        disableButton(addFaqButton);
                    } else {
                        trackButtonState = 0;
                    }
                } else {
                    addFaqButton.setEnabled(true);
                    addFaqButton.setBackgroundResource(R.drawable.rectangle_button_bg);
                }

            }
        };
        addQuestion.addTextChangedListener(textWatcher2);
        addAnswer.addTextChangedListener(textWatcher2);

        return view;
    }

    private void disableButton(View view) {
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.rectangle_button_bg_light);
    }


}


