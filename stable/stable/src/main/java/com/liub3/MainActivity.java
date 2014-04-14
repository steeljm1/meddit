package com.liub3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final long TIME_INTERVAL = 1500; // # milliseconds, desired time passed between two back presses.

    //private ListView listView;
    private List<String> category;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        category = new ArrayList<String>();
        StartUpApplication MAC = ((StartUpApplication) getApplicationContext());
        category = MAC.getCategory();
        //setTitle("Main Category");
        popListView();
        registerClickCallback();
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listViewCategory);
        list.setOnItemClickListener(i);
    }

    AdapterView.OnItemClickListener i = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickCat = category.get(position);
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CategoryView.class);
            intent.putExtra("Clicked Category", clickCat);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            //String message = "You click position: " + position + " Which is category: " + clickCat;
            //com.liub3.Message.message(MainActivity.this, message);
        }
    };

    private void popListView() {
        ArrayAdapter<String> adapter = new MyListAdapter();
        ListView mainList = (ListView) findViewById(R.id.listViewCategory);
        mainList.setAdapter(adapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main);
        }
    }


    @Override
    public void onBackPressed() {
        //Toast tempToast = null;// = Toast.makeText(getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT);
        //tempToast.setDuration(10);
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            return;
        } else {
            final Toast toast = Toast.makeText(this, "Please Click BACK again to Exit", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 800);
        }

        mBackPressed = System.currentTimeMillis();
    }

    private class MyListAdapter extends ArrayAdapter<String> {

        public MyListAdapter() {
            super(MainActivity.this, R.layout.category_item, category);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View category_view = convertView;
            if (category_view == null) {
                category_view = getLayoutInflater().inflate(R.layout.category_item, parent, false);
            }
            String currentCategory = category.get(position);
            TextView txtCategory = (TextView) category_view.findViewById(R.id.category_item);
            txtCategory.setText(currentCategory);
            return category_view;
        }
    }
}
