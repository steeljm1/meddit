package com.liub3;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.util.List;

/**
 * Created by liub3 on 8/04/14.
 */
public class StartUpApplication extends Application {

    SQLiteAdapter mySQLiteAdapter;
    private List<String> category;
    private static final String DB_NAME = "OTAGOMEDDIT.sqlite";

    @Override
    public void onCreate() {
        super.onCreate();
        firstRunPreferences();
        if(getFirstRun()){
            //Log.d("TAG","First time");
            //this.deleteDatabase("DB_NAME");
            File file = new File("data/data/com.liub3/databases/"+DB_NAME);
            if(file.exists()){
                boolean b = file.delete();
                //Log.d("TAG",""+b);
            }
        }
        mySQLiteAdapter = new SQLiteAdapter(this);
        //mySQLiteAdapter.updateCategory();
        InitCategory();

    }

    private void InitCategory() {
        category = mySQLiteAdapter.fillCategory();
    }

    public List<String> getCategory() {
        return category;
    }


    /**
     * get if this is the first run
     *
     * @return returns true, if this is the first run
     */
    public boolean getFirstRun() {
        return mPrefs.getBoolean("firstRun", true);
    }

    /**
     * store the first run
     */
    public void setRunned() {
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putBoolean("firstRun", false);
        edit.commit();
    }

    SharedPreferences mPrefs;

    /**
     * setting up preferences storage
     */
    public void firstRunPreferences() {
        Context mContext = this.getApplicationContext();
        mPrefs = mContext.getSharedPreferences("myAppPrefs", 0); //0 = mode private. only this app can read these preferences
    }
}
