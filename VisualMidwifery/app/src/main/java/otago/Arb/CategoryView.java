package otago.Arb;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import ContentView.ContentCategoryListFragment;
import DBController.DatabaseController;
import Fragments.BaseFragment;
import Fragments.BaseFragmentList;
import ModelView.ModelAngleCategoryListFragment;
import Models.MainCategoryModel;
import QuizView.QuizLogIn;
import otago.Arb.R;


public class CategoryView extends FragmentActivity{
    ArrayList<String> mainMenuArrayList;

    int activityId;
    int subTabId;
    int mainID;
    /*
* This block is for the main contains of this activity
*/
    private CharSequence mTitle;//the title of this activity
    private CharSequence mDrawerTitle = "Main Menu";

    private TabHost mTabHost;//my tab host
    private HashMap<String, Stack<Fragment>> mStacks;
    private String mCurrentTab;//save current tabs identifier in this
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        //////////////////////////////////////////////////////////////////
        /*
        Navigation stacks for each tab gets created
        tab identifier is used as key to get respective stack for each tab
        * */
        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(TabConstants.TAB_CONTENT,new Stack<Fragment>());
        mStacks.put(TabConstants.TAB_MODEL,new Stack<Fragment>());
        mStacks.put(TabConstants.TAB_QUIZ,new Stack<Fragment>());

        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setOnTabChangedListener(OnTabChangeListener);
        mTabHost.setup();
        initializeTabs();
         //////////////////////////////////////////////////////////////////
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
            mTabHost.setCurrentTab(subTabId);
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

                //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                if (groupPosition != activityId) {
                    DrawerSelectReload(groupPosition, childPosition);
                } else {
                    //viewPager.setCurrentItem(childPosition, true);
                    //actionBar.setSelectedNavigationItem(childPosition);
                    mTabHost.setCurrentTab(childPosition);
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

    }

    public int getmMainID() {
        return mainID;
    }

    private View createTabView(final int id,final String tab_textIn) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon,null);
        ImageView imageVIew = (ImageView) view.findViewById(R.id.tab_icon);
        imageVIew.setImageDrawable(getResources().getDrawable(id));

        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(tab_textIn);
        return view;
    }

    public void initializeTabs(){
        //setup your tab icons and content views
        TabHost.TabSpec spec    =   mTabHost.newTabSpec(TabConstants.TAB_CONTENT);
        mTabHost.setCurrentTab(-4);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_a_state_btn,"Material"));
        mTabHost.addTab(spec);


        spec                    =   mTabHost.newTabSpec(TabConstants.TAB_MODEL);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_b_state_btn,"Model"));
        mTabHost.addTab(spec);

        spec                    =   mTabHost.newTabSpec(TabConstants.TAB_QUIZ);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_c_state_btn,"Quiz"));
        mTabHost.addTab(spec);
    }

    TabHost.OnTabChangeListener OnTabChangeListener = new TabHost.OnTabChangeListener(){
        public void onTabChanged(String tabId){
            mCurrentTab = tabId;
            if(mStacks.get(tabId).size() == 0){
                //first time this tab is selected so add first fragment of that tab
                //donot need animation so that argument is false
                //adding a new fragment which is not present in stack so add to stack is true
                if(tabId.equals(TabConstants.TAB_CONTENT)){
                    pushFragments(tabId,new ContentCategoryListFragment(),false,true);
                }
                else if(tabId.equals(TabConstants.TAB_MODEL)){
                    pushFragments(tabId,new ModelAngleCategoryListFragment(),false,true);
                }
                else if(tabId.equals(TabConstants.TAB_QUIZ)){
                    pushFragments(tabId,new QuizLogIn(),false,true);
                }
            }else{
                //switching tabs, and target tab is already has at leatest one fragment
                //no need of animation, no need of stack pushing, just show the target fragment

                //the bug in here is for image pager when changing tab back the image cannot be loaded
                if(tabId == TabConstants.TAB_CONTENT && mStacks.get(tabId).size() == 3){
                    popFragments();
                }
                ///
                pushFragments(tabId, mStacks.get(tabId).lastElement(), false, false);

            }
        }
    };

    //switch tab programmatically fron inside any of the fragment
    public void setCurrentTab(int val){
        mTabHost.setCurrentTab(val);
    }
    /*
     *      To add fragment to a tab.
     *  tag             ->  Tab identifier
     *  fragment        ->  Fragment to show, in tab identified by tag
     *  shouldAnimate   ->  should animate transaction. false when we switch tabs, or adding first fragment to a tab
     *                      true when when we are pushing more fragment into navigation stack.
     *  shouldAdd       ->  Should add to fragment navigation stack (mStacks.get(tag)). false when we are switching tabs (except for the first time)
     *                      true in all other cases.
     */
    public void pushFragments(String tag, Fragment fragment,boolean shouldAnimate, boolean shouldAdd){
        if(shouldAdd)
            mStacks.get(tag).push(fragment);
        FragmentManager   manager         =   getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft            =   manager.beginTransaction();
        if(shouldAnimate)
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.realtabcontent, fragment);
        ft.commit();
    }


    public void popFragments(){
      /*
       *    Select the second last fragment in current tab's stack..
       *    which will be shown after the fragment transaction given below
       */
        Fragment fragment             =   mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 2);

      /*pop current fragment from stack.. */
        mStacks.get(mCurrentTab).pop();

      /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        FragmentManager   manager         =   getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft            =   manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.realtabcontent, fragment);
        ft.commit();
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

        for(int i = 0; i < mainMenuArrayList.size(); i++) {
            listDataChild.put(mainMenuArrayList.get(i), myCats); // Header, Child data
        }
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
        boolean fragmentBack = true;
        try{
            fragmentBack = ((BaseFragment)mStacks.get(mCurrentTab).lastElement()).onBackPressed();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            fragmentBack = ((BaseFragmentList)mStacks.get(mCurrentTab).lastElement()).onBackPressed();
        }catch (Exception e){
            e.printStackTrace();
        }
        //((BaseFragment)mStacks.get(mCurrentTab).lastElement()).onBackPressed() == false || ((BaseFragmentList)mStacks.get(mCurrentTab).lastElement()).onBackPressed() == false
        if(!fragmentBack){
       		/*
       		 * top fragment in current tab doesn't handles back press, we can do our thing, which is
       		 *
       		 * if current tab has only one fragment in stack, ie first fragment is showing for this tab.
       		 *        finish the activity
       		 * else
       		 *        pop to previous fragment in stack for the same tab
       		 *
       		 */
            if(mStacks.get(mCurrentTab).size() == 1){
                super.onBackPressed();  // or call finish..
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }else{
                popFragments();
            }
        }else{
            //do nothing.. fragment already handled back button press.
        }
    }
    /*
     *   Imagine if you wanted to get an image selected using ImagePicker intent to the fragment. Ofcourse I could have created a public function
     *  in that fragment, and called it from the activity. But couldn't resist myself.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mStacks.get(mCurrentTab).size() == 0){
            return;
        }

        /*Now current fragment on screen gets onActivityResult callback..*/
        mStacks.get(mCurrentTab).lastElement().onActivityResult(requestCode, resultCode, data);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HOME)
        {
            MessageToast.message(this,"Hello world");
        }
        return super.onKeyDown(keyCode, event);
    }
}

