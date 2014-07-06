package Models;

import android.graphics.Bitmap;

/**
 * Created by liub3 on 16/05/14.
 */
public class ContentFieldModel {
    int id;
    Bitmap imageContent;
    String textContent;
    int categoryID;

    public ContentFieldModel() {
    }

    public int getId() {
        return id;
    }

    public Bitmap getImageContent() {
        return imageContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageContent(Bitmap imageContent) {
        this.imageContent = imageContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
