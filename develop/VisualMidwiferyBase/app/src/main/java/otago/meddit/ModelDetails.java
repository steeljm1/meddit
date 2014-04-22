package otago.meddit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ImageView;


public class ModelDetails extends ActionBarActivity {

    private ImageView myModelImage;
    private SQLiteAdapter mySQLAdapter;
    private String[] extra;
    private ProgressDialog mProgressDialog;
    private Bitmap modelImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentIn = getIntent();
        Bundle b = intentIn.getExtras();
        if (b != null) {
            extra = b.getStringArray("INFO");
            setTitle(extra[0] + " > " + extra[1]);
        }

        mySQLAdapter = new SQLiteAdapter(this);
        myModelImage = (ImageView) findViewById(R.id.modelimage);
        new LoadModelImage().execute();

    }

    private class LoadModelImage extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ModelDetails.this);
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
            modelImage = mySQLAdapter.getImage(extra[0], extra[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myModelImage.setImageBitmap(modelImage);
            Thread splash_screen = new Thread() {
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
            splash_screen.start();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
