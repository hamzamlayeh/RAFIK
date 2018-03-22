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
        resetvalue();
    }
    public  void resetvalue(){
        SharedPreferences.Editor editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
        editor.remove("Nom");
        editor.remove("Prenom");
        editor.remove("Age");
        editor.remove("Email");
        editor.remove("Mobile");
        editor.remove("Password");
        editor.remove("Password_conf");
        editor.remove("sexe");
        editor.remove("Nom_Pays");
        editor.remove("ID_img");
        editor.remove("Id_code");
        editor.remove("Code_pays");
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
