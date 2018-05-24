package com.triza.android.Dialogs;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.triza.android.R;

/**
 * Created by Weylar on 24/05/2018.
 */

public class TagEntryInfo extends DialogFragment {

    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage("Search tags are simply search terms that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming\"")
                .setTitle("What are Search Tags?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setCancelable(true);


        return builder.create();
    }



}
