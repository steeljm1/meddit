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
import Models.ContentFieldModel;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;

// Pages through images also adds note fragment to the screen when clicked on.
public class ImagePager extends BaseFragment {

    ArrayList<ContentFieldModel> content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.image_pager, container, false);

        this.content = (ArrayList<ContentFieldModel>)getArguments().getSerializable("content");

        final ViewPager viewPager = (ViewPager)v.findViewById(R.id.imagePager);

        ImageFragmentAdapter viewPagerAdapter = new ImageFragmentAdapter(getFragmentManager(),content);

        viewPager.setAdapter(viewPagerAdapter);

        int position = getArguments().getInt("position");
        viewPager.setCurrentItem(position);

        return v;
    }
}

class ImageFragmentAdapter extends FragmentStatePagerAdapter
{
    ArrayList<ContentFieldModel> content;

    public ImageFragmentAdapter(FragmentManager fm, ArrayList<ContentFieldModel> content) {

        super(fm);
        this.content = content;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Image_Fragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable("image", content.get(position).getImageContent());
        bundle.putString("notes", content.get(position).getTextContent());

        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public int getCount() {
        return content.size();
    }
}
