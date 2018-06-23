package com.triza.android.Gigs.AddGigGallery;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.triza.android.Adapters.GridPictureAdapter;
import com.triza.android.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;



/*Created by weylar on 23/05/18*/

public class AddGigGalleryFragment extends Fragment {
   private ImageView picturePicker;
  private  GridView gridPictures;
    private static final int RC_PHOTO_PICKER = 101;
    private GridPictureAdapter gridPictureAdapter;
    public AddPictureClass addPictureClass;
    private  List<AddPictureClass> list;
    Bitmap bitmapReceived;
    byte[] byteArray;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private LinearLayout emptyLinear;
    private CheckBox termOfService;
    private TextView tvTermOfService;


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
        emptyLinear = view.findViewById(R.id.empty_linear);
        termOfService = view.findViewById(R.id.cb_term_of_service);
        tvTermOfService = view.findViewById(R.id.tv_term_of_service);

        list = new ArrayList<>();
        gridPictureAdapter = new GridPictureAdapter(getActivity(), list, emptyLinear);

        /*This my little code here uses spannableString class to underline and assign click on my string*/
        setTextUnderlineAndClickable();

        picturePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });




        /*CLICKING ON IMAGE FROM GRIDVIEW*/
        gridPictures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /**fragmentManager = getFragmentManager();
                 fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.newGigFragmentHolder, new CropImageActivity()).commit();
                 fragmentTransaction.addToBackStack(null);
                 addPictureClass = list.get(i);
                 list.add(i, new AddPictureClass(bitmapReceived));**/

            }
        });
        return view;
    }

    private void setTextUnderlineAndClickable() {
        SpannableString content = new SpannableString("By checking this box, you agree to Triza's Term of service and Privacy policy.");
        content.setSpan(new UnderlineSpan(), 43, 58, 0);
        content.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.addDefaultShareMenuItem();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getActivity(), Uri.parse("https://triza.com/term-of-service"));
            }
        }, 43, 58, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.setSpan(new UnderlineSpan(), 63, 77, 0);
        content.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.addDefaultShareMenuItem();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getActivity(), Uri.parse("https://triza.com/privacy-policy"));
            }
        }, 63, 77, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTermOfService.setText(content);
        tvTermOfService.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public void onResume() {
        super.onResume();
        /**    Bundle bundle = this.getArguments();
         if (bundle != null) {
         byteArray = bundle.getByteArray(BITMAP);
         bitmapReceived = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
         }*/
        gridPictureAdapter.notifyDataSetChanged();
        if (gridPictureAdapter.getCount() > 0) {
            emptyLinear.setVisibility(View.GONE);
        } else {
            emptyLinear.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER) {
            if (resultCode == RESULT_OK) {



                /*Converting uri to bitmap*/
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                list.add(new AddPictureClass(bitmap));
                gridPictures.setAdapter(gridPictureAdapter);


            } else {
                Snackbar.make(getView(), "No image selected!", Snackbar.LENGTH_SHORT).show();
            }
        }

    }


}


