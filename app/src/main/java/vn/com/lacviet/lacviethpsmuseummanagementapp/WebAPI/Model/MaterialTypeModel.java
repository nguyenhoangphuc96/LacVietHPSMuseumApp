package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MaterialTypeModel {

    @SerializedName("MATERIALTYPE1")
    @Expose
    private String mATERIALTYPE1;
    @SerializedName("MATERIALNAME")
    @Expose
    private String mATERIALNAME;
    @SerializedName("MATERIALNOTE")
    @Expose
    private String mATERIALNOTE;
    @SerializedName("MATERIALORDER")
    @Expose
    private Integer mATERIALORDER;
    @SerializedName("CUSTOMERID")
    @Expose
    private String cUSTOMERID;
    @SerializedName("EXHIBITS")
    @Expose
    private List<Object> eXHIBITS = null;

    public String getMATERIALTYPE1() {
        return mATERIALTYPE1;
    }

    public void setMATERIALTYPE1(String mATERIALTYPE1) {
        this.mATERIALTYPE1 = mATERIALTYPE1;
    }

    public String getMATERIALNAME() {
        return mATERIALNAME;
    }

    public void setMATERIALNAME(String mATERIALNAME) {
        this.mATERIALNAME = mATERIALNAME;
    }

    public String getMATERIALNOTE() {
        return mATERIALNOTE;
    }

    public void setMATERIALNOTE(String mATERIALNOTE) {
        this.mATERIALNOTE = mATERIALNOTE;
    }

    public Integer getMATERIALORDER() {
        return mATERIALORDER;
    }

    public void setMATERIALORDER(Integer mATERIALORDER) {
        this.mATERIALORDER = mATERIALORDER;
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