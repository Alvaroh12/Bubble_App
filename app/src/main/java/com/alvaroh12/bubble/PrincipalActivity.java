package com.alvaroh12.bubble;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.SlidingRootNavLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class PrincipalActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{

    private static final int POS_CLOSE = 0;
    private static final int POS_HOME = 1;
    private static final int POS_MYPROFILE = 2;
    private static final int POS_ABOUT_US = 3;
    private static final int POS_LOGOUT = 5;

    private String[]screenTitles;
    private Drawable[]screenIcons;

    private SlidingRootNav slidingRootNav;

    String nombre;
    int id_user = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        nombre = getIntent().getStringExtra("usuario");
        id_user = getIntent().getIntExtra("id_user",id_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //slidingRootNav = new SlidingRootNavBuilder(this)
        //        .withDragDistance(180)
        //        .withRootViewScale(0.75f)
        //        .withRootViewElevation(25)
        //        .withToolbarMenuToggle(toolbar)
        //        .withMenuOpened(false)
        //        .withContentClickableWhenMenuOpened(false)
        //        .withSavedState(savedInstanceState)
        //        .withMenuLayout(R.layout.drawer_menu)
        //        .inject();

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME).setCheked(true),
                createItemFor(POS_MYPROFILE),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_HOME);
    }

    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(R.color.green)
                .withTextTint(R.color.black)
                .withSelectedIconTint(color(R.color.green))
                .withSelectedTextItem(color(R.color.green));
    }

    @ColorInt
    private int color (@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[]icons = new Drawable[ta.length()];
        for (int i = 0; i<ta.length();i++){
            int id =ta.getResourceId(i, 0);
            if (id!=0){
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position==POS_HOME){

            Bundle args = new Bundle();

            // Colocamos el String
            args.putString("usuario", nombre);
            args.putInt("id_user", id_user);

            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(args);
            transaction.replace(R.id.container, homeFragment);
        }
        else if(position==POS_MYPROFILE){
            ProfileFragment profile = new ProfileFragment();
            transaction.replace(R.id.container, profile);
        }
        else if(position==POS_ABOUT_US){
            HomeFragment homeFragment = new HomeFragment();
            transaction.replace(R.id.container, homeFragment);
        }
        else if(position==POS_LOGOUT){
            finish();
        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void recoger(){




    }
}
