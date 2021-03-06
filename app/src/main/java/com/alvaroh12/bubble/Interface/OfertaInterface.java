package com.alvaroh12.bubble.Interface;


import com.alvaroh12.bubble.DynamicRVModel;
import com.alvaroh12.bubble.DynamicRVModelCompra;
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

    @GET("oferta/listar/{id}/{categoria}")
    Call <List<DynamicRVModel>> getOfertaByIdCategoria(@Path("id")int id_User, @Path("categoria")String categoria);

    @GET("oferta/listarOU/{id}")
    Call <List<DynamicRVModel>> getOfertaUsuario(@Path("id")int id_User);

    @GET("oferta/listarMOU/{id}")
    Call <List<DynamicRVModel>> getMisOfertaUsuario(@Path("id")int id_User);

    @GET("oferta/listarOUE/{id}")
    Call <List<DynamicRVModelCompra>> getOfertaUsuarioEmpleo(@Path("id")int id_User);

    @GET("oferta/listarOUEM/{id}")
    Call <List<DynamicRVModelCompra>> getOfertaUsuarioEmpleoMe(@Path("id")int id_User);

    @POST("oferta/agregar")
    Call <Void> agregarOferta(@Body Oferta user);

    @POST("oferta/actualizar/{id}")
    Call <Void> editOferta(@Path("id") int id_oferta,@Body Oferta oferta);

    @DELETE("oferta/eliminar/{id}")
    Call <Void> deleteOferta(@Path("id") int id_oferta);

}
