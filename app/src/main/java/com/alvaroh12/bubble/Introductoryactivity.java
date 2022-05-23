package com.alvaroh12.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Introductoryactivity extends AppCompatActivity {

    ImageView logo,splahImg;
    TextView appName;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductoryactivity);

        logo=(ImageView) findViewById(R.id.logo);
        splahImg = (ImageView) findViewById(R.id.img);
        appName = (TextView) findViewById(R.id.app_name);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie);

        splahImg.animate().translationY(-16000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(14000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(14000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(14000).setDuration(1000).setStartDelay(4000);



    }
}