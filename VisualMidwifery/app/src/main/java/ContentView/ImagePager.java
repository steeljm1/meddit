package ContentView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import Fragments.BaseFragment;
import Helper.NonSwipeableViewPager;
import Models.ContentFieldModel;
import otago.Midwifery.R;

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

        this.content = (ArrayList<ContentFieldModel>)getArguments().getSerializable("content");//get all content from last fragment

        viewPager = (NonSwipeableViewPager)v.findViewById(R.id.imagePager);//link layout to object
        viewPager.setPagingEnabled(true);//set properties of viewpager

        ImageFragmentAdapter viewPagerAdapter = new ImageFragmentAdapter(getFragmentManager(),content);//set up adapter for the viewpager

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());

        int position = getArguments().getInt("position");
        viewPager.setCurrentItem(position);//get the clicked content and switch displaying pager

        return v;
    }

}
