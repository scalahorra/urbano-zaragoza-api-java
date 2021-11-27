package com.example.transportezaragozaapi.bizi;

public class Bizi {

    private final String titulo;
    private final String icono;
    private final String id;
    private final String ultimaActualizacion;
    private final Integer bicisDisponibles;
    private final Integer anclajesDisponibles;

    public Bizi(String titulo, String id, String icono, String ultimaActualizacion, int bicisDisponibles, int anclajesDisponibles) {
        this.titulo = titulo;
        this.id = id;
        this.icono = icono;
        this.ultimaActualizacion = ultimaActualizacion;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getIcono() {
        return icono;
    }

    public String getId() {
        return id;
    }

    public String getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public Integer getBicisDisponibles() {
        return bicisDisponibles;
    }

    public Integer getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

}