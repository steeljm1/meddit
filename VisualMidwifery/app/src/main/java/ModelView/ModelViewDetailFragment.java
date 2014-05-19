package ModelView;



import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

import DBController.DatabaseController;
import Models.ModelViewModel;
import otago.Midwifery.CategoryView;
import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ModelViewDetailFragment extends Fragment implements View.OnClickListener {

    int modelID;
    int mainID;
    private ImageView myModelImage;
    ModelViewModel temp;
    private DatabaseController dataSource;
    private ProgressDialog mProgressDialog;
    private Bitmap modelImage;
    View view;
    private ImageButton imageButtonBack;
    public TextView txtView;

    static ModelViewDetailFragment modelDetails;

    public static ModelViewDetailFragment getInstance() {
        return modelDetails;
    }

    public ModelViewDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        modelDetails = this;
        view = inflater.inflate(R.layout.fragment_model_view_details,container,false);
        txtView = (TextView) view.findViewById(R.id.txtviewModel);
        myModelImage = (ImageView) view.findViewById(R.id.modelImage);
        imageButtonBack = (ImageButton) view.findViewById(R.id.imageBtnCancel);
        dataSource = new DatabaseController(getActivity());
        mainID = ((CategoryView) getActivity()).getmMainID();
        Bundle bundle = getArguments();
        modelID = bundle.getInt("modelID");

        imageButtonBack.setOnClickListener(this);

        try {
            ArrayList<ModelViewModel> allModels = dataSource.GetAllModelView(mainID);
            for(ModelViewModel m : allModels){
                if(m.getId() == modelID){
                    temp = m;
                    break;
                }
            }
            //
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtView.setText(getActivity().getTitle());

        new LoadModelImage().execute();
    }
    private class LoadModelImage extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Loading Model Image");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            modelImage = temp.getModelImage();
            //modelImage = mySQLAdapter.getImage(category, clickAngle);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myModelImage.setImageBitmap(modelImage);
            Thread show_model_image = new Thread() {
                public void run() {
                    try {
                        sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        mProgressDialog.dismiss();
                    }
                }
            };
            show_model_image.start();

        }
    }
    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out);

        fragmentTransaction.remove(getFragmentManager().findFragmentByTag("ModelDetail"));
        fragmentTransaction.commit();
    }
}
