package com.example.user.rafiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class E8 extends AppCompatActivity {

    Intent intent;
    Animation animation,animation2;
ImageView coeur;
TextView bpm;
    final Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);


        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        coeur=(ImageView)findViewById(R.id.Coeur);
        bpm = (TextView)findViewById(R.id.BPM_D);
        coeur.startAnimation(animation2);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int x = rand.nextInt(120);
                coeur.startAnimation(animation);
                bpm.setText(x+"");
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
                bpm.setText(x+"");
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
