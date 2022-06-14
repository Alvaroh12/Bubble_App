package com.alvaroh12.bubble;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.alvaroh12.bubble.Interface.OfertaInterface;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfertasFragment extends Fragment {

    private ArrayList<DynamicRVModel> ofertas = new ArrayList<>();
    private DynamicRVAdapter dynamicRVAdapter;
    private RecyclerView recyclerView;

    int id_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.ofertas_fragment,container, false);

        recyclerView = root.findViewById(R.id.rvOferta);
        id_user = getArguments().getInt("id_user");

        recibirOfertas(ofertas, id_user);


        dynamicRVAdapter = new DynamicRVAdapter(ofertas);
        dynamicRVAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(),OfertaActivityProfile.class);
                intent.putExtra("id_oferta",ofertas.get(recyclerView.getChildAdapterPosition(v)).getId_oferta());
                intent.putExtra("tipo_oferta",ofertas.get(recyclerView.getChildAdapterPosition(v)).getTipo_oferta());
                intent.putExtra("categoria",ofertas.get(recyclerView.getChildAdapterPosition(v)).getCategoria());
                intent.putExtra("descripcion",ofertas.get(recyclerView.getChildAdapterPosition(v)).getDescripcion());
                intent.putExtra("precio",ofertas.get(recyclerView.getChildAdapterPosition(v)).getPrecio());
                intent.putExtra("nombreOferente",ofertas.get(recyclerView.getChildAdapterPosition(v)).getNombre());
                intent.putExtra("correoOferente",ofertas.get(recyclerView.getChildAdapterPosition(v)).getCorreo());
                intent.putExtra("idOferente",ofertas.get(recyclerView.getChildAdapterPosition(v)).getId_usuario());

                intent.putExtra("id_user", id_user);
                startActivity(intent);

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(dynamicRVAdapter);

        return root;

    }


    public void recibirOfertas(ArrayList<DynamicRVModel> listaOfertas, int id_user){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.134:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OfertaInterface ofertaInterface = retrofit.create(OfertaInterface.class);
        Call<List<DynamicRVModel>> call = ofertaInterface.getMisOfertaUsuario(id_user);
        call.enqueue(new Callback<List<DynamicRVModel>>() {

            @Override
            public void onResponse(Call<List<DynamicRVModel>> call, Response<List<DynamicRVModel>> response) {
                List<DynamicRVModel> listUsers = response.body();
                for (DynamicRVModel u:listUsers){
                    listaOfertas.add(u);
                }
                dynamicRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DynamicRVModel>> call, Throwable t) {
                Toast.makeText(getContext(), "ERRORRRRRRRRRRR 1", Toast.LENGTH_LONG).show();
            }
        });

    }

}
