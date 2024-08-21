package com.dmjsistemas.impl;

import com.dmjsistemas.dao.IEncabezadosRDao;
import com.dmjsistemas.dao.IRutasDao;
import com.dmjsistemas.model.EncabezadosRecepcion;
import com.dmjsistemas.util.Conexion;
import com.dmjsistemas.util.HibernateUtil;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EncabezadoRecepcionImpl extends Conexion implements IEncabezadosRDao {

    @Override
    @SuppressWarnings({"ConvertToTryWithResources", "null"})
    public void insertarEncabezadoRecepcion(EncabezadosRecepcion er) {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(er);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<EncabezadosRecepcion> listaFacturaExiste(String factura) {
        List<EncabezadosRecepcion> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM EncabezadosRecepcion WHERE cveDoc='" + factura + "'";
        try {
            lista = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return lista;
    }

    @Override
    public List<EncabezadosRecepcion> obtenerInfoEncabezadosRecepcion(String fec1, String fec2, String rfc) throws SQLException {

        List<EncabezadosRecepcion> listaValidacionFacturaR = new ArrayList<>();
        EncabezadosRecepcion er = new EncabezadosRecepcion();

        ConectarSae();
        Statement st = getCnSae().createStatement();
        ResultSet rs = st.executeQuery("SELECT F.CVE_DOC, F.DOC_ANT, F.CVE_CLPV, CT.FECHA_APLI, F.CAN_TOT, F.RFC, "
                + "C.NOMBRE, C.CALLE, C.COLONIA, C.ESTADO, C.CODIGO, C.TELEFONO, C.CVE_OBS, C.NUMEXT, C.LOCALIDAD, C.MUNICIPIO "
                + "FROM FACTF01 F "
                + "INNER JOIN "
                + "CUEN_DET01 CT ON F.CVE_DOC = CT.NO_FACTURA "
                + "INNER JOIN "
                + "CLIE01 C ON F.CVE_CLPV = C.CLAVE "
                + "WHERE F.STATUS <> 'C' AND CT.REF_SIST='B'  AND CT.FECHA_APLI BETWEEN '" + fec1 + "' AND '" + fec2 + "' AND F.RFC='" + rfc + "'");

        if (!rs.isBeforeFirst()) {

        } else {
            int num = 1;
            while (rs.next()) {

                System.out.println("FACTURAS ENCONTRADAS:" + num + " " + rs.getString(1));
                er.setCveDoc(rs.getString("CVE_DOC"));
                er.setDocAnt(rs.getString("DOC_ANT"));
                er.setCveClpv(rs.getString("CVE_CLPV"));
                er.setFechaApli(rs.getDate("FECHA_APLI"));
                er.setCanTot(rs.getDouble("CAN_TOT"));
                er.setRfc(rs.getString("RFC"));
                er.setNombre(rs.getString("NOMBRE"));
                er.setCalle(rs.getString("CALLE"));
                er.setColonia(rs.getString("COLONIA"));
                er.setEstado(rs.getString("ESTADO"));
                er.setCodigo(rs.getString("CODIGO"));
                er.setTelefono(rs.getString("TELEFONO"));
                er.setStrObs(rs.getString("CVE_OBS"));
                er.setNumext(rs.getString("NUMEXT"));
                er.setLocalidad(rs.getString("LOCALIDAD"));
                er.setMunicipio(rs.getString("MUNICIPIO"));

                listaValidacionFacturaR.add(er);
                er = new EncabezadosRecepcion();
                num++;
            }
        }

        CerrarSae();
        return listaValidacionFacturaR;
    }

    @Override
    public String obtenerInfoOBS(String cveOBS) throws SQLException {
        String claveObs = null;
        ConectarSae();

        Statement st = getCnSae().createStatement();
        ResultSet rs = st.executeQuery("SELECT STR_OBS FROM OCLI01 WHERE CVE_OBS='" + cveOBS + "'");

        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                claveObs = rs.getString(1);
            }
        } else {
            claveObs = "0";
        }

        CerrarSae();

        return claveObs;
    }

    @Override
    public List<EncabezadosRecepcion> listaFRP(String factura) {
        List<EncabezadosRecepcion> lista = new ArrayList<>();
        List<EncabezadosRecepcion> listaFRP = new ArrayList<>();
        EncabezadosRecepcion er = new EncabezadosRecepcion();

        try {

            ConectarSae();

            Statement st = getCnSae().createStatement();
            ResultSet rs = st.executeQuery("SELECT F.CVE_DOC AS FACTURA, FR.CVE_DOC AS REMISION, FR.DOC_ANT AS PEDIDO "
                    + "FROM FACTF01 F INNER JOIN "
                    + "FACTR01 FR ON F.DOC_ANT = FR.CVE_DOC "
                    + "WHERE F.CVE_DOC ='" + factura + "'");

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    er.setCveDoc(rs.getString("FACTURA"));
                    er.setDocAnt(rs.getString("REMISION"));
                    er.setColonia(rs.getString("PEDIDO"));

                    lista.add(er);
                    er = new EncabezadosRecepcion();
                }
            }
            EncabezadosRecepcion encabezado = new EncabezadosRecepcion();
            for (int i = 0; i < lista.size(); i++) {
                if (i == 0) {

                    encabezado = new EncabezadosRecepcion();
                    encabezado.setCveDoc("CDU590909BQ3F" + lista.get(i).getCveDoc().trim());
                    listaFRP.add(encabezado);

                    encabezado = new EncabezadosRecepcion();
                    encabezado.setCveDoc("REMISION-" + lista.get(i).getDocAnt().trim());
                    listaFRP.add(encabezado);

                    encabezado = new EncabezadosRecepcion();
                    encabezado.setCveDoc("PEDIDO-" + lista.get(i).getColonia().trim());
                    listaFRP.add(encabezado);

                }
            }

            CerrarSae();

        } catch (SQLException ex) {
            Logger.getLogger(EncabezadoRecepcionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFRP;
    }

    public void generarPDFRemision(String documento, String rem, String ruta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException {

        ConectarPolizas();
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            //se carga el reporte
            Map parameter = new HashMap();
            parameter.put("documento", documento);

            URL in = this.getClass().getResource("/com/dmjsistemas/util/Remision.jasper");

            jasperReport = (JasperReport) JRLoader.loadObject(in);
            //se procesa el archivo jasper
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, this.getCnPol());
            //se crea el archivo PDF

            JasperExportManager.exportReportToPdfFile(jasperPrint, ruta + "REMISION-" + rem.trim() + ".pdf");

        } catch (JRException ex) {
            System.err.println("Error iReport: " + ex.getMessage());
        }
        CerrarPolizas();
    }

    public void generarPDFPedido(String documento, String ped, String ruta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException {

        ConectarPolizas();
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            //se carga el reporte
            Map parameter = new HashMap();
            parameter.put("documento", documento);

            URL in = this.getClass().getResource("/com/dmjsistemas/util/Pedido.jasper");

            jasperReport = (JasperReport) JRLoader.loadObject(in);
            //se procesa el archivo jasper
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, this.getCnPol());
            //se crea el archivo PDF

            JasperExportManager.exportReportToPdfFile(jasperPrint, ruta + "PEDIDO-" + ped.trim() + ".pdf");

        } catch (JRException ex) {
            System.err.println("Error iReport: " + ex.getMessage());
        }
        CerrarPolizas();
    }

    @Override
    public List<EncabezadosRecepcion> listaFRP_PDF(String factura) {
        List<EncabezadosRecepcion> lista = new ArrayList<>();
        List<EncabezadosRecepcion> listaFRP = new ArrayList<>();
        EncabezadosRecepcion er = new EncabezadosRecepcion();
        IRutasDao ruta = new RutasDaoImpl();
        String rutaRemision = ruta.pathPDFCobranza("ruta_pdf_remision");

        IRutasDao ruta2 = new RutasDaoImpl();
        String rutaPedido = ruta2.pathPDFCobranza("ruta_pdf_pedido");

        try {

            ConectarSae();

            Statement st = getCnSae().createStatement();
            ResultSet rs = st.executeQuery("SELECT F.CVE_DOC AS FACTURA, FR.CVE_DOC AS REMISION, FR.DOC_ANT AS PEDIDO "
                    + "FROM FACTF01 F INNER JOIN "
                    + "FACTR01 FR ON F.DOC_ANT = FR.CVE_DOC "
                    + "WHERE F.CVE_DOC ='" + factura + "'");

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    er.setCveDoc(rs.getString("FACTURA"));
                    er.setDocAnt(rs.getString("REMISION"));
                    er.setColonia(rs.getString("PEDIDO"));

                    lista.add(er);
                    er = new EncabezadosRecepcion();
                }
            }
            EncabezadosRecepcion encabezado = new EncabezadosRecepcion();
            for (int i = 0; i < lista.size(); i++) {
                if (i == 0) {

                    encabezado = new EncabezadosRecepcion();
                    encabezado.setCveDoc("CDU590909BQ3F" + lista.get(i).getCveDoc().trim());
                    listaFRP.add(encabezado);

                    encabezado = new EncabezadosRecepcion();
                    encabezado.setCveDoc("REMISION-" + lista.get(i).getDocAnt().trim());
                    listaFRP.add(encabezado);

                    encabezado = new EncabezadosRecepcion();
                    encabezado.setCveDoc("PEDIDO-" + lista.get(i).getColonia().trim());
                    listaFRP.add(encabezado);

                    try {

                        generarPDFRemision(lista.get(i).getCveDoc(), lista.get(i).getDocAnt(), rutaRemision);

                        generarPDFPedido(lista.get(i).getCveDoc(), lista.get(i).getColonia(), rutaPedido);

                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | JRException ex) {
                        Logger.getLogger(EncabezadoRecepcionImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            CerrarSae();

        } catch (SQLException ex) {
            Logger.getLogger(EncabezadoRecepcionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFRP;
    }

}
