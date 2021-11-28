package com.example.transportezaragozaapi.tranvia;

public class Tranvia {

    private final String idTranvia;
    private final String tituloTranvia;
    private String destinoTranvia1;
    private Integer minutosTranvia1;
    private String destinoTranvia2;
    private Integer minutosTranvia2;
    private final String iconoTranvia;

    public Tranvia(String idTranvia, String tituloTranvia, String destinoTranvia1, Integer minutosTranvia1,
                   String destinoTranvia2, Integer minutosTranvia2, String iconoTranvia) {
        this.idTranvia = idTranvia;
        this.tituloTranvia = tituloTranvia;
        this.destinoTranvia1 = destinoTranvia1;
        this.minutosTranvia1 = minutosTranvia1;
        this.destinoTranvia2 = destinoTranvia2;
        this.minutosTranvia2 = minutosTranvia2;
        this.iconoTranvia = iconoTranvia;
    }

    public String getIdTranvia() {
        return idTranvia;
    }

    public String getTituloTranvia() {
        return tituloTranvia;
    }

    public String getDestinoTranvia1() {
        return destinoTranvia1;
    }

    public void setDestinoTranvia1(String destinoTranvia1) {
        this.destinoTranvia1 = destinoTranvia1;
    }

    public Integer getMinutosTranvia1() {
        return minutosTranvia1;
    }

    public void setMinutosTranvia1(Integer minutosTranvia1) {
        this.minutosTranvia1 = minutosTranvia1;
    }

    public String getDestinoTranvia2() {
        return destinoTranvia2;
    }

    public void setDestinoTranvia2(String destinoTranvia2) {
        this.destinoTranvia2 = destinoTranvia2;
    }

    public Integer getMinutosTranvia2() {
        return minutosTranvia2;
    }

    public void setMinutosTranvia2(Integer minutosTranvia2) {
        this.minutosTranvia2 = minutosTranvia2;
    }

    public String getIconoTranvia() {
        return iconoTranvia;
    }
}
