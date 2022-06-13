package com.alvaroh12.bubble;

public class DynamicRVModel {

    String tipo_oferta;
    String categoria;
    String nombre;
    double precio;



    public DynamicRVModel(String tipo_oferta, String categoria, double precio, String nombre) {
        this.tipo_oferta = tipo_oferta;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getTipo_oferta() {
        return tipo_oferta;
    }

    public void setTipo_oferta(String tipo_oferta) {
        this.tipo_oferta = tipo_oferta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
