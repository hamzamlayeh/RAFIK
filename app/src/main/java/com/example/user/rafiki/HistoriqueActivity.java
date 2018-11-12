package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class HistoriqueActivity extends AppCompatActivity {

    CheckBox Chek_FC, Chek_FR, Chek_Temp, Chek_Pas, Chek_Chute;
    CheckBox Chek_QT, Chek_Mar, Chek_Cour, Chek_cycl, Chek_somm;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int Etat,Cycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        pref = getSharedPreferences("Historique", MODE_PRIVATE);

        Chek_FC = findViewById(R.id.chek_fc);
        Chek_FR = findViewById(R.id.chek_fr);
        Chek_Temp = findViewById(R.id.chek_temp);
        Chek_Pas = findViewById(R.id.chek_pas);
        Chek_Chute = findViewById(R.id.chek_chute);

        Chek_QT = findViewById(R.id.chek_quotidien);
        Chek_Mar = findViewById(R.id.chek_marche);
        Chek_Cour = findViewById(R.id.chek_course);
        Chek_cycl = findViewById(R.id.chek_cyclisme);
        Chek_somm = findViewById(R.id.chek_sommeil);

    }

    public void Bt_OK(View view) {

        if (valider()) {
            editor = pref.edit();
            editor.putInt("Cycle", Cycle);
            editor.putInt("Etat", Etat);
            editor.apply();
         startActivity(new Intent(this, DetaileHistorique.class));
        }
    }

    private boolean valider() {
        boolean valide = true;
        if (!(Chek_FC.isChecked() || Chek_FR.isChecked() || Chek_Temp.isChecked() || Chek_Pas.isChecked()
                || Chek_Chute.isChecked())) {
            valide = false;
            Toast.makeText(this, "Cocher !!", Toast.LENGTH_SHORT).show();
        }
        if (!(Chek_QT.isChecked() || Chek_Mar.isChecked() || Chek_Cour.isChecked() || Chek_cycl.isChecked()
                || Chek_somm.isChecked())) {
            valide = false;
            Toast.makeText(this, "Cocher !!", Toast.LENGTH_SHORT).show();
        }
        return valide;
    }

    public void get_FC(View view) {
        Etat=1;
        Chek_FC.setChecked(true);
        Chek_FR.setChecked(false);
        Chek_Temp.setChecked(false);
        Chek_Pas.setChecked(false);
        Chek_Chute.setChecked(false);

        Chek_FC.setClickable(false);
        Chek_FR.setClickable(true);
        Chek_Temp.setClickable(true);
        Chek_Pas.setClickable(true);
        Chek_Chute.setClickable(true);
    }

    public void get_FR(View view) {
        Etat=2;
        Chek_FC.setChecked(false);
        Chek_FR.setChecked(true);
        Chek_Temp.setChecked(false);
        Chek_Pas.setChecked(false);
        Chek_Chute.setChecked(false);

        Chek_FC.setClickable(true);
        Chek_FR.setClickable(false);
        Chek_Temp.setClickable(true);
        Chek_Pas.setClickable(true);
        Chek_Chute.setClickable(true);
    }

    public void get_Temp(View view) {
        Etat=3;
        Chek_FC.setChecked(false);
        Chek_FR.setChecked(false);
        Chek_Temp.setChecked(true);
        Chek_Pas.setChecked(false);
        Chek_Chute.setChecked(false);

        Chek_FC.setClickable(true);
        Chek_FR.setClickable(true);
        Chek_Temp.setClickable(false);
        Chek_Pas.setClickable(true);
        Chek_Chute.setClickable(true);
    }

    public void get_Pas(View view) {
        Etat=4;
        Chek_FC.setChecked(false);
        Chek_FR.setChecked(false);
        Chek_Temp.setChecked(false);
        Chek_Pas.setChecked(true);
        Chek_Chute.setChecked(false);

        Chek_FC.setClickable(true);
        Chek_FR.setClickable(true);
        Chek_Temp.setClickable(true);
        Chek_Pas.setClickable(false);
        Chek_Chute.setClickable(true);
    }

    public void get_Chute(View view) {
        Etat=5;
        Chek_FC.setChecked(false);
        Chek_FR.setChecked(false);
        Chek_Temp.setChecked(false);
        Chek_Pas.setChecked(false);
        Chek_Chute.setChecked(true);

        Chek_FC.setClickable(true);
        Chek_FR.setClickable(true);
        Chek_Temp.setClickable(true);
        Chek_Pas.setClickable(true);
        Chek_Chute.setClickable(false);
    }

    public void get_Quotidien(View view) {
        Cycle=1;
        Chek_QT.setChecked(true);
        Chek_Mar.setChecked(false);
        Chek_Cour.setChecked(false);
        Chek_cycl.setChecked(false);
        Chek_somm.setChecked(false);

        Chek_QT.setClickable(false);
        Chek_Mar.setClickable(true);
        Chek_Cour.setClickable(true);
        Chek_cycl.setClickable(true);
        Chek_somm.setClickable(true);
    }

    public void get_Marche(View view) {
        Cycle=2;
        Chek_QT.setChecked(false);
        Chek_Mar.setChecked(true);
        Chek_Cour.setChecked(false);
        Chek_cycl.setChecked(false);
        Chek_somm.setChecked(false);

        Chek_QT.setClickable(true);
        Chek_Mar.setClickable(false);
        Chek_Cour.setClickable(true);
        Chek_cycl.setClickable(true);
        Chek_somm.setClickable(true);
    }

    public void get_Course(View view) {
        Cycle=3;
        Chek_QT.setChecked(false);
        Chek_Mar.setChecked(false);
        Chek_Cour.setChecked(true);
        Chek_cycl.setChecked(false);
        Chek_somm.setChecked(false);

        Chek_QT.setClickable(true);
        Chek_Mar.setClickable(true);
        Chek_Cour.setClickable(false);
        Chek_cycl.setClickable(true);
        Chek_somm.setClickable(true);
    }

    public void get_Cyclisme(View view) {
        Cycle=4;
        Chek_QT.setChecked(false);
        Chek_Mar.setChecked(false);
        Chek_Cour.setChecked(false);
        Chek_cycl.setChecked(true);
        Chek_somm.setChecked(false);

        Chek_QT.setClickable(true);
        Chek_Mar.setClickable(true);
        Chek_Cour.setClickable(true);
        Chek_cycl.setClickable(false);
        Chek_somm.setClickable(true);
    }

    public void get_Sommeil(View view) {
        Cycle=5;
        Chek_QT.setChecked(false);
        Chek_Mar.setChecked(false);
        Chek_Cour.setChecked(false);
        Chek_cycl.setChecked(false);
        Chek_somm.setChecked(true);

        Chek_QT.setClickable(true);
        Chek_Mar.setClickable(true);
        Chek_Cour.setClickable(true);
        Chek_cycl.setClickable(true);
        Chek_somm.setClickable(false);
    }
}
