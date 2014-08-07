package otago.Midwifery;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;

import ExamRevision.ExamRevisionPagerAdapter;
import Helper.NonSwipeableViewPager;

public class ExamRevision extends FragmentActivity implements ActionBar.TabListener {

    /**
     * Called when the activity is first created.
     */
    public static NonSwipeableViewPager viewPager;
    int tabPos;
    int counter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_revision);
        counter = 0;
        Intent intentIn = getIntent();

        Bundle b = intentIn.getExtras();
        if (b != null) {
            tabPos = b.getInt("tabPos");
            if(tabPos == 0)
            {
                counter = 0;
            }
            else {
                counter = -1;
            }
        }
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        //actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        viewPager = (NonSwipeableViewPager) findViewById(R.id.examPager);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(4);
        ExamRevisionPagerAdapter viewPagerAdapter = new ExamRevisionPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.

                actionBar.setSelectedNavigationItem(position);
                counter++;

                if(counter>=1 && position != 0) {
                    counter = -1;
                    Intent intent = new Intent();
                    intent.setClass(getApplication(), ExamRevision.class);
                    Bundle extras = new Bundle();
                    extras.putInt("tabPos", position);
                    intent.putExtras(extras);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }

        });
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < viewPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(viewPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
        //viewPager.setCurrentItem(position);
        viewPager.setCurrentItem(tabPos);
        actionBar.setSelectedNavigationItem(tabPos);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
