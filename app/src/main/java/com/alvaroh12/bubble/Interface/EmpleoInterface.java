package com.alvaroh12.bubble.Interface;

import com.alvaroh12.bubble.Model.Empleo;
import com.alvaroh12.bubble.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmpleoInterface {

    @GET("empleo/listar")
    Call<List<Usuario>> getUser();

    @GET("empleo/listar/{id}")
    Call <List<Usuario>> getEmpleoById(@Path("id")int id_empleo);

    @POST("empleo/agregar")
    Call <Void> agregarEmpleo(@Body Empleo empleo);

    @POST("empleo/actualizar/{id}/{aceptado}")
    Call <Void> editEmpleo(@Path("id") int id_empleo,@Path("aceptado") int aceptado);

    @DELETE("empleo/eliminar/{id}")
    Call <Void> deleteEmpleo(@Path("id") int id_empleo);

}
