package com.triza.android.Admin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.triza.android.Categories.Categories;
import com.triza.android.R;


public class AddCategoryFragment extends Fragment {


    private Categories mCategory;
    private String mCatImageUrl;
    private String mCatTitle;
    private String mCatDescription;

    //views
    private EditText cat_title_editText, cat_desc_editText;
    private ImageButton imagePicker;
    private Button sendButton;

    private OnAddCatFragmentContinueListener mListener;

    public AddCategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);

        //instantiate the view variables
        cat_desc_editText = view.findViewById(R.id.cat_desc_edt);
        cat_title_editText = view.findViewById(R.id.cat_title_edt);
        sendButton = view.findViewById(R.id.sendBtn);
       // imagePicker = view.findViewById(R.id.image_picker);


        return  view;
    }

    public void onContinueButtonPressed() {
        mCatTitle = cat_title_editText.getText().toString();
        mCatDescription = cat_desc_editText.getText().toString();
        mCategory = new Categories(mCatTitle,mCatDescription);
        if (mListener != null) {
            mListener.onAddCatFragmentInteraction(mCategory);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddCatFragmentContinueListener) {
            mListener = (OnAddCatFragmentContinueListener) context;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddCatFragmentContinueListener {
        // TODO: Update argument type and name
        void onAddCatFragmentInteraction(Categories category);
    }
}
