package com.alvaroh12.bubble.Model;

public class Oferta {

    private int id_oferta;
    private String tipo_oferta;
    private String descripcion;
    private double precio;
    private int id_usuario;
    private int categoria;

    public void Oferta(){

    }

    public Oferta(int id_oferta, String tipo_oferta, String descripcion, double precio, int id_usuario, int categoria) {
        this.id_oferta = id_oferta;
        this.tipo_oferta = tipo_oferta;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_usuario = id_usuario;
        this.categoria = categoria;
    }


    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public String getTipo_oferta() {
        return tipo_oferta;
    }

    public void setTipo_oferta(String tipo_oferta) {
        this.tipo_oferta = tipo_oferta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
