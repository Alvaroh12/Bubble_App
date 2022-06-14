package com.alvaroh12.bubble;

public class DynamicRVModelCompra {

    String tipo_oferta;
    String descripcion;
    String nombre;
    String categoria;
    Double precio;
    int isAceptado;
    int id_empleo;


    public DynamicRVModelCompra(String tipo_oferta, String descripcion, String nombre,
                                String categoria, Double precio, int isAceptado, int id_empleo) {
        this.tipo_oferta = tipo_oferta;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.isAceptado = isAceptado;
        this.id_empleo = id_empleo;
    }

    public int getId_empleo() {
        return id_empleo;
    }

    public void setId_empleo(int id_empleo) {
        this.id_empleo = id_empleo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getIsAceptado() {
        return isAceptado;
    }

    public void setIsAceptado(int isAceptado) {
        this.isAceptado = isAceptado;
    }
}
