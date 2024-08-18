package com.dmjsistemas.impl;

import com.dmjsistemas.dao.IRutasDao;
import com.dmjsistemas.model.Rutas;
import com.dmjsistemas.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RutasDaoImpl implements IRutasDao {

    @Override
    public String pathPDFCobranza(String nombre) {

        String path = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "SELECT path FROM Rutas WHERE nombre=:nombre";
        try {
            Query q = session.createQuery(hql);
            q.setParameter("nombre", nombre);
            path = (String) q.setMaxResults(1).uniqueResult();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            t.rollback();
        }
        return path;
    }

}
