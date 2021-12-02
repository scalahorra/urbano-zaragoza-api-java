package com.example.transportezaragozaapi.bus;

public class Poste {

    private String idPoste;
    private String tituloPoste;


    public Poste(String idPoste, String tituloPoste) {
        this.idPoste = idPoste;
        this.tituloPoste = tituloPoste;
    }


    public String getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(String idPoste) {
        this.idPoste = idPoste;
    }

    public String getTituloPoste() {
        return tituloPoste;
    }

    public void setTituloPoste(String tituloPoste) {
        this.tituloPoste = tituloPoste;
    }
}
