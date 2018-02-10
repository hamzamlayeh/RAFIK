package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    Intent ite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void identifier(View view) {
        ite=new Intent(this,E8.class);
        startActivity(ite);
    }

    public void inscrire(View view) {
        ite=new Intent(this,Inscription.class);
        startActivity(ite);
        SharedPreferences.Editor editor = getSharedPreferences("sexe", MODE_PRIVATE).edit();
        editor.remove("sexe");
        editor.commit();
    }


}
