package com.alvaroh12.bubble;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CompraActivityProfile extends AppCompatActivity {

    int estado;String estad;
    int id_oferta;
    int id_usuario;
    String tipo_oferta;
    String nombre;
    String categoria;
    String descripcion;
    Double precio;

    int idUsuarioOferente;

    TextView nombreUsuarioOferta;
    TextView tipoOferta;
    TextView categoriaTV;
    TextView descripcionTV;
    TextView precioTV;
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_profile);

        estado = getIntent().getIntExtra("estado", estado);
        if (estado==0){
            estad="En Espera";
        }else{
            estad="Aceptado";
        }
        id_usuario = getIntent().getIntExtra("id_user", id_usuario);
        id_oferta = getIntent().getIntExtra("id_oferta", id_oferta);
        tipo_oferta = getIntent().getStringExtra("tipo_oferta");
        nombre = getIntent().getStringExtra("nombreOferente");
        categoria = getIntent().getStringExtra("categoria");
        descripcion = getIntent().getStringExtra("descripcion");
        precio = getIntent().getDoubleExtra("precio", 0.0);

        idUsuarioOferente = getIntent().getIntExtra("idOferente", id_usuario);


        nombreUsuarioOferta = findViewById(R.id.userCompraProfile);
        nombreUsuarioOferta.setText("Creador: " + nombre);

        tipoOferta = findViewById(R.id.tipoOfertaCompraProfile);
        tipoOferta.setText(tipo_oferta);

        categoriaTV = findViewById(R.id.categoriaCompraProfile);
        categoriaTV.setText("Categoria: " + categoria);

        descripcionTV = findViewById(R.id.descripcionCompraProfile);
        descripcionTV.setText("Descripción: " + descripcion);

        precioTV = findViewById(R.id.precioCompraProfile);
        precioTV.setText("Precio: " + precio + "€");

        status = findViewById(R.id.estadoCompraProfile);
        status.setText("Estado: " + estad);

        }


    }