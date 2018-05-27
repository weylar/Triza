package com.triza.android.Home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.triza.android.Adapters.GigsAdapterHorizontal;
import com.triza.android.Categories.CategoryActivity;
import com.triza.android.Gigs.Gigs;
import com.triza.android.HomeActivity;
import com.triza.android.R;
import com.triza.android.RecyclerItemClickListeners;
import com.triza.android.Search.Search;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    MyViewPagerAdapter myvpAdapter;
    int[] image;
    String[] imageDesc;
    TextView allCat, featuredViewAll, trendingViewAll;
    TextView prog;
    TextView video;
    TextView music;
    TextView buss;
    TextView imageDescription;
    TextView imageDescrptionExtra;
    ImageView category, search, preference;
    Context context = getActivity();
    int timer = 5000; //in milliseconds
    int page = 0;
    Handler handler;
    RecyclerView recyclerViewFeatured;
    RecyclerView recyclerViewTrending;
    GigsAdapterHorizontal gigAdapter;
    LinearLayoutManager linearLayoutManagerTrending;
    LinearLayoutManager linearLayoutManagerFeatured;
    DividerItemDecoration itemDecoration;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGigsDatabaseReference;
    private DatabaseReference mFavouritesDatabaseReference;
    private DataSnapshot favouritesDataSnapshots;



    // Runnable to help auto swtch my pageviewer

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (myvpAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, timer);
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        handler = new Handler(); //Initializing the handler

//        These are dummy set of images in place of oncloud images
        image = new int[]{R.drawable.desert, R.drawable.a, R.drawable.b,
                R.drawable.c, R.drawable.d, R.drawable.desert, R.drawable.e, R.drawable.f};

//        These are dummy set of image decsription in place of oncloud descriptions
        imageDesc = new String[]{"PROGRAMMING & TECH", "GRAPHICS & DESIGN", "DIGITAL MARKETING", "WRITING & TRANSLATION", "VIDEO & ANIMATION",
                "MUSIC & AUDIO", "BUSINESS & ACCOUNTING", "FUN & LIFESTYLE"};



        linearLayoutManagerFeatured = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false); //Layout manager in charge of horizontal recycler view
        linearLayoutManagerTrending = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);//Layout manager in charge of horizontal recycler view



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);

        //Referencing all objects from xml
        category = view.findViewById(R.id.ic_categories);
        search = view.findViewById(R.id.ic_search);
        preference = view.findViewById(R.id.ic_preference);
        allCat = view.findViewById(R.id.all_cat);
        allCat.setText("All Categories");
        prog = view.findViewById(R.id.prog);
        prog.setText("Programming & Tech");
        video = view.findViewById(R.id.video);
        video.setText("Video & Animation");
        music = view.findViewById(R.id.music);
        music.setText("Music & Audio");
        buss = view.findViewById(R.id.buss);
        buss.setText("Business & Accounting");
        viewPager = view.findViewById(R.id.home_view_pager); //Referencing viewpager here
        tabLayout = view.findViewById(R.id.tab_dots); //Referencing the tablayout here
        tabLayout.setupWithViewPager(viewPager, true);
        myvpAdapter = new MyViewPagerAdapter(context, image);
        viewPager.setAdapter(myvpAdapter); //setting the viewpager adapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener); //implementing viewpagerchange listener method
        imageDescription = view.findViewById(R.id.image_desc); //referncing image decs
        imageDescription.setText("PROGRAMMING & TECH");// A DEFAULT TEXT BEFORE CHANGELISTENER IS CALLED
        imageDescrptionExtra = view.findViewById(R.id.image_desc_extra); //Referncing image decs extra here
        imageDescrptionExtra.setText("Make it work for you on triza");
        featuredViewAll = view.findViewById(R.id.featured_view_all);
        featuredViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeaturedGigs.class);
                startActivity(intent);
            }
        });
        trendingViewAll = view.findViewById(R.id.trending_view_all);
        trendingViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrendingGigs.class);
                startActivity(intent);
            }
        });


        //Wiring up the recyclerview to populate
        recyclerViewFeatured = view.findViewById(R.id.recycler_view_featured);
        recyclerViewTrending = view.findViewById(R.id.recycler_view_trending);

        recyclerViewFeatured.setLayoutManager(linearLayoutManagerFeatured);  //I set manager for recycler here
        recyclerViewTrending.setLayoutManager(linearLayoutManagerTrending);  //I set manager for recycler here

        recyclerViewFeatured.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view
        recyclerViewTrending.setItemAnimator(new DefaultItemAnimator()); //Animator for recycler view

        //itemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        //recyclerView.addItemDecoration(itemDecoration);
        gigAdapter = new GigsAdapterHorizontal(getActivity(), HomeActivity.gigList); //My adapter in charge of recycler view


        recyclerViewFeatured.setAdapter(gigAdapter); //i set the adapter on recycler here
        recyclerViewTrending.setAdapter(gigAdapter); //i set the adapter on recycler here

        /*Assingns on click listerner*/
        recyclerViewFeatured.addOnItemTouchListener(new RecyclerItemClickListeners(getActivity(), recyclerViewFeatured, new RecyclerItemClickListeners.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO: go to gig details revome snackbar
                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {


            }
        }));
        recyclerViewTrending.addOnItemTouchListener(new RecyclerItemClickListeners(getActivity(), recyclerViewTrending, new RecyclerItemClickListeners.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO: go to gig details revome snackbar

                Snackbar.make(view, position + "", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {


            }
        }));

        /*Making the views functional*/
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSearch = new Intent(getActivity(), Search.class);
                startActivity(moveToSearch);
            }
        });
        preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "You clicked prefernce icon", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        return view;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            imageDescription.setText(imageDesc[position]);
            page = position;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        Context context;
        int[] images;
        LayoutInflater layoutInflater;

        public MyViewPagerAdapter(Context context, int[] images) {
            this.context = context;
            this.images = images;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView;
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.pager_item, container, false);
            imageView = view.findViewById(R.id.image_view); //getting reference to mage in the view
            imageView.setImageResource(images[position]); //setting images by position on imageview
            container.addView(view); //adding pagelayout to the current page of the viewpager
            return view;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public boolean isViewFromObject(View v, Object object) {
            return v == object;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, timer);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


}
