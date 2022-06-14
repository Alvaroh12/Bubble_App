package com.alvaroh12.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaroh12.bubble.Interface.EmpleoInterface;
import com.alvaroh12.bubble.Interface.UsuarioInterface;
import com.alvaroh12.bubble.Model.Empleo;
import com.alvaroh12.bubble.Model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompraActivity extends AppCompatActivity {

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

    EditText peticion;

    Button btCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        miNombre = getIntent().getStringExtra("name");
        id_usuario = getIntent().getIntExtra("id_user",id_usuario);
        id_oferta = getIntent().getIntExtra("id_oferta",id_oferta);
        tipo_oferta = getIntent().getStringExtra("tipo_oferta");
        nombre=getIntent().getStringExtra("nombreOferente");
        categoria=getIntent().getStringExtra("categoria");
        descripcion=getIntent().getStringExtra("descripcion");
        precio = getIntent().getDoubleExtra("precio",0.0);
        correo=getIntent().getStringExtra("correoOferente");

        idUsuarioOferente = getIntent().getIntExtra("idOferente", id_usuario);


        nombreUsuarioOferta = findViewById(R.id.userCompra);
        nombreUsuarioOferta.setText("Creador: " + nombre );

        tipoOferta=findViewById(R.id.tipoOfertaCompra);
        tipoOferta.setText(tipo_oferta);

        categoriaTV = findViewById(R.id.categoriaCompra);
        categoriaTV.setText("Categoria: " + categoria);

        descripcionTV = findViewById(R.id.descripcionCompra);
        descripcionTV.setText("Descripción: "+descripcion);

        precioTV = findViewById(R.id.precioCompra);
        precioTV.setText("Precio: "+precio+"€");

        peticion = findViewById(R.id.peticionText);

        btCompra = findViewById(R.id.btCompra);
        btCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(btCompra.getContext(), peticion.getText().toString(), Toast.LENGTH_SHORT).show();

                // Defino mi Intent y hago uso del objeto ACTION_SEND
                Intent intent = new Intent(Intent.ACTION_SEND);

                // Defino los Strings Email, Asunto y Mensaje con la función putExtra
                intent.putExtra(Intent.EXTRA_EMAIL,
                        new String[] { correo });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Bubble: Interesado en " + tipo_oferta);
                intent.putExtra(Intent.EXTRA_TEXT, peticion.getText().toString());

                // Establezco el tipo de Intent
                intent.setType("message/rfc822");
                // Lanzo el selector de cliente de Correo
                startActivity(Intent.createChooser(intent, "Elige un cliente de correo"));

                addEmpleo(id_oferta, id_usuario, idUsuarioOferente);
            }
        });


    }


    public void addEmpleo(int idOferta, int idUsuario, int idUsuarioOferente){


        int isAceptado = 0;
        int isCancelado = 0;
        //double id_oferta;
        //int id_usuario_comprador;
        //int id_usuario_oferente;

            Empleo empleo = new Empleo(isAceptado, isCancelado, idOferta, idUsuario, idUsuarioOferente);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.134:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EmpleoInterface empleoInterface = retrofit.create(EmpleoInterface.class);
            Call call = empleoInterface.agregarEmpleo(empleo);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                Toast.makeText(btCompra.getContext(), "Aceptado", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });




    }

}