package com.triza.android.Categories;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.triza.android.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddCategoryActivity extends AppCompatActivity implements AddCategoryFragment.OnAddCatFragmentContinueListener, AddSubCategoryFragment.OnAddSubCatFragmentInteractionListener {


    public static Categories mCategory;
    FragmentTransaction fragmentTransaction;

    private ProgressBar cat_saving_prgBr;

    private  Uri mSelectedImageUrl;

    StorageReference photoRef;

    private static final int RC_PHOTO_PICKER = 101;
    //firebase variable
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
    private DatabaseReference mSubCategoriesDatabaseReference;
	private FirebaseStorage mFirebaseStorage;
	private StorageReference mStorageReference;

	AddCategoryFragment addCategoryFragment;
    List<SubCategories> mSubCategories = new ArrayList<>();
    FragmentManager fragmentManager;
    Fragment fragmentOld;
    int fragCount = 0;
    TextView fragName;
    AddSubCategoryFragment addSubCategoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        cat_saving_prgBr = findViewById(R.id.cat_saving_progress_bar);
        fragName = findViewById(R.id.frag_name);

        // Check whether the activity is using the layout version with
        // the newCatFragmentHolder FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.newCatFragmentHolder) != null) {

            fragmentManager = getSupportFragmentManager();
            addCategoryFragment = new AddCategoryFragment();
            addSubCategoryFragment = new AddSubCategoryFragment();

            /*This method sets my overview fragment by activity launch*/
            setUpFragment(savedInstanceState, addCategoryFragment, fragName, "Overview");

        }


//        // Check whether the activity is using the layout version with
//        // the fragment_container FrameLayout. If so, we must add the first fragment
//        if (findViewById(R.id.newCatFragmentHolder) != null) {
//
//            // However, if we're being restored from a previous state,
//            // then we don't need to do anything and should return or else
//            // we could end up with overlapping fragments.
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            // Create an instance of AddCategoryFragment
//            addCategoryFragment = new AddCategoryFragment();
//
//            // In case this activity was started with special instructions from an Intent,
//            // pass the Intent's extras to the fragment as arguments
//            addCategoryFragment.setArguments(getIntent().getExtras());
//
//            // Add the fragment to the 'newCatFragmentHolder' FrameLayout
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.newCatFragmentHolder, addCategoryFragment).commit();
//        }


    }

    public void onSaveAndContinueButton(View view) {
        fragCount += 1; //This increments by 1

        if (fragCount == 1) {
            //go get the data entered from AddCategoryfragment then move to SubCatFragment
            addCategoryFragment.onContinueButtonPressed();

            //pass arguments to AddSubCategoryFragment
            Bundle args = new Bundle();
            args.putString(AddSubCategoryFragment.ARG_CAT_TITLE, mCategory.getCatTitle());
            args.putString(AddSubCategoryFragment.ARG_CAT_IMAGE_URL, mSelectedImageUrl.toString());
            addSubCategoryFragment.setArguments(args);

            setUpFragment(addSubCategoryFragment, fragName, "Sub Categories");
        } else if (fragCount == 2) {


            //go get the dat entered in subCatFragment and then save to firebase
            addSubCategoryFragment.onSaveButtonPressed();

            view.setEnabled(false);
            // save the data to firebase
            saveCategoryToFirebase();

        }
    }

    public  void imagePicker(View view){
        Intent intent  = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"),AddCategoryActivity.RC_PHOTO_PICKER);

    }

    public void backPressed(View v) {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragCount -= 1;
        if (fragCount < 0) {
            finish();
        }
        if (fragCount == 0) {

            fragName.setText("Overview");
        }
        if (fragCount == 1) {

            fragName.setText("Sub Categories");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void setUpFragment(Fragment fragmentNew, TextView fragName, String fragNameVal) {


        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentNew.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.newCatFragmentHolder, fragmentNew);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragName.setText(fragNameVal);


    }//without bundle

    //with bundle
    public void setUpFragment(Bundle savedInstanceState, Fragment fragmentNew, TextView fragName, String fragNameVal) {
        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentOld = fragmentManager.findFragmentById(R.id.newCatFragmentHolder);


        fragmentNew.setArguments(getIntent().getExtras());

        if (fragmentOld != null) {
            fragmentTransaction.remove(fragmentOld);
        }
        fragmentTransaction.add(R.id.newCatFragmentHolder, fragmentNew).commit();

        fragName.setText(fragNameVal);


    } //with bundle

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_PHOTO_PICKER){
            if(resultCode == RESULT_OK){
                mSelectedImageUrl = data.getData();
            }
        }
    }

    @Override
    public void onAddCatFragmentInteraction(Categories category) {
        mCategory =  category;

    }

    @Override
    public void onAddSubCatFragmentInteraction(List<SubCategories> subCategories) {
        mSubCategories = subCategories;

    }

    public void saveCategoryToFirebase() {

        //instantiate the firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("categories");

        mSubCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("sub_categories");
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child("categories_images");
        //custom name
        String dateStamp = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss:SSS").format(new Date()).toString();
        //photoRef = mStorageReference.child(selectedimageUrl.getLastPathSegment());
        photoRef = mStorageReference.child("category_" + dateStamp);
        //upload file to firebase storage
        photoRef.putFile(mSelectedImageUrl).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    cat_saving_prgBr.setVisibility(View.GONE);
                    Toast.makeText(AddCategoryActivity.this, "Something went wrong. Please try again!", Toast.LENGTH_LONG).show();

                    throw task.getException();
                }

                //continue with the task to get the download url
                return photoRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri imageUrl = task.getResult();
                    mCategory.setCatImageUrl(imageUrl.toString());

                    SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
                    f.setLenient(false);
                    try {
                        mCategory.catDateAdded = f.parse(f.format(new Date())).getTime();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // mCategoriesDatabaseReference.push().setValue(mCategory);
                    //add empty data and get the pushId/key
                    String catId = mCategoriesDatabaseReference.push().getKey();
                    mCategory.setCatId(catId);
                    mCategory.setNoOfSubCategories(mSubCategories.size());
                    //creat a child using the created/gotten (pushId) key of the empty data created
                    mCategoriesDatabaseReference.child(catId).setValue(mCategory);

                    for (SubCategories subCategory : mSubCategories) {
                        subCategory.setCatId(catId);

                        //add empty data and get the pushId/key
                        String subCatId = mSubCategoriesDatabaseReference.push().getKey();
                        subCategory.setSubCatId(subCatId);

                        //save sub_category
                        mSubCategoriesDatabaseReference.child(subCatId).setValue(subCategory);

                    }


                    Toast.makeText(AddCategoryActivity.this, mCategory.getCatTitle() + " added to category", Toast.LENGTH_LONG).show();
                    cat_saving_prgBr.setVisibility(View.GONE);
                    finish();
                } else {
                    //handle failure
                    cat_saving_prgBr.setVisibility(View.GONE);
                    Toast.makeText(AddCategoryActivity.this, "Something went wrong. Please try again!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
