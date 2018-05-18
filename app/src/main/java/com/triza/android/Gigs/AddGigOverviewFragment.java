package com.triza.android.Gigs;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_gig_overview_fragment, container, false);
        titleTextCount = view.findViewById(R.id.title_text_count);
        gigTitle_editText = view.findViewById(R.id.gig_title_editText);

        //programatically i set the text counter by using text watcher and attached to editetxt
        titleTextCount.setText(0 + "/70 max");

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
                titleTextCount.setText(titleListener.length() + "/70 max");
                if (titleListener.length() > 60){
                    titleTextCount.setTextColor(Color.parseColor("RED"));
                }
                if (titleListener.length() > 70){
                    titleTextCount.setText(70 - titleListener.length() + "/70 max");
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
