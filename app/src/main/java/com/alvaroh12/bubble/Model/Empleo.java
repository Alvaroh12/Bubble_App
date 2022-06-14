package com.alvaroh12.bubble.Model;

public class Empleo {

    private int id_empleo;
    private int isAceptado;
    private int isCancelado;
    private double id_oferta;
    private int id_usuario_comprador;
    private int id_usuario_oferente;

    public void Empleo(){

    }

    public Empleo(int id_empleo, int isAceptado, int isCancelado, double id_oferta, int id_usuario_comprador,
                  int id_usuario_oferente) {
        this.id_empleo = id_empleo;
        this.isAceptado = isAceptado;
        this.isCancelado = isCancelado;
        this.id_oferta = id_oferta;
        this.id_usuario_comprador = id_usuario_comprador;
        this.id_usuario_oferente = id_usuario_oferente;
    }

    public Empleo(int isAceptado, int isCancelado, double id_oferta, int id_usuario_comprador,
                  int id_usuario_oferente) {
        this.isAceptado = isAceptado;
        this.isCancelado = isCancelado;
        this.id_oferta = id_oferta;
        this.id_usuario_comprador = id_usuario_comprador;
        this.id_usuario_oferente = id_usuario_oferente;
    }


    public int getId_empleo() {
        return id_empleo;
    }

    public void setId_empleo(int id_empleo) {
        this.id_empleo = id_empleo;
    }

    public int getIsAceptado() {
        return isAceptado;
    }

    public void setIsAceptado(int isAceptado) {
        this.isAceptado = isAceptado;
    }

    public int getIsCancelado() {
        return isCancelado;
    }

    public void setIsCancelado(int isCancelado) {
        this.isCancelado = isCancelado;
    }

    public double getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(double id_oferta) {
        this.id_oferta = id_oferta;
    }

    public int getId_usuario_comprador() {
        return id_usuario_comprador;
    }

    public void setId_usuario_comprador(int id_usuario_comprador) {
        this.id_usuario_comprador = id_usuario_comprador;
    }

    public int getId_usuario_oferente() {
        return id_usuario_oferente;
    }

    public void setId_usuario_oferente(int id_usuario_oferente) {
        this.id_usuario_oferente = id_usuario_oferente;
    }

}
