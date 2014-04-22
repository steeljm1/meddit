package otago.meddit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import otago.meddit.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private String selectedCategory;

    public QuizFragment(String selectedCategoryIn) {
        // Required empty public constructor
        selectedCategory = selectedCategoryIn;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }


}
