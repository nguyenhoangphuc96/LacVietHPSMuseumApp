package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExhibitModel {

    @SerializedName("NO")
    @Expose
    private Integer nO;
    @SerializedName("EXHID")
    @Expose
    private Integer eXHID;
    @SerializedName("EXHIBITNAME")
    @Expose
    private String eXHIBITNAME;
    @SerializedName("OTHERNAME")
    @Expose
    private String oTHERNAME;
    @SerializedName("CODEID")
    @Expose
    private String cODEID;
    @SerializedName("NUMBER")
    @Expose
    private Integer nUMBER;
    @SerializedName("ELEMENT")
    @Expose
    private String eLEMENT;
    @SerializedName("MATERIALTYPE")
    @Expose
    private Integer mATERIALTYPE;
    @SerializedName("MATERIALNAME")
    @Expose
    private String mATERIALNAME;
    @SerializedName("VERIFICATIONDATE")
    @Expose
    private String vERIFICATIONDATE;
    @SerializedName("AGES")
    @Expose
    private String aGES;
    @SerializedName("EXPERTISE")
    @Expose
    private Integer eXPERTISE;
    @SerializedName("EXPERTISENAME")
    @Expose
    private String eXPERTISENAME;
    @SerializedName("EXPERTISEDATE")
    @Expose
    private String eXPERTISEDATE;
    @SerializedName("SOURCE")
    @Expose
    private Integer sOURCE;
    @SerializedName("SOURCENAME")
    @Expose
    private String sOURCENAME;
    @SerializedName("RESOURCENOTE")
    @Expose
    private String rESOURCENOTE;
    @SerializedName("TIME")
    @Expose
    private Integer tIME;
    @SerializedName("TIMENAME")
    @Expose
    private String tIMENAME;
    @SerializedName("TIMENOTE")
    @Expose
    private String tIMENOTE;
    @SerializedName("LOCATIONID")
    @Expose
    private String lOCATIONID;
    @SerializedName("LOCATIONNAME")
    @Expose
    private String lOCATIONNAME;
    @SerializedName("LOCATIONNOTE")
    @Expose
    private String lOCATIONNOTE;
    @SerializedName("OWNER")
    @Expose
    private Integer oWNER;
    @SerializedName("OWNERNAME")
    @Expose
    private String oWNERNAME;
    @SerializedName("OWNERADDRESS")
    @Expose
    private String oWNERADDRESS;
    @SerializedName("SECRETLEVEL")
    @Expose
    private Integer sECRETLEVEL;
    @SerializedName("SECRETLEVELNAME")
    @Expose
    private String sECRETLEVELNAME;
    @SerializedName("STUFF")
    @Expose
    private String sTUFF;
    @SerializedName("STUFFNAME")
    @Expose
    private String sTUFFNAME;
    @SerializedName("DESCPHYSICS")
    @Expose
    private String dESCPHYSICS;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("COLOR")
    @Expose
    private String cOLOR;
    @SerializedName("MAKETECH")
    @Expose
    private Integer mAKETECH;
    @SerializedName("MAKETECHNAME")
    @Expose
    private String mAKETECHNAME;
    @SerializedName("MAINTENANCE")
    @Expose
    private Integer mAINTENANCE;
    @SerializedName("MAINTENANCENAME")
    @Expose
    private String mAINTENANCENAME;
    @SerializedName("RELICS")
    @Expose
    private String rELICS;
    @SerializedName("VENDOR")
    @Expose
    private String vENDOR;
    @SerializedName("COST")
    @Expose
    private Integer cOST;
    @SerializedName("VENDORSADDRESS")
    @Expose
    private String vENDORSADDRESS;
    @SerializedName("CONTENT")
    @Expose
    private String cONTENT;
    @SerializedName("FIELD")
    @Expose
    private Integer fIELD;
    @SerializedName("FIELDNAME")
    @Expose
    private String fIELDNAME;
    @SerializedName("COLLECTOR")
    @Expose
    private Integer cOLLECTOR;
    @SerializedName("COLLECTORNAME")
    @Expose
    private String cOLLECTORNAME;
    @SerializedName("COLLECTORDATE")
    @Expose
    private String cOLLECTORDATE;
    @SerializedName("USERID")
    @Expose
    private String uSERID;
    @SerializedName("USERIDMODIFIED")
    @Expose
    private String uSERIDMODIFIED;
    @SerializedName("CREATEDDATE")
    @Expose
    private String cREATEDDATE;
    @SerializedName("MODIFIEDDATE")
    @Expose
    private String mODIFIEDDATE;
    @SerializedName("ISDELETE")
    @Expose
    private Object iSDELETE;
    @SerializedName("CUSTOMERID")
    @Expose
    private Object cUSTOMERID;
    @SerializedName("REALNUMBER")
    @Expose
    private Integer rEALNUMBER;
    @SerializedName("NameAction")
    @Expose
    private Object nameAction;
    @SerializedName("STRVERIFICATIONDATE")
    @Expose
    private String sTRVERIFICATIONDATE;
    @SerializedName("STREXPERTISEDATE")
    @Expose
    private String sTREXPERTISEDATE;
    @SerializedName("STRCOLLECTORDATE")
    @Expose
    private String sTRCOLLECTORDATE;

    public Integer getNO() {
        return nO;
    }

    public void setNO(Integer nO) {
        this.nO = nO;
    }

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

    public String getOTHERNAME() {
        return oTHERNAME;
    }

    public void setOTHERNAME(String oTHERNAME) {
        this.oTHERNAME = oTHERNAME;
    }

    public String getCODEID() {
        return cODEID;
    }

    public void setCODEID(String cODEID) {
        this.cODEID = cODEID;
    }

    public Integer getNUMBER() {
        return nUMBER;
    }

    public void setNUMBER(Integer nUMBER) {
        this.nUMBER = nUMBER;
    }

    public String getELEMENT() {
        return eLEMENT;
    }

    public void setELEMENT(String eLEMENT) {
        this.eLEMENT = eLEMENT;
    }

    public Integer getMATERIALTYPE() {
        return mATERIALTYPE;
    }

    public void setMATERIALTYPE(Integer mATERIALTYPE) {
        this.mATERIALTYPE = mATERIALTYPE;
    }

    public String getMATERIALNAME() {
        return mATERIALNAME;
    }

    public void setMATERIALNAME(String mATERIALNAME) {
        this.mATERIALNAME = mATERIALNAME;
    }

    public String getVERIFICATIONDATE() {
        return vERIFICATIONDATE;
    }

    public void setVERIFICATIONDATE(String vERIFICATIONDATE) {
        this.vERIFICATIONDATE = vERIFICATIONDATE;
    }

    public String getAGES() {
        return aGES;
    }

    public void setAGES(String aGES) {
        this.aGES = aGES;
    }

    public Integer getEXPERTISE() {
        return eXPERTISE;
    }

    public void setEXPERTISE(Integer eXPERTISE) {
        this.eXPERTISE = eXPERTISE;
    }

    public String getEXPERTISENAME() {
        return eXPERTISENAME;
    }

    public void setEXPERTISENAME(String eXPERTISENAME) {
        this.eXPERTISENAME = eXPERTISENAME;
    }

    public String getEXPERTISEDATE() {
        return eXPERTISEDATE;
    }

    public void setEXPERTISEDATE(String eXPERTISEDATE) {
        this.eXPERTISEDATE = eXPERTISEDATE;
    }

    public Integer getSOURCE() {
        return sOURCE;
    }

    public void setSOURCE(Integer sOURCE) {
        this.sOURCE = sOURCE;
    }

    public String getSOURCENAME() {
        return sOURCENAME;
    }

    public void setSOURCENAME(String sOURCENAME) {
        this.sOURCENAME = sOURCENAME;
    }

    public String getRESOURCENOTE() {
        return rESOURCENOTE;
    }

    public void setRESOURCENOTE(String rESOURCENOTE) {
        this.rESOURCENOTE = rESOURCENOTE;
    }

    public Integer getTIME() {
        return tIME;
    }

    public void setTIME(Integer tIME) {
        this.tIME = tIME;
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

    public String getLOCATIONID() {
        return lOCATIONID;
    }

    public void setLOCATIONID(String lOCATIONID) {
        this.lOCATIONID = lOCATIONID;
    }

    public String getLOCATIONNAME() {
        return lOCATIONNAME;
    }

    public void setLOCATIONNAME(String lOCATIONNAME) {
        this.lOCATIONNAME = lOCATIONNAME;
    }

    public String getLOCATIONNOTE() {
        return lOCATIONNOTE;
    }

    public void setLOCATIONNOTE(String lOCATIONNOTE) {
        this.lOCATIONNOTE = lOCATIONNOTE;
    }

    public Integer getOWNER() {
        return oWNER;
    }

    public void setOWNER(Integer oWNER) {
        this.oWNER = oWNER;
    }

    public String getOWNERNAME() {
        return oWNERNAME;
    }

    public void setOWNERNAME(String oWNERNAME) {
        this.oWNERNAME = oWNERNAME;
    }

    public String getOWNERADDRESS() {
        return oWNERADDRESS;
    }

    public void setOWNERADDRESS(String oWNERADDRESS) {
        this.oWNERADDRESS = oWNERADDRESS;
    }

    public Integer getSECRETLEVEL() {
        return sECRETLEVEL;
    }

    public void setSECRETLEVEL(Integer sECRETLEVEL) {
        this.sECRETLEVEL = sECRETLEVEL;
    }

    public String getSECRETLEVELNAME() {
        return sECRETLEVELNAME;
    }

    public void setSECRETLEVELNAME(String sECRETLEVELNAME) {
        this.sECRETLEVELNAME = sECRETLEVELNAME;
    }

    public String getSTUFF() {
        return sTUFF;
    }

    public void setSTUFF(String sTUFF) {
        this.sTUFF = sTUFF;
    }

    public String getSTUFFNAME() {
        return sTUFFNAME;
    }

    public void setSTUFFNAME(String sTUFFNAME) {
        this.sTUFFNAME = sTUFFNAME;
    }

    public String getDESCPHYSICS() {
        return dESCPHYSICS;
    }

    public void setDESCPHYSICS(String dESCPHYSICS) {
        this.dESCPHYSICS = dESCPHYSICS;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getCOLOR() {
        return cOLOR;
    }

    public void setCOLOR(String cOLOR) {
        this.cOLOR = cOLOR;
    }

    public Integer getMAKETECH() {
        return mAKETECH;
    }

    public void setMAKETECH(Integer mAKETECH) {
        this.mAKETECH = mAKETECH;
    }

    public String getMAKETECHNAME() {
        return mAKETECHNAME;
    }

    public void setMAKETECHNAME(String mAKETECHNAME) {
        this.mAKETECHNAME = mAKETECHNAME;
    }

    public Integer getMAINTENANCE() {
        return mAINTENANCE;
    }

    public void setMAINTENANCE(Integer mAINTENANCE) {
        this.mAINTENANCE = mAINTENANCE;
    }

    public String getMAINTENANCENAME() {
        return mAINTENANCENAME;
    }

    public void setMAINTENANCENAME(String mAINTENANCENAME) {
        this.mAINTENANCENAME = mAINTENANCENAME;
    }

    public String getRELICS() {
        return rELICS;
    }

    public void setRELICS(String rELICS) {
        this.rELICS = rELICS;
    }

    public String getVENDOR() {
        return vENDOR;
    }

    public void setVENDOR(String vENDOR) {
        this.vENDOR = vENDOR;
    }

    public Integer getCOST() {
        return cOST;
    }

    public void setCOST(Integer cOST) {
        this.cOST = cOST;
    }

    public String getVENDORSADDRESS() {
        return vENDORSADDRESS;
    }

    public void setVENDORSADDRESS(String vENDORSADDRESS) {
        this.vENDORSADDRESS = vENDORSADDRESS;
    }

    public String getCONTENT() {
        return cONTENT;
    }

    public void setCONTENT(String cONTENT) {
        this.cONTENT = cONTENT;
    }

    public Integer getFIELD() {
        return fIELD;
    }

    public void setFIELD(Integer fIELD) {
        this.fIELD = fIELD;
    }

    public String getFIELDNAME() {
        return fIELDNAME;
    }

    public void setFIELDNAME(String fIELDNAME) {
        this.fIELDNAME = fIELDNAME;
    }

    public Integer getCOLLECTOR() {
        return cOLLECTOR;
    }

    public void setCOLLECTOR(Integer cOLLECTOR) {
        this.cOLLECTOR = cOLLECTOR;
    }

    public String getCOLLECTORNAME() {
        return cOLLECTORNAME;
    }

    public void setCOLLECTORNAME(String cOLLECTORNAME) {
        this.cOLLECTORNAME = cOLLECTORNAME;
    }

    public String getCOLLECTORDATE() {
        return cOLLECTORDATE;
    }

    public void setCOLLECTORDATE(String cOLLECTORDATE) {
        this.cOLLECTORDATE = cOLLECTORDATE;
    }

    public String getUSERID() {
        return uSERID;
    }

    public void setUSERID(String uSERID) {
        this.uSERID = uSERID;
    }

    public String getUSERIDMODIFIED() {
        return uSERIDMODIFIED;
    }

    public void setUSERIDMODIFIED(String uSERIDMODIFIED) {
        this.uSERIDMODIFIED = uSERIDMODIFIED;
    }

    public String getCREATEDDATE() {
        return cREATEDDATE;
    }

    public void setCREATEDDATE(String cREATEDDATE) {
        this.cREATEDDATE = cREATEDDATE;
    }

    public String getMODIFIEDDATE() {
        return mODIFIEDDATE;
    }

    public void setMODIFIEDDATE(String mODIFIEDDATE) {
        this.mODIFIEDDATE = mODIFIEDDATE;
    }

    public Object getISDELETE() {
        return iSDELETE;
    }

    public void setISDELETE(Object iSDELETE) {
        this.iSDELETE = iSDELETE;
    }

    public Object getCUSTOMERID() {
        return cUSTOMERID;
    }

    public void setCUSTOMERID(Object cUSTOMERID) {
        this.cUSTOMERID = cUSTOMERID;
    }

    public Integer getREALNUMBER() {
        return rEALNUMBER;
    }

    public void setREALNUMBER(Integer rEALNUMBER) {
        this.rEALNUMBER = rEALNUMBER;
    }

    public Object getNameAction() {
        return nameAction;
    }

    public void setNameAction(Object nameAction) {
        this.nameAction = nameAction;
    }

    public String getSTRVERIFICATIONDATE() {
        return sTRVERIFICATIONDATE;
    }

    public void setSTRVERIFICATIONDATE(String sTRVERIFICATIONDATE) {
        this.sTRVERIFICATIONDATE = sTRVERIFICATIONDATE;
    }

    public String getSTREXPERTISEDATE() {
        return sTREXPERTISEDATE;
    }

    public void setSTREXPERTISEDATE(String sTREXPERTISEDATE) {
        this.sTREXPERTISEDATE = sTREXPERTISEDATE;
    }

    public String getSTRCOLLECTORDATE() {
        return sTRCOLLECTORDATE;
    }

    public void setSTRCOLLECTORDATE(String sTRCOLLECTORDATE) {
        this.sTRCOLLECTORDATE = sTRCOLLECTORDATE;
    }

}


