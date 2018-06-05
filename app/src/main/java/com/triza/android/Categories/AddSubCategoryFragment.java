package com.triza.android.Categories;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSubCategoryFragment.OnAddSubCatFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSubCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubCategoryFragment extends Fragment {

    // the fragment initialization parameters
    public static String ARG_CAT_TITLE = "catTitle";
    public static String ARG_CAT_IMAGE_URL = "catImageUrl";

    private String mCatTitle;
    private String mCatImageUrl;
    //interface
    public static OnAddSubCatFragmentInteractionListener mListener;
    private List<SubCategories> mSubCategories;


    //views
    private TextView catTitleTextView;
    private ImageView catImageView;
    private String mSubCatTitle;
    ImageView addMore;
    View mView;
    private List<EditText> subCatEditTexts = new ArrayList<>();

    public AddSubCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param catTitle Parameter 1.
     * @param catImageUrl Parameter 2.
     * @return A new instance of fragment AddSubCategoryFragment.
     */
    public static AddSubCategoryFragment newInstance(String catTitle, String catImageUrl) {
        AddSubCategoryFragment fragment = new AddSubCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CAT_TITLE, catTitle);
        args.putString(ARG_CAT_IMAGE_URL, catImageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCatTitle = getArguments().getString(ARG_CAT_TITLE);
            mCatImageUrl = getArguments().getString(ARG_CAT_IMAGE_URL);
        }

        //instantiate the list
        mSubCategories = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_sub_category, container, false);

        catTitleTextView = view.findViewById(R.id.cat_title_textView);
        catImageView = view.findViewById(R.id.cat_imageView);
        addMore = view.findViewById(R.id.ic_add_more);


        Glide.with(getActivity())
                .load(mCatImageUrl)
                .into(catImageView);

        catTitleTextView.setText(mCatTitle);

        //add the first edittext
        addEditText(view);

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditText(mView);
            }
        });

        mView = view;


        return view;

    }

    private void addEditText(View view) {
        EditText new_sub_cat_title_editText = new EditText(getActivity());
        LinearLayout ll = view.findViewById(R.id.sub_cat_holder);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        new_sub_cat_title_editText.setLayoutParams(p);
        new_sub_cat_title_editText.setFocusable(true);
        new_sub_cat_title_editText.setTextSize(24);
        subCatEditTexts.add(new_sub_cat_title_editText);
        ll.addView(new_sub_cat_title_editText);

    }


    public void onSaveButtonPressed() {

        for (EditText sub : subCatEditTexts) {
            mSubCatTitle = sub.getText().toString();
            Log.e("onSaveButtonPressed", mSubCatTitle);

            if (mSubCatTitle.equals(""))
                continue;
            mSubCategories.add(new SubCategories(mSubCatTitle));
        }
//        for (Iterator iterator = subCatEditTexts.iterator(); iterator.hasNext(); ) {
//            EditText sub = (EditText) iterator.next();
//            mSubCatTitle = sub.getText().toString();
//            Log.e("onSaveButtonPressed", mSubCatTitle);
//
////            Toast.makeText(getActivity(),mSubCatTitle,Toast.LENGTH_SHORT).show();
//            if (mSubCatTitle == "")
//                continue;
//            SubCategories subCategory = new SubCategories(mSubCatTitle);
//            mSubCategories.add(subCategory);
//        }



        if (mListener != null) {
            mListener.onAddSubCatFragmentInteraction(mSubCategories);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddSubCatFragmentInteractionListener) {
            mListener = (OnAddSubCatFragmentInteractionListener) context;
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


    public interface OnAddSubCatFragmentInteractionListener {
        void onAddSubCatFragmentInteraction(List<SubCategories> subCategories);
    }
}
