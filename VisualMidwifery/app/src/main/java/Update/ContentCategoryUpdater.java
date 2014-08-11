package Update;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Models.ContentCategoryModel;

/**
 * Created by glenn_000 on 11/08/2014.
 */
public class ContentCategoryUpdater extends ModelUpdater {

    public ContentCategoryUpdater(Context context) {
        super(context);
    }

    private void onPost(ArrayList<ContentCategoryModel> models) {
        controller.updateContentCategories(models);
    }

    // READING JSON STREAM CONTENT CATEGORY
    public void readContentCategoryJsonStream(InputStream in) throws IOException {
        try {
            JSONObject json = new JSONObject(convertInputToJson(in));
            JSONArray jsonArray = json.getJSONArray("items");
            onPost(readContentCategory(jsonArray));
            in.close();
        } catch (JSONException e) {
            Log.d("JSON PARSE ERROR", "Reading from input stream failed");
        }
    } // End readJsonStream



    /* PRE:  */
    private ArrayList<ContentCategoryModel> readContentCategory(JSONArray ccJson) throws IOException {
        ArrayList<ContentCategoryModel> list = new ArrayList<ContentCategoryModel>();
        try { // Constructs object list to be compared against the database
            for(int i = 0; i < ccJson.length(); i++) {
                JSONObject row = ccJson.getJSONObject(i);
                ContentCategoryModel ccm = constructModel(row);
                list.add(ccm);
            }
        } catch (JSONException e) {

        } return list;
    } // END readContentCategory


    private ContentCategoryModel constructModel(JSONObject item) {
        try{
            ContentCategoryModel ccm = new ContentCategoryModel();
            JSONObject mainCategory = item.getJSONObject("MainCategories");

            ccm.setTitle(item.getString("Title"));
            ccm.setMainId(mainCategory.getInt("id"));

            return ccm;
        } catch (JSONException e) {
            Log.d("JSON", "JSON parsing error :" + e.toString());
        } return null;
    }
}


