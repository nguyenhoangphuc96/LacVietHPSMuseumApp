package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllExhibitJsonResponse {
    @SerializedName("Total")
    @Expose
    private Integer total;
    @SerializedName("ExhibitModels")
    @Expose
    private List<ExhibitMainScreenModel> exhibitModels = null;

    public List<ExhibitMainScreenModel> getExhibitModels() {
        return exhibitModels;
    }

    public void setExhibitModels(List<ExhibitMainScreenModel> exhibitModels) {
        this.exhibitModels = exhibitModels;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}