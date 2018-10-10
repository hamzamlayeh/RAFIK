package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Constante;
import com.example.user.rafiki.ItemData.Contacts_Medecins;
import com.example.user.rafiki.ItemData.Contacts_Parentaux;
import com.tuyenmonkey.mkloader.MKLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContactMedcin extends AppCompatActivity {

    EditText Nom, Prenom, Mobile, Email, Hopital;
    EditText Nom2, Prenom2, Mobile2, Email2, Hopital2;
    EditText Nom3, Prenom3, Mobile3, Email3, Hopital3;
    Spinner Spinner, Spinner2, Spinner3;
    String Code, Code2, Code3, mail, mail2, mail3;
    LinearLayout L1, L2, L3;
    String[] codes = new String[199];
    Liste_code_payes adapter;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    List<Contacts_Medecins> listContacts_M = new ArrayList<Contacts_Medecins>();
    MKLoader mkLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_medcin);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        Nom = findViewById(R.id.nom);
        Prenom = findViewById(R.id.prenom);
        Mobile = findViewById(R.id.mobile);
        Email = findViewById(R.id.email);
        Hopital = findViewById(R.id.hobital);
        Nom2 = findViewById(R.id.nom1);
        Prenom2 = findViewById(R.id.prenom1);
        Mobile2 = findViewById(R.id.mobile1);
        Email2 = findViewById(R.id.email1);
        Hopital2 = findViewById(R.id.hobital1);
        Nom3 = findViewById(R.id.nom2);
        Prenom3 = findViewById(R.id.prenom2);
        Mobile3 = findViewById(R.id.mobile2);
        Email3 = findViewById(R.id.email2);
        Hopital3 = findViewById(R.id.hobital2);
        Spinner = findViewById(R.id.spinner);
        Spinner2 = findViewById(R.id.spinner1);
        Spinner3 = findViewById(R.id.spinner2);
        L1 = findViewById(R.id.l1);
        L2 = findViewById(R.id.l2);
        L3 = findViewById(R.id.l3);
        mkLoader = findViewById(R.id.alerr);
        remplirspinir();

        if (ds.getCountMedecins() > 0) {
            listContacts_M = ds.getListMedecins();
            Nom.setText(listContacts_M.get(0).getNom());
            Prenom.setText(listContacts_M.get(0).getPrenom());
            Mobile.setText(listContacts_M.get(0).getMobile());
            Email.setText(listContacts_M.get(0).getEmail());
            Hopital.setText(listContacts_M.get(0).getHopital());
            Spinner.setSelection(TrouverIndice(listContacts_M.get(0).getCode()));

            Nom2.setText(listContacts_M.get(1).getNom());
            Prenom2.setText(listContacts_M.get(1).getPrenom());
            Mobile2.setText(listContacts_M.get(1).getMobile());
            Email2.setText(listContacts_M.get(1).getEmail());
            Hopital2.setText(listContacts_M.get(1).getHopital());
            Spinner2.setSelection(TrouverIndice(listContacts_M.get(1).getCode()));

            Nom3.setText(listContacts_M.get(2).getNom());
            Prenom3.setText(listContacts_M.get(2).getPrenom());
            Mobile3.setText(listContacts_M.get(2).getMobile());
            Email3.setText(listContacts_M.get(2).getEmail());
            Hopital3.setText(listContacts_M.get(2).getHopital());
            Spinner3.setSelection(TrouverIndice(listContacts_M.get(2).getCode()));
        }
        suppCarde1();
        suppCarde2();
        suppCarde3();
    }

    public void suppCarde1() {
        L1.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom.setText("");
                Prenom.setText("");
                Mobile.setText("");
                Email.setText("");
                Hopital.setText("");
                Spinner.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 1);
                return false;
            }
        });
        L2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom2.setText("");
                Prenom2.setText("");
                Mobile2.setText("");
                Email2.setText("");
                Hopital2.setText("");
                Spinner2.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 2);
                return false;
            }
        });
        L3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom3.setText("");
                Prenom3.setText("");
                Mobile3.setText("");
                Email3.setText("");
                Hopital3.setText("");
                Spinner3.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 3);
                return false;
            }
        });
        Nom.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom.setText("");
                Prenom.setText("");
                Mobile.setText("");
                Email.setText("");
                Hopital.setText("");
                Spinner.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 1);
                return false;
            }
        });
        Prenom.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom.setText("");
                Prenom.setText("");
                Mobile.setText("");
                Email.setText("");
                Hopital.setText("");
                Spinner.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", codes[0], "", ""), 1);
                return false;
            }
        });
        Email.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom.setText("");
                Prenom.setText("");
                Mobile.setText("");
                Email.setText("");
                Hopital.setText("");
                Spinner.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 1);
                return false;
            }
        });
        Mobile.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom.setText("");
                Prenom.setText("");
                Mobile.setText("");
                Email.setText("");
                Hopital.setText("");
                Spinner.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 1);
                return false;
            }
        });
        Hopital.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom.setText("");
                Prenom.setText("");
                Mobile.setText("");
                Email.setText("");
                Hopital.setText("");
                Spinner.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 1);
                return false;
            }
        });
    }

    public void suppCarde2() {
        Nom2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom2.setText("");
                Prenom2.setText("");
                Mobile2.setText("");
                Email2.setText("");
                Hopital2.setText("");
                Spinner2.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 2);
                return false;
            }
        });
        Prenom2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom2.setText("");
                Prenom2.setText("");
                Mobile2.setText("");
                Email2.setText("");
                Hopital2.setText("");
                Spinner2.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 2);
                return false;
            }
        });
        Email2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom2.setText("");
                Prenom2.setText("");
                Mobile2.setText("");
                Email2.setText("");
                Hopital2.setText("");
                Spinner2.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 2);
                return false;
            }
        });
        Mobile2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom2.setText("");
                Prenom2.setText("");
                Mobile2.setText("");
                Email2.setText("");
                Hopital2.setText("");
                Spinner2.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 2);
                return false;
            }
        });
        Hopital2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom2.setText("");
                Prenom2.setText("");
                Mobile2.setText("");
                Email2.setText("");
                Hopital2.setText("");
                Spinner2.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 2);
                return false;
            }
        });
    }

    public void suppCarde3() {
        Nom3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom3.setText("");
                Prenom3.setText("");
                Mobile3.setText("");
                Email3.setText("");
                Hopital3.setText("");
                Spinner3.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 3);
                return false;
            }
        });
        Prenom3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom3.setText("");
                Prenom3.setText("");
                Mobile3.setText("");
                Email3.setText("");
                Hopital3.setText("");
                Spinner3.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 3);
                return false;
            }
        });
        Email3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom3.setText("");
                Prenom3.setText("");
                Mobile3.setText("");
                Email3.setText("");
                Hopital3.setText("");
                Spinner3.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 3);
                return false;
            }
        });
        Mobile3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom3.setText("");
                Prenom3.setText("");
                Mobile3.setText("");
                Email3.setText("");
                Hopital3.setText("");
                Spinner3.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 3);
                return false;
            }
        });
        Hopital3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Nom3.setText("");
                Prenom3.setText("");
                Mobile3.setText("");
                Email3.setText("");
                Hopital3.setText("");
                Spinner3.setSelection(0);
                long x = ds.UpdateMedecins(new Contacts_Medecins("", "", "", "", "", ""), 3);
                return false;
            }
        });
    }

    public void remplirspinir() {

        rempli_code_pays();
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spinner.setAdapter(adapter);
        Spinner2.setAdapter(adapter);
        Spinner3.setAdapter(adapter);
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Code = codes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Code2 = codes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Code3 = codes[i];
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

    public int TrouverIndice(String item) {
        int i = 0;
        for (String code : codes) {
            if (code.equals(item)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    public void retoure(View view) {
        mail = Email.getText().toString();
        mail2 = Email2.getText().toString();
        mail3 = Email3.getText().toString();
        if (valider()) {
            listContacts_M.add(0, new Contacts_Medecins(Nom.getText().toString(), Prenom.getText().toString(),
                    Mobile.getText().toString(), Code, mail, Hopital.getText().toString()));
            listContacts_M.add(1, new Contacts_Medecins(Nom2.getText().toString(), Prenom2.getText().toString(),
                    Mobile2.getText().toString(), Code2, mail2, Hopital2.getText().toString()));
            listContacts_M.add(2, new Contacts_Medecins(Nom3.getText().toString(), Prenom3.getText().toString(),
                    Mobile3.getText().toString(), Code3, mail3, Hopital3.getText().toString()));

            if (ds.getCountMedecins() <= 0) {
                mkLoader.setVisibility(View.VISIBLE);
                int i = 0;
                while (i < listContacts_M.size()) {

                    long x = ds.addMedecins(listContacts_M.get(i));
                    i++;
                }
                Intent ite = new Intent(this, ContactsActivity.class);
                startActivity(ite);
                ContactMedcin.this.finish();
            } else {
                mkLoader.setVisibility(View.VISIBLE);
                int i = 0;
                while (i < listContacts_M.size()) {

                    long x = ds.UpdateMedecins(listContacts_M.get(i), i + 1);
                    i++;
                }
                Intent ite = new Intent(this, ContactsActivity.class);
                startActivity(ite);
                ContactMedcin.this.finish();
            }
        }
    }

    private boolean valider() {
        boolean valide = true;
        if (!mail.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())) {
            Email.setError(getString(R.string.email_invalide));
            valide = false;
        }
        if (!mail2.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail2).matches())) {
            Email2.setError(getString(R.string.email_invalide));
            valide = false;
        }
        if (!mail3.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail3).matches())) {
            Email3.setError(getString(R.string.email_invalide));
            valide = false;
        }
        return valide;
    }

    public void alert(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" ").setIcon(R.drawable.alert)
                .setMessage(getString(R.string.modifier) + "       " + getString(R.string.cliquer_sur_medecins) + "\n" +
                        getString(R.string.supprimer) + "   " + getString(R.string.appui_long_sur_medecins)
                )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, ContactsActivity.class);
            startActivity(ite);
        }
        return false;
    }
}
