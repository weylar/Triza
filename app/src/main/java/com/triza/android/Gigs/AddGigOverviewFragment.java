package com.triza.android.Gigs;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ryanpope.tagedittext.TagEditText;
import com.triza.android.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddGigOverviewFragment.OnAddGigOverviewListener} interface
 * to handle interaction events.
 * Use the {@link AddGigOverviewFragment} factory method to
 * create an instance of this fragment.
 */
public class AddGigOverviewFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private String gig_title, gig_desc = "", category_id = "", sub_cat_id = "", search_tag = "";

    private EditText gigTitle_editText;
    private OnAddGigOverviewListener mListener;
    TextView titleTextCount;
    TagEditText searchTagEditText;



    public AddGigOverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddGigOverviewFragment.
     */
//    public static AddGigOverviewFragment newInstance(String param1, String param2) {
//        AddGigOverviewFragment fragment = new AddGigOverviewFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_gig_overview_fragment, container, false);
        titleTextCount = view.findViewById(R.id.title_text_count);
        gigTitle_editText = view.findViewById(R.id.gig_title_editText);
        searchTagEditText = view.findViewById(R.id.search_tag_editText);
        searchTagEditText.getText().toString();
        titleTextCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), searchTagEditText.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        //Toast.makeText(getActivity(), searchTagEditText.getText().toString(), Toast.LENGTH_SHORT).show();




        //programatically i set the text counter by using text watcher and attached to editetxt
        titleTextCount.setText(0 + "/70 max");
        titleTextCount.setTextColor(Color.rgb(0, 150, 136));



        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               String titleListener = gigTitle_editText.getText().toString();
                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.blinking);
                if (titleListener.length() <= 60) {
                    titleTextCount.setText(titleListener.length() + "/70 max");
                    titleTextCount.setTextColor(Color.rgb(0, 150, 136));

                }

                if (titleListener.length() > 60) {
                    titleTextCount.setText(titleListener.length() + "/70 max");
                    titleTextCount.setTextColor(Color.rgb(29, 36, 228));
                }
                if (titleListener.length() > 70) {
                    titleTextCount.setText(70 - titleListener.length() + "/70 max");
                    titleTextCount.setTextColor(Color.parseColor("RED"));
                    titleTextCount.startAnimation(anim);
                }

            }
        };
        gigTitle_editText.addTextChangedListener(textWatcher);

        return view;
    }

    public void onSaveOverviewButtonPressed() {
        gig_title = gigTitle_editText.getText().toString();
        if (mListener != null) {
            mListener.onAddGigOverview(gig_title, gig_desc, category_id, sub_cat_id, search_tag);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddGigOverviewListener) {
            mListener = (OnAddGigOverviewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnAddGigOverviewListener {
        void onAddGigOverview(String gig_title, String gig_desc, String category_id, String sub_cat_id, String search_tag);
    }





}
