package com.alvaroh12.bubble;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProfileFragment extends Fragment {

    float v = 0;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    LoginAdapter loginAdapter;

    TextView userProfile;

    int id_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile,container, false);

        userProfile = root.findViewById(R.id.nombrePerfil);
        id_user = getArguments().getInt("id_user");
        String usuario = getArguments().getString("name");

        userProfile.setText(usuario);
        //RECIBIR EL NOMBRE USUARIO COMO EN HOME FRAGMENT

        tabLayout = (TabLayout) root.findViewById(R.id.tab_layout2);
        viewPager2 = (ViewPager2) root.findViewById(R.id.view_pager2);


        //FRAGMENTS DE RECYCLED VIEW DE CADA TIPO (COMPRAS OFERTAS EMPLEOS)
        Bundle args = new Bundle();
        args.putInt("id_user", id_user);
        OfertasFragment ofFragment = new OfertasFragment();
        ofFragment.setArguments(args);

        CompraFragment comFragment = new CompraFragment();
        comFragment.setArguments(args);

        EmpleoFragment empFragment = new EmpleoFragment();
        empFragment.setArguments(args);

        loginAdapter = new LoginAdapter(super.getParentFragmentManager(), getLifecycle());
        loginAdapter.addFragment(comFragment);
        loginAdapter.addFragment(ofFragment);
        loginAdapter.addFragment(empFragment);

        viewPager2.setAdapter(loginAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Compras");
                        break;
                    case 1:
                        tab.setText("Ofertas");
                        break;
                    case 2:
                        tab.setText("Empleos");
                        break;
                }
            }
        }).attach();


        tabLayout.setTranslationY(300);


        tabLayout.setAlpha(v);


        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        return root;

    }
}