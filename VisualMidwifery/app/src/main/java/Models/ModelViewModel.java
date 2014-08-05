package Models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liub3 on 16/05/14.
 */
public class ModelViewModel implements Parcelable {
    int id;
    int mainId;
    String angle;
    Bitmap modelImage;
    String lastEdited;
    int step;

    public ModelViewModel() {
    }

    public int getId() {
        return id;
    }

    public int getMainId() {
        return mainId;
    }

    public String getAngle() {
        return angle;
    }

    public Bitmap getModelImage() {
        return modelImage;
    }

    public String getLastEdited(){return lastEdited;}

    public int getStep(){return step;}

    public void setId(int id) {
        this.id = id;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public void setModelImage(Bitmap modelImage) {
        this.modelImage = modelImage;
    }

    public void setLastEdited(String LastEdited){this.lastEdited = LastEdited;}

    public void setStep(int step){this.step = step;}

    @Override
    public String toString(){
        return angle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
