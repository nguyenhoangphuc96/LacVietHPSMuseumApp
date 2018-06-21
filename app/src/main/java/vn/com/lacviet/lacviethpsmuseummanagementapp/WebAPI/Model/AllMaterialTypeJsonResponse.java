package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMaterialTypeJsonResponse {

    @SerializedName("MaterialTypeModels")
    @Expose
    private List<MaterialTypeModel> materialTypeModels = null;

    public List<MaterialTypeModel> getMaterialTypeModels() {
        return materialTypeModels;
    }

    public void setMaterialTypeModels(List<MaterialTypeModel> materialTypeModels) {
        this.materialTypeModels = materialTypeModels;
    }

}