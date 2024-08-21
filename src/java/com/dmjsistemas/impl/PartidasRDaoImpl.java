package com.dmjsistemas.impl;

import com.dmjsistemas.dao.IPartidasRDao;
import com.dmjsistemas.model.PartidasRecepcion;
import com.dmjsistemas.util.Conexion;
import com.dmjsistemas.util.HibernateUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class PartidasRDaoImpl extends Conexion implements IPartidasRDao {

    @Override
    @SuppressWarnings({"null", "ConvertToTryWithResources"})
    public void insertarPartidaRecpcion(PartidasRecepcion pr) {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pr);
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
    @SuppressWarnings("null")
    public List<PartidasRecepcion> listaPartidasRecepcion(String factura) {
        List<PartidasRecepcion> lista = new ArrayList<>();
        try {

            PartidasRecepcion pr = new PartidasRecepcion();

            ConectarSae();
            Statement st = getCnSae().createStatement();
            ResultSet rs = st.executeQuery("SELECT FR.DOC_ANT,  FR.CVE_DOC, FR.FECHA_ENT, PR.E_LTPD, PR.CVE_ART, PR.CANT, PR.UNI_VENTA, INV.DESCR, INV.CTRL_ALM, PR.PREC, PR.TOT_PARTIDA "
                    + "FROM FACTR01 FR "
                    + "INNER JOIN "
                    + "PAR_FACTR01 PR ON FR.CVE_DOC = PR.CVE_DOC "
                    + "INNER JOIN "
                    + "INVE01 INV ON PR.CVE_ART = INV.CVE_ART "
                    + "WHERE  FR.CVE_DOC ='" + factura + "' ");

            if (!rs.isBeforeFirst()) {
            } else {

                while (rs.next()) {
                    System.out.println("Datos: " + rs.getString(1));
                    pr.setDocAnt(rs.getString("DOC_ANT"));
                    pr.setCveDoc(rs.getString("CVE_DOC"));
                    pr.setFechaEnt(rs.getDate("FECHA_ENT"));
                    pr.setELtpd(rs.getInt("E_LTPD"));
                    pr.setCveArt(rs.getString("CVE_ART"));
                    pr.setCant(rs.getDouble("CANT"));
                    pr.setUniVenta(rs.getString("UNI_VENTA"));
                    pr.setDescr(rs.getString("DESCR"));
                    pr.setCtrlAlm(rs.getString("CTRL_ALM"));
                    pr.setPrec(rs.getDouble("PREC"));
                    pr.setTotPartida(rs.getDouble("TOT_PARTIDA"));

                    lista.add(pr);
                    pr = new PartidasRecepcion();
                }
            }

            CerrarSae();

        } catch (SQLException ex) {
            Logger.getLogger(PartidasRDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String buscarLote(int lote) throws SQLException {
        String Nolote = null;

        ConectarSae();

        Statement st = getCnSae().createStatement();
        ResultSet rs = st.executeQuery("SELECT LTPD.LOTE "
                + "FROM "
                + "ENLACE_LTPD01 ELTPD "
                + "INNER JOIN "
                + "LTPD01 LTPD ON ELTPD.REG_LTPD = LTPD.REG_LTPD "
                + "WHERE ELTPD.E_LTPD='" + lote + "';");

        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                Nolote = rs.getString(1);
            }

        } else {
            Nolote = "0";
        }

        CerrarSae();

        return Nolote;

    }

}
