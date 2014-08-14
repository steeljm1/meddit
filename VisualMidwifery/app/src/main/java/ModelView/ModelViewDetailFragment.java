package ModelView;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import DBController.DatabaseController;
import Fragments.BaseFragment;
import Helper.PinchTouchImageView;
import Helper.PinchTouchModelDetailsImageView;
import Models.ModelViewModel;
import otago.Midwifery.CategoryView;
import otago.Midwifery.MenuActivity;
import otago.Midwifery.MessageToast;
import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ModelViewDetailFragment extends BaseFragment{
    DatabaseController dataSource;
    ArrayList<ModelViewModel> angles;
    ArrayList<ModelViewModel> matchAngles;
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

        matchAngles = new ArrayList<ModelViewModel>();
        modelImageDisplay = (PinchTouchModelDetailsImageView) mActivity.findViewById(R.id.modelImage);
        try{
            angles = dataSource.GetAllModelView(mainID);
            String angleName = "";
            for(final ModelViewModel m : angles){
                if(m.getId() == modelID){
                    //put in array list
                    angleName = m.getAngle();
                    //matchAngles.add(m);
                    //modelImageDisplay.setImageBitmap(m.getModelImage());
                    //new MyAsyncTask(getActivity(),m,modelImageDisplay).execute();
                }
            }
            for(final ModelViewModel m : angles){
                if(m.getAngle().equals(angleName)){
                    //put in array list
                    matchAngles.add(m);
                    //modelImageDisplay.setImageBitmap(m.getModelImage());
                    //new MyAsyncTask(getActivity(),m,modelImageDisplay).execute();
                }
            }
            //MessageToast.message(mActivity,""+angleName);
            if(matchAngles.size() == 1)
            {
                LinearLayout l =(LinearLayout) mActivity.findViewById(R.id.seekBarVisiable);
                l.setVisibility(LinearLayout.GONE);
                new MyAsyncTask(getActivity(),matchAngles.get(0),modelImageDisplay).execute();
            }
            else
            {
                SeekBar seekBar = (SeekBar) mActivity.findViewById(R.id.modelViewDetailSeekBar);
                final TextView seekBarValue = (TextView) mActivity.findViewById(R.id.seekbarValue);
                seekBarValue.setText(""+0);
                seekBar.setMax(matchAngles.size()-1);
                seekBar.setClickable(true);
                new MyAsyncTask(getActivity(),matchAngles.get(0),modelImageDisplay).execute();
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekBarValue.setText(String.valueOf(progress));
                        new MyAsyncTask(getActivity(),matchAngles.get(progress),modelImageDisplay).execute();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    class MyAsyncTask extends AsyncTask<String, String, String>
    {
        ModelViewModel m;
        ImageView imageView;
        Activity mContext;
        private ProgressDialog dialog;
        public  MyAsyncTask(Activity context, ModelViewModel mIn, ImageView imageViewIn) {
            mContext = context;
            m = mIn;
            imageView = imageViewIn;
            dialog = new ProgressDialog(mContext);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            if (dialog.isShowing()) {
                imageView.setImageBitmap(m.getModelImage());
                dialog.dismiss();
            }
        }
    }
}
