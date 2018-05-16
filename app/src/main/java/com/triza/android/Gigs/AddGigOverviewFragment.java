package com.triza.android.Gigs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    private String gig_title, gig_desc = "", category_id="", sub_cat_id="", search_tag = "";

    private EditText gigTitle_editText;
    private OnAddGigOverviewListener mListener;

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

        gigTitle_editText = view.findViewById(R.id.gig_title_editText);

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
