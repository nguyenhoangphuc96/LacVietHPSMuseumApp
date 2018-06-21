package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllResultJsonResponse {

    @SerializedName("Total")
    @Expose
    private Integer total;
    @SerializedName("ExhibitModels")
    @Expose
    private List<ExhibitMainScreenModel> exhibitMainScreenModel = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ExhibitMainScreenModel> getExhibitMainScreenModels() {
        return exhibitMainScreenModel;
    }

    public void setExhibitModels(List<ExhibitMainScreenModel> exhibitMainScreenModel) {
        this.exhibitMainScreenModel = exhibitMainScreenModel;
    }

}