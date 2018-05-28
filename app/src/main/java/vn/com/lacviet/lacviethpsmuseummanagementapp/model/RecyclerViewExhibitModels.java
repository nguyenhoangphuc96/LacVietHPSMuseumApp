package vn.com.lacviet.lacviethpsmuseummanagementapp.model;

public class RecyclerViewExhibitModels {
    int ExhID;
    String ExhibitName;
    String Description;
    int image;

    public RecyclerViewExhibitModels() {
    }

    public RecyclerViewExhibitModels(int exhID, String exhibitName, String description, int image) {
        ExhID = exhID;
        ExhibitName = exhibitName;
        Description = description;
        this.image = image;
    }

    public int getExhID() {
        return ExhID;
    }

    public void setExhID(int exhID) {
        ExhID = exhID;
    }

    public String getExhibitName() {
        return ExhibitName;
    }

    public void setExhibitName(String exhibitName) {
        ExhibitName = exhibitName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
