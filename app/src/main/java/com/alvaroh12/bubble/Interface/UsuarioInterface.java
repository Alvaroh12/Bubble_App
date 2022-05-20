package com.alvaroh12.bubble.Interface;

import com.alvaroh12.bubble.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioInterface {

    @GET("usuario/listar")
    Call <List<Usuario>> getUser();

    @GET("usuario/listar/{id}")
    Call <List<Usuario>> getUserById(@Path("id")int id_User);

    @POST("usuario/agregar")
    Call <Void> agregarUser(@Body Usuario user);

    @POST("usuario/actualizar/{id}")
    Call <Void> editUser(@Path("id") int id_User,@Body Usuario user);

    @DELETE("usuario/eliminar/{id}")
    Call <Void> deleteUser(@Path("id") int id_User);


}
