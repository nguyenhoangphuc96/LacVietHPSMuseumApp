package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StuffModel {

    @SerializedName("STUFFID")
    @Expose
    private String sTUFFID;
    @SerializedName("STUFFCODE")
    @Expose
    private String sTUFFCODE;
    @SerializedName("STUFFNAME")
    @Expose
    private String sTUFFNAME;
    @SerializedName("STUFFNOTE")
    @Expose
    private String sTUFFNOTE;
    @SerializedName("STUFFORDER")
    @Expose
    private Integer sTUFFORDER;
    @SerializedName("CUSTOMERID")
    @Expose
    private String cUSTOMERID;
    @SerializedName("EXHIBITS")
    @Expose
    private List<Object> eXHIBITS = null;

    public String getSTUFFID() {
        return sTUFFID;
    }

    public void setSTUFFID(String sTUFFID) {
        this.sTUFFID = sTUFFID;
    }

    public String getSTUFFCODE() {
        return sTUFFCODE;
    }

    public void setSTUFFCODE(String sTUFFCODE) {
        this.sTUFFCODE = sTUFFCODE;
    }

    public String getSTUFFNAME() {
        return sTUFFNAME;
    }

    public void setSTUFFNAME(String sTUFFNAME) {
        this.sTUFFNAME = sTUFFNAME;
    }

    public String getSTUFFNOTE() {
        return sTUFFNOTE;
    }

    public void setSTUFFNOTE(String sTUFFNOTE) {
        this.sTUFFNOTE = sTUFFNOTE;
    }

    public Integer getSTUFFORDER() {
        return sTUFFORDER;
    }

    public void setSTUFFORDER(Integer sTUFFORDER) {
        this.sTUFFORDER = sTUFFORDER;
    }

    public String getCUSTOMERID() {
        return cUSTOMERID;
    }

    public void setCUSTOMERID(String cUSTOMERID) {
        this.cUSTOMERID = cUSTOMERID;
    }

    public List<Object> getEXHIBITS() {
        return eXHIBITS;
    }

    public void setEXHIBITS(List<Object> eXHIBITS) {
        this.eXHIBITS = eXHIBITS;
    }

}