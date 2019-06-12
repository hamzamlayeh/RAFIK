package com.example.user.rafiki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.example.user.rafiki.ItemData.MailBody;

import java.util.ArrayList;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editors;
    Intent ite;
    EditText email, pass;
    String mail, password;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Toast.makeText(this, ""+ DebugDB.getAddressLog(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, TestValeurTrame(10,10), Toast.LENGTH_SHORT).show();

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
        }else {
            editors.putString("lang", "en");
            editors.apply();
        }
        setContentView(R.layout.activity_login);
        context=this;
        email = findViewById(R.id.nom);
        pass = findViewById(R.id.password);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        //si vous pouvez supprimer touts les champs de table just lever le commentaire
        //ds.removetable();
       // List list = ds.getAllClient();

    }

    public void identifier(View view) {
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
//        ite = new Intent(this, MainActivity.class);
//        startActivity(ite);
        if (valider()) {
            SharedPreferences.Editor editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
            if (ds.verifUser(mail, password)) {
                editor.putString("Email", mail);
                editor.apply();
                ite = new Intent(this, E7.class);
                startActivity(ite);
            } else {
                Toast.makeText(this, R.string.EmailOuMotDePasseInvalide, Toast.LENGTH_LONG).show();
            }
        }

//ds.removetable();
 }

    private boolean valider() {
        boolean valide = true;
        if (mail.isEmpty()) {
            email.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if(!mail.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            email.setError(getString(R.string.email_invalide));
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
        editor.remove("Poid");
        editor.remove("Code_pays");
        editor.apply();
    }


    public void password_lost(View view) {
        if (isOnline()) {
            if (ds.verifEmail(email.getText().toString())) {
                SendMail sm = new SendMail(this, email.getText().toString(), getString(R.string.mot_de_passe_oublie),
                        MailBody.getBody(ds.getPassword(email.getText().toString()),context));
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
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
//    public static int hexToLong(String hex) {
//        return Integer.parseInt(hex, 16);
//    }
//    public static String TestValeurTrame(int dec, int dec2) {
//        return Integer.toHexString(dec2) + Integer.toHexString(dec);
//    }
}
