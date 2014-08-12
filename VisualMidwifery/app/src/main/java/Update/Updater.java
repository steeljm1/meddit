package Update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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


/**
 * Created by glenn_000 on 2/08/2014.
 * Class is to facilitate the updating of information within the app, CRUD operations
 */

public class Updater {
    public Context context;

    public Updater(Context context) {
        this.context = context;
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
            new UpdateTask(context).execute(urls);
        } else {
            Toast.makeText(context, "Failed to connect to the network", Toast.LENGTH_LONG).show();
        }
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
                        publishProgress(i);
                        Thread.sleep(200);
                    }
                    else {
                        Log.e("Building Input Stream", "Failed to download file");
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
            Log.d("Update", "Update Complete");
        }
    }
}


