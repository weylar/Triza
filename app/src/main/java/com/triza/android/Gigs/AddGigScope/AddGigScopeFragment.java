package com.triza.android.Gigs.AddGigScope;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.triza.android.Adapters.CustomOfferAdapter;
import com.triza.android.AddGigInfoDialogs;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;

import static com.triza.android.AddGigInfoDialogs.ADD_PACKAGE_DESCRIPTION;
import static com.triza.android.AddGigInfoDialogs.ADD_PACKAGE_NAME;
import static com.triza.android.AddGigInfoDialogs.EXTRA_OFFERS;
import static com.triza.android.AddGigInfoDialogs.NAME;


public class AddGigScopeFragment extends Fragment {
    TextView tvPackageName, tvPackageDesc, tvDeliverTime, tvMinimumPrice, tvExtraOffer, tvAddCustomOffer;
    EditText edPackageName, edPackageDesc, edAddCustomOffer, edPrice;
    Spinner deliveryTime;
    RelativeLayout relAddCustomOffer;
    Button addCustom, cancelCustom;
    int trackButtonState = 1;
    LinearLayout linearCustom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_gig_scope, container, false);

        /*Referencing all views from XML*/
        tvPackageName = view.findViewById(R.id.tv_package_name);
        edPackageName = view.findViewById(R.id.ed_package_name);
        tvPackageDesc = view.findViewById(R.id.tv_package_desc);
        edPackageDesc = view.findViewById(R.id.ed_package_desc);
        tvDeliverTime = view.findViewById(R.id.tv_deliver_time);
        deliveryTime = view.findViewById(R.id.spinner_deliver_time);
        tvMinimumPrice = view.findViewById(R.id.tv_price);
        tvMinimumPrice = view.findViewById(R.id.tv_price);
        edPrice = view.findViewById(R.id.price_edit_text);
        tvExtraOffer = view.findViewById(R.id.tv_extra_offers);
        tvAddCustomOffer = view.findViewById(R.id.tv_add_extra_offer);
        edAddCustomOffer = view.findViewById(R.id.ed_add_extra_offer);
        relAddCustomOffer = view.findViewById(R.id.rel_add_custom_offer);
        addCustom = view.findViewById(R.id.bt_add_custom);
        cancelCustom = view.findViewById(R.id.bt_cancel_custom);
        linearCustom = view.findViewById(R.id.linear_custom);



        /*Set button to disabled by default*/
        disableButton(addCustom);

        /*This checks and track edt text Content to enable and disable add button*/
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edAddCustomOffer.getText().toString().length() == 0) {
                    if (trackButtonState == 1) {
                        disableButton(addCustom);
                    } else {
                        trackButtonState = 0;
                    }
                } else {
                    addCustom.setEnabled(true);
                    addCustom.setBackgroundResource(R.drawable.rectangle_button_bg);
                }

            }
        };
        edAddCustomOffer.addTextChangedListener(textWatcher);

        /*Custom offer clicks should make editText visible here*/
        tvAddCustomOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relAddCustomOffer.setVisibility(View.VISIBLE);
                tvAddCustomOffer.setVisibility(View.GONE);


            }
        });

        /*Add button should remove view and add a list to the offer*/
        addCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relAddCustomOffer.setVisibility(View.GONE);
                tvAddCustomOffer.setVisibility(View.VISIBLE);

                /*Get edit text inputs and set on the new appended text view*/
                List list = new ArrayList();
                CustomOffersClass customOffersClass = new CustomOffersClass(edAddCustomOffer.getText().toString().trim());
                list.add(customOffersClass);
                CustomOfferAdapter customOfferAdapter = new CustomOfferAdapter(getActivity(), list);
                for (int i = 0; i < customOfferAdapter.getCount(); i++) {
                    View item = customOfferAdapter.getView(i, null, null);
                    linearCustom.addView(item);
                }
                edAddCustomOffer.getText().clear();


            }
        });

        /*Cancel button should remove view only*/
        cancelCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relAddCustomOffer.setVisibility(View.GONE);
                tvAddCustomOffer.setVisibility(View.VISIBLE);
            }
        });

        tvPackageName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= (tvPackageName.getRight() - tvPackageName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                        AddGigInfoDialogs addGigInfoDialogs = new AddGigInfoDialogs();
                        Bundle bundle = new Bundle();
                        bundle.putString(NAME, ADD_PACKAGE_NAME);
                        addGigInfoDialogs.setArguments(bundle);
                        addGigInfoDialogs.setTargetFragment(AddGigScopeFragment.this, 1);
                        addGigInfoDialogs.show(getFragmentManager(), "123");
                        return true;
                    }
                }
                return true;
            }


        });
        tvPackageDesc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= (tvPackageDesc.getRight() - tvPackageDesc.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                        AddGigInfoDialogs addGigInfoDialogs = new AddGigInfoDialogs();
                        Bundle bundle = new Bundle();
                        bundle.putString(NAME, ADD_PACKAGE_DESCRIPTION);
                        addGigInfoDialogs.setArguments(bundle);
                        addGigInfoDialogs.setTargetFragment(AddGigScopeFragment.this, 1);
                        addGigInfoDialogs.show(getFragmentManager(), "123");
                        return true;
                    }
                }
                return true;
            }


        });
        tvExtraOffer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= (tvExtraOffer.getRight() - tvExtraOffer.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                        AddGigInfoDialogs addGigInfoDialogs = new AddGigInfoDialogs();
                        Bundle bundle = new Bundle();
                        bundle.putString(NAME, EXTRA_OFFERS);
                        addGigInfoDialogs.setArguments(bundle);
                        addGigInfoDialogs.setTargetFragment(AddGigScopeFragment.this, 1);
                        addGigInfoDialogs.show(getFragmentManager(), "123");
                        return true;
                    }
                }
                return true;
            }


        });

        return view;
    }

    private void disableButton(View view) {
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.rectangle_button_bg_light);
    }


}
