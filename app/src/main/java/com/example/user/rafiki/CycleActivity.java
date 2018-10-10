package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class CycleActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);
        pref = getApplicationContext().getSharedPreferences("Cycle", MODE_PRIVATE);
        editor = pref.edit();
    }

    public void Precedent(View view) {
        Intent ite = new Intent(this, E8.class);
        startActivity(ite);
    }

    public void Go_quotidien(View view) {
        editor.putInt("Indice", 1);
        editor.apply();
        Intent ite = new Intent(this, ParametresMesures.class);
        startActivity(ite);
    }

    public void Go_marche(View view) {
        editor.putInt("Indice", 2);
        editor.apply();
        Intent ite = new Intent(this, ParametresMesures.class);
        startActivity(ite);
    }

    public void Go_course(View view) {
        editor.putInt("Indice", 3);
        editor.apply();
        Intent ite = new Intent(this, ParametresMesures.class);
        startActivity(ite);
    }

    public void Go_cyclisme(View view) {
        editor.putInt("Indice", 4);
        editor.apply();
        Intent ite = new Intent(this, ParametresMesures.class);
        startActivity(ite);
    }

    public void Go_sommeil(View view) {
        editor.putInt("Indice", 5);
        editor.apply();
        Intent ite = new Intent(this, ParametresMesures.class);
        startActivity(ite);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, E8.class);
            startActivity(ite);
        }
        return false;
    }
}
