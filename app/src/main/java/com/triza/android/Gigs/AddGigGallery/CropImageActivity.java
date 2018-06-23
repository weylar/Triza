package com.triza.android.Gigs.AddGigGallery;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.theartofdev.edmodo.cropper.CropImageView;

import com.triza.android.R;

import java.io.ByteArrayOutputStream;

/*Created by weylar on 23/05/18*/

public class CropImageActivity extends Fragment {
    CropImageView cropImageView;
    TextView rotateLeft, reset, flipHorizontal, flipVertical, cropImage;
    ImageView cancelButton, saveButton;
    Bitmap croppedImage;
    public static final String BITMAP = "BitmapImage";
    OnDataSentListener mCallback;
    AddGigGalleryFragment addGigGalleryFragment;


    public CropImageActivity() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addGigGalleryFragment = new AddGigGalleryFragment();
        try {
            mCallback = (OnDataSentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement On ");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.image_croper_layout, container, false);

        cropImageView = view.findViewById(R.id.crop_image_view);
        cancelButton = view.findViewById(R.id.cancel_button);
        saveButton = view.findViewById(R.id.save_button);
        rotateLeft = view.findViewById(R.id.rotate_left);
        reset = view.findViewById(R.id.reset);
        flipHorizontal = view.findViewById(R.id.flip_horizontal);
        flipVertical = view.findViewById(R.id.flip_vertical);
        cropImage = view.findViewById(R.id.tv_crop_image);

        /*Sets image from uri passed through intent*/
        cropImageView.setImageBitmap(addGigGalleryFragment.addPictureClass.getBitmap());



        /*Crop image on button pressed*/
        cropImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                croppedImage = cropImageView.getCroppedImage(500, 500);
                cropImageView.setImageBitmap(croppedImage);
            }
        });

        /*Sets rotation accordingly to button state*/
        rotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cropImageView.getRotation() == 0) {
                    cropImageView.setRotation(90);
                } else if (cropImageView.getRotation() == 90) {
                    cropImageView.setRotation(180);
                } else if (cropImageView.getRotation() == 180) {
                    cropImageView.setRotation(270);
                } else if (cropImageView.getRotation() == 270) {
                    cropImageView.setRotation(360);
                } else if (cropImageView.getRotation() == 360) {
                    cropImageView.setRotation(0);
                }
            }
        });

        /*Flips image horzontaly*/
        flipHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cropImageView.getRotationY() == 0) {
                    cropImageView.setRotationY(180);
                } else {
                    cropImageView.setRotationY(0);
                }
            }
        });

        /*Flips image vertically*/
        flipVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cropImageView.getRotationX() == 0) {
                    cropImageView.setRotationX(180);
                } else {
                    cropImageView.setRotationX(0);
                }
            }
        });

//        Resets all changes
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cropImageView.getRotationY() != 0) {
                    cropImageView.setRotationY(0);
                }
                if (cropImageView.getRotationX() != 0) {
                    cropImageView.setRotationX(0);
                }
                if (cropImageView.getRotation() != 0) {
                    cropImageView.setRotation(0);
                }
                //  cropImageView.setImageUriAsync(getIntent().getData());

            }
        });

        /*Closes activity*/
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertBitmapToByteArray();
            }
        });
        return view;
    }

    void convertBitmapToByteArray() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        croppedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        mCallback.onDataSent(bytes);
        getFragmentManager().popBackStack();
    }


}



