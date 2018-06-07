package com.triza.android.Gigs;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Categories.Categories;
import com.triza.android.Categories.SubCategories;
import com.triza.android.R;

import java.util.ArrayList;
import java.util.List;


public class AddGigOverviewFragment extends Fragment {

    boolean isTwise = false;
    TextView titleTextCount;
    ImageView tagInfo;
    boolean isEdit = true;
    private EditText searchTagEditText;
    ArrayAdapter<Categories> categoryAdapter;
    ArrayAdapter<SubCategories> subCategoryAdapter;
    private String gig_title, gig_desc = "", category_id = "", sub_cat_id = "", search_tag = "";
    private EditText gigTitle_editText;
    private Spinner catSpinner, subCatSpinner;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
    private DatabaseReference mSubCategoriesDatabaseReference;


    public AddGigOverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("categories");
        mSubCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("sub_categories");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_gig_overview_fragment, container, false);
        titleTextCount = view.findViewById(R.id.title_text_count);
        gigTitle_editText = view.findViewById(R.id.gig_title_editText);
        searchTagEditText = view.findViewById(R.id.search_tag_editText);
        catSpinner = view.findViewById(R.id.category_spinner);
        subCatSpinner = view.findViewById(R.id.sub_cat_spinner);
        //tagInfo = view.findViewById(R.id.tag_info);


       /* tagInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TagEntryInfo dialog = new TagEntryInfo();

                /*this help to target this particular fragment in  the main activity*/
               /* dialog.setTargetFragment(AddGigOverviewFragment.this, 0);
                dialog.show(getFragmentManager(), "123");
            }
        }); */


        //programatically i set the text counter by using text watcher and attached to editetxt
        titleTextCount.setText(0 + "/70 max");
        titleTextCount.setTextColor(Color.rgb(0, 0, 0));


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String titleListener = gigTitle_editText.getText().toString();

                if (titleListener.length() <= 60) {
                    titleTextCount.setText(titleListener.length() + "/70 max");
                    titleTextCount.setTextColor(Color.rgb(0, 0, 0));

                }

                if (titleListener.length() > 60) {
                    titleTextCount.setText(titleListener.length() + "/70 max");
                    titleTextCount.setTextColor(Color.rgb(0, 150, 136));
                }
                if (titleListener.length() > 70) {
                    titleTextCount.setText(70 - titleListener.length() + "/70 max");
                    titleTextCount.setTextColor(Color.parseColor("RED"));

                }

            }
        };
        gigTitle_editText.addTextChangedListener(textWatcher);

        double scaletype = getResources().getDisplayMetrics().density;
        if (scaletype >= 3.0) {
            isTwise = true;
        }
        searchTagEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 1 && !isEdit) {
                    if (!Character.isSpaceChar(s.charAt(0))) {
                        if (s.charAt(start) == ',')
                            if (s.charAt(0) == ',') {
                                String noComma = "Tags cannot start with a comma!";
                                Toast.makeText(getActivity(), noComma, Toast.LENGTH_LONG).show();
                            } else {
                                setTag();// generate chips
                            }

                    } else {
                        searchTagEditText.getText().clear();
                        searchTagEditText.setSelection(0);
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isEdit) {
                    setTag();

                }
            }
        });


        mCategoriesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<Categories> categories = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    Categories areaName = areaSnapshot.getValue(Categories.class);
                    categories.add(areaName);
                }


                categoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                catSpinner.setAdapter(categoryAdapter);

                catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        // Here you get the current item (a Categories object) that is selected by its position
                        Categories category = categoryAdapter.getItem(position);
                        // Here you can do the action you want to...
                        category_id = category.getCatId();

                        getSubCategories(category_id);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapter) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        gigTitle_editText = view.findViewById(R.id.gig_title_editText);


        return view;
    }

    private void getSubCategories(String category_id) {
        mSubCategoriesDatabaseReference.orderByChild("catId")
                .equalTo(category_id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final List<SubCategories> subCategories = new ArrayList<>();

                        for (DataSnapshot subCatSnapshot : dataSnapshot.getChildren()) {
                            SubCategories subCategory = subCatSnapshot.getValue(SubCategories.class);
                            subCategories.add(subCategory);
                        }


                        final ArrayAdapter<SubCategories> subCategoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subCategories);
                        subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subCatSpinner.setAdapter(subCategoryAdapter);
                        subCatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                SubCategories subCategory = subCategoryAdapter.getItem(position);
                                sub_cat_id = subCategory.getCatId();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapter) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public void setTag() {
        if (searchTagEditText.getText().toString().contains(",")) // just to be sure there is comman in string
        {

            SpannableStringBuilder ssb = new SpannableStringBuilder(searchTagEditText.getText().toString());
            // split string wich comma
            String chips[] = searchTagEditText.getText().toString().trim().split(",");
            int x = 0;
            for (String c : chips) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                TextView textView = (TextView) inflater.inflate(R.layout.tag_edittext, null);
                textView.setText(c); // set text
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                textView.measure(spec, spec);
                textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
                Bitmap b = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(b);
                canvas.translate(-textView.getScrollX(), -textView.getScrollY());
                textView.draw(canvas);
                textView.setDrawingCacheEnabled(true);
                Bitmap cacheBmp = textView.getDrawingCache();
                Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
                textView.destroyDrawingCache(); // destory drawable
                BitmapDrawable bmpDrawable = new BitmapDrawable(viewBmp);
                int width = bmpDrawable.getIntrinsicWidth();
                int height = bmpDrawable.getIntrinsicHeight();
                if (isTwise) {
                    width = width * 2;
                    height = height * 2;
                }
                bmpDrawable.setBounds(0, 0, width, height);
                ssb.setSpan(new ImageSpan(bmpDrawable), x, x + c.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                x = x + c.length() + 1;
            }
            // set chips span
            isEdit = false;


            searchTagEditText.setText(ssb);


            // move cursor to last
            searchTagEditText.setSelection(searchTagEditText.getText().length());
        }

    }


}
