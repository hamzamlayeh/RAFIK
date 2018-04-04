package com.example.user.rafiki;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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
    Drawable d,d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        d = getResources().getDrawable(R.drawable.edittext_error_style);
        d1 = getResources().getDrawable(R.drawable.edittext_style_default);
        email = findViewById(R.id.emil);
        pass = findViewById(R.id.password);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        //si vous pouvez supprimer touts les champs de table just lever le commentaire
        //ds.removetable();
        List<clients> list=ds.getAllClient();
        System.out.println(list.size());

    }
    @TargetApi(16)
    public void identifier(View view) {


        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
//        ite = new Intent(this, E8.class);
//        startActivity(ite);
        if (!valider()) {
            Toast.makeText(getApplicationContext(), "Verifier Tous les champs", Toast.LENGTH_LONG).show();
        } else {
            if (ds.verifUser(mail, password)) {
                ite = new Intent(this, E8.class);
                startActivity(ite);
                d.setVisible(false,false);
                pass.setBackground(d1);
                email.setBackground(d1);
            } else {
                Toast.makeText(this, "Email ou mot de pass Invalide", Toast.LENGTH_LONG).show();
                pass.setBackground(d);
                email.setBackground(d);
            }
        }
    }
    @TargetApi(16)
    private boolean valider() {
        boolean valide = true;
        if (mail.isEmpty() || (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
           email.setError("");
            valide = false;
            email.setBackground(d);
        }
        else
        {
            email.setBackground(d1);
        }
        if (password.isEmpty()) {
            pass.setError("");
            valide = false;
            pass.setBackground(d);
        }
        else {
            pass.setBackground(d1);
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



    public void password_lost(View view)
    {
        //Intent intent = new Intent(LoginActivity.this,PasswordLostMailSender.class);
        //startActivity(intent);
       if(ds.verifEmail(email.getText().toString()))
       {
           SendMail sm = new SendMail(this, email.getText().toString(), "Mot de passe oublié", ds.getPassword(email.getText().toString()));
           sm.execute();
       }
       else
       {
           Toast.makeText(LoginActivity.this,"Mail n'existe pas",Toast.LENGTH_LONG).show();
       }


    }
}
