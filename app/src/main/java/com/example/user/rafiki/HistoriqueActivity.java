package com.example.user.rafiki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class HistoriqueActivity extends AppCompatActivity {

    CheckBox Chek_FC,Chek_FR,Chek_Temp,Chek_Pas,Chek_Chute;
    CheckBox Chek_QT,Chek_Mar,Chek_Cour,Chek_cycl,Chek_somm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        Chek_FC=findViewById(R.id.chek_fc);
        Chek_FR=findViewById(R.id.chek_fr);
        Chek_Temp=findViewById(R.id.chek_temp);
        Chek_Pas=findViewById(R.id.chek_pas);
        Chek_Chute=findViewById(R.id.chek_chute);

        Chek_QT=findViewById(R.id.chek_quotidien);
        Chek_Mar=findViewById(R.id.chek_marche);
        Chek_Cour=findViewById(R.id.chek_course);
        Chek_cycl=findViewById(R.id.chek_cyclisme);
        Chek_somm=findViewById(R.id.chek_sommeil);

    }


    public void Bt_OK(View view) {

    }

    public void get_FC(View view) {
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
}
