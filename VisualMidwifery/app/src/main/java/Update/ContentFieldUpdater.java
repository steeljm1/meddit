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

import Models.ContentFieldModel;

/**
 * Created by glenn_000 on 11/08/2014.
 */
public class ContentFieldUpdater  extends ModelUpdater {

    public ContentFieldUpdater(Context context){
        super(context);
    }

    private void onPost(ArrayList<ContentFieldModel> models) {
        controller.updateContentField(models);
    }

    // READING JSON STREAM CONTENT CATEGORY
    @Override
    public void readInputStream(InputStream in) throws IOException {
        try {
            JSONObject json = new JSONObject(convertInputToJson(in));
            JSONArray jsonArray = json.getJSONArray("items");
            onPost(readContentField(jsonArray));
            in.close();
        } catch (JSONException e) {
            Log.d("JSON PARSE ERROR", "Reading from input stream failed");
        }
    } // End readJsonStream


    /* PRE:  */
    private ArrayList<ContentFieldModel> readContentField(JSONArray ccJson) throws IOException {

        ArrayList<ContentFieldModel> list = new ArrayList<ContentFieldModel>();
        try { // Constructs object list to be compared against the database
            for (int i = 0; i < ccJson.length(); i++) {
                JSONObject row = ccJson.getJSONObject(i);
                ContentFieldModel cfm = constructModel(row);
                list.add(cfm);
            }
        } catch (JSONException e) {

        }
        return list;
    } // END readContentCategory
    /* POST:  */

    private ContentFieldModel constructModel(JSONObject item) {
        try {
            int id = item.getInt("ID");
            String Notes = item.getString("Notes");

            String LastUpdated = item.getString("LastUpdated");
            Log.d("TIME", LastUpdated);

            JSONObject ContentCategory = item.getJSONObject("ContentCategory");
            int catID = ContentCategory.getInt("id");

            String imgUrl = item.getString("ImageUrl");

            ContentFieldModel cfm = new ContentFieldModel();
            cfm.setId(id);
            cfm.setCategoryID(catID);
            cfm.setImageContent(downloadImage(imgUrl));
            cfm.setTextContent(Notes);

            return cfm;
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
