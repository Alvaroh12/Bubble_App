package com.alvaroh12.bubble.Interface;


import com.alvaroh12.bubble.Model.Oferta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OfertaInterface {

    @GET("oferta/listar")
    Call<List<Oferta>> getOferta();

    @GET("oferta/listar/{id}")
    Call <List<Oferta>> getOfertaById(@Path("id")int id_User);

    @POST("oferta/agregar")
    Call <Void> agregarOferta(@Body Oferta user);

    @POST("oferta/actualizar/{id}")
    Call <Void> editOferta(@Path("id") int id_oferta,@Body Oferta oferta);

    @DELETE("oferta/eliminar/{id}")
    Call <Void> deleteOferta(@Path("id") int id_oferta);

}
