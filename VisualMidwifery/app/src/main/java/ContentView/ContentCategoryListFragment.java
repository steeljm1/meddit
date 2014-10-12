package ContentView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import DBController.DatabaseController;
import Fragments.BaseFragmentList;
import Models.ContentCategoryModel;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;

/**
 * Created on 17/05/2014.
 * This class extends list fragment
 * it will display the layout with a Itemlist
 */
public class ContentCategoryListFragment extends BaseFragmentList
{
    DatabaseController dataSource; //The controller of database with will get data from database
    ArrayList<ContentCategoryModel> categories;//the collections of all categories
    int mainID;//local mainID will be fill by bundle from last activity

    //default override method will be called when view created
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mainID = mActivity.getmMainID();//fill the mainID
        dataSource = new DatabaseController(getActivity());//init database controller
        getView().setBackgroundColor(getResources().getColor(R.color.imageFragment));//programmly change the background color
        try {
            categories = dataSource.GetAllContentCategory(mainID);//try to get all of categories, using mainID to index the associated categories

            ArrayAdapter<ContentCategoryModel> adapter = new ArrayAdapter<ContentCategoryModel>(getActivity(), android.R.layout.simple_list_item_1, categories);
            //instant of array adapter which set up the properties and layout of the category list
            setListAdapter(adapter);//setup list adapter
        } catch (SQLException e) {
            e.printStackTrace();//output the error
        }
    }

    //Item on click method tragger when an item in the list is clicked
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ContentCategoryModel clickedCategory = categories.get(position);
        int categoryID = clickedCategory.getId();//get the clicked item id

        Fragment GridViewRoot = new ContentGridViewFragment();//try to create a new fragment programmly

        Bundle data = new Bundle();//pack some of variables and pass to the new fragment
        data.putInt("categoryID", categoryID);
        data.putInt("mainID", mainID);
        //put categoryID and the mainID

        GridViewRoot.setArguments(data);//pass the bundle from this fragment

        mActivity.pushFragments(TabConstants.TAB_CONTENT,GridViewRoot,true,true);//Contained activity will push current fragment into stack when user click back
        //button the peek item will be popped
    }
}
