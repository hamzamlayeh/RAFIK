package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Inscription extends AppCompatActivity {
    EditText naisence, nom, prenom, sexe, email, pass, confirm_pass;
    String name, password, conf_password, after_name, berthday, mail;
    Intent ite;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

         prefs = getSharedPreferences("sexe", MODE_PRIVATE);
        String restoredText = prefs.getString("sexe", null);

        email = (EditText) findViewById(R.id.email);
        sexe = (EditText) findViewById(R.id.sexe);
        naisence = (EditText) findViewById(R.id.age);

        if (restoredText != null) {
            String sex = prefs.getString("sexe", "");//"No name defined" is the default value.
            sexe.setText(sex);
        }
    }

    public void get_age(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);

    }

    public void setage(String age) {

        naisence.setText(age);
    }

    public void get_payer(View view) {

        ite = new Intent(this, Liste_payers.class);
        startActivity(ite);
    }

    public void get_sexe(View view) {
        ite = new Intent(this, SexeActivity.class);
        startActivity(ite);
    }

    public void sinscrire(View view) {
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        pass = (EditText) findViewById(R.id.pass);
        confirm_pass = (EditText) findViewById(R.id.conf_pass);
        connecter();
        SharedPreferences.Editor editor = getSharedPreferences("sexe", MODE_PRIVATE).edit();
        editor.remove("sexe");
        editor.commit();
    }

    private void connecter() {
        name = nom.getText().toString().trim();
        after_name = prenom.getText().toString().trim();
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        conf_password = confirm_pass.getText().toString().trim();
        berthday = naisence.getText().toString().trim();
//        sexee = sexe.getText().toString().trim();
        if (!valider()) {
            Toast.makeText(getApplicationContext(), "Verifier Tout les champs", Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(" " + getString(R.string.alert_msg1) + "\n" + " " + getString(R.string.alert_msg2))
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ite = new Intent(Inscription.this, Verif_code_mailActivity.class);
                            startActivity(ite);
                        }
                    }).show();
        }
    }
    private boolean valider() {
        boolean valide = true;
        if (name.isEmpty() || name.length() > 25) {
            nom.setError(" Remplir Votre nom ");
            valide = false;
        }
        if (after_name.isEmpty() || after_name.length() > 25) {
            prenom.setError("Remplir Votre prenom");
            valide = false;
        }
        if (mail.isEmpty() || (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            email.setError("Votre E-mail doit etre valide ");
            valide = false;
        }
        if (password.isEmpty()) {
            pass.setError("Remplir Votre mot de passe ");
            valide = false;
    }
        if (conf_password.isEmpty()) {
        confirm_pass.setError("Remplir Votre mot de passe de confirmation ");
        valide = false;
    }
        if (!conf_password.isEmpty() && (!conf_password.contentEquals(password))) {
            confirm_pass.setError(" Votre mot de passe n ai pas identique  ");
            valide = false;
        }
        if (berthday.isEmpty()) {
        naisence.setError("Remplir Votre Date de naissence");
        valide = false;
    }
        return valide;
    }
}
