package com.triza.android.Categories;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.triza.android.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCategoryActivity extends AppCompatActivity {

    private String mCatImageUrl;
    private String mCatTitle;
    private String mCatDescription;

    private  Uri selectedimageUrl;

    StorageReference photoRef;
    //views
    private EditText cat_title_editText, cat_desc_editText;
    private ImageButton imagePicker;
    private Button sendButton;
    private static final int RC_PHOTO_PICKER = 101;
    //firebase variable
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
	private FirebaseStorage mFirebaseStorage;
	private StorageReference mStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        //instantiate the view variables
        cat_desc_editText = findViewById(R.id.cat_desc_edt);
        cat_title_editText = findViewById(R.id.cat_title_edt);
        sendButton = findViewById(R.id.sendBtn);
        imagePicker = findViewById(R.id.image_picker);



        //instantiate the firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("categories");
        mFirebaseStorage = FirebaseStorage.getInstance();
		mStorageReference = mFirebaseStorage.getReference().child("categories_images");

		imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"),RC_PHOTO_PICKER);
            }
        });


        //handle send button click
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCatDescription = cat_desc_editText.getText().toString();
                mCatTitle = cat_title_editText.getText().toString();

//                //get a reference to store file
//                //gets the name of the file
//                //categories_imagess/triza.jpg will produce triza.jpg

                //custom name
                String dateStamp= new SimpleDateFormat("dd-mm-yyyy HH:mm:ss:SSS").format(new Date()).toString();
//                photoRef = mStorageReference.child(selectedimageUrl.getLastPathSegment());
                photoRef = mStorageReference.child("category_"+dateStamp);
                //upload file to firebase storage
                photoRef.putFile(selectedimageUrl).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();
                        }

                        //continue with the task to get the download url
                        return  photoRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            Uri imageUrl = task.getResult();
                            mCatImageUrl = imageUrl.toString();

                            mCategoriesDatabaseReference.push().setValue(new Categories(mCatTitle, mCatDescription, mCatImageUrl));

                        }
                        else{
                            //handle failure
                        }

                    }
                });

                Toast.makeText(AddCategoryActivity.this, mCatTitle, Toast.LENGTH_LONG).show();

                photoRef.getDownloadUrl();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_PHOTO_PICKER){
            if(resultCode == RESULT_OK){
                selectedimageUrl = data.getData();
            }
        }
    }
}
