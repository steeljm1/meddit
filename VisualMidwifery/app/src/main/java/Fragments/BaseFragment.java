package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import otago.Midwifery.CategoryView;

/**
 * Created by liub3 on 16/07/2014.
 * this class extends normal fragment
 * only disable default on back press method
 * the base class for most of fragments in whole app
 */
public class BaseFragment extends Fragment {
    public CategoryView mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (CategoryView) this.getActivity();
    }

    public boolean onBackPressed(){
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
