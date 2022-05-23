package com.alvaroh12.bubble;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.alvaroh12.bubble.Interface.UsuarioInterface;
import com.alvaroh12.bubble.Model.Usuario;
import com.alvaroh12.bubble.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupTabFragment extends Fragment {

    EditText email;
    EditText userName;
    EditText password;
    EditText passwordConfirm;
    Button signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container, false);

        email = (EditText) root.findViewById(R.id.email);
        userName = (EditText) root.findViewById(R.id.userName);
        password = (EditText) root.findViewById(R.id.pass);
        passwordConfirm = (EditText) root.findViewById(R.id.confirmPass);
        signup = (Button) root.findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarUsuario();
            }
        });


        return root;
    }


    public void comprobarUsuario(){

        String correo = email.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.135:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
        Call<List<Usuario>> call = usuarioInterface.getUser();
        call.enqueue(new Callback<List<Usuario>>() {

            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {

                List<Usuario> listUsers = response.body();
                boolean encontrado=false;
                for (Usuario u:listUsers){
                    if (u.getCorreo().equals(correo)){
                        encontrado=true;
                    }
                }

                if (!encontrado){
                    addUsuario();
                }else{

                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

            }
        });

    }

    public void addUsuario(){

        String nombre = userName.getText().toString();
        String correo = email.getText().toString();
        String contra = password.getText().toString();
        String confirm = passwordConfirm.getText().toString();




        if (contra.equals(confirm)){
            Usuario user = new Usuario(nombre,correo, confirm );
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.135:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
            Call call = usuarioInterface.agregarUser(user);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                    Intent intent = new Intent(email.getContext(), MainActivity.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
        }



    }




}
