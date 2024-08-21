package com.dmjsistemas.model;

import java.util.Date;

public class EncabezadosR {

    private String CVE_DOC;
    private String DOC_ANT;
    private String CVE_CLPV;
    private Date FECHA_APLI;
    private double CAN_TOT;
    private String RFC;
    private String NOMBRE;
    private String CALLE;
    private String COLONIA;
    private String ESTADO;
    private String CODIGO;
    private String TELEFONO;
    private String STR_OBS;

    public EncabezadosR() {
    }

    public EncabezadosR(String CVE_DOC, String DOC_ANT, String CVE_CLPV, Date FECHA_APLI, double CAN_TOT, String RFC, String NOMBRE, String CALLE, String COLONIA, String ESTADO, String CODIGO, String TELEFONO, String STR_OBS) {
        this.CVE_DOC = CVE_DOC;
        this.DOC_ANT = DOC_ANT;
        this.CVE_CLPV = CVE_CLPV;
        this.FECHA_APLI = FECHA_APLI;
        this.CAN_TOT = CAN_TOT;
        this.RFC = RFC;
        this.NOMBRE = NOMBRE;
        this.CALLE = CALLE;
        this.COLONIA = COLONIA;
        this.ESTADO = ESTADO;
        this.CODIGO = CODIGO;
        this.TELEFONO = TELEFONO;
        this.STR_OBS = STR_OBS;
    }

    public String getCVE_DOC() {
        return CVE_DOC;
    }

    public void setCVE_DOC(String CVE_DOC) {
        this.CVE_DOC = CVE_DOC;
    }

    public String getDOC_ANT() {
        return DOC_ANT;
    }

    public void setDOC_ANT(String DOC_ANT) {
        this.DOC_ANT = DOC_ANT;
    }

    public String getCVE_CLPV() {
        return CVE_CLPV;
    }

    public void setCVE_CLPV(String CVE_CLPV) {
        this.CVE_CLPV = CVE_CLPV;
    }

    public Date getFECHA_APLI() {
        return FECHA_APLI;
    }

    public void setFECHA_APLI(Date FECHA_APLI) {
        this.FECHA_APLI = FECHA_APLI;
    }

    public double getCAN_TOT() {
        return CAN_TOT;
    }

    public void setCAN_TOT(double CAN_TOT) {
        this.CAN_TOT = CAN_TOT;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getSTR_OBS() {
        return STR_OBS;
    }

    public void setSTR_OBS(String STR_OBS) {
        this.STR_OBS = STR_OBS;
    }

}
