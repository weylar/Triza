package com.triza.android.Gigs.AddGalleryGig;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.theartofdev.edmodo.cropper.CropImageView;
import com.triza.android.Adapters.GridPictureAdapter;
import com.triza.android.Categories.AddCategoryActivity;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/*Created by weylar on 23/05/18*/

public class CropImageActivity extends AppCompatActivity {
    CropImageView cropImageView;
    TextView rotateLeft, reset, flipHorizontal, flipVertical, cropImage;
    ImageView cancelButton, saveButton;
    static Bitmap croppedImage;


    public CropImageActivity() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_croper_layout);

        cropImageView = findViewById(R.id.crop_image_view);
        cancelButton = findViewById(R.id.cancel_button);
        saveButton = findViewById(R.id.save_button);
        rotateLeft = findViewById(R.id.rotate_left);
        reset = findViewById(R.id.reset);
        flipHorizontal = findViewById(R.id.flip_horizontal);
        flipVertical = findViewById(R.id.flip_vertical);
        cropImage = findViewById(R.id.tv_crop_image);

        /*Sets image from uri passed through intent*/
        cropImageView.setImageUriAsync(getIntent().getData());



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
                cropImageView.setImageUriAsync(getIntent().getData());

            }
        });

        /*Closes activity*/
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}



