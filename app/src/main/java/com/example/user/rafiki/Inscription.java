package com.example.user.rafiki;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
    clients client;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    ImageView image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_inscription);


        nom = (EditText) findViewById(id.nom);
        prenom = (EditText) findViewById(id.prenom);
        naisence = (EditText) findViewById(id.age);
        payes = (EditText) findViewById(id.payer);
        mobile = (EditText) findViewById(id.mobile);
        email = (EditText) findViewById(id.email);
        sexe = (EditText) findViewById(id.sexe);
        pass = (EditText) findViewById(id.pass);
        confirm_pass = (EditText) findViewById(id.conf_pass);
        spinner = (Spinner) findViewById(id.code_pays);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();

        remplirspinir();
        restoredvalue();
    }

    public void restoredvalue() {
        String restoredsexe = prefs.getString("sexe", null);
        String restoredpays = prefs.getString("Nom_Pays", null);
        int restoredcode = prefs.getInt("Id_code", 0);
        String restorednom = prefs.getString("Nom", null);
        String restoredprenom = prefs.getString("Prenom", null);
        String restoredage = prefs.getString("Age", null);
        String restoredemail = prefs.getString("Email", null);
        String restoredtel = prefs.getString("Mobile", null);
        String restoredpass = prefs.getString("Password", null);
        String restoredpass_conf = prefs.getString("Password_conf", null);
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
        if (restoredcode != 0) {
            int id_code = prefs.getInt("Id_code", 0);
            spinner.setSelection(id_code);

        }
        if (restorednom != null) {
            String value = prefs.getString("Nom", "");//"No name defined" is the default value.
            nom.setText(value);

        }
        if (restoredprenom != null) {
            String value = prefs.getString("Prenom", "");//"No name defined" is the default value.
            prenom.setText(value);

        }
        if (restoredage != null) {
            String value = prefs.getString("Age", "");//"No name defined" is the default value.
            naisence.setText(value);
        }
        if (restoredemail != null) {
            String value = prefs.getString("Email", "");//"No name defined" is the default value.
            email.setText(value);

        }
        if (restoredtel != null) {
            String value = prefs.getString("Mobile", "");//"No name defined" is the default value.
            mobile.setText(value);

        }

        if (restoredpass != null) {
            String value = prefs.getString("Password", "");//"No name defined" is the default value.
            pass.setText(value);

        }
        if (restoredpass_conf != null) {
            String value = prefs.getString("Password_conf", "");//"No name defined" is the default value.
            confirm_pass.setText(value);

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
                editor.putInt("Id_code", i);
                editor.apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner.setSelection(-1);
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
        editor.putString("Age", naisence.getText().toString());
    }

    public void get_payer(View view) {
        remplir_champs();
        editor.putString("Age", naisence.getText().toString());
        ite = new Intent(this, Liste_payers.class);
        startActivity(ite);

    }

    public void get_sexe(View view) {
        remplir_champs();
        editor.putString("Age", naisence.getText().toString());
        ite = new Intent(this, SexeActivity.class);
        startActivity(ite);
    }

    public void sinscrire(View view) {
        berthday = naisence.getText().toString().trim();
        sexee = sexe.getText().toString().trim();
        payers = payes.getText().toString().trim();
        name = nom.getText().toString().trim();
        after_name = prenom.getText().toString().trim();
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        conf_password = confirm_pass.getText().toString().trim();
        phone = mobile.getText().toString().trim();

        if (!valider()) {
            Toast.makeText(getApplicationContext(), "Verifier Tout les champs", Toast.LENGTH_LONG).show();
        } else {
            String fullphone = prefs.getString("Code_pays", null) + phone;
            remplir_champs();

            client = new clients(name, after_name, berthday, payers, fullphone, sexee, mail, password);
            List<clients> list = ds.getAllClient();
            if (list.size() > 2) {
                Toast.makeText(Inscription.this, "Vous avez depasser 3 comptes ", Toast.LENGTH_LONG).show();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(Inscription.this);
                alert.setMessage(" " + getString(string.alert_msg1) + "\n" + " " + getString(string.alert_msg2))
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long ids = ds.addClient(client);
                                if (ids == -1) {
                                    Toast.makeText(Inscription.this, "Ereur dans l insertion", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Inscription.this, "Insertion terminer", Toast.LENGTH_LONG).show();
                                    ite = new Intent(Inscription.this, Verif_code_mailActivity.class);
                                    startActivity(ite);
                                }
                            }

                        }).show();
            }
        }
    }

    public void remplir_champs() {
        editor.putString("Nom", nom.getText().toString().trim());
        editor.putString("Prenom", prenom.getText().toString().trim());
        editor.putString("Email", email.getText().toString().trim());
        editor.putString("Password", pass.getText().toString().trim());
        editor.putString("Password_conf", confirm_pass.getText().toString().trim());
        editor.putString("Mobile", mobile.getText().toString().trim());
        editor.apply();
    }

    @TargetApi(16)
    private boolean valider() {

        Drawable d = getResources().getDrawable(drawable.edittext_error_style);
        Drawable d1 = getResources().getDrawable(drawable.edittext_style_default);
        boolean valide = true;
        if (name.isEmpty() || name.length() > 25) {
            nom.setError("");
            nom.setBackground(d);
            valide = false;
        }
        else
        {
            nom.setBackground(d1);

        }
        if (after_name.isEmpty() || after_name.length() > 25) {
            prenom.setError("");
            prenom.setBackground(d);
            valide = false;
        }
        else
        {
            prenom.setBackground(d1);
        }
        if(mail.isEmpty() || (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            email.setError("");
            email.setBackground(d);
            valide = false;
        }
        else
        {
            email.setBackground(d1);
        }
        if (ds.verifEmail(mail)) {
            email.setError(getString(string.chekmail));
            valide = false;
        }
        if (password.isEmpty()) {
            pass.setError("");
            pass.setBackground(d);
            valide = false;
        }
        else
        {
            pass.setBackground(d1);
        }
        if (!password.isEmpty() && (password.length() < 6)) {
            pass.setError("");
            pass.setBackground(d);
            valide = false;
        }else{

            pass.setBackground(d1);
        }
        if (conf_password.isEmpty()) {
            confirm_pass.setError("");
            confirm_pass.setBackground(d);
            valide = false;
        }
        else
        {
            confirm_pass.setBackground(d1);
        }
        if (!conf_password.isEmpty() && (!conf_password.contentEquals(password))) {
            confirm_pass.setError(getString(string.err_pass2));
            confirm_pass.setBackground(d);
            valide = false;
        }else {

            confirm_pass.setBackground(d1);
        }
        if (berthday.isEmpty()) {
            naisence.setError("");
            naisence.setBackground(d);
            valide = false;
        }
        else
        {
            naisence.setBackground(d1);
        }
        if (sexee.isEmpty()) {
            sexe.setError("");
            valide = false;
            sexe.setBackground(d);
        }
        else
        {
            sexe.setBackground(d1);
        }
        if (payers.isEmpty()) {
            payes.setError("");
            valide = false;
            payes.setBackground(d);
        }
        else
        {
            payes.setBackground(d1);
        }
        if (phone.isEmpty()) {
            mobile.setError("");
            valide = false;
            mobile.setBackground(d);
        }
        else
        {
            mobile.setBackground(d1);
        }
        if (spinner.getSelectedItemPosition() == -1) {

            valide = false;
        }
        return valide;
    }
}
