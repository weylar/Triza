package com.triza.android.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.triza.android.R;

/**
 * Created by Weylar on 17/05/2018.
 */

public class ConfirmDeleteAllFav extends DialogFragment {
    private DeleteAll mListener;
    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try {
            mListener = (DeleteAll) getTargetFragment();

        } catch (final ClassCastException e) {
            throw new ClassCastException(getTargetFragment().toString());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage("Are you sure you want to delete all? \nThis will clear all your favorite gigs and can't be undone")
                .setTitle("Action confirmation!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.deleteAll();
                    }
                })
                .setIcon(R.drawable.ic_warning_24dp)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setCancelable(true);


        return builder.create();
    }



    /*Inner interface that listens to yes click*/
    public interface DeleteAll {
        void deleteAll();
    }

}
