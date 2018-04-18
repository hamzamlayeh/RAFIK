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
    ImageView coeur;
    TextView bpm;
    ImageView poumon;
    TextView rpm;
    TextView temps;
    TextView oxy;
    final Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);

        //Toast.makeText(getApplicationContext(),E7_2.str,Toast.LENGTH_LONG).show();

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        coeur=(ImageView)findViewById(R.id.Coeur);
        bpm = (TextView)findViewById(R.id.BPM_D);
        poumon=(ImageView)findViewById(R.id.poumon);
        rpm = (TextView)findViewById(R.id.RPM_D);
        temps = (TextView)findViewById(R.id.TEMP_D);
        oxy = (TextView)findViewById(R.id.oxigen);
        coeur.startAnimation(animation2);
        poumon.startAnimation(animation2);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int x = rand.nextInt(120);
                coeur.startAnimation(animation);
                bpm.setText(E7_2.str[2]+"");
                poumon.startAnimation(animation);
                rpm.setText(E7_2.str[3]+"");
                temps.setText(E7_2.str[4]+"");
                oxy.setText(E7_2.str[5]+"%");
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
                int x = rand.nextInt(120);
                bpm.setText(E7_2.str[2]+"");
                poumon.startAnimation(animation2);
                rpm.setText(E7_2.str[3]+"");
                temps.setText(E7_2.str[4]+"");
                oxy.setText(E7_2.str[5]+"%");
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
