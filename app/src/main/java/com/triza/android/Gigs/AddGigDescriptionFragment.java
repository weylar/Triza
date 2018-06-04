package com.triza.android.Gigs;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.triza.android.R;

/*Created by weylar on 23/05/18*/

public class AddGigDescriptionFragment extends Fragment {


        public AddGigDescriptionFragment(){
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

            return view;
        }


        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

        }

        @Override
        public void onDetach() {
            super.onDetach();
        }





    }


