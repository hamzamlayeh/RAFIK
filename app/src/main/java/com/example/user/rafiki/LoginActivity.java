package com.example.user.rafiki;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editors;
    Intent ite;
    EditText email, pass;
    String mail, password;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editors = pref.edit();

        String lang = pref.getString("lang", null);
        if (lang != null) {

            String language = pref.getString("lang", "en"); // ta langue
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration conf = getBaseContext().getResources().getConfiguration();

            conf.locale = locale;
            getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emil);
        pass = findViewById(R.id.password);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        //si vous pouvez supprimer touts les champs de table just lever le commentaire
        //ds.removetable();
        List<clients> list = ds.getAllClient();
        System.out.println(list.size());

    }

    @TargetApi(16)
    public void identifier(View view) {
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
//        ite = new Intent(this, E7.class);
//        startActivity(ite);
        if (valider()) {
            if (ds.verifUser(mail, password)) {
                ite = new Intent(this, E7.class);
                startActivity(ite);
            } else {
                Toast.makeText(this, R.string.EmailOuMotDePasseInvalide, Toast.LENGTH_LONG).show();
            }
        }
    }

    @TargetApi(16)
    private boolean valider() {
        boolean valide = true;
        if (mail.isEmpty()) {
            email.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (password.isEmpty()) {
            pass.setError(getString(R.string.champs_obligatoir));
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


    public void password_lost(View view) {
        //Intent intent = new Intent(LoginActivity.this,PasswordLostMailSender.class);
        //startActivity(intent);
        if (isOnline()) {
            if (ds.verifEmail(email.getText().toString())) {
                SendMail sm = new SendMail(this, email.getText().toString(), getString(R.string.mot_de_passe_oublie), ds.getPassword(email.getText().toString()));
                sm.execute();
            } else {
                Toast.makeText(LoginActivity.this, R.string.MailNexistePas, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, R.string.chek_internet, Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void fr(View view) {

        editors.putString("lang", "fr");  // Saving string
        // Save the changes in SharedPreferences
        editors.commit();
        String language = pref.getString("lang", "en"); // ta langue
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration conf = getBaseContext().getResources().getConfiguration();
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        recreate();

    }

    public void en(View view) {
        editors.putString("lang", "en");
        editors.commit();
        String language = pref.getString("lang", "en"); // ta langue
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration conf = getBaseContext().getResources().getConfiguration();
        conf.locale = locale;
        getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}
