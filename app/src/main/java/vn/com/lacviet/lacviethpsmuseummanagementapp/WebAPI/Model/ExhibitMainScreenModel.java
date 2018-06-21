package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExhibitMainScreenModel {

    @SerializedName("EXHID")
    @Expose
    private Integer eXHID;
    @SerializedName("EXHIBITNAME")
    @Expose
    private String eXHIBITNAME;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;

    public Integer getEXHID() {
        return eXHID;
    }

    public void setEXHID(Integer eXHID) {
        this.eXHID = eXHID;
    }

    public String getEXHIBITNAME() {
        return eXHIBITNAME;
    }

    public void setEXHIBITNAME(String eXHIBITNAME) {
        this.eXHIBITNAME = eXHIBITNAME;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

}


