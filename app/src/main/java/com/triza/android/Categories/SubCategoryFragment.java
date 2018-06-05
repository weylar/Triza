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
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubCategoryFragment.OnAddSubCatFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubCategoryFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_CAT_TITLE
    public static String ARG_CAT_TITLE = "catTitle";
    public static String ARG_CAT_IMAGE_URL = "catImageUrl";

    private String mCatTitle;
    private String mCatImageUrl;
    public static List<SubCategories> mSubCategories = new ArrayList<>();
    String mSubCatTitle;

//    static int numbOfSub = 0;

    //views
    private TextView catTitleTextView;
    private ImageView catImageView;
    public static List<EditText> subCatEditTexts = new ArrayList<>();
    ImageView addMore;
    View mView;

    //interface
    private OnAddSubCatFragmentInteractionListener mListener;

    public SubCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param catTitle Parameter 1.
     * @param catImageUrl Parameter 2.
     * @return A new instance of fragment SubCategoryFragment.
     */
    public static SubCategoryFragment newInstance(String catTitle, String catImageUrl) {
        SubCategoryFragment fragment = new SubCategoryFragment();
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
//        addEditText(view);

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
//        numbOfSub++;
        EditText new_sub_cat_title_editText = new EditText(getActivity());
        LinearLayout ll = view.findViewById(R.id.sub_cat_holder);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        new_sub_cat_title_editText.setLayoutParams(p);
//        new_sub_cat_title_editText.setId(numbOfSub);
        new_sub_cat_title_editText.setFocusable(true);
        new_sub_cat_title_editText.setTextSize(24);
        subCatEditTexts.add(new_sub_cat_title_editText);
        ll.addView(new_sub_cat_title_editText);


    }


    public void onSaveButtonPressed() {
//        Toast.makeText(getContext(),"here",Toast.LENGTH_SHORT).show();


        for (Iterator iterator = subCatEditTexts.iterator(); iterator.hasNext(); ) {
            EditText sub = (EditText) iterator.next();
            mSubCatTitle = sub.getText().toString();
            Log.e("onSaveButtonPressed", " numbSub");

//            Toast.makeText(getActivity(),mSubCatTitle,Toast.LENGTH_SHORT).show();
            if (mSubCatTitle == "")
                continue;
            SubCategories subCategory = new SubCategories(mSubCatTitle);
            mSubCategories.add(subCategory);
        }

////        for(int i = 1; i<=numbOfSub; i++){
////            EditText sub = mView.findViewById(1);
//            mSubCatTitle = new_sub_cat_title_editText.getText().toString();
//            Toast.makeText(getActivity(),mSubCatTitle,Toast.LENGTH_SHORT).show();
////            if(mSubCatTitle == "")
////                continue;
//            SubCategories subCategory = new SubCategories(mSubCatTitle);
//            mSubCategories.add(subCategory);
////        }


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
