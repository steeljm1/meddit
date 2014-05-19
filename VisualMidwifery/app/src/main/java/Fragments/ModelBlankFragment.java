package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ContentView.ContentCategoryListFragment;
import ModelView.ModelAngleCategoryListFragment;
import otago.Midwifery.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ModelBlankFragment extends Fragment {

    // THIS ONLY SIMULATES THE SPACE WHERE MODEL VIEW WILL SIT
    public ModelBlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modelblankfragment, container, false);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment modelAngleCategoryListFragment = new ModelAngleCategoryListFragment();
        ft.replace(R.id.RootFrameM, modelAngleCategoryListFragment,"ModelAngleCategoryList");
        ft.commit();

        return v;
    }


}
