package com.alvaroh12.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaroh12.bubble.Interface.CategoriaInterface;
import com.alvaroh12.bubble.Interface.OfertaInterface;
import com.alvaroh12.bubble.Model.Categoria;
import com.alvaroh12.bubble.Model.Oferta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddOfertaActivity extends AppCompatActivity {

    int id_user;
    String usuario;

    Spinner spinner;

    String tipoOferta;
    int categoria;
    String descripcion;
    Double precio;

    EditText tipoOfertaET;
    EditText descripcionET;
    EditText precioET;

    TextView user;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_oferta);

        id_user = getIntent().getIntExtra("id_user", id_user);
        usuario = getIntent().getStringExtra("name");

        user = findViewById(R.id.userAdd);
        user.setText("Usuario: " + usuario);


        spinner = findViewById(R.id.categoriaAdd);
        ArrayList<Categoria> categorias = new ArrayList<>();//CARGAR LAS CATG DE LA BASE DE DATOS
        recibirCategorias(categorias);
        categorias.add(new Categoria("Seleccione una categoria"));
        ArrayAdapter<Categoria> adapter= new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                categorias);
        spinner.setAdapter(adapter);


        tipoOfertaET = findViewById(R.id.tipoOfertaAdd);
        descripcionET = findViewById(R.id.descripcionAdd);
        precioET = findViewById(R.id.precioAdd);

        add = findViewById(R.id.btCompra);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoOferta = tipoOfertaET.getText().toString();
                categoria = mostrarCategoria();
                descripcion = descripcionET.getText().toString();
                precio = Double.parseDouble(precioET.getText().toString());

                if (categoria!=0){
                    agregarOferta(tipoOferta, descripcion, precio, id_user,categoria);
                }else{
                    Toast.makeText(spinner.getContext(), "Debes Elegir una categoria", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private int mostrarCategoria() {
        Categoria categoria = (Categoria) spinner.getSelectedItem();
        int categ = categoria.getId_categoria();
        return categ;
    }


    public void recibirCategorias(ArrayList<Categoria> listaOfertas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriaInterface categoriaInterface = retrofit.create(CategoriaInterface.class);
        Call<List<Categoria>> call = categoriaInterface.getCategoria();
        call.enqueue(new Callback<List<Categoria>>() {

            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                List<Categoria> listUsers = response.body();
                for (Categoria c:listUsers ) {
                    listaOfertas.add(c);
                }

            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(spinner.getContext(), "ERRORRRRRRRRRRR", Toast.LENGTH_LONG).show();
            }
        });

    }

 public void agregarOferta(String tipOferta, String descrip, double prec, int idUser, int categ){

        Oferta oferta = new Oferta(0, tipoOferta, descrip,prec, idUser, categ);
     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl("http://192.168.1.134:8080/")
             .addConverterFactory(GsonConverterFactory.create())
             .build();

     OfertaInterface ofertaInterface = retrofit.create(OfertaInterface.class);
     Call<Void> call = ofertaInterface.agregarOferta(oferta);
     call.enqueue(new Callback<Void>() {

         @Override
         public void onResponse(Call<Void> call, Response<Void> response) {
             Toast.makeText(spinner.getContext(), "Oferta creada", Toast.LENGTH_LONG).show();
         }

         @Override
         public void onFailure(Call<Void> call, Throwable t) {
             Toast.makeText(spinner.getContext(), "ERRORR", Toast.LENGTH_LONG).show();
         }
     });

 }

}