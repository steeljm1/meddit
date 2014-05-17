package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ContentView.ContentCategoryListFragment;
import otago.Midwifery.R;

public class ContentBlankFragment extends Fragment {


    public ContentBlankFragment() {
        // Required empty public constructor
    }

    // THE FRAGMENT FOR THE STATIC CONTENT THAT IS DISPLAYED IN THE BASE LEVEL FRAGMENT PAGER
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contentblankfragment, container, false);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment contentCategoryListFragment = new ContentCategoryListFragment();
        ft.replace(R.id.RootFrame, contentCategoryListFragment,"ContentCategoryList");
        ft.commit();

        return v;
    }
}
