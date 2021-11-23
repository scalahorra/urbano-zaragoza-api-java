package com.example.transportezaragozaapi.bizi;

public class Bizi {

    private String title, icon, id, lastUpdate;
    private Integer bicisDisponibles, anclajesDisponibles;

    public Bizi(String title, String id, int bicisDisponibles, int anclajesDisponibles, String icon, String lastUpdate) {
        this.title = title;
        this.id = id;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
        this.icon = icon;
        this.lastUpdate = lastUpdate;
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

    public String getLastUpdate(){
        return lastUpdate;
    }

}
