
package com.dmjsistemas.dao;

import com.dmjsistemas.model.EncabezadosRecepcion;
import java.sql.SQLException;
import java.util.List;


public interface IEncabezadosRDao {
    
       public void insertarEncabezadoRecepcion(EncabezadosRecepcion er);
       
       public List<EncabezadosRecepcion> listaFacturaExiste(String factura);
       
       public List<EncabezadosRecepcion> obtenerInfoEncabezadosRecepcion(String fec1, String fec2, String rfc) throws SQLException;
       
       public List<EncabezadosRecepcion> obtenerInfoEncabezadosRecepcionXF(String factura) throws SQLException;
       
       public String  obtenerInfoOBS(String cveOBS) throws SQLException;
       
       public List<EncabezadosRecepcion> listaFRP(String factura);
       
       public List<EncabezadosRecepcion> listaFRP_PDF(String factura);
       
}
