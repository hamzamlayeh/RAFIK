package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ParametresMesures extends AppCompatActivity {

    ImageView Img_etat, Resaux,batteri,Img_lock;
    TextView textHaute, textBas,niveaubatt;
    Chronometer chronometer;
    ToggleButton btn_P_R;
    Button stop;
    ConstraintLayout constraintDistance;
    int Indice;
    long lastPause;
    SharedPreferences prefs, pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_mesures);

        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        boolean value = pref.getBoolean("connexion", false);
        Indice = prefs.getInt("Indice", 0);

        niveaubatt = findViewById(R.id.NiveauBatt);
        batteri = findViewById(R.id.batterie);
        Img_etat = findViewById(R.id.imageView10);
        Img_lock = findViewById(R.id.imageView1);
        Resaux = findViewById(R.id.imageView29);
        textHaute = findViewById(R.id.Signes);
        textBas = findViewById(R.id.BIOMETRIQUE);
        constraintDistance = findViewById(R.id.constraint_distance);
        chronometer = findViewById(R.id.simpleChronometer);
        btn_P_R = findViewById(R.id.button3);
        stop = findViewById(R.id.button5);

        if (value) {
            Resaux.setImageResource(R.drawable.resaux);
        } else {
            Resaux.setImageResource(R.drawable.resaux2);
        }
        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.quotidien);
                    Img_etat.setImageResource(R.drawable.icon_quotidien);
                    break;
                case 2:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.VISIBLE);
                    textHaute.setText(R.string.marche);
                    Img_etat.setImageResource(R.drawable.icon_marche);
                    break;
                case 3:
                    textBas.setVisibility(View.VISIBLE);
                    constraintDistance.setVisibility(View.VISIBLE);
                    textHaute.setText(R.string.course);
                    textBas.setText(R.string.a_pied);
                    Img_etat.setImageResource(R.drawable.icone_course);
                    break;
                case 4:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.cyclisme);
                    Img_etat.setImageResource(R.drawable.icone_cycle);
                    break;
                case 5:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.sommeil);
                    Img_etat.setImageResource(R.drawable.icon_sommeil);
                    break;
            }
        }
    }
    public void declancher(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        btn_P_R.setClickable(true);
        stop.setClickable(true);
    }

    public void Stop(View view) {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        lastPause = 0;
        btn_P_R.setText(R.string.pause);
        Img_lock.setImageResource(R.drawable.lock_close);
        btn_P_R.setBackgroundResource(R.drawable.button_rudus);
        btn_P_R.setChecked(false);
        btn_P_R.setClickable(false);
        startActivity(new Intent(this,DetaileCardiaque.class));
    }

    public void pause_rep(View view) {

        if (btn_P_R.isChecked()){
            //en pause
            btn_P_R.setText(R.string.reprendre);
            Img_lock.setImageResource(R.drawable.lock_open);
            btn_P_R.setBackgroundResource(R.drawable.button_rudus4);
            lastPause = SystemClock.elapsedRealtime();
            chronometer.stop();
        }else {
            //en reprendre
            chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
            chronometer.start();
            Img_lock.setImageResource(R.drawable.lock_close);
            btn_P_R.setText(R.string.pause);
            btn_P_R.setBackgroundResource(R.drawable.button_rudus);
        }
    }
    public void parammetres(View view) {
        Intent ite = new Intent(this, MenuActivity.class);
        startActivity(ite);
    }

    public void Cycles(View view) {
        Intent ite = new Intent(this, CycleActivity.class);
        startActivity(ite);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exite(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, CycleActivity.class);
            startActivity(ite);
        }
        return false;
    }
}
