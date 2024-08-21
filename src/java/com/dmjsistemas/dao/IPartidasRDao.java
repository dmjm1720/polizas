package com.dmjsistemas.dao;

import com.dmjsistemas.model.PartidasRecepcion;
import java.sql.SQLException;
import java.util.List;

public interface IPartidasRDao {

    public void insertarPartidaRecpcion(PartidasRecepcion pr);

    public List<PartidasRecepcion> listaPartidasRecepcion(String factura) throws SQLException;

    public String buscarLote(int lote) throws SQLException;
}
