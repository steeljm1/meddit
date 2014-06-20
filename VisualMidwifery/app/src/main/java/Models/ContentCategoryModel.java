package Models;

/**
 * Created by liub3 on 16/05/14.
 */
public class ContentCategoryModel {
    int id;
    int mainId;
    String title;

    public ContentCategoryModel() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getMainId() {
        return mainId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
