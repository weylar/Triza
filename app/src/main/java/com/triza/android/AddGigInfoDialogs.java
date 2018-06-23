package com.triza.android;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Switch;

import com.triza.android.R;

/**
 * Created by Weylar on 24/05/2018.
 */

public class AddGigInfoDialogs extends DialogFragment {
    public static final String NAME = "Name";
    public static final String INPUT_SEARCH_TAG = "InputSearchTag";
    public static final String ENTER_GIG_TITLE = "EnterGigTitle";
    public static final String ADD_PACKAGE_NAME = "AddPackageName";
    public static final String ADD_PACKAGE_DESCRIPTION = "AddPackageDescription";
    public static final String EXTRA_OFFERS = "ExtraOffers";
    public static final String DESCRIBE_GIG = "DescribeYourGig";
    public static final String FREQUENTLY_ASKED_QUESTIONS = "FrequentlyAskedQuestions";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String name = getArguments().getString(NAME);
        if (name != null) {
            return chooseContentToDisplay(name).create();
        }
        return null;

    }

    @NonNull
    private AlertDialog.Builder getBuilderSearchTag() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Search tags are keywords that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming.\"")
                .setTitle("What are Search Tags?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    @NonNull
    private AlertDialog.Builder getBuilderGigTitle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Gig title ")
                .setTitle("What is Gig Title?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    @NonNull
    private AlertDialog.Builder getBuilderPackageName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Search tags are simply search terms that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming\"")
                .setTitle("What are GigTitle?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    @NonNull
    private AlertDialog.Builder getBuilderPackageDescription() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Search tags are simply search terms that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming\"")
                .setTitle("What are GigTitle?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    @NonNull
    private AlertDialog.Builder getBuilderExtraOffers() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Search tags are simply search terms that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming\"")
                .setTitle("What are GigTitle?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    @NonNull
    private AlertDialog.Builder getBuilderGigDescription() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Search tags are simply search terms that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming\"")
                .setTitle("What are GigTitle?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    @NonNull
    private AlertDialog.Builder getBuilderFaq() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Search tags are simply search terms that help identify your gig on Triza. These tags best describe your gig and will " +
                "determine your gig exposure when buyers search for gigs.\nE.g For an Android developer you can use the tags \"Android\", \"Java\", \"Programming\"")
                .setTitle("What are GigTitle?")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setCancelable(true);
        return builder;
    }

    private AlertDialog.Builder chooseContentToDisplay(String action) {
        if (action.equals(INPUT_SEARCH_TAG)) {
            return getBuilderSearchTag();

        } else if (action.equals(ENTER_GIG_TITLE)) {
            return getBuilderGigTitle();

        } else if (action.equals(ADD_PACKAGE_NAME)) {
            return getBuilderPackageName();

        } else if (action.equals(ADD_PACKAGE_DESCRIPTION)) {
            return getBuilderPackageDescription();

        } else if (action.equals(EXTRA_OFFERS)) {
            return getBuilderExtraOffers();

        } else if (action.equals(DESCRIBE_GIG)) {
            return getBuilderGigDescription();

        } else if (action.equals(FREQUENTLY_ASKED_QUESTIONS)) {
            return getBuilderFaq();
        }
        return null;
    }


}
