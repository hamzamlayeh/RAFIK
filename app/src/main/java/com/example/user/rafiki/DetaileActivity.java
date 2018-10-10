package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DetaileActivity extends AppCompatActivity {

    ImageView Etat_Cycle, Resaux, Txt_Cycle;
    SharedPreferences prefs, pref;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);

        Etat_Cycle = findViewById(R.id.etat_cycle);
        Resaux = findViewById(R.id.imageView29);
        Txt_Cycle = findViewById(R.id.txt_etat);
        linearLayout = findViewById(R.id.linearLayout11);

        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
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
        boolean value = pref.getBoolean("connexion", false);
        int Indice = prefs.getInt("Indice", 0);

        if (value) {
            Resaux.setImageResource(R.drawable.resaux);
        } else {
            Resaux.setImageResource(R.drawable.resaux2);
        }

        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    Etat_Cycle.setImageResource(R.drawable.icon_quotidien);
                    Txt_Cycle.setImageResource(R.drawable.quotidien);
                    linearLayout.setVisibility(View.INVISIBLE);
                    break;
                case 2:

                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
                    Txt_Cycle.setImageResource(R.drawable.marche);
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
                    Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
                    Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    linearLayout.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Txt_Cycle.setImageResource(R.drawable.sommeil);
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
}
