package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageByIDResponse {

    @SerializedName("ExhibitImages")
    @Expose
    private List<String> exhibitImages = null;

    public List<String> getExhibitImages() {
        return exhibitImages;
    }

    public void setExhibitImages(List<String> exhibitImages) {
        this.exhibitImages = exhibitImages;
    }

}