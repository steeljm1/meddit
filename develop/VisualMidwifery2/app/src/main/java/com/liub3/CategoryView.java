package com.liub3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;


public class CategoryView extends FragmentActivity {

    ViewPager viewPager = null;

    public Context getContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        String category="";
        Intent intentIn = getIntent();
        Bundle b = intentIn.getExtras();
        if (b != null) {
            category = (String) b.get("Clicked Category");
            setTitle(category);

        }
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = new Bundle();
        bundle.putString("CATEGORY",category);


        viewPager = (ViewPager) findViewById(R.id.pager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),bundle);
        viewPager.setAdapter(myAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


}

class MyAdapter extends FragmentStatePagerAdapter {
    private final Bundle fragmentBundle;

    public MyAdapter(FragmentManager fm,Bundle data) {
        super(fm);
        fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        //Log.d("LIUB3","get item is called "+position);


        if (position == 0) {
            fragment = new MaterialFragment();
        }
        if (position == 1) {
            fragment = new ModelFragment();
        }
        if (position == 2) {
            fragment = new QuizFragment();

        }
        fragment.setArguments(this.fragmentBundle);
        return fragment;
    }

    @Override
    public int getCount() {
        //Log.d("LIUB3","get count is called");
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Material";
        }
        if (position == 1) {
            return "Model View";
        }

        if (position == 2) {
            return "Quiz";
        }
        return null;
    }

}