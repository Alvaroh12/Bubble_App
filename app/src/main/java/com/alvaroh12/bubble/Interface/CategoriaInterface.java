package com.alvaroh12.bubble.Interface;

import com.alvaroh12.bubble.Model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriaInterface {

    @GET("categoria/listar")
    Call<List<Categoria>> getCategoria();

    @GET("categoria/listar/{categoria}")
    Call<List<Categoria>> getCategoriaByName(@Path("categoria")String categoria);

    //hacer un buscar por nombre para luego sacar el id y usar el metodo de get oferta by id
}
