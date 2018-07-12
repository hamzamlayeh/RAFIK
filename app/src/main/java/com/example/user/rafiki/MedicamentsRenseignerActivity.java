package com.example.user.rafiki;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Fiche;

public class MedicamentsRenseignerActivity extends AppCompatActivity {

    EditText Nom_medica,Nb_prise_Ma,Nb_prise_Mi,Nb_prise_S,Date_debut,Date_fin;
    String nom_medica,nb_prise_ma,nb_prise_mi,nb_prise_s,date_debut,date_fin;
    String Prise_Matin,Prise_Midi,Prise_Soire;
    static int P;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicaments_renseigner);
        Nom_medica=findViewById(R.id.nom_medicament);
        Inscription.NUM_PAGE=3;
        Nb_prise_Ma=findViewById(R.id.nb_jour);
        Nb_prise_Mi=findViewById(R.id.nb_midi);
        Nb_prise_S=findViewById(R.id.nb_soire);
        Date_debut=findViewById(R.id.debut_traitment);
        Date_fin=findViewById(R.id.fin_traitment);
    }
    public void get_age(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        P=1;
    }
    public void setage(String date) {
        Date_debut.setText(date);
        Date_debut.setError(null);
    }
    public void get_age2(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        P=2;
    }
    public void setage2(String date) {
        Date_fin.setText(date);
        Date_fin.setError(null);
    }
    public void retoure_fiche(View view) {
        nom_medica = Nom_medica.getText().toString().trim();
        nb_prise_mi = Nb_prise_Mi.getText().toString().trim();
        nb_prise_s = Nb_prise_S.getText().toString().trim();
        nb_prise_ma = Nb_prise_Ma.getText().toString().trim();
        date_debut = Date_debut.getText().toString().trim();
        date_fin = Date_fin.getText().toString().trim();
        if (valider()) {

            Toast.makeText(this, Prise_Matin+"//"+Prise_Midi+"//"+Prise_Soire, Toast.LENGTH_SHORT).show();
//            if (list.size()<1){
//                long ids = ds.addFiche(fiches);
//                if (ids == -1) {
//                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
//                } else {
//                    Intent ite = new Intent(this, MenuActivity.class);
//                    startActivity(ite);
//                }
//            }else {
//                long ids=ds.UpdateFiche(email,fiches);
//                if (ids == -1) {
//                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
//                } else {
//                    Intent ite = new Intent(this, MenuActivity.class);
//                    startActivity(ite);
//                }
//            }

        }
    }
    private boolean valider() {

        boolean valide = true;
        if (nom_medica.isEmpty()) {
            Nom_medica.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (nb_prise_ma.isEmpty()) {
            Prise_Matin="0";
        }else {
            Prise_Matin=nb_prise_ma;
        }
        if (nb_prise_mi.isEmpty()) {
            Prise_Midi="0";
        }else {
            Prise_Midi=nb_prise_mi;
        }
        if (nb_prise_s.isEmpty()) {
            Prise_Soire="0";
        }else {
            Prise_Soire=nb_prise_s;
        }
        if (date_debut.isEmpty()) {
            Date_debut.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (date_fin.isEmpty()) {
            Date_fin.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        return valide;
    }


}
