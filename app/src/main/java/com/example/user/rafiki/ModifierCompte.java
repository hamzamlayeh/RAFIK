package com.example.user.rafiki;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModifierCompte extends AppCompatActivity {

    EditText naisence, nom, prenom, sexe, email, pass, payes, mobile;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    List<clients> list = new ArrayList<>();
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_compte);

        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        naisence = (EditText) findViewById(R.id.age);
        payes = (EditText) findViewById(R.id.payer);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        sexe = (EditText) findViewById(R.id.sexe);
        pass = (EditText) findViewById(R.id.pass);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        list = ds.getAllClient();
        restoredUser();
    }

    public void restoredUser() {
        String restoredemail = prefs.getString("Email", "");

        if (list.get(0).getEmail().equals(restoredemail)) {
            nom.setText(list.get(0).getNom());
            prenom.setText(list.get(0).getPrenom());
            naisence.setText(list.get(0).getAge());
            payes.setText(list.get(0).getPayer());
            mobile.setText(list.get(0).getMobile());
            email.setText(list.get(0).getEmail());
            sexe.setText(list.get(0).getSexe());
            pass.setText(list.get(0).getPassword());
        }
    }

    public void get_age(View view) {
    }

    public void get_payer(View view) {
    }

    public void get_sexe(View view) {
//        Intent ite = new Intent(this, SexeActivity.class);
//        startActivity(ite);
    }

    public void modifierUser(View view) {
    }
}
