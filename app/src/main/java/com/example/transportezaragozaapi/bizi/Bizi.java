package com.example.transportezaragozaapi.bizi;

public class Bizi {

    private final String tituloBici;
    private final String iconoBici;
    private final String idBici;
    private final String ultActualizacionBici;
    private final Integer bicisDisponibles;
    private final Integer anclajesDisponibles;

    public Bizi(String titulo, String id, String icono, String ultimaActualizacion, int bicisDisponibles, int anclajesDisponibles) {
        this.tituloBici = titulo;
        this.idBici = id;
        this.iconoBici = icono;
        this.ultActualizacionBici = ultimaActualizacion;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
    }


    public String getTituloBici() {
        return tituloBici;
    }

    public String getIconoBici() {
        return iconoBici;
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