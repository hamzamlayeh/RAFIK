package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;

public class DetaileActivity extends AppCompatActivity {

    ImageView Etat_Cycle;
    TextView Txt_Cycle,Txt_Calorie, Txt_vitesse, Txt_Cadense, Txt_Nbpas, Txt_Distance, Txt_Chrono;
    SharedPreferences prefs, pref;
    LinearLayout linearLayout;
    double Duree, Nbr_Pas, Distance;
    MySQLiteOpenHelper helper;
    UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);

        Etat_Cycle = findViewById(R.id.etat_cycle);
        Txt_Cycle = findViewById(R.id.txt_etat);
        Txt_Calorie = findViewById(R.id.chifre_cal);
        Txt_vitesse = findViewById(R.id.chiffre_vite);
        Txt_Cadense = findViewById(R.id.chiffre_cad);
        Txt_Nbpas = findViewById(R.id.chiffre_pas);
        Txt_Distance = findViewById(R.id.chiffre_dist);
        Txt_Chrono = findViewById(R.id.chrono);
        linearLayout = findViewById(R.id.linearLayout11);

        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        Test_Donnees();

    }

    public void precedant(View view) {
        Intent ite = new Intent(this, DetaileTemperature.class);
        startActivity(ite);
        overridePendingTransition(R.anim.exit_to_right, R.anim.enter_from_left);
    }

    public void parammetres(View view) {
        Intent ite = new Intent(this, MenuActivity.class);
        startActivity(ite);
    }

    public void Test_Donnees() {
        String restoredcal = prefs.getString("Calorie", null);
        String restoredchrono = prefs.getString("Chronomaitre", null);
        String restoredduree = prefs.getString("Duree_Minute", null);
        String restorednbpas = prefs.getString("Nbr_Pas", null);
        int Indice = prefs.getInt("Indice", 0);
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);

        if (restoredcal != null) {
            Txt_Calorie.setText(String.valueOf((int) Double.parseDouble(restoredcal)));
        }
        if (restoredchrono != null) {
            if (restoredchrono.length() > 5) {
                Txt_Chrono.setText(restoredchrono);
            } else {
                Txt_Chrono.setText("00:" + restoredchrono);
            }
        }
        if (restoredduree != null) {
            Duree = Double.parseDouble(restoredduree);
        }
        if (restorednbpas != null) {
            Nbr_Pas = Double.parseDouble(restorednbpas);
            Txt_Nbpas.setText(String.valueOf((int) Nbr_Pas));
        }
        Txt_Cadense.setText(format.format(Nbr_Pas / Duree));

        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    Etat_Cycle.setImageResource(R.drawable.icon_quotidien);
//                    Txt_Cycle.setImageResource(R.drawable.quotidien);
                    Txt_Cycle.setText(R.string.quotidien);
                    linearLayout.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
//                    Txt_Cycle.setImageResource(R.drawable.marche);
                    Txt_Cycle.setText(R.string.marche);
                    linearLayout.setVisibility(View.VISIBLE);
                    Distance = Nbr_Pas / 1600;
                    Txt_Distance.setText(format.format(Distance));
                    Txt_vitesse.setText(format.format(Distance / (Duree / 60)));
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
//                    Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    Txt_Cycle.setText(R.string.course_pied);
                    linearLayout.setVisibility(View.VISIBLE);
                    Distance = Nbr_Pas / 1250;
                    Txt_Distance.setText(format.format(Distance));
                    Txt_vitesse.setText(format.format(Distance / (Duree / 60)));
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
//                    Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    Txt_Cycle.setText(R.string.cyclisme);
                    linearLayout.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
//                    Txt_Cycle.setImageResource(R.drawable.sommeil);
                    Txt_Cycle.setText(R.string.sommeil);
                    linearLayout.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exite(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, DetaileTemperature.class);
            startActivity(ite);
        }
        return false;
    }

    public void acueil(View view) {
        Intent ite = new Intent(this, E8.class);
        startActivity(ite);
    }

    public void historique(View view) {
        Intent ite = new Intent(this, HistoriqueActivity.class);
        startActivity(ite);
    }

    public void Cycle(View view) {
        startActivity(new Intent(this, CycleActivity.class));
    }

    public void supprimer(View view) {
        final String fuldate = prefs.getString("Date_Cycle", null);

        if (fuldate != null) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            alt.setTitle(" " + getString(R.string.finir_activity))
                    .setIcon(R.drawable.alert)
                    .setMessage("\n " + getString(R.string.text_supprimer_cycle))
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ds.deleteCycle(fuldate);
                            startActivity(new Intent(getApplicationContext(), ParametresMesures.class));
                        }
                    })
                    .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
        }
    }
}
