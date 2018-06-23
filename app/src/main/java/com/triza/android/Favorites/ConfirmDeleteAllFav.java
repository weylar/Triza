package com.triza.android.Favorites;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.triza.android.R;

/**
 * Created by Weylar on 17/05/2018.
 */

public class ConfirmDeleteAllFav extends DialogFragment {
    private DeleteAll mListener;



    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DeleteAll) getTargetFragment();
        }catch (ClassCastException e){

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage("Are you sure you want to delete all favorite gigs? \nThis will clear all your favorite gigs and can't be undone")
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
