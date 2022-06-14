package com.alvaroh12.bubble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginActivity extends AppCompatActivity {

    float v = 0;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    LoginAdapter loginAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager2 = (ViewPager2) findViewById(R.id.view_pager);



        loginAdapter = new LoginAdapter(getSupportFragmentManager(), getLifecycle());
        loginAdapter.addFragment(new LoginTabFragment());
        loginAdapter.addFragment(new SignupTabFragment());

        viewPager2.setAdapter(loginAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Login");
                        break;
                    case 1:
                        tab.setText("Signup");
                        break;
                }
            }
        }).attach();


        tabLayout.setTranslationY(300);


        tabLayout.setAlpha(v);


        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();


    }
}