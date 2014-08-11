package Update;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import DBController.DatabaseController;

/**
 * Created by glenn_000 on 11/08/2014.
 */
public abstract class ModelUpdater {
    Context context;
    DatabaseController controller;

    protected ModelUpdater(Context context) {
        this.context = context;
        this.controller = new DatabaseController(context);
    }

    protected String convertInputToJson(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null)
            result += line;

        in.close();
        return result;
    }
}
