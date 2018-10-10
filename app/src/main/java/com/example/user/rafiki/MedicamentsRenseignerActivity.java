package com.example.user.rafiki;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.user.rafiki.ItemData.Medicament_Item;

import java.util.ArrayList;

public class MedicamentsRenseignerActivity extends AppCompatActivity {

    EditText Nom_medica, Nb_prise_Ma, Nb_prise_Mi, Nb_prise_S, Date_debut, Date_fin;
    TextView Heur_Ma, Heur_Mi, Heur_S;
    String nom_medica, nb_prise_ma, nb_prise_mi, nb_prise_s, date_debut, date_fin;
    String Prise_Matin, Prise_Midi, Prise_Soire, heur_matin, heur_midi, heur_soire;
    int Lundi, Mardi, Mercredi, Jeudi, Vendredi, Samedi, Dimanche;
    ToggleButton btn_Lu, btn_M, btn_Me, btn_J, btn_V, btn_S, btn_D;
    static int P;
    Medicament_Item medica_Item;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    ArrayList<Medicament_Item> list = new ArrayList<Medicament_Item>();
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicaments_renseigner);
        Inscription.NUM_PAGE = 3;
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        Nom_medica = findViewById(R.id.nom_medicament);
        Nb_prise_Ma = findViewById(R.id.nb_jour);
        Nb_prise_Mi = findViewById(R.id.nb_midi);
        Nb_prise_S = findViewById(R.id.nb_soire);
        Date_debut = findViewById(R.id.debut_traitment);
        Date_fin = findViewById(R.id.fin_traitment);
        Heur_Ma = findViewById(R.id.heur_matin);
        Heur_Mi = findViewById(R.id.heur_midi);
        Heur_S = findViewById(R.id.heur_soire);
        //TOGGLEBUTTON
        btn_Lu = findViewById(R.id.btn_l);
        btn_M = findViewById(R.id.btn_Ma);
        btn_Me = findViewById(R.id.btn_Me);
        btn_J = findViewById(R.id.btn_J);
        btn_V = findViewById(R.id.btn_V);
        btn_S = findViewById(R.id.btn_S);
        btn_D = findViewById(R.id.btn_D);
        //Inistialisation des color
        Lundi = ContextCompat.getColor(this, R.color.left);
        Mardi = ContextCompat.getColor(this, R.color.left);
        Mercredi = ContextCompat.getColor(this, R.color.left);
        Jeudi = ContextCompat.getColor(this, R.color.left);
        Vendredi = ContextCompat.getColor(this, R.color.left);
        Samedi = ContextCompat.getColor(this, R.color.left);
        Dimanche = ContextCompat.getColor(this, R.color.left);
        if (MedicamentsActivity.Id > 0) {
            Id = getIntent().getExtras().getInt("Id");
            list = (ArrayList<Medicament_Item>) ds.getMedicament();
            int i = 0;
            while (i < list.size()) {
                if (list.get(i).get_id() == Id) {
                    Nom_medica.setText(list.get(i).getNom_medica());
                    Nb_prise_Ma.setText(list.get(i).getNb_matin());
                    Nb_prise_Mi.setText(list.get(i).getNb_midi());
                    Nb_prise_S.setText(list.get(i).getNb_soire());
                    Date_debut.setText(list.get(i).getDate_debut());
                    Date_fin.setText(list.get(i).getDate_fin());
                    Heur_Ma.setText(list.get(i).getHeure_matin());
                    Heur_Mi.setText(list.get(i).getHeure_midi());
                    Heur_S.setText(list.get(i).getHeur_soire());
                    btn_Lu.setTextColor(list.get(i).getColor_lu());
                    btn_M.setTextColor(list.get(i).getColor_ma());
                    btn_Me.setTextColor(list.get(i).getColor_me());
                    btn_J.setTextColor(list.get(i).getColor_ju());
                    btn_V.setTextColor(list.get(i).getColor_ve());
                    btn_S.setTextColor(list.get(i).getColor_sa());
                    btn_D.setTextColor(list.get(i).getColor_di());
                    // inistalisation des couleur
                    Lundi = list.get(i).getColor_lu();
                    Mardi = list.get(i).getColor_ma();
                    Mercredi = list.get(i).getColor_me();
                    Jeudi = list.get(i).getColor_ju();
                    Vendredi = list.get(i).getColor_ve();
                    Samedi = list.get(i).getColor_sa();
                    Dimanche = list.get(i).getColor_di();
                    break;
                }
                i++;
            }
        }
    }

    public void getD(View view) {
        if (btn_D.isChecked()) {
            btn_D.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Dimanche = ContextCompat.getColor(this, R.color.btn_radio);

        } else {
            btn_D.setTextColor(ContextCompat.getColor(this, R.color.left));
            Dimanche = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void getS(View view) {
        if (btn_S.isChecked()) {
            btn_S.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Samedi = ContextCompat.getColor(this, R.color.btn_radio);

        } else {
            btn_S.setTextColor(Color.BLACK);
            Samedi = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void getV(View view) {
        if (btn_V.isChecked()) {
            btn_V.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Vendredi = ContextCompat.getColor(this, R.color.btn_radio);
        } else {
            btn_V.setTextColor(Color.BLACK);
            Vendredi = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void getJ(View view) {
        if (btn_J.isChecked()) {
            btn_J.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Jeudi = ContextCompat.getColor(this, R.color.btn_radio);
        } else {
            btn_J.setTextColor(Color.BLACK);
            Jeudi = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void getM(View view) {
        if (btn_M.isChecked()) {
            btn_M.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Mardi = ContextCompat.getColor(this, R.color.btn_radio);
        } else {
            btn_M.setTextColor(Color.BLACK);
            Mardi = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void getMe(View view) {
        if (btn_Me.isChecked()) {
            btn_Me.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Mercredi = ContextCompat.getColor(this, R.color.btn_radio);
        } else {
            btn_Me.setTextColor(Color.BLACK);
            Mercredi = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void getL(View view) {
        if (btn_Lu.isChecked()) {
            btn_Lu.setTextColor(ContextCompat.getColor(this, R.color.btn_radio));
            Lundi = ContextCompat.getColor(this, R.color.btn_radio);
        } else {
            btn_Lu.setTextColor(Color.BLACK);
            Lundi = ContextCompat.getColor(this, R.color.left);
        }
    }

    public void get_age(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        P = 1;
    }

    public void setage(String date) {
        Date_debut.setText(date);
        Date_debut.setError(null);
    }

    public void get_age2(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        P = 2;
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
        heur_matin = Heur_Ma.getText().toString().trim();
        heur_midi = Heur_Mi.getText().toString().trim();
        heur_soire = Heur_S.getText().toString().trim();
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        if (valider()) {
            medica_Item = new Medicament_Item(nom_medica, nb_prise_ma, nb_prise_mi, nb_prise_s, date_debut,
                    date_fin, heur_matin, heur_midi, heur_soire, Lundi, Mardi, Mercredi, Jeudi, Vendredi, Samedi, Dimanche);
            if (MedicamentsActivity.Id == -1) {
                long ids = ds.addMidica(medica_Item);
                if (ids == -1) {
                    loading.dismiss();
                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    loading.dismiss();
                    Intent ite = new Intent(this, MedicamentsActivity.class);
                    startActivity(ite);
                    MedicamentsRenseignerActivity.this.finish();
                }
            } else {
                long ids = ds.UpdateMedicament(medica_Item, Id);
                if (ids == -1) {
                    loading.dismiss();
                    Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
                } else {
                    loading.dismiss();
                    Intent ite = new Intent(this, MedicamentsActivity.class);
                    startActivity(ite);
                    MedicamentsRenseignerActivity.this.finish();
                }
            }
        } else {
            loading.dismiss();
        }
    }

    private boolean valider() {

        boolean valide = true;
        if (nom_medica.isEmpty()) {
            Nom_medica.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (nb_prise_ma.isEmpty()) {
            Prise_Matin = "0";
        } else {
            Prise_Matin = nb_prise_ma;
        }
        if (nb_prise_mi.isEmpty()) {
            Prise_Midi = "0";
        } else {
            Prise_Midi = nb_prise_mi;
        }
        if (nb_prise_s.isEmpty()) {
            Prise_Soire = "0";
        } else {
            Prise_Soire = nb_prise_s;
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
