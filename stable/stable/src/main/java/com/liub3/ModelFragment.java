package com.liub3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liub3 on 10/04/14.
 */
public class ModelFragment extends Fragment {
    private Bundle args;
    private String category;
    private SQLiteAdapter mySQLiteAdapter;
    private List<String> angles;

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Log.d("LIUB3", "onCreateView");
        if (container == null) {
        }
        View view = inflater.inflate(R.layout.modelfragment, container, false);

        listView = (ListView) view.findViewById(R.id.modelfragmentListView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, angles);
        MyListAdapter adapter = new MyListAdapter(getActivity(),R.layout.category_item,angles);
        listView.setAdapter(adapter);

        registerClickCallback();

        return view;
    }

    private void registerClickCallback() {
        listView.setOnItemClickListener(i);
    }

    AdapterView.OnItemClickListener i = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickAngle = angles.get(position);
            Intent myIntent = new Intent();
            myIntent.setClass(getActivity(),ModelDetails.class);
            myIntent.putExtra("INFO",new String[]{category,clickAngle});
            startActivity(myIntent);
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            //com.liub3.Message.message(getActivity(), clickCat);
        }
    };

    private class MyListAdapter extends ArrayAdapter<String> {
        public MyListAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        /*public MyListAdapter() {
            super(getActivity(), R.layout.category_item, category);
        }*/

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View category_view = convertView;
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            if (category_view == null) {
                category_view = inflater.inflate(R.layout.category_item, parent, false);
            }
            String currentCategory = angles.get(position);
            TextView txtCategory = (TextView) category_view.findViewById(R.id.category_item);
            txtCategory.setText(currentCategory);
            return category_view;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        category = args.getString("CATEGORY");

        mySQLiteAdapter = new SQLiteAdapter(this.getActivity());
        angles = mySQLiteAdapter.getAngle(category);

    }


}
