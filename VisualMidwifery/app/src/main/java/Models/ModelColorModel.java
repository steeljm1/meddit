package Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liub3 on 16/05/14.
 * Models will be generate by controller
 * getting and setting methods are for easy access
 */
public class ModelColorModel implements Parcelable{
    int id;
    int modelID;
    String hex;
    String partName;
    String lastEdited;

    public ModelColorModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public void setHex(String hex){this.hex = hex;}
    public String getHex(){return hex;}

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setLastEdited(String LastEdited){this.lastEdited = LastEdited;}
    public String getLastEdited(){return lastEdited;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
