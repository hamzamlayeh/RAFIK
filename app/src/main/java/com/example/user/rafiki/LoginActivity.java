package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Intent ite;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email= findViewById(R.id.emil);
    }
    public void identifier(View view) {
        ite=new Intent(this,E8.class);
        startActivity(ite);
    }

    public void inscrire(View view) {
        ite=new Intent(this,Inscription.class);
        startActivity(ite);
        SharedPreferences.Editor editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
        editor.remove("sexe");
        editor.remove("Nom_Pays");
        editor.remove("ID_img");
        editor.commit();
    }
    public void setEmail(String e) {

        email.setText(e);
    }

    public void monCompte(View view) {


            FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
            Compte_pop  pop= new Compte_pop();
            pop.show(manager, null);


    }
}
