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
 * this fragment is for displaying the model and let user navigate models
 */
public class ModelAngleCategoryListFragment extends BaseFragmentList {
    //if database can not index anything using passed in id
    //then tell user to update app
    DatabaseController dataSource;
    ArrayList<ModelViewModel> angles;//angles array to store all angles associated with passed in id
    int mainID;
    static String nothing = "Nothing to display, Please update...";


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainID = ((CategoryView) getActivity()).getmMainID();

        dataSource = new DatabaseController(getActivity());
        angles = new ArrayList<ModelViewModel>();
        //init database and angle array
        this.getView().setBackgroundColor(getResources().getColor(R.color.imageFragment));
        //set up background color
        try{
            for(ModelViewModel m : dataSource.GetAllModelView(mainID))
            {
                if(m.getStep() == 0)
                {
                    angles.add(m);
                }
            }//put all models into array and only step 0
            if(angles.size() == 0)
            {
                ModelViewModel fakeM = new ModelViewModel();
                fakeM.setId(0);
                fakeM.setMainId(0);
                fakeM.setLastEdited("0");
                fakeM.setStep(0);
                fakeM.setAngle(nothing);
                angles.add(fakeM);
            }//if there are nothing can be found from database then fake an item and display it
            ArrayAdapter<ModelViewModel> adapter = new ArrayAdapter<ModelViewModel>(getActivity(), android.R.layout.simple_list_item_1, angles);
            setListAdapter(adapter);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //when item is clicked by users
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ModelViewModel clickedCategory = angles.get(position);
        int modelID = clickedCategory.getId();
        if(modelID != 0) {
            Fragment modelViewDetail = new ModelViewDetailFragment();

            Bundle data = new Bundle();
            data.putInt("modelID", modelID);
            //pack up model id and pass to next fragment
            modelViewDetail.setArguments(data);
            mActivity.pushFragments(TabConstants.TAB_MODEL, modelViewDetail, true, true);
        }
    }
}
