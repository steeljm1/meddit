package otago.meddit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by liub3 on 15/04/14.
 */
public class SQLiteAdapter {
    //private static final String DB_NAME = "db.sqlite";

    private Context context;
    private SQLiteDatabase database;
    private DataBaseHelper dbOpenHelper;
    private ArrayList<String> category;
    private ArrayList<String> angle;

    public SQLiteAdapter(Context contextIn) {
        context = contextIn;
        dbOpenHelper = new DataBaseHelper(context);

        try {

            dbOpenHelper.CreateDatabase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            database = dbOpenHelper.OpenDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
    }

    public ArrayList<String> fillCategory() {
        category = new ArrayList<String>();
        Cursor myCursor = database.query(CategoryColumn.TABLENAME, new String[]{CategoryColumn.CATEGORY}, null, null, null, null, null);

        myCursor.moveToFirst();
        if (!myCursor.isAfterLast()) {
            do {
                String name = myCursor.getString(0);
                category.add(name);
            } while (myCursor.moveToNext());
        }
        myCursor.close();
        return category;
    }
    public ArrayList<String> getAngle(String categoryIn) {
        angle = new ArrayList<String>();
        SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();

        _QB.setTables(ModelViewColumn.TABLENAME + " JOIN " + CategoryColumn.TABLENAME + " ON " +
                ModelViewColumn.CATEGORY + " = " + CategoryColumn.CATEGORY);

        Cursor myCursor = _QB.query(database, new String[]{ModelViewColumn.ANGLE}, ModelViewColumn.CATEGORY + " = '" + categoryIn + "'", null, null, null, null);

        myCursor.moveToFirst();
        if (!myCursor.isAfterLast()) {
            do {
                String anAngle = myCursor.getString(0);
                angle.add(anAngle);
            } while (myCursor.moveToNext());
        }
        myCursor.close();
        return angle;
    }

    public Bitmap getImage(String categoryIn, String angleIn) {

        String qu = "SELECT " + ModelViewColumn.IMAGE + " FROM " + ModelViewColumn.TABLENAME + " WHERE "
                + ModelViewColumn.CATEGORY + " = \"" + categoryIn + "\" AND " + ModelViewColumn.ANGLE + " = '" + angleIn+"'";
        Cursor cur = database.rawQuery(qu, null);

        if (cur.moveToFirst()) {
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null;
    }

    public int updateCategory(){
        ContentValues newValue = new ContentValues();
        newValue.put(CategoryColumn.CATEGORY,"123");
        String[] whereArgs = {"Fetal Skull"};
        int count = database.update(CategoryColumn.TABLENAME,newValue,CategoryColumn.CATEGORY+" =?",whereArgs);
        return count;
    }
}

/*
    * category Table
    * */
final class CategoryColumn implements BaseColumns {
    private CategoryColumn() {
    }

    public static final String TABLENAME = "category";
    public static final String FULL_ID = TABLENAME + "." + _ID;
    public static final String CATEGORY = "name";
}

/*
* modelview Table
* */
final class ModelViewColumn implements BaseColumns {
    private ModelViewColumn() {
    }

    public static final String TABLENAME = "modelview";
    public static final String FULL_ID = TABLENAME + "." + _ID;
    public static final String CATEGORY = "category";
    public static final String ANGLE = "angle";
    public static final String IMAGE = "image";
}
