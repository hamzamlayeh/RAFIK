package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.user.rafiki.R.*;

public class Inscription extends AppCompatActivity {
    EditText naisence, nom, prenom, sexe, email, pass, confirm_pass, payes, mobile;
    String name, password, conf_password, after_name, berthday, mail, sexee, payers, phone;
    String[] codes = new String[199];
    Spinner spinner;
    Intent ite;
    Liste_code_payes adapter;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_inscription);

        spinner = (Spinner) findViewById(id.code_pays);
        email = (EditText) findViewById(id.email);
        sexe = (EditText) findViewById(id.sexe);
        naisence = (EditText) findViewById(id.age);
        payes = (EditText) findViewById(id.payer);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        String restoredsexe = prefs.getString("sexe", null);
        String restoredpays = prefs.getString("Nom_Pays", null);
        String restoredcode = prefs.getString("Code_pays", null);

        remplirspinir();

        if (restoredsexe != null) {
            String sex = prefs.getString("sexe", "");//"No name defined" is the default value.
            sexe.setText(sex);

        }
        if (restoredpays != null) {
            String payss = prefs.getString("Nom_Pays", "");//"No name defined" is the default value.
            String imgp = prefs.getString("Id_img", "");//"No name defined" is the default value.
            payes.setText(" " + payss);
            payes.setCompoundDrawablesWithIntrinsicBounds(Constante.imgs[Integer.parseInt(imgp)], 0, 0, 0);

        }
        if (restoredcode != null) {
            int id_code = prefs.getInt("id_code", 0);
            spinner.setSelection(id_code);

        }

    }

    public void remplirspinir() {

        rempli_code_pays();
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putString("Code_pays", codes[i]);
                editor.putInt("id_code", i);
                editor.apply();
                String cv= String.valueOf(i);
                Toast.makeText(getApplicationContext(),cv,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void rempli_code_pays() {

        try {
            InputStream inputStream = getAssets().open("indicatif_pays.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                codes[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        nom = (EditText) findViewById(id.nom);
        prenom = (EditText) findViewById(id.prenom);
        pass = (EditText) findViewById(id.pass);
        confirm_pass = (EditText) findViewById(id.conf_pass);
        mobile = (EditText) findViewById(id.mobile);
        connecter();
    }

    private void connecter() {
        name = nom.getText().toString().trim();
        after_name = prenom.getText().toString().trim();
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        conf_password = confirm_pass.getText().toString().trim();
        berthday = naisence.getText().toString().trim();
        sexee = sexe.getText().toString().trim();
        payers = payes.getText().toString().trim();
        phone = mobile.getText().toString().trim();
        if (!valider()) {
            Toast.makeText(getApplicationContext(), "Verifier Tout les champs", Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(" " + getString(string.alert_msg1) + "\n" + " " + getString(string.alert_msg2))
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editor.putString("Nom", name);
                            editor.putString("Prenom", after_name);
                            editor.putString("Email", mail);
                            editor.putString("Password", password);
                            editor.putString("Password_conf", conf_password);
                            editor.putString("Mobile", phone);
                            editor.apply();
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
        if (sexee.isEmpty()) {
            sexe.setError("Remplir Votre Sexe");
            valide = false;
        }
        if (payers.isEmpty()) {
            payes.setError("Remplir Votre Pays");
            valide = false;
        }
        if (phone.isEmpty()) {
            mobile.setError("Taper votre N°Telephone");
            valide = false;
        }
        return valide;
    }
}
