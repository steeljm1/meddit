package DBController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import DBTables.ContentCategoryTable;
import DBTables.ContentFieldsTable;
import DBTables.MainCategoryTable;
import DBTables.ModelColorTable;
import DBTables.ModelViewTable;
import Models.ContentCategoryModel;
import Models.ContentFieldModel;
import Models.MainCategoryModel;
import Models.ModelColorModel;
import Models.ModelViewModel;

/**
 * Created by liub3 on 16/05/14.
 */
public class DatabaseController {
    private SQLiteDatabase database;
    private ContentCategoryTable contentCategoryTable;
    private ContentFieldsTable contentFieldsTable;
    private MainCategoryTable mainCategoryTable;
    private ModelViewTable modelViewTable;
    private ModelColorTable modelColorTable;

    public DatabaseController(Context context) {
        contentCategoryTable = new ContentCategoryTable(context);
        contentFieldsTable = new ContentFieldsTable(context);
        mainCategoryTable = new MainCategoryTable(context);
        modelViewTable = new ModelViewTable(context);
        modelColorTable = new ModelColorTable(context);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<ContentCategoryModel> GetAllContentCategory(int mainID) throws SQLException {
        ArrayList<ContentCategoryModel> contentCategoryModelArrayList = new ArrayList<ContentCategoryModel>();

        database = contentCategoryTable.getWritableDatabase();
        //queries from here
        String[] contentCategoryColumns = {contentCategoryTable.COLUMN_ID, contentCategoryTable.COLUMN_TITLE, contentCategoryTable.COLUMN_MAINID};

        String whereClause = "_mainID = " + String.valueOf(mainID);
        Cursor cursor = database.query(contentCategoryTable.TABLE_NAME, contentCategoryColumns, whereClause, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ContentCategoryModel newContentCategoryModel = cursorToContentCategoryModel(cursor);
            contentCategoryModelArrayList.add(newContentCategoryModel);
            cursor.moveToNext();
        }

        contentCategoryTable.close();

        return contentCategoryModelArrayList;
    }
    private ContentCategoryModel cursorToContentCategoryModel(Cursor cursor){
        ContentCategoryModel temp = new ContentCategoryModel();

        temp.setId(cursor.getInt(0));
        temp.setTitle(cursor.getString(1));
        temp.setMainId(cursor.getInt(2));

        return temp;
    }

    public void updateContentCategories(ArrayList<ContentCategoryModel> modelArray) {

        database = contentCategoryTable.getWritableDatabase();
        database.delete(ContentCategoryTable.TABLE_NAME, null, null);

        for(int i = 0; i < modelArray.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(ContentCategoryTable.COLUMN_MAINID, modelArray.get(i).getMainId());
            values.put(ContentCategoryTable.COLUMN_TITLE, modelArray.get(i).getTitle());
            database.insert(ContentCategoryTable.TABLE_NAME, null, values);
        }

        contentCategoryTable.close();

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<ContentFieldModel> GetAllContentField() throws SQLException {
        ArrayList<ContentFieldModel> contentFieldModelArrayList = new ArrayList<ContentFieldModel>();

        database = contentFieldsTable.getWritableDatabase();
        //queries from here
        String[] contentFieldsColumns = {contentFieldsTable.COLUMN_ID, contentFieldsTable.COLUMN_IMAGE, contentFieldsTable.COLUMN_NOTES,contentFieldsTable.COLUMN_CATEGORYID};
        Cursor cursor = database.query(contentFieldsTable.TABLE_NAME, contentFieldsColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ContentFieldModel newContentFieldModel = cursorToContentFieldsModel(cursor);
            contentFieldModelArrayList.add(newContentFieldModel);
            cursor.moveToNext();
        }

        contentFieldsTable.close();

        return contentFieldModelArrayList;
    }

    public ArrayList<ContentFieldModel> getContentOfCategory(int categoryId)
    {
        ArrayList<ContentFieldModel> content = new ArrayList<ContentFieldModel>();

        String[] contentFieldsColumns = {contentFieldsTable.COLUMN_ID, contentFieldsTable.COLUMN_IMAGE, contentFieldsTable.COLUMN_NOTES,contentFieldsTable.COLUMN_CATEGORYID};

        database = contentFieldsTable.getWritableDatabase();

        String whereClause = "_categoryID = " + String.valueOf(categoryId);
        Cursor cursor = database.query(ContentFieldsTable.TABLE_NAME, contentFieldsColumns, whereClause, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            ContentFieldModel newContent = cursorToContentFieldsModel(cursor);
            content.add(newContent);

            cursor.moveToNext();
        }

        cursor.close();
        contentFieldsTable.close();
        database.close();
        return  content;
    }

    private ContentFieldModel cursorToContentFieldsModel(Cursor cursor){
        ContentFieldModel temp = new ContentFieldModel();

        temp.setId(cursor.getInt(0));

        byte[] image = cursor.getBlob(1);
        temp.setImageContent(BitmapFactory.decodeByteArray(image, 0, image.length));

        temp.setTextContent(cursor.getString(2));
        temp.setCategoryID(cursor.getInt(3));
        return temp;
    }

    // UPDATE METHOD
    public void updateContentField(ArrayList<ContentFieldModel> modelArray) {

        database = contentFieldsTable.getWritableDatabase();
        database.delete(ContentFieldsTable.TABLE_NAME, null, null);

        for(int i = 0; i < modelArray.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(ContentFieldsTable.COLUMN_CATEGORYID, modelArray.get(i).getCategoryID());
            values.put(ContentFieldsTable.COLUMN_NOTES, modelArray.get(i).getTextContent());
            values.put(ContentFieldsTable.COLUMN_IMAGE, bitmapToByteArray(modelArray.get(i).getImageContent()));
            database.insert(ContentFieldsTable.TABLE_NAME, null, values);
        }

        contentFieldsTable.close();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<MainCategoryModel> GetAllMainCategory() throws SQLException {
        ArrayList<MainCategoryModel> mainCategoryModelsArrayList = new ArrayList<MainCategoryModel>();

        database = mainCategoryTable.getWritableDatabase();
        //queries from here
        String[] mainCategoryColumns = {mainCategoryTable.COLUMN_ID, mainCategoryTable.COLUMN_TITLE};
        Cursor cursor = database.query(mainCategoryTable.TABLE_NAME, mainCategoryColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            MainCategoryModel newmainCategoryModel = cursorToMainCategoryModel(cursor);
            mainCategoryModelsArrayList.add(newmainCategoryModel);
            cursor.moveToNext();
        }

        mainCategoryTable.close();

        return mainCategoryModelsArrayList;
    }
    private MainCategoryModel cursorToMainCategoryModel(Cursor cursor){
        MainCategoryModel temp = new MainCategoryModel();

        temp.setId(cursor.getInt(0));
        temp.setTitle(cursor.getString(1));

        return temp;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<ModelViewModel> GetAllModelView(int mainID) throws SQLException {
        ArrayList<ModelViewModel> modelViewModelsArrayList = new ArrayList<ModelViewModel>();

        database = modelViewTable.getWritableDatabase();
        //queries from here
        String[] modelViewColumns = {modelViewTable.COLUMN_ID, modelViewTable.COLUMN_MAINID,modelViewTable.COLUMN_ANGLE,modelViewTable.COLUMN_IMAGE,modelViewTable.COLUMN_LASTEDITED,modelViewTable.COLUMN_STEP};

        String whereClause = "_mainID = " + String.valueOf(mainID);
        Cursor cursor = database.query(modelViewTable.TABLE_NAME, modelViewColumns, whereClause, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ModelViewModel newmodelViewModel = cursorToModelViewModel(cursor);
            modelViewModelsArrayList.add(newmodelViewModel);
            cursor.moveToNext();
        }

        modelViewTable.close();

        return modelViewModelsArrayList;
    }
    private ModelViewModel cursorToModelViewModel(Cursor cursor){
        ModelViewModel temp = new ModelViewModel();

        temp.setId(cursor.getInt(0));
        temp.setMainId(cursor.getInt(1));
        temp.setAngle(cursor.getString(2));

        byte[] image = cursor.getBlob(3);
        temp.setModelImage(BitmapFactory.decodeByteArray(image, 0, image.length));

        temp.setLastEdited(cursor.getString(4));

        temp.setStep(cursor.getInt(5));

        return temp;
    }

    public void updateModelView(ArrayList<ModelViewModel> modelArray) {

        database = modelViewTable.getWritableDatabase();
        database.delete(ModelViewTable.TABLE_NAME, null, null);

        for(int i = 0; i < modelArray.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(ModelViewTable.COLUMN_MAINID, modelArray.get(i).getMainId());
            values.put(ModelViewTable.COLUMN_ANGLE, modelArray.get(i).getAngle());
            values.put(ModelViewTable.COLUMN_IMAGE, bitmapToByteArray(modelArray.get(i).getModelImage()));
            values.put(ModelViewTable.COLUMN_LASTEDITED, modelArray.get(i).getLastEdited());
            //values.put(ModelViewTable.COLUMN_STEP, modelArray.get(i).getStep());
            database.insert(ModelViewTable.TABLE_NAME, null, values);
        }

        modelViewTable.close();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<ModelColorModel> GetAllModelColor() throws SQLException {
        ArrayList<ModelColorModel> modelColorModelsArrayList = new ArrayList<ModelColorModel>();

        database = modelColorTable.getWritableDatabase();
        //queries from here
        String[] modelColorColumns = {modelColorTable.COLUMN_ID, modelColorTable.COLUMN_MODELID,modelColorTable.COLUMN_HEX,modelColorTable.COLUMN_NAME,modelColorTable.COLUMN_LASTEDITED};
        Cursor cursor = database.query(modelColorTable.TABLE_NAME, modelColorColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ModelColorModel newmodelColorModel = cursorToModelColorModel(cursor);
            modelColorModelsArrayList.add(newmodelColorModel);
            cursor.moveToNext();
        }

        modelColorTable.close();

        return modelColorModelsArrayList;
    }
    private ModelColorModel cursorToModelColorModel(Cursor cursor){
        ModelColorModel temp = new ModelColorModel();

        temp.setId(cursor.getInt(0));
        temp.setModelID(cursor.getInt(1));
        temp.setHex(cursor.getString(2));
        temp.setPartName(cursor.getString(3));
        temp.setLastEdited(cursor.getString(4));

        return temp;
    }

    public void updateModelColour(ArrayList<ModelColorModel> modelArray) {
        database = modelColorTable.getWritableDatabase();
        database.delete(ModelColorTable.TABLE_NAME, null, null);

        for(int i = 0; i < modelArray.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(ModelColorTable.COLUMN_MODELID, modelArray.get(i).getModelID());
            values.put(ModelColorTable.COLUMN_HEX, modelArray.get(i).getHex());
            values.put(ModelColorTable.COLUMN_NAME, modelArray.get(i).getPartName());
            values.put(ModelColorTable.COLUMN_LASTEDITED, modelArray.get(i).getLastEdited());
            database.insert(ModelColorTable.TABLE_NAME, null, values);
        }
        modelColorTable.close();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //Utility when updating
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();
        return image;
    }
}
