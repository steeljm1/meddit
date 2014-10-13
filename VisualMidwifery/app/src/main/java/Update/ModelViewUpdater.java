package Update;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Models.ModelViewModel;

/**
 * Created by glenn_000 on 11/08/2014.
 */
public class ModelViewUpdater extends ModelUpdater {

    public ModelViewUpdater(Context context) {
        super(context);
    }

    private void onPost(ArrayList<ModelViewModel> models) {
        controller.updateModelView(models);
    }

    // READING JSON STREAM CONTENT CATEGORY
    @Override
    public void readInputStream(InputStream in) throws IOException {
        try {
            JSONObject json = new JSONObject(convertInputToJson(in));
            JSONArray jsonArray = json.getJSONArray("items");
            onPost(readModelView(jsonArray));
            in.close();
        } catch (JSONException e) {
            Log.d("JSON PARSE ERROR", "Reading from input stream failed");
        }
    } // End readJsonStream


    /* PRE:  */
    private ArrayList<ModelViewModel> readModelView(JSONArray ccJson) throws IOException {

        ArrayList<ModelViewModel> list = new ArrayList<ModelViewModel>();
        try { // Constructs object list to be compared against the database
            for (int i = 0; i < ccJson.length(); i++) {
                JSONObject row = ccJson.getJSONObject(i);
                ModelViewModel mvm = constructModel(row);
                list.add(mvm);
            }
        } catch (JSONException e) {

        }
        return list;
    } // END readContentCategory
    /* POST:  */

    private ModelViewModel constructModel(JSONObject item) {
        try {
            ModelViewModel mvm = new ModelViewModel();
            JSONObject mainCategory = item.getJSONObject("MainCategories");

            mvm.setLastEdited(item.getString("LastUpdated"));
            mvm.setAngle(item.getString("Title"));
            mvm.setStep(item.getInt("Step"));
            mvm.setModelImage(downloadImage(item.getString("ImageUrl")));
            mvm.setMainId(mainCategory.getInt("id"));

            return mvm;
        } catch (JSONException e) {
            Log.d("JSON", "JSON parsing error :" + e.toString());
        }
        return null;
    }

    private Bitmap downloadImage(String imageUrl) {
        try{
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream in = connection.getInputStream();

            return BitmapFactory.decodeStream(in);
        }
        catch (IOException e){
            Log.e("IMAGE", "Downloading new image failed");
            return null;
        }
    }

}
