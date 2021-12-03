package com.example.transportezaragozaapi.bus;

public class Poste {

    private String urlPoste;
    private String tituloPoste;


    public Poste(String urlPoste, String tituloPoste) {
        this.urlPoste = urlPoste;
        this.tituloPoste = tituloPoste;
    }


    public String getUrlPoste() {
        return urlPoste;
    }

    public void setUrlPoste(String urlPoste) {
        this.urlPoste = urlPoste;
    }

    public String getTituloPoste() {
        return tituloPoste;
    }

    public void setTituloPoste(String tituloPoste) {
        this.tituloPoste = tituloPoste;
    }
}
