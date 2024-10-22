package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import otago.Midwifery.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class QuizBlankFragment extends Fragment {

    // THIS ONLY SIMULATES WHERE THE QUIZ FRAGMENT WILL SIT
    public QuizBlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle x = getArguments();
        if(x != null)
        {
            String y = String.valueOf(x.getInt("categoryID"));
            Toast.makeText(getActivity(), y, Toast.LENGTH_LONG).show();
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizblankfragment, container, false);
    }

}
