package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    EditText NomUtilisateur;
    SharedPreferences pref;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("Inscription", MODE_PRIVATE);

        NomUtilisateur=findViewById(R.id.nom);

        String email = pref.getString("Email", "");

        NomUtilisateur.setText(ds.getNom(email));
    }

    public void E8(View view) {
        Intent ite = new Intent(this, E8.class);
        startActivity(ite);
    }

    public void donnees_pers(View view) {
        Intent ite = new Intent(this, ModifierCompte.class);
        startActivity(ite);
    }

    public void fiche(View view) {
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
    }

    public void contacts(View view) {
        Intent ite = new Intent(this, ContactsActivity.class);
        startActivity(ite);
    }

    public void alertes(View view) {
        Intent ite = new Intent(this, ParametreAlertes.class);
        startActivity(ite);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void deconnecter(View view) {
        Intent ite = new Intent(this, LoginActivity.class);
        startActivity(ite);
        this.finishAffinity();

    }


}
