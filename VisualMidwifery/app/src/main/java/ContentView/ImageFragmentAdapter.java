package ContentView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import Models.ContentFieldModel;

/**
 * Created by Bo on 4/08/2014.
 */
public class ImageFragmentAdapter extends FragmentStatePagerAdapter {
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
