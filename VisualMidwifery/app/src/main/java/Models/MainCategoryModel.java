package Models;

/**
 * Created by liub3 on 16/05/14.
 * Models will be generate by controller
 * getting and setting methods are for easy access
 */
public class MainCategoryModel {
    int id;
    String title;

    public MainCategoryModel() {
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
