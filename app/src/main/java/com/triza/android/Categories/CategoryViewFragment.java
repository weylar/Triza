package com.triza.android.Categories;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.triza.android.Adapters.CategoriesAdapter;
import com.triza.android.R;


public class CategoryViewFragment extends Fragment {

    CategoriesAdapter categoriesAdapter;

    private OnCategorySelectedInteractionListener mListener;

    public CategoryViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }

        //get the listing of categories from CategoryActivity (categoriesAdapter)
        categoriesAdapter = CategoryActivity.categoriesAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_view, container, false);

        GridView gridView = view.findViewById(R.id.cat_grid_list);

        gridView.setAdapter(categoriesAdapter);


        return view;
    }

    public void onCategorySelected(Uri uri) {
        if (mListener != null) {
            mListener.onCategorySelectedInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCategorySelectedInteractionListener) {
            mListener = (OnCategorySelectedInteractionListener) context;
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


    public interface OnCategorySelectedInteractionListener {
        // TODO: Update argument type and name
        void onCategorySelectedInteraction(Uri uri);
    }
}
