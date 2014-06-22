package Models;

/**
 * Created by liub3 on 16/05/14.
 */
public class ModelColorModel {
    int id;
    int modelID;
    int codeR;
    int codeG;
    int codeB;
    String partName;

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

    public int getCodeR() {
        return codeR;
    }

    public void setCodeR(int codeR) {
        this.codeR = codeR;
    }

    public int getCodeG() {
        return codeG;
    }

    public void setCodeG(int codeG) {
        this.codeG = codeG;
    }

    public int getCodeB() {
        return codeB;
    }

    public void setCodeB(int codeB) {
        this.codeB = codeB;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
