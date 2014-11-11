package ModelView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.sql.SQLException;
import java.util.ArrayList;
import DBController.DatabaseController;
import Fragments.BaseFragment;
import Helper.PinchTouchModelDetailsImageView;
import Models.ModelViewModel;
import otago.Arb.CategoryView;
import otago.Arb.MessageToast;
import otago.Arb.R;

/**
 * A simple {@link Fragment} subclass.
 * this fragment is trying to display models chosen in last fragment
 */
public class
        ModelViewDetailFragment extends BaseFragment{
    DatabaseController dataSource;
    ArrayList<ModelViewModel> angles;
    ArrayList<ModelViewModel> matchAngles;
    int mainID;
    public static int modelID;
    PinchTouchModelDetailsImageView modelImageDisplay;
    public static TextView txtView;
    public static boolean ON;

    public ModelViewDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        modelID = bundle.getInt("modelID");
       //unpack variable passed from last fragment

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
                    //get all angle names in array list
                    angleName = m.getAngle();
                }
            }
            for(final ModelViewModel m : angles){
                if(m.getAngle().equals(angleName)){
                    //get matched angle in array list
                    matchAngles.add(m);
                }
            }
            //check how many matched angles in array
            if(matchAngles.size() == 1)
            {
                ON = false;
                LinearLayout l =(LinearLayout) mActivity.findViewById(R.id.seekBarVisiable);
                l.setVisibility(LinearLayout.GONE);

                LinearLayout f = (LinearLayout) mActivity.findViewById(R.id.imageFrame);
                f.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 8f));

                new MyAsyncTask(getActivity(),matchAngles.get(0),modelImageDisplay).execute();
            }
            //if more than one means layout changes
            else
            {
                //sort matchAngles arraylist with bubble because there not large population in array
                ON = true;
                LinearLayout l =(LinearLayout) mActivity.findViewById(R.id.infoLayout);
                l.setVisibility(LinearLayout.GONE);

                LinearLayout f = (LinearLayout) mActivity.findViewById(R.id.imageFrame);
                f.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 7f));

                for(int i = 1; i < matchAngles.size(); i++)
                {
                    for(int j = 0; j <  matchAngles.size() - i; j++)
                    {
                        if(matchAngles.get(j).getStep() > matchAngles.get(j + 1).getStep())
                        {
                            ModelViewModel temp = matchAngles.get(j);
                            matchAngles.set(j,matchAngles.get(j + 1)) ;
                            matchAngles.set(j+1,temp);//swap
                        }
                    }
                }
                //bubble sort end

                //set up seek bar
                final SeekBar seekBar = (SeekBar) mActivity.findViewById(R.id.modelViewDetailSeekBar);
                ImageButton imgBtnLA = (ImageButton) mActivity.findViewById(R.id.imgBtnLa);
                ImageButton imgBtnRA = (ImageButton) mActivity.findViewById(R.id.imgBtnRa);
                seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbarlayout));
                //textview to display status of seek bar
                final TextView seekBarValue = (TextView) mActivity.findViewById(R.id.seekbarValue);
                seekBarValue.setText(""+0);

                seekBar.setMax(matchAngles.size()-1);
                seekBar.setClickable(true);
                new MyAsyncTask(getActivity(),matchAngles.get(0),modelImageDisplay).execute();
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekBarValue.setText(String.valueOf(progress));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        new MyAsyncTask(getActivity(),matchAngles.get(seekBar.getProgress()),modelImageDisplay).execute();

                    }
                });
                //init imageButtons
                imgBtnLA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int cruProgress = seekBar.getProgress();
                        //0 based
                        if(cruProgress!=0)
                        {
                            seekBar.setMax(matchAngles.size());
                            seekBar.setProgress(cruProgress--);
                            seekBar.setProgress(0);
                            seekBar.setMax(matchAngles.size()-1);
                            seekBar.setProgress(cruProgress--);
                            new MyAsyncTask(mActivity,matchAngles.get(seekBar.getProgress()),modelImageDisplay).execute();
                        }
                        else
                        {
                            MessageToast.message(mActivity,"This is first step!");
                        }
                    }
                });
                imgBtnRA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int cruProgress = seekBar.getProgress();
                        //0 based
                        if(cruProgress != matchAngles.size()-1)
                        {
                            seekBar.setMax(matchAngles.size());
                            seekBar.setProgress(cruProgress++);
                            seekBar.setProgress(0);
                            seekBar.setMax(matchAngles.size()-1);
                            seekBar.setProgress(cruProgress++);
                            new MyAsyncTask(mActivity,matchAngles.get(seekBar.getProgress()),modelImageDisplay).execute();
                        }
                        else
                        {
                            MessageToast.message(mActivity,"This is last step!");
                        }
                    }
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    //when model open because the model image is too large so leave 500ms to let image fully load
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
            dialog.setCanceledOnTouchOutside(false);
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
