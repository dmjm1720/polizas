package com.dmjsistemas.util;

public class Configuracion {

    //**Windows**//
//    private static final String conexDBSae = "jdbc:sqlserver://192.168.1.37\\SQLEXPRESS;databaseName=SAE90Empre01";
//    private static final String conexDBCoi = "jdbc:sqlserver://192.168.1.37\\SQLEXPRESS;databaseName=COI10Empre1";
//    private static final String conexDBPolizas = "jdbc:sqlserver://192.168.0.11;databaseName=polizas";
//    private static final String conexUser = "sa";
//    private static final String conexPwd = "Aspel**2013";
//    private static final String conexPwd1 = "S1st3m4S*Duch3";
//// 

    private static final String conexDBSae = "jdbc:sqlserver://192.168.1.7;databaseName=SAE90Empre01";
    private static final String conexDBCoi = "jdbc:sqlserver://192.168.1.7;databaseName=COI10EMPRE1";
    private static final String conexDBPolizas = "jdbc:sqlserver://192.168.1.7;databaseName=polizas";
    private static final String conexUser = "sa";
    private static final String conexPwd = "Pr4xi5A5a*";
    private static final String conexPwd1 = "Pr4xi5A5a*";

    //**Linux**//
    public static String getConexDBSae() {
        return conexDBSae;
    }

    public static String getConexDBCoi() {
        return conexDBCoi;
    }

    public static String getConexUser() {
        return conexUser;
    }

    public static String getConexPwd() {
        return conexPwd;
    }

    public static String getConexDBPolizas() {
        return conexDBPolizas;
    }

    public static String getConexPwd1() {
        return conexPwd1;
    }
    
//    

}
