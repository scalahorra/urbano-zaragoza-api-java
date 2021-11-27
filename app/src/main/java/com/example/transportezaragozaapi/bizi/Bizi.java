package com.example.transportezaragozaapi.bizi;

public class Bizi {

    private String title, icon, id;
    private Integer bicisDisponibles, anclajesDisponibles;

    public Bizi(String title, String id, String icon) {
        this.title = title;
        this.id = id;
        this.icon = icon;
    }

    public Bizi(String title, String id, int bicisDisponibles, int anclajesDisponibles, String icon) {
        this.title = title;
        this.id = id;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
        this.icon = icon;
    }


    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
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