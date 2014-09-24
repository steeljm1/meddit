package Update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
<<<<<<< HEAD
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
=======
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
>>>>>>> origin/bo
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
import java.util.Calendar;
import java.util.Date;
=======
>>>>>>> origin/bo


/**
 * Created by glenn_000 on 2/08/2014.
 * Class is to facilitate the updating of information within the app, CRUD operations
 */

public class Updater {
    public Context context;
    private UpdateTask updateTask;

    public Updater(Context context) {
        this.context = context;
        updateTask = new UpdateTask(context);
    }

    public void cancelUpdate() {
        updateTask.cancel(true);
    }

    public void runUpdate() {
        //Checks for valid internet connection
        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String[] urls = { "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ContentCategory.json",
                    "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ModelView.json",
                    "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ContentField.json",
                    "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ModelColour.json"
            };

            //New async task to make the http request
            updateTask = new UpdateTask(context);
            updateTask.execute(urls);
        } else {
            Toast.makeText(context, "Failed to connect to the network", Toast.LENGTH_LONG).show();
        }
<<<<<<< HEAD


=======
>>>>>>> origin/bo
    }

    class UpdateTask extends AsyncTask<String, Integer, Void> {
        private ProgressDialog dialog;
        ModelUpdater[] modelUpdaters = {
                                        new ContentCategoryUpdater(context),
                                        new ModelViewUpdater(context),
                                        new ContentFieldUpdater(context),
                                        new ModelColourUpdater(context)
                                    };

        public UpdateTask(Context context) {
            dialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMessage("Downloading Updates");
            dialog.setIndeterminate(false);
            dialog.setMax(4);
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                for(int i = 0; i < modelUpdaters.length; i++) {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet get = new HttpGet(params[i]);
                    HttpResponse response = client.execute(get);

                    int StatusCode = response.getStatusLine().getStatusCode();
                    if (StatusCode == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream in = entity.getContent();
                        modelUpdaters[i].readInputStream(in);
<<<<<<< HEAD
                        publishProgress(i + 1);
=======
                        publishProgress(i);
>>>>>>> origin/bo
                        Thread.sleep(200);
                    }
                    else {
                        Log.e("Building Input Stream", "Failed to download file");
                    }

                    if(isCancelled()) {
                        dialog.dismiss();
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
<<<<<<< HEAD
            // Open shared preferences storage
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();

            // Create new timestamp
            Date date = new Date();
            String timestamp = String.valueOf(date.getTime());

            // Save date of last updated
            editor.putString("LastUpdated", timestamp);
            editor.apply();

=======
>>>>>>> origin/bo
            Log.d("Update", "Update Complete");
        }
    }
}


