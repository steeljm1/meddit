package DBTables;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by liub3 on 16/05/14.
 * table in database use this for easy access
 */
public class ModelViewTable extends SQLiteAssetHelper {

    public static final String TABLE_NAME = "modelView";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MAINID = "_mainID";
    public static final String COLUMN_ANGLE = "angle";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_LASTEDITED = "LastEdited";
    public static final String COLUMN_STEP = "step";

    public static final int DATABASE_VERSION = 1;

    private static String DB_NAME = "AndroidDB";

    public ModelViewTable(Context context) {
        super(context,DB_NAME, null, DATABASE_VERSION);
    }
}
