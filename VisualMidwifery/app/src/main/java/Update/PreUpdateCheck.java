package Update;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import otago.Arb.R;

/**
 * Created by glenn_000 on 18/09/2014.
 */
public class PreUpdateCheck {

    Context context;
    Updater updater;

    public PreUpdateCheck(Context context) {
        this.context = context;
        updater = new Updater(context);
    }

    public void runUpdate() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Resources res = context.getResources();
            String url = res.getString(R.string.update_pre_check);

            new CheckForUpdates(updater, context).execute(url);
        } else {
            Toast.makeText(context, "Failed to connect to the network", Toast.LENGTH_LONG).show();
        }
    }
}



class CheckForUpdates extends AsyncTask<String, Void, Void> {

    String lastCMSUpdate;
    boolean updatesAvailable = false;

    Context context;
    SharedPreferences sharedPreferences;

    Updater updater;

    CheckForUpdates(Updater updater, Context context) {
        this.updater = updater;
        this.context = context;

        lastCMSUpdate = "";
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Override
    protected Void doInBackground(String... strings) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(strings[0]);
            HttpResponse response = client.execute(get);

            int StatusCode = response.getStatusLine().getStatusCode();
            if (StatusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                JSONObject object = new JSONObject(inputToString(in));

                int itemsCount = object.getInt("totalSize");
                JSONArray items = object.getJSONArray("items");

                JSONObject lastUpdate = items.getJSONObject(itemsCount - 1);

                lastCMSUpdate = lastUpdate.getString("LastUpdated");
            } else {
                Log.e("Building Input Stream", "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String inputToString(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        String result = "";

        while ((line = bufferedReader.readLine()) != null)
            result += line;

        in.close();

        return result;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(sharedPreferences.contains("LastUpdated")) {
            String lastUpdated = sharedPreferences.getString("LastUpdated", "");

            Long a = (Long.valueOf(lastUpdated) / 1000);
            Long b = Long.valueOf(lastCMSUpdate);

            if(a < b) {
                updater.runUpdate();
            }
            else {
                Toast.makeText(context, "No new updates available", Toast.LENGTH_LONG).show();
            }
        } else {
            updater.runUpdate();
        }
    }
}