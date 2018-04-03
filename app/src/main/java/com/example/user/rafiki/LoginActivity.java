package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Intent ite;
    EditText email, pass;
    String mail, password;
    MySQLiteOpenHelper helper;
    UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emil);
        pass = findViewById(R.id.password);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        //si vous pouvez supprimer touts les champs de table just lever le commentaire
        //ds.removetable();
        List<clients> list=ds.getAllClient();
        System.out.println(list.size());

    }

    public void identifier(View view) {
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        if (!valider()) {
            Toast.makeText(getApplicationContext(), "Vérifier tous les champs", Toast.LENGTH_LONG).show();
        } else {
            if (ds.verifUser(mail, password)) {
                ite = new Intent(this, E8.class);
                startActivity(ite);
            } else {
                Toast.makeText(this, "Email ou mot de pass invalide", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean valider() {

        boolean valide = true;
        if (mail.isEmpty() || (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            email.setError(getString(R.string.err_mail));
            valide = false;
        }
        if (password.isEmpty()) {
            pass.setError(getString(R.string.err_pass));
            valide = false;
        }
        return valide;
    }

    public void inscrire(View view) {
        ite = new Intent(this, Inscription.class);
        startActivity(ite);
        resetvalue();
    }

    public void resetvalue() {
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
//    public void setEmail(String e) {
//
//        email.setText(e);
//    }

    public void monCompte(View view) {


//            FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
//            Compte_pop  pop= new Compte_pop();
//            pop.show(manager, null);


    }
}
