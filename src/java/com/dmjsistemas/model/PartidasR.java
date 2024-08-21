package com.dmjsistemas.model;

import java.util.Date;

public class PartidasR {

    private String DOC_ANT;
    private String CVE_DOC;
    private Date FECHA_ENT;
    private int E_LTPD;
    private String CVE_ART;
    private Double CANT;
    private String UNI_VENTA;
    private String DESCR;
    private String CTRL_ALM;
    private String LOTE;

    public PartidasR() {
    }

    public PartidasR(String DOC_ANT, String CVE_DOC, Date FECHA_ENT, int E_LTPD, String CVE_ART, Double CANT, String UNI_VENTA, String DESCR, String CTRL_ALM, String LOTE) {
        this.DOC_ANT = DOC_ANT;
        this.CVE_DOC = CVE_DOC;
        this.FECHA_ENT = FECHA_ENT;
        this.E_LTPD = E_LTPD;
        this.CVE_ART = CVE_ART;
        this.CANT = CANT;
        this.UNI_VENTA = UNI_VENTA;
        this.DESCR = DESCR;
        this.CTRL_ALM = CTRL_ALM;
        this.LOTE = LOTE;
    }

    public String getDOC_ANT() {
        return DOC_ANT;
    }

    public void setDOC_ANT(String DOC_ANT) {
        this.DOC_ANT = DOC_ANT;
    }

    public String getCVE_DOC() {
        return CVE_DOC;
    }

    public void setCVE_DOC(String CVE_DOC) {
        this.CVE_DOC = CVE_DOC;
    }

    public Date getFECHA_ENT() {
        return FECHA_ENT;
    }

    public void setFECHA_ENT(Date FECHA_ENT) {
        this.FECHA_ENT = FECHA_ENT;
    }

    public int getE_LTPD() {
        return E_LTPD;
    }

    public void setE_LTPD(int E_LTPD) {
        this.E_LTPD = E_LTPD;
    }

    public String getCVE_ART() {
        return CVE_ART;
    }

    public void setCVE_ART(String CVE_ART) {
        this.CVE_ART = CVE_ART;
    }

    public Double getCANT() {
        return CANT;
    }

    public void setCANT(Double CANT) {
        this.CANT = CANT;
    }

    public String getUNI_VENTA() {
        return UNI_VENTA;
    }

    public void setUNI_VENTA(String UNI_VENTA) {
        this.UNI_VENTA = UNI_VENTA;
    }

    public String getDESCR() {
        return DESCR;
    }

    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }

    public String getCTRL_ALM() {
        return CTRL_ALM;
    }

    public void setCTRL_ALM(String CTRL_ALM) {
        this.CTRL_ALM = CTRL_ALM;
    }

    public String getLOTE() {
        return LOTE;
    }

    public void setLOTE(String LOTE) {
        this.LOTE = LOTE;
    }

}
