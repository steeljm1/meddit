package otago.Midwifery;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import DBController.DatabaseController;
import Fragments.ContentBlankFragment;
import Fragments.ModelBlankFragment;
import Fragments.QuizBlankFragment;
import Models.ContentFieldModel;
import Models.MainCategoryModel;


public class CategoryView extends FragmentActivity implements ActionBar.TabListener {
    ArrayList<String> mainMenuArrayList;
    int activityId;
    int subTabId;
    int mainID;
    static CategoryView categoryView;
    MyPagerAdapter myPagerAdapter;
    /*
* This block is for the main contains of this activity
*/
    private CharSequence mTitle;//the title of this activity
    private CharSequence mDrawerTitle = "Main Menu";


    /////////////////////////////////////////////////////////////////////
    //####################################################################
    /*
    * This block is for generate the navigation drawer
    * */
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //####################################################################
    /*
    * This block is for generate drawer content list view etc...
    * */
    MyDrawerAdapter listAdapter;
    ExpandableListView expListView;
    //List<String> listDataHeader;//main menu array list
    HashMap<String, List<String>> listDataChild;

    //####################################################################
    private ActionBar actionBar;
    private ViewPager viewPager;
    private ActionBar.Tab tabs;

    public static CategoryView getInstance() {
        return categoryView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        categoryView = this;
/*
* get info from last activity
* */
        Intent intentIn = getIntent();

        Bundle b = intentIn.getExtras();
        if (b != null) {
            mainID = b.getInt("menuID");
            activityId = b.getInt("activityId");
            subTabId = b.getInt("subTabId");
            mainMenuArrayList = new ArrayList<String>(Arrays.asList(b.getStringArray("CategoryList")));
            setTitle(mainMenuArrayList.get(activityId));
            mTitle = mainMenuArrayList.get(activityId);
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
        * This block is mainly for generate drawer
        * */


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout2);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new MyDrawerAdapter(this, mainMenuArrayList, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //Toast.makeText(getApplicationContext(),
                //        mainMenuArrayList.get(groupPosition) + " Expanded",
                //        Toast.LENGTH_SHORT).show();

                int count = listAdapter.getGroupCount();
                for (int position = 0; position <= count; position++) {
                    if (position != groupPosition) {
                        expListView.collapseGroup(position);
                    }
                }
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(),
                //        mainMenuArrayList.get(groupPosition) + " Collapsed",
                //        Toast.LENGTH_SHORT).show();
            }
        });
////////////////////////
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                if (groupPosition != activityId) {
                    DrawerSelectReload(groupPosition, childPosition);
                } else {
                    viewPager.setCurrentItem(childPosition, true);
                    actionBar.setSelectedNavigationItem(childPosition);
                    mDrawerLayout.closeDrawers();
                }
                return false;
            }
        });
//////////////////////////
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                ImageButton imgBtnHome = (ImageButton) findViewById(R.id.imgBtnHome);

                View.OnClickListener imgBtnHomeOnClick = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //MessageToast.message(getApplication(), "On CLick home");
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                };

                imgBtnHome.setOnClickListener(imgBtnHomeOnClick);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        * This block start to create tab view for this activity
        * */
        actionBar = this.getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(2);

        myPagerAdapter = new MyPagerAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        List<String> myCats = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CateActivity)));
        for (int i = 0; i < myCats.size(); i++) {
            tabs = actionBar.newTab();
            tabs.setText(myCats.get(i));
            tabs.setTabListener(this);
            actionBar.addTab(tabs);
        }

        viewPager.setCurrentItem(subTabId, true);
        actionBar.setSelectedNavigationItem(subTabId);//change to selected fragment and tab

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
        mDrawerLayout.closeDrawers();

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //get the last tab selected
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public int getmMainID() {
        return mainID;
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        //SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            Fragment fg = null;
            if (position == 0) {
                fg = new ContentBlankFragment();
                //mTitle.toString(), mainMenuArrayList
            }
            if (position == 1) {
                fg = new ModelBlankFragment();
                //mTitle.toString(), mainMenuArrayList
            }
            if (position == 2) {
                fg = new QuizBlankFragment();
                //mTitle.toString(), mainMenuArrayList
            }
            return fg;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    * this block is for navigation drawer
    * */

    private void prepareListData() {
        //listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        List<String> myCats = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CateActivity)));

        listDataChild.put(mainMenuArrayList.get(0), myCats); // Header, Child data
        listDataChild.put(mainMenuArrayList.get(1), myCats);
        listDataChild.put(mainMenuArrayList.get(2), myCats);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void DrawerSelectReload(int positionIn, int subTabIn) {
        Intent intent = new Intent();
        intent.setClass(this, CategoryView.class);
        Bundle extras = new Bundle();

        DatabaseController myDatabase = new DatabaseController(this);
        try{
            ArrayList<MainCategoryModel> tempCategory = myDatabase.GetAllMainCategory();
            extras.putInt("menuID", tempCategory.get(positionIn).getId());
        }catch (SQLException e){
            e.printStackTrace();
        }


        extras.putInt("activityId", positionIn);

        extras.putInt("subTabId", subTabIn);

        String[] newArray = new String[mainMenuArrayList.size()];
        extras.putStringArray("CategoryList", mainMenuArrayList.toArray(newArray));

        intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        finish();
    }
}

