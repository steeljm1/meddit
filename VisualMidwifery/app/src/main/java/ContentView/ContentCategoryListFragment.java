package ContentView;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

import DBController.DatabaseController;
import Models.ContentCategoryModel;
import Models.MainCategoryModel;
import otago.Midwifery.CategoryView;
import otago.Midwifery.MessageToast;

/**
 * Created by liub3 on 17/05/2014.
 */
public class ContentCategoryListFragment extends ListFragment
{
    DatabaseController dataSource;
    ArrayList<ContentCategoryModel> categories;
    int mainID;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        int mainID = ((CategoryView) getActivity()).getmMainID();

        dataSource = new DatabaseController(getActivity());

        try {
            categories =dataSource.GetAllContentCategory();
            MessageToast.message(getActivity(), ""+mainID);

            ArrayAdapter<ContentCategoryModel> adapter = new ArrayAdapter<ContentCategoryModel>(getActivity(), android.R.layout.simple_list_item_1, categories);
            setListAdapter(adapter);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }
}
