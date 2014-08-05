package ModelView;



import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

import DBController.DatabaseController;
import Fragments.BaseFragment;
import Helper.PinchTouchImageView;
import Helper.PinchTouchModelDetailsImageView;
import Models.ModelViewModel;
import otago.Midwifery.CategoryView;
import otago.Midwifery.MessageToast;
import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ModelViewDetailFragment extends BaseFragment {
    DatabaseController dataSource;
    ArrayList<ModelViewModel> angles;
    int mainID;
    public static int modelID;
    PinchTouchModelDetailsImageView modelImageDisplay;
    public static TextView txtView;

    public ModelViewDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        modelID = bundle.getInt("modelID");

        return inflater.inflate(R.layout.fragment_model_view_details, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        txtView = (TextView) mActivity.findViewById(R.id.txtviewModel);

        dataSource = new DatabaseController(getActivity());
        mainID = ((CategoryView) getActivity()).getmMainID();

        try{
            angles = dataSource.GetAllModelView(mainID);

            for(ModelViewModel m : angles){
                if(m.getId() == modelID){
                    modelImageDisplay = (PinchTouchModelDetailsImageView) mActivity.findViewById(R.id.modelImage);
                    modelImageDisplay.setImageBitmap(m.getModelImage());
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
