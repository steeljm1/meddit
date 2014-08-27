package otago.Midwifery;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import DBController.DatabaseController;
import Models.MainCategoryModel;


public class MenuActivity extends ActionBarActivity {

    private final CharSequence mTitle = "Visual Midwifery";//the title of this activity
    private final CharSequence mDrawerTitle = "Main Menu";
    private static final long TIME_INTERVAL = 1500; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    private ImageButton settingsButton;

    private DatabaseController myDatabase;
    private ArrayList<String> mainMenuArrayList;
    private ArrayList<String> mainMenuArrayListDrawer;

    private ListView mainMenuListView;
    private MyMainMenuListAdapter myMainMenuListAdapter;
    private ArrayList<MainCategoryModel> tempCategory;

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
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //set title of this activity
        this.getActionBar().setTitle(mTitle);

        //Init sqlAdapter to get the category
        myDatabase = new DatabaseController(this);
        mainMenuArrayList = new ArrayList<String>();
        mainMenuArrayListDrawer = new ArrayList<String>();
        try {
            tempCategory = myDatabase.GetAllMainCategory();
            for (MainCategoryModel mC : tempCategory) {
                mainMenuArrayList.add(mC.getTitle());
                mainMenuArrayListDrawer.add(mC.getTitle());
            }
            //add an item for exam review
            mainMenuArrayList.add("Exam Revision");
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        listAdapter = new MyDrawerAdapter(this, mainMenuArrayListDrawer, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
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
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                selectItem(groupPosition, childPosition);
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
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);

                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                ImageButton imgBtnExit = (ImageButton) findViewById(R.id.imgBtnExit);

                    View.OnClickListener imgBtnExitOnClick = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                };
                imgBtnExit.setOnClickListener(imgBtnExitOnClick);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        settingsButton = (ImageButton)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), otago.Midwifery.SettingsActivity.class);
                startActivity(intent);
            }
        });
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
            if(position != 3) {
                selectItem(position, subTab);
            }else{
                //selected exam revision item
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, ExamRevision.class);
                startActivity(intent);

                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        }
    };

    //start another activity according to what category has been selected
    //pass in MainCategory user selected and index of sub category
    private void selectItem(int positionIn, int subTabIn) {

        Intent intent = new Intent();
        intent.setClass(MenuActivity.this, CategoryView.class);
        Bundle extras = new Bundle();

        extras.putInt("menuID", tempCategory.get(positionIn).getId());
        extras.putInt("activityId", positionIn);

        extras.putInt("subTabId", subTabIn);

        String[] newArray = new String[mainMenuArrayListDrawer.size()];
        extras.putStringArray("CategoryList", mainMenuArrayListDrawer.toArray(newArray));

        intent.putExtras(extras);
        startActivity(intent);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        mDrawerLayout.closeDrawers();

    }


    @Override
    public void onBackPressed() {
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
