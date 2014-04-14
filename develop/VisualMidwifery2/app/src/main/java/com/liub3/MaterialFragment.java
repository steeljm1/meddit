package com.liub3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liub3 on 10/04/14.
 */
public class MaterialFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        //Log.d("LIUB3", "onCreateView");
        return inflater.inflate(R.layout.materialfragment,container,false);
    }
}
