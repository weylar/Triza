package com.triza.android.Categories;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.triza.android.Adapters.SubCatAdapter;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;


public class SubCategoryViewFragment extends Fragment {

    private OnSubCategorySelectedInteractionListener mListener;

    //    List<SubCategories> subCategories =new ArrayList<>();
    SubCatAdapter subCategoryAdapter;

    public SubCategoryViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }

        //get the listing of subCategories from CategoryActivity (subCategories)
//        subCategories = CategoryActivity.subCategories;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category_view, container, false);

        ListView subCategoryListView = view.findViewById(R.id.sub_categories_listView);
        //subCategoryAdapter = CategoryActivity.subCategoriesAdapter;
        subCategoryAdapter = new SubCatAdapter(getActivity(), new ArrayList<SubCategories>());
        subCategoryListView.setAdapter(subCategoryAdapter);


//        subCategoryListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view,
//                                               int position, long id) {
//                        SubCategories selectedSubCategory = subCategoryAdapter.getItem(position);
//                        Toast.makeText(getActivity(), selectedSubCategory.getSubCatTitle(), Toast.LENGTH_SHORT).show();
//
////                        sub_cat_id = subCategory.getCatId();
//                        if (mListener != null) {
//                            mListener.onSubCategorySelectedInteraction(selectedSubCategory);
//                        }
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapter) {
//                    }
//                });

        subCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SubCategories selectedSubCategory = subCategoryAdapter.getItem(position);

//                        sub_cat_id = subCategory.getCatId();
                if (mListener != null) {
                    mListener.onSubCategorySelectedInteraction(selectedSubCategory);
                }
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(SubCategories selectedSubCategory) {
        if (mListener != null) {
            mListener.onSubCategorySelectedInteraction(selectedSubCategory);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSubCategorySelectedInteractionListener) {
            mListener = (OnSubCategorySelectedInteractionListener) context;
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


    public interface OnSubCategorySelectedInteractionListener {
        // TODO: Update argument type and name
        void onSubCategorySelectedInteraction(SubCategories subCategory);
    }
}
