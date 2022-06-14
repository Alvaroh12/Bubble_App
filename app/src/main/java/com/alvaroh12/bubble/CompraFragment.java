package com.alvaroh12.bubble;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaroh12.bubble.Interface.OfertaInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompraFragment extends Fragment {

    private ArrayList<DynamicRVModelCompra> compras = new ArrayList<>();
    private DynamicRVAdapterCompra dynamicRVAdapter;
    private RecyclerView recyclerView;

    int id_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.compras_fragment,container, false);

        recyclerView = root.findViewById(R.id.rvCompra);
        id_user = getArguments().getInt("id_user");

       recibirCompras(compras, id_user);


        dynamicRVAdapter = new DynamicRVAdapterCompra(compras);
        dynamicRVAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(),CompraActivityProfile.class);
                intent.putExtra("estado",compras.get(recyclerView.getChildAdapterPosition(v)).getIsAceptado());
                intent.putExtra("tipo_oferta",compras.get(recyclerView.getChildAdapterPosition(v)).getTipo_oferta());
                intent.putExtra("categoria",compras.get(recyclerView.getChildAdapterPosition(v)).getCategoria());
                intent.putExtra("descripcion",compras.get(recyclerView.getChildAdapterPosition(v)).getDescripcion());
                intent.putExtra("precio",compras.get(recyclerView.getChildAdapterPosition(v)).getPrecio());
                intent.putExtra("nombreOferente",compras.get(recyclerView.getChildAdapterPosition(v)).getNombre());

                intent.putExtra("id_user", id_user);
                startActivity(intent);
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
        Call<List<DynamicRVModelCompra>> call = ofertaInterface.getOfertaUsuarioEmpleo(id_user);
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

}
