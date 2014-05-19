package Models;

import android.graphics.Bitmap;

/**
 * Created by liub3 on 16/05/14.
 */
public class ModelViewModel {
    int id;
    int mainId;
    String angle;
    Bitmap modelImage;

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

    @Override
    public String toString(){
        return angle;
    }
}
