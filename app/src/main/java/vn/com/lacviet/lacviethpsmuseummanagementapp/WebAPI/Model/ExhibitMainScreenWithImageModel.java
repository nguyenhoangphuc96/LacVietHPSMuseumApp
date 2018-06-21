package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

public class ExhibitMainScreenWithImageModel {

    private Integer eXHID;
    private String eXHIBITNAME;
    private String dESCRIPTION;
    private String eIMAGE;

    public ExhibitMainScreenWithImageModel(Integer eXHID, String eXHIBITNAME, String dESCRIPTION, String eIMAGE) {
        this.eXHID = eXHID;
        this.eXHIBITNAME = eXHIBITNAME;
        this.dESCRIPTION = dESCRIPTION;
        this.eIMAGE = eIMAGE;
    }

    public Integer geteXHID() {
        return eXHID;
    }

    public void seteXHID(Integer eXHID) {
        this.eXHID = eXHID;
    }

    public String geteXHIBITNAME() {
        return eXHIBITNAME;
    }

    public void seteXHIBITNAME(String eXHIBITNAME) {
        this.eXHIBITNAME = eXHIBITNAME;
    }

    public String getdESCRIPTION() {
        return dESCRIPTION;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String geteIMAGE() {
        return eIMAGE;
    }

    public void seteIMAGE(String eIMAGE) {
        this.eIMAGE = eIMAGE;
    }
}
