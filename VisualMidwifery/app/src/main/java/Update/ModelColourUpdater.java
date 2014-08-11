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

import Models.ModelColorModel;

/**
 * Created by glenn_000 on 11/08/2014.
 */
public class ModelColourUpdater extends ModelUpdater {

    public ModelColourUpdater(Context context) {
        super(context);
    }

    private void onPost(ArrayList<ModelColorModel> models) {
        controller.updateModelColour(models);
    }

    public void readModelColourJsonStream(InputStream in) throws IOException {
        try {
            JSONObject json = new JSONObject(convertInputToJson(in));
            JSONArray jsonArray = json.getJSONArray("items");
            onPost(readModelColour(jsonArray));
            in.close();
        } catch (JSONException e) {
            Log.d("JSON PARSE ERROR", "Reading from input stream failed");
        }
    } // End readJsonStream


    /* PRE:  */
    private ArrayList<ModelColorModel> readModelColour(JSONArray ccJson) throws IOException {

        ArrayList<ModelColorModel> list = new ArrayList<ModelColorModel>();
        try { // Constructs object list to be compared against the database
            for (int i = 0; i < ccJson.length(); i++) {
                JSONObject row = ccJson.getJSONObject(i);
                ModelColorModel mcm = constructModel(row);
                list.add(mcm);
            }
        } catch (JSONException e) {

        }
        return list;
    } // END readContentCategory
    /* POST:  */

    private ModelColorModel constructModel(JSONObject item) {
        try {
            int id = item.getInt("ID");
            String Name = item.getString("Name");

            String LastUpdated = item.getString("LastUpdated");
            Log.d("TIME", LastUpdated);

            JSONObject modelView = item.getJSONObject("ModelView");
            int modelViewId = modelView.getInt("id");

            String hexColor = item.getString("BgColor");

            ModelColorModel mcm = new ModelColorModel();
            mcm.setId(id);
            mcm.setModelID(modelViewId);
            mcm.setPartName(Name);
            mcm.setHex(hexColor);
            mcm.setLastEdited(LastUpdated);

            return mcm;
        } catch (JSONException e) {
            Log.d("JSON", "JSON parsing error :" + e.toString());
        } return null;
    }
}
