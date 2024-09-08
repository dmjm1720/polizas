package com.dmjsistemas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection cnSae;
    private Connection cnCoi;
    private Connection cnPol;

    public Connection getCnSae() {
        return cnSae;
    }

    public void setCnSae(Connection cnSae) {
        this.cnSae = cnSae;
    }

    public Connection getCnCoi() {
        return cnCoi;
    }

    public void setCnCoi(Connection cnCoi) {
        this.cnCoi = cnCoi;
    }

    public Connection getCnPol() {
        return cnPol;
    }

    public void setCnPol(Connection cnPol) {
        this.cnPol = cnPol;
    }
    
    

    public void ConectarSae() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cnSae = DriverManager.getConnection(Configuracion.getConexDBSae(), Configuracion.getConexUser(), Configuracion.getConexPwd());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CerrarSae() throws SQLException {
        try {
            if ((this.cnSae != null)
                    && (!this.cnSae.isClosed())) {
                this.cnSae.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    public void ConectarCoi() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cnCoi = DriverManager.getConnection(Configuracion.getConexDBCoi(), Configuracion.getConexUser(), Configuracion.getConexPwd());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CerrarCoi() throws SQLException {
        try {
            if ((this.cnCoi != null)
                    && (!this.cnCoi.isClosed())) {
                this.cnCoi.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    public void ConectarPolizas() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cnPol = DriverManager.getConnection(Configuracion.getConexDBPolizas(), Configuracion.getConexUser(), Configuracion.getConexPwd1());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CerrarPolizas() throws SQLException {
        try {
            if ((this.cnPol != null)
                    && (!this.cnPol.isClosed())) {
                this.cnPol.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}
