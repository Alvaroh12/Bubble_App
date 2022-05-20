package com.alvaroh12.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.alvaroh12.bubble.Interface.UsuarioInterface;
import com.alvaroh12.bubble.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTextView = (TextView) findViewById(R.id.jsonText);
        //getUsuario();
        //getUsuarioById();
        //deleteUsuario();
        //addUsuario();
        //editUsuario();
    }

     public void getUsuario(){

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.1.135:8080/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
         Call<List<Usuario>> call = usuarioInterface.getUser();
         call.enqueue(new Callback<List<Usuario>>() {
             @Override
             public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                 if(!response.isSuccessful()){
                     mJsonTextView.setText("Código: " + response.code());
                     return;
                 }
                 List<Usuario> listUsers = response.body();

                 for (Usuario u:listUsers){
                     String content = "";
                     content+="Id_Usuario: " + u.getId_usuario() + "\n";
                     content+="Nombre: " + u.getNombre() + "\n";
                     content+="Correo: " + u.getCorreo() + "\n";
                     content+="Password: " + u.getPassword() + "\n\n";
                     mJsonTextView.append(content);
                 }
             }

             @Override
             public void onFailure(Call<List<Usuario>> call, Throwable t) {
                mJsonTextView.setText(t.getMessage());
             }
         });
     }

     public void getUsuarioById(){

        int id = 2;

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.1.135:8080/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
         Call<List<Usuario>> call = usuarioInterface.getUserById(id);
         call.enqueue(new Callback<List<Usuario>>() {
             @Override
             public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                 if(!response.isSuccessful()){
                     mJsonTextView.setText("Código: " + response.code());
                     return;
                 }
                 List<Usuario> listUsers = response.body();

                 for (Usuario u:listUsers){
                     String content = "";
                     content+="Id_Usuario: " + u.getId_usuario() + "\n";
                     content+="Nombre: " + u.getNombre() + "\n";
                     content+="Correo: " + u.getCorreo() + "\n";
                     content+="Password: " + u.getPassword() + "\n\n";
                     mJsonTextView.append(content);
                 }
             }

             @Override
             public void onFailure(Call<List<Usuario>> call, Throwable t) {
                 mJsonTextView.setText(t.getMessage());
             }
         });
     }

     public void deleteUsuario(){
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.1.135:8080/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
         Call call = usuarioInterface.deleteUser(8);
         call.enqueue(new Callback<List<Usuario>>() {
             @Override
             public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                 if(!response.isSuccessful()){
                     mJsonTextView.setText("Código: " + response.code());
                     return;
                 }

                 if(response.isSuccessful()){
                     mJsonTextView.setText("Dato eliminado: " + response.code());
                 }
             }

             @Override
             public void onFailure(Call<List<Usuario>> call, Throwable t) {
                 mJsonTextView.setText(t.getMessage());
             }
         });
     }

     public void addUsuario(){
        Usuario user = new Usuario("Nombre","Correo", "Pasword" );

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.1.135:8080/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
         Call call = usuarioInterface.agregarUser(user);
         call.enqueue(new Callback() {
             @Override
             public void onResponse(Call call, Response response) {
                 if(!response.isSuccessful()){
                     mJsonTextView.setText("Código: " + response.code());
                     return;
                 }

                 if(response.isSuccessful()){
                     mJsonTextView.setText("Usuario Agregado");
                 }
             }

             @Override
             public void onFailure(Call call, Throwable t) {
                 mJsonTextView.setText(t.getMessage());
             }
         });

     }

     public void editUsuario(){
         Usuario user = new Usuario(7,"LOL","Mod", "Mod" );

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.1.135:8080/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
         Call call = usuarioInterface.editUser(user.getId_usuario(),user);
         call.enqueue(new Callback() {
             @Override
             public void onResponse(Call call, Response response) {
                 if(!response.isSuccessful()){
                     mJsonTextView.setText("Código: " + response.code());
                     return;
                 }

                 if(response.isSuccessful()){
                     mJsonTextView.setText("Usuario Modificado: " + response.code());
                 }
             }

             @Override
             public void onFailure(Call call, Throwable t) {
                 mJsonTextView.setText(t.getMessage());
             }
         });
     }

}