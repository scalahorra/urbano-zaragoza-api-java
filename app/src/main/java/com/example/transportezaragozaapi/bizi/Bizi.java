package com.example.transportezaragozaapi.bizi;

public class Bizi {

    private final String titulo;
    private final String icono;
    private final String id;
    private final Integer bicisDisponibles;
    private final Integer anclajesDisponibles;

    public Bizi(String titulo, String id, int bicisDisponibles, int anclajesDisponibles, String icono) {
        this.titulo = titulo;
        this.id = id;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
        this.icono = icono;
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

    public Integer getBicisDisponibles() {
        return bicisDisponibles;
    }

    public Integer getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

}