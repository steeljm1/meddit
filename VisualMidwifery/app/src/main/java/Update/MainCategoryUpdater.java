package Update;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Models.MainCategoryModel;

/**
 * Created by glennsp1 on 22/10/2014.
 */
public class MainCategoryUpdater extends ModelUpdater {

    protected MainCategoryUpdater(Context context) {
        super(context);
    }

    private void onPost(ArrayList<MainCategoryModel> models) {
        controller.updateMainCategory(models);
    }

    @Override
    protected void readInputStream(InputStream in) throws IOException {
        try {
            JSONObject json = new JSONObject(convertInputToJson(in));
            JSONArray jsonArray = json.getJSONArray("items");
            onPost(readMainCategory(jsonArray));
            in.close();
        } catch (JSONException e) {
            Log.d("JSON PARSE ERROR", "Reading from input stream failed");
        }
    } // END readInputStream


    /* PRE:  */
    private ArrayList<MainCategoryModel> readMainCategory(JSONArray ccJson) throws IOException {
        ArrayList<MainCategoryModel> list = new ArrayList<MainCategoryModel>();
        try { // Constructs object list to be compared against the database
            for(int i = 0; i < ccJson.length(); i++) {
                JSONObject row = ccJson.getJSONObject(i);
                MainCategoryModel ccm = constructModel(row);
                list.add(ccm);
            }
        } catch (JSONException e) {

        } return list;
    } // END readMainCategory


    private MainCategoryModel constructModel(JSONObject item) {
        try{
            MainCategoryModel mcm = new MainCategoryModel();

            mcm.setId(item.getInt("ID"));
            mcm.setTitle(item.getString("Title"));

            return mcm;
        } catch (JSONException e) {
            Log.d("JSON", "JSON parsing error :" + e.toString());
        } return null;
    }

}
