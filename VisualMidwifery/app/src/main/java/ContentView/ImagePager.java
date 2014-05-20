package ContentView;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import Models.ContentFieldModel;
import otago.Midwifery.R;

// Pages through images also adds note fragment to the screen when clicked on.
public class ImagePager extends Fragment {

    ArrayList<ContentFieldModel> content;

    public ImagePager()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.image_pager, container, false);

        this.content = (ArrayList<ContentFieldModel>)getArguments().getSerializable("content");

        final ViewPager viewPager = (ViewPager)v.findViewById(R.id.imagePager);
        viewPager.setAdapter(new ImageFragmentAdapter(getFragmentManager(), content));

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
