package com.dmjsistemas.model;

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
@Table(name = "PARTIDAS_RECEPCION", schema = "dbo", catalog = "polizas")
public class PartidasRecepcion implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "DOC_ANT", length = 50)
    private String docAnt;

    @Column(name = "CVE_DOC", length = 50)
    private String cveDoc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ENT", length = 23)
    private Date fechaEnt;

    @Column(name = "E_LTPD")
    private Integer ELtpd;

    @Column(name = "CVE_ART", length = 50)
    private String cveArt;

    @Column(name = "CANT", precision = 53, scale = 0)
    private Double cant;

    @Column(name = "UNI_VENTA", length = 50)
    private String uniVenta;

    @Column(name = "DESCR", length = 50)
    private String descr;

    @Column(name = "CTRL_ALM", length = 50)
    private String ctrlAlm;

    @Column(name = "LOTE", length = 50)
    private String lote;

    @Column(name = "PREC", precision = 53, scale = 0)
    private Double prec;

    @Column(name = "TOT_PARTIDA", precision = 53, scale = 0)
    private Double totPartida;

    public PartidasRecepcion() {
    }

    public PartidasRecepcion(int id) {
        this.id = id;
    }

    public PartidasRecepcion(int id, String docAnt, String cveDoc, Date fechaEnt, Integer ELtpd, String cveArt, Double cant, String uniVenta, String descr, String ctrlAlm, String lote, Double prec, Double totPartida) {
        this.id = id;
        this.docAnt = docAnt;
        this.cveDoc = cveDoc;
        this.fechaEnt = fechaEnt;
        this.ELtpd = ELtpd;
        this.cveArt = cveArt;
        this.cant = cant;
        this.uniVenta = uniVenta;
        this.descr = descr;
        this.ctrlAlm = ctrlAlm;
        this.lote = lote;
        this.prec = prec;
        this.totPartida = totPartida;
    }

    public PartidasRecepcion(String docAnt, String cveDoc, Date fechaEnt, Integer ELtpd, String cveArt, Double cant, String uniVenta, String descr, String ctrlAlm, String lote, Double prec, Double totPartida) {
        this.docAnt = docAnt;
        this.cveDoc = cveDoc;
        this.fechaEnt = fechaEnt;
        this.ELtpd = ELtpd;
        this.cveArt = cveArt;
        this.cant = cant;
        this.uniVenta = uniVenta;
        this.descr = descr;
        this.ctrlAlm = ctrlAlm;
        this.lote = lote;
        this.prec = prec;
        this.totPartida = totPartida;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocAnt() {
        return this.docAnt;
    }

    public void setDocAnt(String docAnt) {
        this.docAnt = docAnt;
    }

    public String getCveDoc() {
        return this.cveDoc;
    }

    public void setCveDoc(String cveDoc) {
        this.cveDoc = cveDoc;
    }

    public Date getFechaEnt() {
        return this.fechaEnt;
    }

    public void setFechaEnt(Date fechaEnt) {
        this.fechaEnt = fechaEnt;
    }

    public Integer getELtpd() {
        return this.ELtpd;
    }

    public void setELtpd(Integer ELtpd) {
        this.ELtpd = ELtpd;
    }

    public String getCveArt() {
        return this.cveArt;
    }

    public void setCveArt(String cveArt) {
        this.cveArt = cveArt;
    }

    public Double getCant() {
        return this.cant;
    }

    public void setCant(Double cant) {
        this.cant = cant;
    }

    public String getUniVenta() {
        return this.uniVenta;
    }

    public void setUniVenta(String uniVenta) {
        this.uniVenta = uniVenta;
    }

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCtrlAlm() {
        return this.ctrlAlm;
    }

    public void setCtrlAlm(String ctrlAlm) {
        this.ctrlAlm = ctrlAlm;
    }

    public String getLote() {
        return this.lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getPrec() {
        return prec;
    }

    public void setPrec(Double prec) {
        this.prec = prec;
    }

    public Double getTotPartida() {
        return totPartida;
    }

    public void setTotPartida(Double totPartida) {
        this.totPartida = totPartida;
    }

}
