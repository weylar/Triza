package com.triza.android.Categories;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.triza.android.Adapters.GigsAdapterVertical;
import com.triza.android.R;
//import com.triza.android.RecyclerItemClickListeners;


public class GigsListingFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GigsAdapterVertical gigsAdapterVertical;


    public GigsListingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gigs_listing, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_gigs_listing_vertical);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //Layout manager in charge of horizontal recycler view
        gigsAdapterVertical = new GigsAdapterVertical(getActivity(), CategoryActivity.gigList);

        recyclerView.setLayoutManager(linearLayoutManager);  //I set manager for recycler here
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerView.setAdapter(gigsAdapterVertical);
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListeners(getActivity(), recyclerView, new RecyclerItemClickListeners.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
//
//            }
//        }));

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
