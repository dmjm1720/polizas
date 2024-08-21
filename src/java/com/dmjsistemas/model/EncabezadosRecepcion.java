package com.dmjsistemas.model;
// Generated 17/08/2024 10:40:37 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ENCABEZADOS_RECEPCION", schema = "dbo", catalog = "polizas")
public class EncabezadosRecepcion implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "CVE_DOC", length = 50)
    private String cveDoc;

    @Column(name = "DOC_ANT", length = 50)
    private String docAnt;

    @Column(name = "CVE_CLPV", length = 50)
    private String cveClpv;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_APLI", length = 23)
    private Date fechaApli;

    @Column(name = "CAN_TOT", precision = 53, scale = 0)
    private Double canTot;

    @Column(name = "RFC", length = 50)
    private String rfc;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CALLE", length = 120)
    private String calle;

    @Column(name = "COLONIA", length = 50)
    private String colonia;

    @Column(name = "ESTADO", length = 50)
    private String estado;

    @Column(name = "CODIGO", length = 50)
    private String codigo;

    @Column(name = "TELEFONO", length = 50)
    private String telefono;

    @Column(name = "STR_OBS", length = 50)
    private String strObs;

    @Column(name = "NUMEXT", length = 255)
    private String numext;

    @Column(name = "LOCALIDAD", length = 255)
    private String localidad;

    @Column(name = "MUNICIPIO", length = 255)
    private String municipio;

    @Column(name = "TOTAL_LETRA", length = 255)
    private String totalLetra;

    public EncabezadosRecepcion() {
    }

    public EncabezadosRecepcion(int id) {
        this.id = id;
    }

    public EncabezadosRecepcion(String cveDoc) {
        this.cveDoc = cveDoc;
    }

    public EncabezadosRecepcion(int id, String cveDoc, String docAnt, String cveClpv, Date fechaApli, Double canTot, String rfc, String nombre, String calle, String colonia, String estado, String codigo, String telefono, String strObs, String numext, String localidad, String municipio, String totalLetra) {
        this.id = id;
        this.cveDoc = cveDoc;
        this.docAnt = docAnt;
        this.cveClpv = cveClpv;
        this.fechaApli = fechaApli;
        this.canTot = canTot;
        this.rfc = rfc;
        this.nombre = nombre;
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.codigo = codigo;
        this.telefono = telefono;
        this.strObs = strObs;
        this.numext = numext;
        this.localidad = localidad;
        this.municipio = municipio;
        this.totalLetra = totalLetra;
    }

    public EncabezadosRecepcion(String cveDoc, String docAnt, String cveClpv, Date fechaApli, Double canTot, String rfc, String nombre, String calle, String colonia, String estado, String codigo, String telefono, String strObs, String numext, String localidad, String municipio, String totalLetra) {
        this.cveDoc = cveDoc;
        this.docAnt = docAnt;
        this.cveClpv = cveClpv;
        this.fechaApli = fechaApli;
        this.canTot = canTot;
        this.rfc = rfc;
        this.nombre = nombre;
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.codigo = codigo;
        this.telefono = telefono;
        this.strObs = strObs;
        this.numext = numext;
        this.localidad = localidad;
        this.municipio = municipio;
        this.totalLetra = totalLetra;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCveDoc() {
        return this.cveDoc;
    }

    public void setCveDoc(String cveDoc) {
        this.cveDoc = cveDoc;
    }

    public String getDocAnt() {
        return this.docAnt;
    }

    public void setDocAnt(String docAnt) {
        this.docAnt = docAnt;
    }

    public String getCveClpv() {
        return this.cveClpv;
    }

    public void setCveClpv(String cveClpv) {
        this.cveClpv = cveClpv;
    }

    public Date getFechaApli() {
        return this.fechaApli;
    }

    public void setFechaApli(Date fechaApli) {
        this.fechaApli = fechaApli;
    }

    public Double getCanTot() {
        return this.canTot;
    }

    public void setCanTot(Double canTot) {
        this.canTot = canTot;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getStrObs() {
        return this.strObs;
    }

    public void setStrObs(String strObs) {
        this.strObs = strObs;
    }

    public String getNumext() {
        return numext;
    }

    public void setNumext(String numext) {
        this.numext = numext;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTotalLetra() {
        return totalLetra;
    }

    public void setTotalLetra(String totalLetra) {
        this.totalLetra = totalLetra;
    }

    
}
