package ModelView;



import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ModelViewDetailFragment extends Fragment {


    public ModelViewDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_view_details, container, false);
    }


}
