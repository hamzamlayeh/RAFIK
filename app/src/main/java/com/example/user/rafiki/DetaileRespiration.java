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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class DetaileRespiration extends AppCompatActivity {

    ImageView Etat_Cycle, Resaux, Txt_Cycle,Cercle;
    LineChart mchart;
    SharedPreferences prefs, pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile_respiration);

        Etat_Cycle = findViewById(R.id.etat_cycle);
        Resaux = findViewById(R.id.imageView29);
        Txt_Cycle = findViewById(R.id.txt_etat);
        Cercle = findViewById(R.id.imageView17);
        mchart = findViewById(R.id.chart1);

        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        Test_Donnees();

        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(true);
        mchart.getAxisRight().setEnabled(false);
        mchart.getAxisLeft().setEnabled(false);
        mchart.getXAxis().setEnabled(false);
        mchart.getDescription().setEnabled(false);
        mchart.setDrawBorders(false);
        mchart.setPinchZoom(true);
        mchart.setDrawGridBackground(false);

        YAxis leftAxis=mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(32f);
        leftAxis.setAxisMinimum(16f);
        leftAxis.enableGridDashedLine(2f,2f,0);
        leftAxis.setDrawLimitLinesBehindData(true);

        ArrayList<Entry> yvalues = new ArrayList<>();
        for (int i=0;i<=50;i++){
            float val=(float) (Math.random()*(28-21)+21);
            yvalues.add(new Entry((i/3f),val));
        }

        LineDataSet set1 = new LineDataSet(yvalues,"");

        set1.setLineWidth(2f);
        set1.setDrawValues(false);
        set1.setDrawCircles(false);
//        set1.setColor(R.color.bleu);

        ArrayList<ILineDataSet> datasets=new ArrayList<>();
        datasets.add(set1);
        LineData data=new LineData(datasets);
        mchart.setData(data);
        mchart.animateX(1400, Easing.EasingOption.Linear);
    }


    public void precedant(View view) {
        Intent ite = new Intent(this, DetaileCardiaque.class);
        startActivity(ite);
        overridePendingTransition(R.anim.exit_to_right, R.anim.enter_from_left);
    }
    public void suivant(View view) {
        Intent ite = new Intent(this, DetaileTemperature.class);
        startActivity(ite);
//        overridePendingTransition(R.anim.exit_to_left, R.anim.enter_from_right);
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
                    Cercle.setVisibility(View.GONE);
                    break;
                case 2:

                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
                    Txt_Cycle.setImageResource(R.drawable.marche);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
                    Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
                    Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    Cercle.setVisibility(View.GONE);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Txt_Cycle.setImageResource(R.drawable.sommeil);
                    Cercle.setVisibility(View.GONE);
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
            Intent ite = new Intent(this, DetaileCardiogramme.class);
            startActivity(ite);
        }
        return false;
    }
}
