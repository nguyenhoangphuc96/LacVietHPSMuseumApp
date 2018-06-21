package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimeModel {

    @SerializedName("TIMEID")
    @Expose
    private String tIMEID;
    @SerializedName("TIMENAME")
    @Expose
    private String tIMENAME;
    @SerializedName("TIMENOTE")
    @Expose
    private String tIMENOTE;
    @SerializedName("TIMEORDER")
    @Expose
    private Integer tIMEORDER;
    @SerializedName("CUSTOMERID")
    @Expose
    private String cUSTOMERID;
    @SerializedName("EXHIBITS")
    @Expose
    private List<Object> eXHIBITS = null;

    public String getTIMEID() {
        return tIMEID;
    }

    public void setTIMEID(String tIMEID) {
        this.tIMEID = tIMEID;
    }

    public String getTIMENAME() {
        return tIMENAME;
    }

    public void setTIMENAME(String tIMENAME) {
        this.tIMENAME = tIMENAME;
    }

    public String getTIMENOTE() {
        return tIMENOTE;
    }

    public void setTIMENOTE(String tIMENOTE) {
        this.tIMENOTE = tIMENOTE;
    }

    public Integer getTIMEORDER() {
        return tIMEORDER;
    }

    public void setTIMEORDER(Integer tIMEORDER) {
        this.tIMEORDER = tIMEORDER;
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