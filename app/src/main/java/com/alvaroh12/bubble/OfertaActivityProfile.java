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

import com.alvaroh12.bubble.Interface.OfertaInterface;
import com.alvaroh12.bubble.Interface.UsuarioInterface;
import com.alvaroh12.bubble.Model.Oferta;
import com.alvaroh12.bubble.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfertaActivityProfile extends AppCompatActivity {

    String miNombre;

    int id_oferta;
    int id_usuario;
    String tipo_oferta;
    String nombre;
    String categoria;
    String descripcion;
    Double precio;
    String correo;

    int idUsuarioOferente;

    TextView nombreUsuarioOferta;
    TextView tipoOferta;
    TextView categoriaTV;
    TextView descripcionTV;
    TextView precioTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_profile);

        miNombre = getIntent().getStringExtra("name");
        id_usuario = getIntent().getIntExtra("id_user", id_usuario);
        id_oferta = getIntent().getIntExtra("id_oferta", id_oferta);
        tipo_oferta = getIntent().getStringExtra("tipo_oferta");
        nombre = getIntent().getStringExtra("nombreOferente");
        categoria = getIntent().getStringExtra("categoria");
        descripcion = getIntent().getStringExtra("descripcion");
        precio = getIntent().getDoubleExtra("precio", 0.0);
        correo = getIntent().getStringExtra("correoOferente");

        idUsuarioOferente = getIntent().getIntExtra("idOferente", id_usuario);


        nombreUsuarioOferta = findViewById(R.id.userOfertProfile);
        nombreUsuarioOferta.setText("Creador: " + nombre);

        tipoOferta = findViewById(R.id.tipoOfertaOfertaProfile);
        tipoOferta.setText(tipo_oferta);

        categoriaTV = findViewById(R.id.categoriaOfertaProfile);
        categoriaTV.setText("Categoria: " + categoria);

        descripcionTV = findViewById(R.id.descripcionOfertaProfile);
        descripcionTV.setText("Descripción: " + descripcion);

        precioTV = findViewById(R.id.precioOfertaProfile);
        precioTV.setText("Precio: " + precio + "€");




        }

    private void eliminarOferta(int id_oferta) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OfertaInterface usuarioInterface = retrofit.create(OfertaInterface.class);
        Call call = usuarioInterface.deleteOferta(id_oferta);
        call.enqueue(new Callback<List<Oferta>>() {
            @Override
            public void onResponse(Call<List<Oferta>> call, Response<List<Oferta>> response) {

                //Toast.makeText(btCompra.getContext(), "Oferta Eliminada", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Oferta>> call, Throwable t) {
            }
        });
    }



}