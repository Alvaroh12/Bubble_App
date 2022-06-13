package com.alvaroh12.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        //qui recibiremos el objeto seleccionado en HomeFragment  y podremos ver la informacion de la oferta
        //al darle a comprar nos llevara al correo para hablar con el oferente
        // DESPUES HACER OTRO ACTIVITY IGUAL PARA VER QUIEN TE LO COMPRA Y OTRO SOLO PRA VER TUS OFERTAS

    }
}