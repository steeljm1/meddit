package otago.meddit.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import otago.meddit.ModelDetails;
import otago.meddit.R;
import otago.meddit.SQLiteAdapter;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class ModelFragment extends Fragment {

    private String selectedCategory;
    private SQLiteAdapter mySQLiteAdapter;
    private ArrayList<String> angles;

    private ListView listView;

    public ModelFragment(String selectedCategoryIn) {
        // Required empty public constructor
        selectedCategory = selectedCategoryIn;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_model, container, false);
        listView = (ListView) view.findViewById(R.id.modelfragmentListView);

        mySQLiteAdapter = new SQLiteAdapter(this.getActivity());
        angles = mySQLiteAdapter.getAngle(selectedCategory);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, angles);
        listView.setAdapter(adapter);

        registerClickCallback();
    }

    private void registerClickCallback() {
        listView.setOnItemClickListener(i);
    }

    AdapterView.OnItemClickListener i = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickAngle = angles.get(position);
            Intent myIntent = new Intent();
            myIntent.setClass(getActivity(), ModelDetails.class);
            myIntent.putExtra("INFO", new String[]{selectedCategory, clickAngle});
            startActivity(myIntent);
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    };
}
