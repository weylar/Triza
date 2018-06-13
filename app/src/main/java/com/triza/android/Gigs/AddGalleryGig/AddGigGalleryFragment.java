package com.triza.android.Gigs.AddGalleryGig;

import android.support.design.widget.Snackbar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.triza.android.Adapters.GridPictureAdapter;
import com.triza.android.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/*Created by weylar on 23/05/18*/

public class AddGigGalleryFragment extends Fragment {
    ImageView picturePicker;
    GridView gridPictures;
    private static final int RC_PHOTO_PICKER = 101;
    GridPictureAdapter gridPictureAdapter;

    public AddGigGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_gig_gallery_frag, container, false);
        gridPictures = view.findViewById(R.id.grid_pictures);
        picturePicker = view.findViewById(R.id.picture_picker);

        picturePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER) {
            if (resultCode == RESULT_OK) {

                ArrayList<AddPictureClass> list = new ArrayList<>();
                list.add(new AddPictureClass(data.getData()));
                list.add(new AddPictureClass(data.getData()));
                gridPictureAdapter = new GridPictureAdapter(getActivity(), list);
                gridPictures.setAdapter(gridPictureAdapter);
                gridPictures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), CropImageActivity.class);
                        intent.setData(data.getData());
                        startActivity(intent);
                    }
                });


            } else {
                Snackbar.make(getView(), "No image selected!", Snackbar.LENGTH_SHORT).show();
            }
        }

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


