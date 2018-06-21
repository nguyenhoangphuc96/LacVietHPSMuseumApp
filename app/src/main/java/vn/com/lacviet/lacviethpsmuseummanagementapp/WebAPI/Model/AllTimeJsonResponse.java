package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllTimeJsonResponse{

    @SerializedName("TimeModels")
    @Expose
    private List<TimeModel> timeModels = null;

    public List<TimeModel> getTimeModels() {
        return timeModels;
    }

    public void setTimeModels(List<TimeModel> timeModels) {
        this.timeModels = timeModels;
    }

}