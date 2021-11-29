package com.example.transportezaragozaapi.bici;

public class Bici {

    private final String tituloBici;
    private final String idBici;
    private final String ultActualizacionBici;
    private final Integer bicisDisponibles;
    private final Integer anclajesDisponibles;

    public Bici(String titulo, String id, String ultimaActualizacion, int bicisDisponibles, int anclajesDisponibles) {
        this.tituloBici = titulo;
        this.idBici = id;
        this.ultActualizacionBici = ultimaActualizacion;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
    }


    public String getTituloBici() {
        return tituloBici;
    }

    public String getIdBici() {
        return idBici;
    }

    public String getUltActualizacionBici() {
        return ultActualizacionBici;
    }

    public Integer getBicisDisponibles() {
        return bicisDisponibles;
    }

    public Integer getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

}