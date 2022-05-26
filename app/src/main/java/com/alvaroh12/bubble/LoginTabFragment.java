package com.alvaroh12.bubble;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alvaroh12.bubble.Interface.UsuarioInterface;
import com.alvaroh12.bubble.Model.Usuario;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginTabFragment extends Fragment {
    EditText email;
    EditText password;
    TextView forgetPassword;
    Button login;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container, false);

        email = (EditText) root.findViewById(R.id.email);
        password = (EditText) root.findViewById(R.id.pass);
        forgetPassword = (TextView) root.findViewById(R.id.forgetPass);
        login = (Button) root.findViewById(R.id.login);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetPassword.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetPassword.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarUsuario();
            }
        });

        return root;
    }



    public void comprobarUsuario(){
        String contra = password.getText().toString().trim();
        String correo = email.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.136:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioInterface usuarioInterface = retrofit.create(UsuarioInterface.class);
        Call<List<Usuario>> call = usuarioInterface.getUser();
        call.enqueue(new Callback<List<Usuario>>() {

            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {

                List<Usuario> listUsers = response.body();
                boolean encontrado=false;
                String userName = "";
                int id_user = 0;

                if (!correo.isEmpty() && !contra.isEmpty()) {
                    for (Usuario u:listUsers){
                        if (u.getCorreo().equals(correo)&&u.getPassword().equals(contra)){
                            userName=u.getNombre();
                            id_user=u.getId_usuario();
                            encontrado=true;
                        }
                    }

                    if (encontrado){
                        Intent intent = new Intent(email.getContext(),PrincipalActivity.class);
                        intent.putExtra("usuario",userName );
                        intent.putExtra("id_user", id_user);
                        startActivity(intent);

                    }else{//decir que usuario no encontrado
                        Toast.makeText(email.getContext(), "Correo o Contraseña erróneos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(email.getContext(), "Introduzca Correo y Contraseña", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(email.getContext(), "ERROORRR", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
