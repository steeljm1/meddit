package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import otago.Midwifery.CategoryView;

/**
 * Created by liub3 on 17/07/2014.
 */
public class BaseFragmentList extends ListFragment {
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
