package com.dmjsistemas.model;

public class TempRuta {

    private String archivoSalida;

    public TempRuta() {
    }

    public TempRuta(String archivoSalida) {
        this.archivoSalida = archivoSalida;
    }

    public String getRutaTemp() {
        return archivoSalida;
    }

    public void setRutaTemp(String archivoSalida) {
        this.archivoSalida = archivoSalida;
    }
    
    

}
