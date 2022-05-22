package com.alvaroh12.bubble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alvaroh12.bubble.R;

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

        return root;
    }

}
