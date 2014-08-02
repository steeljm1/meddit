package ModelView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import DBController.DatabaseController;
import Fragments.BaseFragmentList;
import Models.ModelViewModel;
import otago.Midwifery.CategoryView;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;

/**
 * Created by liub3 on 19/05/2014.
 */
public class ModelAngleCategoryListFragment extends BaseFragmentList {
    DatabaseController dataSource;
    ArrayList<ModelViewModel> angles;
    int mainID;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainID = ((CategoryView) getActivity()).getmMainID();

        dataSource = new DatabaseController(getActivity());
        this.getView().setBackgroundColor(getResources().getColor(R.color.imageFragment));
        try{
            angles = dataSource.GetAllModelView(mainID);
            ArrayAdapter<ModelViewModel> adapter = new ArrayAdapter<ModelViewModel>(getActivity(),android.R.layout.simple_list_item_1,angles);
            setListAdapter(adapter);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ModelViewModel clickedCategory = angles.get(position);
        int modelID = clickedCategory.getId();

        Fragment modelViewDetail = new ModelViewDetailFragment();

        Bundle data = new Bundle();
        data.putInt("modelID",modelID);

        modelViewDetail.setArguments(data);
        mActivity.pushFragments(TabConstants.TAB_MODEL,modelViewDetail,true,true);
    }
}
