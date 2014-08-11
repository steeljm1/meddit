package Update;

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

import Models.ModelColorModel;


/**
 * Created by glenn_000 on 2/08/2014.
 * Class is to facilitate the updating of information within the app, CRUD operations
 */

public class Updater {
    Context context;
    public Updater(Context context) {
        this.context = context;
        //Checks for valid internet connection
        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String[] urls = { "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ContentCategory.json",
                              "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ModelView.json",
                              "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ModelColour.json",
                              "http://meddit.ict.op.ac.nz/ss/index.php/api/v1/ContentField.json"
                            };

            //New async task to make the http request
            new MyTask().execute(urls);
        } else {
            Toast.makeText(context, "Failed to connect to the network", Toast.LENGTH_LONG).show();
        }
    }

    class MyTask extends AsyncTask<String, Integer, Void> {
        ContentCategoryUpdater contentCategoryUpdater = new ContentCategoryUpdater(context);
        ModelViewUpdater modelViewUpdater = new ModelViewUpdater(context);
        ContentFieldUpdater contentFieldUpdater = new ContentFieldUpdater(context);
        ModelColourUpdater modelColourUpdater = new ModelColourUpdater(context);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGetContentCategories = new HttpGet(params[0]);
            HttpGet httpGetModelViews = new HttpGet(params[1]);
            HttpGet httpGetModelColours = new HttpGet(params[2]);
            HttpGet httpGetContentFields = new HttpGet(params[3]);

            try {
                HttpResponse response1 = client.execute(httpGetContentCategories);
                HttpResponse response2 = client.execute(httpGetModelViews);
                HttpResponse response3 = client.execute(httpGetModelColours);
                HttpResponse response4 = client.execute(httpGetContentFields);

                StatusLine statusLine = response1.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {

                    HttpEntity entity = response1.getEntity();
                    InputStream in1 =  entity.getContent();
                    contentCategoryUpdater.readContentCategoryJsonStream(in1);

                    HttpEntity entity2 = response2.getEntity();
                    InputStream in2 =  entity2.getContent();
                    modelViewUpdater.readModelViewJsonStream(in2);

                    HttpEntity entity3 = response3.getEntity();
                    InputStream in3 =  entity3.getContent();
                    modelColourUpdater.readModelColourJsonStream(in3);

                    HttpEntity entity4 = response4.getEntity();
                    InputStream in4 =  entity4.getContent();
                    contentFieldUpdater.readContentFieldJsonStream(in4);

                } else {
                    Log.e("Building Input Stream", "Failed to download file");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }
}


