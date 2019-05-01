package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Alerts;
import com.example.user.rafiki.ItemData.Constante;
import com.example.user.rafiki.ItemData.SeuilValues;
import com.example.user.rafiki.ItemData.clients;
import com.tuyenmonkey.mkloader.MKLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.example.user.rafiki.R.id;
import static com.example.user.rafiki.R.layout;
import static com.example.user.rafiki.R.string;

public class Inscription extends AppCompatActivity {
    EditText naisence, nom, prenom, sexe, email, poid, pass, confirm_pass, payes, mobile;
    String name, password, conf_password, after_name, berthday, mail, sexee, payers, phone, Poid;
    String[] codes = new String[199];
    Spinner spinner;
    Intent ite;
    Liste_code_payes adapter;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    clients client;
    SeuilValues seuilValues;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    int Age ;
    float FCmarche_X,FCcourse_X,FCactivite_X,FCsommeil_X;
    public static int idc = -1, NUM_PAGE = 1;
    MKLoader mkLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_inscription);

        nom = findViewById(id.nom);
        prenom = findViewById(id.prenom);
        naisence = findViewById(id.age);
        payes = findViewById(id.payer);
        mobile = findViewById(id.mobile);
        email = findViewById(id.email);
        sexe = findViewById(id.sexe);
        pass = findViewById(id.pass);
        poid = findViewById(id.poid);
        confirm_pass = findViewById(id.conf_pass);
        spinner = findViewById(id.code_pays);
        mkLoader = findViewById(R.id.alerr);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();

        remplirspinir();
        restoredvalue();
    }

    public void restoredvalue() {
        String restoredsexe = prefs.getString("sexe", null);
        String restoredpays = prefs.getString("Nom_Pays", null);
        String restorednom = prefs.getString("Nom", null);
        String restoredprenom = prefs.getString("Prenom", null);
        String restoredage = prefs.getString("Age", null);
        String restoredemail = prefs.getString("Email", null);
        String restoredtel = prefs.getString("Mobile", null);
        String restoredpass = prefs.getString("Password", null);
        String restoredpass_conf = prefs.getString("Password_conf", null);
        String restoredpoid = prefs.getString("Poid", null);
        if (restoredsexe != null) {
            String sex = prefs.getString("sexe", "");//"No name defined" is the default value.
            sexe.setText(sex);
        }
        if (restoredpays != null) {
            String payss = prefs.getString("Nom_Pays", "");//"No name defined" is the default value.
            String imgp = prefs.getString("Id_img", "");//"No name defined" is the default value.
            payes.setText(" " + payss);
            payes.setCompoundDrawablesWithIntrinsicBounds(Constante.imgs[Integer.parseInt(imgp)], 0, R.drawable.flash, 0);
        }
        if (idc != -1) {
            spinner.setSelection(idc);

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
        if (restoredpoid != null) {
            String value = prefs.getString("Poid", "");
            poid.setText(value);
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
                idc = i;
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
        try {
            Age = Integer.parseInt(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        naisence.setError(null);
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
        Poid = poid.getText().toString().trim();

        if (valider()) {
            String codephone = prefs.getString("Code_pays", null);
            remplir_champs();
           try {
               Age = Integer.parseInt(berthday);
           }catch (Exception e){
               e.printStackTrace();
           }
            client = new clients(name, after_name, berthday, payers, phone, codephone, sexee, mail,Poid, password);
            FCmarche_X = (float) ((208 - (0.7 * Age)) * 0.7);
            FCcourse_X = (float) ((208 - (0.7 * Age)) * 0.9);
            FCactivite_X = (float) ((208 - (0.7 * Age)) * 0.6);
            FCsommeil_X = (float) ((208 - (0.7 * Age)) * 0.6);

            seuilValues = new SeuilValues(String.valueOf((int)Math.floor(FCmarche_X + 0.5f)), String.valueOf((int)Math.floor(FCcourse_X + 0.5f)),
                    String.valueOf((int)Math.floor(FCactivite_X + 0.5f)), String.valueOf( (int)Math.floor(FCsommeil_X + 0.5f)));
            List list = ds.getAllClient();
            if (list.size() > 0) {
                Toast.makeText(Inscription.this, string.nbCompt, Toast.LENGTH_LONG).show();
            } else {
                long ids = ds.addClient(client);
                if (ids == -1) {
                    Toast.makeText(Inscription.this, string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    long id = ds.addSeuils(seuilValues);
                    if (id == -1) {
                        Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                    } else {
                        ds.addAlert(new Alerts());
                        mkLoader.setVisibility(View.VISIBLE);
                        Toast.makeText(Inscription.this, string.InsertionTerminer, Toast.LENGTH_LONG).show();
                        ite = new Intent(Inscription.this, LoginActivity.class);
                        startActivity(ite);
                    }
                }
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
        editor.putString("Poid", poid.getText().toString().trim());
        editor.apply();
    }

    private boolean valider() {

        boolean valide = true;
        if (name.isEmpty() || name.length() > 25) {
            nom.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (after_name.isEmpty() || after_name.length() > 25) {
            prenom.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (mail.isEmpty()) {
            email.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (!mail.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            email.setError(getString(string.email_invalide));
            valide = false;
        }
        if (ds.verifEmail(mail)) {
            email.setError(getString(string.chekmail));
            valide = false;
        }
        if (password.isEmpty()) {
            pass.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (!password.isEmpty() && (password.length() < 6)) {
            pass.setError(getString(string.err_pass_caractaire));
            valide = false;
        }
        if (conf_password.isEmpty()) {
            confirm_pass.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (!conf_password.isEmpty() && (!conf_password.contentEquals(password))) {
            confirm_pass.setError(getString(string.err_pass2));
            valide = false;
        }
        if (berthday.isEmpty()) {
            naisence.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (sexee.isEmpty()) {
            sexe.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (payers.isEmpty()) {
            payes.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (phone.isEmpty()) {
            mobile.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (Poid.isEmpty()) {
            poid.setError(getString(string.champs_obligatoir));
            valide = false;
        }
        if (spinner.getSelectedItemPosition() == -1) {

            valide = false;
        }
        return valide;
    }
}