package ContentView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
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
 * Created by liub3 on 17/05/2014.
 */
public class ContentCategoryListFragment extends BaseFragmentList
{
    DatabaseController dataSource;
    ArrayList<ContentCategoryModel> categories;
    int mainID;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mainID = mActivity.getmMainID();
        dataSource = new DatabaseController(getActivity());
        this.getView().setBackgroundColor(getResources().getColor(R.color.imageFragment));
        try {
            categories = dataSource.GetAllContentCategory(mainID);

            ArrayAdapter<ContentCategoryModel> adapter = new ArrayAdapter<ContentCategoryModel>(getActivity(), android.R.layout.simple_list_item_1, categories);
            setListAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ContentCategoryModel clickedCategory = categories.get(position);
        int categoryID = clickedCategory.getId();

        Fragment GridViewRoot = new ContentGridViewFragment();

        Bundle data = new Bundle();
        data.putInt("categoryID", categoryID);
        data.putInt("mainID", mainID);

        GridViewRoot.setArguments(data);

        mActivity.pushFragments(TabConstants.TAB_CONTENT,GridViewRoot,true,true);
    }
}
