package com.alvaroh12.bubble;

public class DynamicRVModel {

    int id_oferta;
    String tipo_oferta;
    String categoria;
    String nombre;
    double precio;
    String descripcion;
    String correo;
    int id_usuario;



    public DynamicRVModel(int id_oferta,String tipo_oferta, String categoria, double precio, String nombre, String descripcion, String correo, int id_usuario) {
        this.id_oferta = id_oferta;
        this.tipo_oferta = tipo_oferta;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.correo = correo;
        this.id_usuario = id_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
