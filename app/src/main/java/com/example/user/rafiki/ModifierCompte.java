package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Constante;
import com.example.user.rafiki.ItemData.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ModifierCompte extends AppCompatActivity {

    EditText naisence, nom, prenom, sexe, email, pass, payes, mobile, confirm_pass;
    String name, password, after_name, berthday, mail, sexee, payers, phone, conf_password;
    Spinner spinner;
    static int Indice_Pays;
    Liste_code_payes adapter;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    clients client;
    List<clients> list = new ArrayList<clients>();
    String[] codes = new String[199];
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_compte);
        Inscription.NUM_PAGE = 2;
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        naisence = (EditText) findViewById(R.id.age);
        payes = (EditText) findViewById(R.id.payer);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        sexe = (EditText) findViewById(R.id.sexe);
        pass = (EditText) findViewById(R.id.pass);
        confirm_pass = (EditText) findViewById(R.id.conf_pass);
        spinner = (Spinner) findViewById(R.id.code_pays);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 1);
        ds = new UserDataSource(helper);
        list = ds.getAllClient();
        remplirspinir();
        restoredUser();
        restoredvalue();
    }

    public void restoredUser() {
        String restoredemail = prefs.getString("Email", "");
        if (list.size() >= 1) {
            if (list.get(0).getEmail().equals(restoredemail)) {
                nom.setText(list.get(0).getNom());
                prenom.setText(list.get(0).getPrenom());
                naisence.setText(list.get(0).getAge());
                payes.setText(" " + list.get(0).getPayer());
                payes.setCompoundDrawablesWithIntrinsicBounds(Constante.imgs[Indice_Pays], 0, 0, 0);
                mobile.setText(list.get(0).getMobile());
                email.setText(list.get(0).getEmail());
                sexe.setText(list.get(0).getSexe());
                pass.setText(list.get(0).getPassword());
                confirm_pass.setText(list.get(0).getPassword());
                spinner.setSelection(Indice_Pays);
            }
        }
    }

    public void restoredvalue() {
        String restoredsexe = prefs.getString("sexe", null);
        String restoredpays = prefs.getString("Nom_Pays", null);
        String restoredage = prefs.getString("Age", null);

        if (restoredsexe != null) {
            String sex = prefs.getString("sexe", "");
            sexe.setText(sex);
        }
        if (restoredpays != null) {
            String payss = prefs.getString("Nom_Pays", "");//"No name defined" is the default value.
            String imgp = prefs.getString("Id_img", "");//"No name defined" is the default value.
            payes.setText(" " + payss);
            payes.setCompoundDrawablesWithIntrinsicBounds(Constante.imgs[Integer.parseInt(imgp)], 0, 0, 0);
            spinner.setSelection(Integer.parseInt(imgp));
        }
        if (restoredage != null) {
            naisence.setText(restoredage);
        }
        if (restoredage != null) {
            naisence.setText(restoredage);
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
                if (ligne.equals(list.get(0).getCode())) {
                    Indice_Pays = x;
                }
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
        naisence.setError(null);
        editor.putString("Age", age);
        editor.apply();
    }


    public void get_payer(View view) {
        Intent ite = new Intent(this, Liste_payers.class);
        startActivity(ite);
    }

    public void get_sexe(View view) {
        Intent ite = new Intent(this, SexeActivity.class);
        startActivity(ite);
    }

    public void modifierUser(View view) {
        berthday = naisence.getText().toString().trim();
        sexee = sexe.getText().toString().trim();
        payers = payes.getText().toString().trim();
        name = nom.getText().toString().trim();
        after_name = prenom.getText().toString().trim();
        mail = email.getText().toString().trim();
        password = pass.getText().toString().trim();
        conf_password = confirm_pass.getText().toString().trim();
        phone = mobile.getText().toString().trim();

        if (valider()) {
            String codephone = prefs.getString("Code_pays", null);
            client = new clients(name, after_name, berthday, payers, phone, codephone, sexee, mail, password);
            if (ds.updateClient(list.get(0).get_id(), client)) {
                Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
                editor.putString("Email", mail);
                editor.apply();
                Intent ite = new Intent(this, MenuActivity.class);
                startActivity(ite);
            } else {
                Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean valider() {

        boolean valide = true;
        if (name.isEmpty() || name.length() > 25) {
            nom.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (after_name.isEmpty() || after_name.length() > 25) {
            prenom.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (mail.isEmpty()) {
            email.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (!mail.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            email.setError(getString(R.string.email_invalide));
            valide = false;
        }
        if (password.isEmpty()) {
            pass.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (!password.isEmpty() && (password.length() < 6)) {
            pass.setError(getString(R.string.err_pass_caractaire));
            valide = false;
        }
        if (conf_password.isEmpty()) {
            confirm_pass.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (!conf_password.isEmpty() && (!conf_password.contentEquals(password))) {
            confirm_pass.setError(getString(R.string.err_pass2));
            valide = false;
        }
        if (berthday.isEmpty()) {
            naisence.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (sexee.isEmpty()) {
            sexe.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (payers.isEmpty()) {
            payes.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (phone.isEmpty()) {
            mobile.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (spinner.getSelectedItemPosition() == -1) {

            valide = false;
        }
        return valide;
    }
}
