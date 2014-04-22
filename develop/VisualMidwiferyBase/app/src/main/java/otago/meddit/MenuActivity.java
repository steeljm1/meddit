package otago.meddit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends Activity {
    /*
    * This block is for the main contains of this activity
    */
    private final CharSequence mTitle = "Virtual Midwifery";//the title of this activity
    private final CharSequence mDrawerTitle = "Main Menu";
    private static final long TIME_INTERVAL = 1500; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    private final int activityId = 0;

    private SQLiteAdapter mySQLiteAdapter;
    private ArrayList<String> mainMenuArrayList;

    private ListView mainMenuListView;
    private MyMainMenuListAdapter myMainMenuListAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //set title of this activity
        this.getActionBar().setTitle(mTitle);

        //Init sqlAdapter to get the category
        mySQLiteAdapter = new SQLiteAdapter(this);
        mainMenuArrayList = mySQLiteAdapter.fillCategory();

        //init mainMenu list view
        mainMenuListView = (ListView) findViewById(R.id.mainMenuList);
        //init a main menu list adapter
        myMainMenuListAdapter = new MyMainMenuListAdapter(this, R.id.mainMenuList, mainMenuArrayList);
        //set adapter
        mainMenuListView.setAdapter(myMainMenuListAdapter);
        //set up on item click listener for main menu
        mainMenuListView.setOnItemClickListener(mainMenuOnItemClickListener);

        /*
        * This block is mainly for generate drawer
        * */


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

/////////////////////////////
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

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(
                //        getApplicationContext(),
                //        mainMenuArrayList.get(groupPosition)
                //                + " : "
                //                + listDataChild.get(
                //                mainMenuArrayList.get(groupPosition)).get(
                //                childPosition), Toast.LENGTH_SHORT
                //)
                //        .show();
                selectItem(groupPosition, childPosition);
//################################################################################################
                return false;
            }
        });
/////////////////////////////

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

                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

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

    //Create a listener for main menu
    AdapterView.OnItemClickListener mainMenuOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //get what user clicked
            int subTab = 0;
            selectItem(position, subTab);

        }
    };

    private void selectItem(int positionIn, int subTabIn) {

        Intent intent = new Intent();
        intent.setClass(MenuActivity.this, CategoryView.class);
        Bundle extras = new Bundle();
        extras.putInt("activityId", positionIn);

        extras.putInt("subTabId", subTabIn);

        String[] newArray = new String[mainMenuArrayList.size()];
        extras.putStringArray("CategoryList", mainMenuArrayList.toArray(newArray));

        intent.putExtras(extras);
        startActivity(intent);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        //mDrawerLayout.closeDrawers();
        finish();
    }

    //An inner class to extend and customs main menu list view
    private class MyMainMenuListAdapter extends ArrayAdapter<String> {

        public MyMainMenuListAdapter(Context contextIn, int resourceIn, List<String> arrayListIn) {
            super(contextIn, resourceIn, arrayListIn);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View category_view = convertView;//will return this view

            if (category_view == null) {
                //if cannot find this view, index this view from resource
                category_view = getLayoutInflater().inflate(R.layout.main_menu_item, parent, false);
            }
            String currentCategory = mainMenuArrayList.get(position);//get position of current category system on

            TextView txtCategory = (TextView) category_view.findViewById(R.id.categoryItemTextView);
            txtCategory.setText(currentCategory);//set text for each item
            return category_view;
        }
    }
}
