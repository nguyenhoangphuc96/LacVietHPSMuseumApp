package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SOAnswersResponse {

    @SerializedName("StuffModels")
    @Expose
    private List<StuffModel> stuffModels = null;

    public List<StuffModel> getStuffModels() {
        return stuffModels;
    }

    public void setStuffModels(List<StuffModel> stuffModels) {
        this.stuffModels = stuffModels;
    }

}