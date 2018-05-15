package com.triza.android.Categories;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.triza.android.R;

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
    SubCategories mSubCategory;
    String mSubCatTitle;

    //views
    private EditText sub_cat_title_editText;
    private TextView catTitleTextView;
    private ImageView catImageView;

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
        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);

        catTitleTextView = view.findViewById(R.id.cat_title_textView);
        catImageView = view.findViewById(R.id.cat_imageView);

        Glide.with(getActivity())
                .load(mCatImageUrl)
                .into(catImageView);

        catTitleTextView.setText(mCatTitle);

        sub_cat_title_editText = view.findViewById(R.id.sub_cat_title_edt);

        return view;

    }


    public void onSaveButtonPressed() {
        mSubCatTitle = sub_cat_title_editText.getText().toString();
        mSubCategory = new SubCategories(mSubCatTitle);
        if (mListener != null) {
            mListener.onAddSubCatFragmentInteraction(mSubCategory);
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
    public interface OnAddSubCatFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddSubCatFragmentInteraction(SubCategories subCategory);
    }
}
