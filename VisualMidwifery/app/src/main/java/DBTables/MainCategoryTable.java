package DBTables;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by liub3 on 16/05/14.
 */
public class MainCategoryTable extends SQLiteAssetHelper {

    public static final String TABLE_NAME = "mainCategory";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";

    public static final int DATABASE_VERSION = 1;

    private static String DB_NAME = "AndroidDB";


    public MainCategoryTable(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }
}
