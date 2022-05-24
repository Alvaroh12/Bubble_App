package com.alvaroh12.bubble;

import java.io.Serializable;

public class StaticRvModel implements Serializable {// Modelo parte de Arriba cambiar por el de Usuario

    private int image;
    private String txt;

    public StaticRvModel(int image, String txt) {
        this.image = image;
        this.txt = txt;
    }

    public int getImage() {
        return image;
    }

    public String getTxt() {
        return txt;
    }
}
