package com.example.transportezaragozaapi.bus;

public class Poste {

    private String urlPoste;
    private String idPoste;
    private String tituloPoste;


    public Poste(String urlPoste, String idPoste, String tituloPoste) {
        this.urlPoste = urlPoste;
        this.idPoste = idPoste;
        this.tituloPoste = tituloPoste;
    }


    public String getUrlPoste() {
        return urlPoste;
    }

    public void setUrlPoste(String urlPoste) {
        this.urlPoste = urlPoste;
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
