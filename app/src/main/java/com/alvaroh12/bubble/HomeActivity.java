package com.alvaroh12.bubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;
    ArrayList<StaticRvModel> item = new ArrayList<>();

    ArrayList<DynamicRVModel> items = new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    TextView saludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        saludo = (TextView) findViewById(R.id.nombreUser);

        String nombre = getIntent().getStringExtra("usuario");
        saludo.setText(nombre);
        Toast.makeText(this, "Usuario: " + nombre, Toast.LENGTH_LONG).show();


        item.add(new StaticRvModel(R.drawable.arte, "Arte y Diseño"));
        item.add(new StaticRvModel(R.drawable.traduccion, "Traducción"));
        item.add(new StaticRvModel(R.drawable.musica, "Música"));
        item.add(new StaticRvModel(R.drawable.programacion, "Programación"));
        item.add(new StaticRvModel(R.drawable.diseno_web, "Diseño Web"));
        item.add(new StaticRvModel(R.drawable.redes_sociales, "Redes Sociales"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        staticRvAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Selección: "+item.get
                                        (recyclerView.getChildAdapterPosition(v)).getTxt(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        items.add(new DynamicRVModel("Programador"));
        items.add(new DynamicRVModel("PixelArt"));
        items.add(new DynamicRVModel("Musica"));
        items.add(new DynamicRVModel("Pop"));
        items.add(new DynamicRVModel("Tecno"));
        items.add(new DynamicRVModel("Blues"));
        items.add(new DynamicRVModel("Manga"));
        items.add(new DynamicRVModel("Comic"));



        RecyclerView drv = findViewById(R.id.rv_2);
        //drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(items);
        dynamicRVAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Selección: "+items.get
                        (drv.getChildAdapterPosition(v)).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        drv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        drv.setAdapter(dynamicRVAdapter);


    }





}