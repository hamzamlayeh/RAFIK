package com.example.user.rafiki;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
public class E8 extends AppCompatActivity {

    Intent intent;
    Animation animation,animation2;
    ImageView coeur,poumon, batteri;
    TextView bpm,rpm,temps,oxy,niveaubatt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);

        //Toast.makeText(getApplicationContext(),E7_2.str,Toast.LENGTH_LONG).show();
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        coeur=(ImageView)findViewById(R.id.Coeur);
        batteri=(ImageView)findViewById(R.id.batterie);
        bpm = (TextView)findViewById(R.id.BPM_D);
        poumon=(ImageView)findViewById(R.id.poumon);
        rpm = (TextView)findViewById(R.id.RPM_D);
        temps = (TextView)findViewById(R.id.TEMP_D);
        oxy = (TextView)findViewById(R.id.oxigen);
        niveaubatt = (TextView)findViewById(R.id.NiveauBatt);
        coeur.startAnimation(animation2);
        poumon.startAnimation(animation2);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                coeur.startAnimation(animation);
                bpm.setText(E7_2.str[2]+"");
                poumon.startAnimation(animation);
                rpm.setText(E7_2.str[3]+"");
                temps.setText(E7_2.str[4]+"");
                oxy.setText(E7_2.str[5]+"%");
                niveaubatt.setText(E7_2.str[6]+"%");
                if (E7_2.str[6]==0){
                    batteri.setImageResource(R.drawable.batt7);
                }else if (E7_2.str[6]>=1 && E7_2.str[6]<=13){
                    batteri.setImageResource(R.drawable.batt6);

                }else if (E7_2.str[6]>13 && E7_2.str[6]<=25){
                    batteri.setImageResource(R.drawable.batt5);

                }else if (E7_2.str[6]>25 && E7_2.str[6]<=38){
                    batteri.setImageResource(R.drawable.batt4);

                }else if (E7_2.str[6]>38 && E7_2.str[6]<=50){
                    batteri.setImageResource(R.drawable.batt3);

                }else if (E7_2.str[6]>50 && E7_2.str[6]<=75){
                    batteri.setImageResource(R.drawable.batt2);

                }else if (E7_2.str[6]>76 && E7_2.str[6]<=100){
                    batteri.setImageResource(R.drawable.batt1);

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coeur.startAnimation(animation2);
                bpm.setText(E7_2.str[2]+"");
                poumon.startAnimation(animation2);
                rpm.setText(E7_2.str[3]+"");
                temps.setText(E7_2.str[4]+"");
                oxy.setText(E7_2.str[5]+"%");
                niveaubatt.setText(E7_2.str[6]+"%");
                if (E7_2.str[6]==0){
                    batteri.setImageResource(R.drawable.batt7);
                }else if (E7_2.str[6]>1 && E7_2.str[6]<=12.5){
                    batteri.setImageResource(R.drawable.batt6);

                }else if (E7_2.str[6]>12.5 && E7_2.str[6]<=25){
                    batteri.setImageResource(R.drawable.batt5);

                }else if (E7_2.str[6]>25 && E7_2.str[6]<=37.5){
                    batteri.setImageResource(R.drawable.batt4);

                }else if (E7_2.str[6]>37.5 && E7_2.str[6]<=50){
                    batteri.setImageResource(R.drawable.batt3);

                }else if (E7_2.str[6]>50 && E7_2.str[6]<=62.5){
                    batteri.setImageResource(R.drawable.batt2);

                }else if (E7_2.str[6]>62.5 && E7_2.str[6]<=100){
                    batteri.setImageResource(R.drawable.batt1);

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    public void E10(View view) {
        intent = new Intent(this, E10.class);
        startActivity(intent);
    }


    public void E12(View view) {
        intent = new Intent(this, E12.class);
        startActivity(intent);
    }

    public void E11(View view) {
        intent = new Intent(this, E11.class);
        startActivity(intent);
    }

    public void E9(View view) {
        intent = new Intent(this, E9.class);
        startActivity(intent);
    }

    public void exite(View view) {
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
