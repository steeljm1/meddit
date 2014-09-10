package ContentView;


import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

import Fragments.BaseFragment;
import Helper.NonSwipeableViewPager;
import Models.ContentFieldModel;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;

// Pages through images also adds note fragment to the screen when clicked on.
public class ImagePager extends BaseFragment {

    ArrayList<ContentFieldModel> content;
    public static NonSwipeableViewPager viewPager;

    public ImagePager() {
    }

    public static NonSwipeableViewPager getViewPager()
    {
        return viewPager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.image_pager, container, false);

        this.content = (ArrayList<ContentFieldModel>)getArguments().getSerializable("content");

        viewPager = (NonSwipeableViewPager)v.findViewById(R.id.imagePager);
        viewPager.setPagingEnabled(true);
        ImageFragmentAdapter viewPagerAdapter = new ImageFragmentAdapter(getFragmentManager(),content);

        viewPager.setAdapter(viewPagerAdapter);

        int position = getArguments().getInt("position");
        viewPager.setCurrentItem(position);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
