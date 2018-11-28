package com.example.user.rafiki;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetaileHistorique extends AppCompatActivity {

    ImageView Img_Cycle,Img_txtCycle;
    TextView Txt_Etat;
    SharedPreferences pref;
    int Etat,Cycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile_historique);

        pref = getSharedPreferences("Historique", MODE_PRIVATE);
        Img_Cycle=findViewById(R.id.etat_cycle);
        Img_txtCycle=findViewById(R.id.txt_etat);
        Txt_Etat=findViewById(R.id.txt);
        Etat = pref.getInt("Etat", 0);
        Cycle = pref.getInt("Cycle", 0);

        Recap();
    }
    public void Recap(){
        if (Cycle != 0) {
            switch (Cycle) {
                case 1:
                    Img_Cycle.setImageResource(R.drawable.icon_quotidien);
                    Img_txtCycle.setImageResource(R.drawable.quotidien);
                    break;
                case 2:
                    Img_Cycle.setImageResource(R.drawable.icon_marche);
                    Img_txtCycle.setImageResource(R.drawable.marche);
                    break;
                case 3:
                    Img_Cycle.setImageResource(R.drawable.icone_course);
                    Img_txtCycle.setImageResource(R.drawable.course_a_pied);
                    break;
                case 4:
                    Img_Cycle.setImageResource(R.drawable.icone_cycle);
                    Img_txtCycle.setImageResource(R.drawable.cyclisme);
                    break;
                case 5:
                    Img_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Img_txtCycle.setImageResource(R.drawable.sommeil);
                    break;
            }
        }
        if (Etat != 0) {
            switch (Etat) {
                case 1:
                    Txt_Etat.setText(R.string.frequence_cardiaque);
                    break;
                case 2:
                    Txt_Etat.setText(R.string.respirations);
                    break;
                case 3:
                    Txt_Etat.setText(R.string.tempirateur);
                    break;
                case 4:
                    Txt_Etat.setText(R.string.calories);
                    break;
                case 5:
                    Txt_Etat.setText(R.string.chute);
                    break;
            }
        }
    }

    public void imprime(View view) {

    }
}
