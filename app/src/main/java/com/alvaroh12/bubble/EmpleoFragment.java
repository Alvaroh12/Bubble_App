package com.alvaroh12.bubble;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaroh12.bubble.Interface.EmpleoInterface;
import com.alvaroh12.bubble.Interface.OfertaInterface;
import com.alvaroh12.bubble.Interface.UsuarioInterface;
import com.alvaroh12.bubble.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpleoFragment extends Fragment {//WEB SERVICE NUEVO METODOS Y CREAR NUEVOS MODELOS
    private ArrayList<DynamicRVModelCompra> empleos = new ArrayList<>();
    private DynamicRVAdapterCompra dynamicRVAdapter;
    private RecyclerView recyclerView;

    int id_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.compras_fragment,container, false);

        recyclerView = root.findViewById(R.id.rvCompra);
        id_user = getArguments().getInt("id_user");

        recibirCompras(empleos, id_user);


        dynamicRVAdapter = new DynamicRVAdapterCompra(empleos);
        dynamicRVAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setMessage("¿Quieres Aceptar la petición?").setTitle("Empleo: " + empleos.get(recyclerView.getChildAdapterPosition(v)).getTipo_oferta());
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            aceptar(empleos.get(recyclerView.getChildAdapterPosition(v)).getId_empleo());
                    }
                });

                builder.setNegativeButton("Declinar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEmpleo(empleos.get(recyclerView.getChildAdapterPosition(v)).getId_empleo());
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(dynamicRVAdapter);

        return root;

    }

    public void recibirCompras(ArrayList<DynamicRVModelCompra> listaOfertas, int id_user){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OfertaInterface ofertaInterface = retrofit.create(OfertaInterface.class);
        Call<List<DynamicRVModelCompra>> call = ofertaInterface.getOfertaUsuarioEmpleoMe(id_user);
        call.enqueue(new Callback<List<DynamicRVModelCompra>>() {

            @Override
            public void onResponse(Call<List<DynamicRVModelCompra>> call, Response<List<DynamicRVModelCompra>> response) {
                List<DynamicRVModelCompra> listUsers = response.body();
                for (DynamicRVModelCompra u:listUsers){
                    listaOfertas.add(u);
                }
                dynamicRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<DynamicRVModelCompra>> call, Throwable t) {
                Toast.makeText(getContext(), "ERRORRRRRRRRRRR 1", Toast.LENGTH_LONG).show();
            }
        });

    }





    public void deleteEmpleo(int id_empleo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmpleoInterface empleoInterface = retrofit.create(EmpleoInterface.class);
        Call call = empleoInterface.deleteEmpleo(id_empleo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(recyclerView.getContext(), "Empleo Eliminado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void aceptar(int id_empleo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmpleoInterface empleoInterface = retrofit.create(EmpleoInterface.class);
        Call call = empleoInterface.editEmpleo(id_empleo, 1);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(recyclerView.getContext(), "Empleo Aceptado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

}

