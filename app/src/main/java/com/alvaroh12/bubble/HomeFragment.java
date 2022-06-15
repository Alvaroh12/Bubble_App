package com.alvaroh12.bubble;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaroh12.bubble.Interface.CategoriaInterface;
import com.alvaroh12.bubble.Interface.OfertaInterface;
import com.alvaroh12.bubble.Model.Categoria;
import com.alvaroh12.bubble.Model.Oferta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;
    private ArrayList<StaticRvModel> item = new ArrayList<>();


    private ArrayList<DynamicRVModel>items;
    private DynamicRVAdapter dynamicRVAdapter;


    SearchView searchView;
    TextView saludo;
    String nombre;
    int id_user = 0;

    ImageView add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        add = root.findViewById(R.id.addOferta);

        items = new ArrayList<>();

        RecyclerView drv = root.findViewById(R.id.rv_2);
        searchView = root.findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText, root);
                return false;
            }
        });


        saludo = (TextView) root.findViewById(R.id.nombreUser);

        nombre = getArguments().getString("usuario");
        id_user = getArguments().getInt("id_user");
        saludo.setText(nombre);

        item.add(new StaticRvModel(R.drawable.bubble_icon, "Todos"));
        recibirCategorias(item);

        recyclerView = root.findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        staticRvAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(),"Selección: "+item.get
                        (recyclerView.getChildAdapterPosition(v)).getTxt(),Toast.LENGTH_SHORT).show();

                Toast.makeText(recyclerView.getContext(), "aqui 1: " + items.size(), Toast.LENGTH_SHORT);

                        seleccionOfertas(items, id_user,
                                item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                                , root, drv, v);
                dynamicRVAdapter.setFilteredList(items);
                //dynamicRVAdapter.notifyDataSetChanged();//

                Toast.makeText(recyclerView.getContext(), "aqui 2: " + items.size(), Toast.LENGTH_SHORT);

            }

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);


        recibirOfertas(items, id_user);



        dynamicRVAdapter = new DynamicRVAdapter(items);
        dynamicRVAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(root.getContext(),"Selección: "+items.get
                //        (drv.getChildAdapterPosition(v)).getId_oferta(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(root.getContext(),CompraActivity.class);
                intent.putExtra("id_oferta",items.get(drv.getChildAdapterPosition(v)).getId_oferta());
                intent.putExtra("tipo_oferta",items.get(drv.getChildAdapterPosition(v)).getTipo_oferta());
                intent.putExtra("categoria",items.get(drv.getChildAdapterPosition(v)).getCategoria());
                intent.putExtra("descripcion",items.get(drv.getChildAdapterPosition(v)).getDescripcion());
                intent.putExtra("precio",items.get(drv.getChildAdapterPosition(v)).getPrecio());
                intent.putExtra("nombreOferente",items.get(drv.getChildAdapterPosition(v)).getNombre());
                intent.putExtra("correoOferente",items.get(drv.getChildAdapterPosition(v)).getCorreo());
                intent.putExtra("idOferente",items.get(drv.getChildAdapterPosition(v)).getId_usuario());

                intent.putExtra("id_user", id_user);
                intent.putExtra("name", nombre);
                startActivity(intent);


            }
        });
        drv.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        drv.setAdapter(dynamicRVAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(saludo.getContext(), AddOfertaActivity.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("name", nombre);
                startActivity(intent);
            }
        });


        return  root;
    }

    private void filterList(String newText, ViewGroup v) {
        List<DynamicRVModel> filteredList = new ArrayList<>();
        for (DynamicRVModel dynamic:items) {
            if (dynamic.getTipo_oferta().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(dynamic);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(v.getContext(), "No se encontró", Toast.LENGTH_SHORT).show();
        }else{
            dynamicRVAdapter.setFilteredList(filteredList);
        }
    }


    public void recibirOfertas(ArrayList<DynamicRVModel> listaOfertas, int id_user){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OfertaInterface ofertaInterface = retrofit.create(OfertaInterface.class);
        Call<List<DynamicRVModel>> call = ofertaInterface.getOfertaUsuario(id_user);
        call.enqueue(new Callback<List<DynamicRVModel>>() {

            @Override
            public void onResponse(Call<List<DynamicRVModel>> call, Response<List<DynamicRVModel>> response) {
                List<DynamicRVModel> listUsers = response.body();
                for (DynamicRVModel u:listUsers){
                    listaOfertas.add(u);
                }
                dynamicRVAdapter.notifyDataSetChanged();
                staticRvAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<DynamicRVModel>> call, Throwable t) {
                Toast.makeText(getContext(), "ERRORRRRRRRRRRR 1", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void recibirOfertasByCategoria(ArrayList<DynamicRVModel> listaOfertas, int id_user, String categoria){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OfertaInterface ofertaInterface = retrofit.create(OfertaInterface.class);
        Call<List<DynamicRVModel>> call = ofertaInterface.getOfertaByIdCategoria(id_user, categoria);//quitar el 10
        call.enqueue(new Callback<List<DynamicRVModel>>() {

            @Override
            public void onResponse(Call<List<DynamicRVModel>> call, Response<List<DynamicRVModel>> response) {
                List<DynamicRVModel> listUsers = response.body();
                for (DynamicRVModel u:listUsers){
                    listaOfertas.add(u);
                }
                dynamicRVAdapter.notifyDataSetChanged();
                staticRvAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<DynamicRVModel>> call, Throwable t) {
                Toast.makeText(getContext(), "ERRORRRRRRRRRRR 2", Toast.LENGTH_LONG).show();
            }
        });

    }



    public void seleccionOfertas(ArrayList<DynamicRVModel> listaOfertas, int id_user, String categoria, ViewGroup root, RecyclerView drv, View v){
        if (item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Programación")){

            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);


        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Arte y Diseño")){
            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);


        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Traducción")){
            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);

        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Traducción")){
            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);

        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Música")){
            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);

        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Diseño Web")){
            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);

        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Redes Sociales")){
            items.clear();
            recibirOfertasByCategoria(items, id_user, categoria);

        }else if(item.get(recyclerView.getChildAdapterPosition(v)).getTxt()
                .equalsIgnoreCase("Todos")){
            items.clear();
            recibirOfertas(items, id_user);

        }
    }

    public void recibirCategorias(ArrayList<StaticRvModel> listaOfertas){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriaInterface categoriaInterface = retrofit.create(CategoriaInterface.class);
        Call<List<Categoria>> call = categoriaInterface.getCategoria();
        call.enqueue(new Callback<List<Categoria>>() {

            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                List<Categoria> listUsers = response.body();
                for (Categoria u:listUsers){
                    int image = 0;
                    if (u.getCategoria()
                            .equalsIgnoreCase("Programación")){
                        image = R.drawable.programacion;


                    }else if(u.getCategoria()
                            .equalsIgnoreCase("Arte y Diseño")){
                        image = R.drawable.arte;


                    }else if(u.getCategoria()
                            .equalsIgnoreCase("Traducción")){
                        image = R.drawable.traduccion;

                    }else if(u.getCategoria()
                            .equalsIgnoreCase("Traducción")){
                        image = R.drawable.traduccion;

                    }else if(u.getCategoria()
                            .equalsIgnoreCase("Música")){
                        image = R.drawable.musica;

                    }else if(u.getCategoria()
                            .equalsIgnoreCase("Diseño Web")){
                        image = R.drawable.diseno_web;

                    }else if(u.getCategoria()
                            .equalsIgnoreCase("Redes Sociales")){
                        image = R.drawable.redes_sociales;

                    }
                    listaOfertas.add(new StaticRvModel(image,u.getCategoria()));
                }
                staticRvAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getContext(), "ERRORRRRRRRRRRR 3", Toast.LENGTH_LONG).show();
            }
        });

    }

}
