package DBTables;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by liub3 on 16/05/14.
 */
public class ModelColorTable extends SQLiteAssetHelper {

    public static final String TABLE_NAME = "modelColor";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MODELID = "_modelID";
    public static final String COLUMN_R = "R";
    public static final String COLUMN_G = "G";
    public static final String COLUMN_B = "B";
    public static final String COLUMN_NAME = "name";


    public static final int DATABASE_VERSION = 1;

    private static String DB_NAME = "AndroidDB";

    public ModelColorTable(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }
}
