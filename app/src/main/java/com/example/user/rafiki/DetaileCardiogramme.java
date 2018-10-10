package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

public class DetaileCardiogramme extends AppCompatActivity {

    ImageView Etat_Cycle, Resaux, Txt_Cycle;
    LineChart mchart;
    SharedPreferences prefs, pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile_cardiogramme);

        Etat_Cycle = findViewById(R.id.etat_cycle);
        Resaux = findViewById(R.id.imageView29);
        Txt_Cycle = findViewById(R.id.txt_etat);
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

        YAxis leftAxis = mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(10f);
        leftAxis.setAxisMinimum(-10f);
        leftAxis.enableGridDashedLine(2f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        ArrayList<Entry> yvalues = new ArrayList<>();

        yvalues.add(new Entry(0f, 0f));
        yvalues.add(new Entry(2f, 0f));
        yvalues.add(new Entry(2.5f, 4f));
        yvalues.add(new Entry(2.7f, -4f));
        yvalues.add(new Entry(3f, 2f));
        yvalues.add(new Entry(3.2f, -2f));
        yvalues.add(new Entry(3.5f, 1f));
        yvalues.add(new Entry(3.7f, -1f));
        yvalues.add(new Entry(3.79f, 0f));
        yvalues.add(new Entry(7f, 0f));
        yvalues.add(new Entry(7.5f, 4f));
        yvalues.add(new Entry(7.7f, -4f));
        yvalues.add(new Entry(8f, 2f));
        yvalues.add(new Entry(8.2f, -2f));
        yvalues.add(new Entry(8.5f, 1f));
        yvalues.add(new Entry(8.7f, -1f));
        yvalues.add(new Entry(8.79f, 0f));
        yvalues.add(new Entry(11, 0f));

        LineDataSet set1 = new LineDataSet(yvalues, "");

        set1.setLineWidth(2f);
        set1.setDrawValues(false);

        set1.setDrawCircles(false);

        ArrayList<ILineDataSet> datasets = new ArrayList<>();
        datasets.add(set1);
        LineData data = new LineData(datasets);
        mchart.setData(data);
        mchart.animateX(1400, Easing.EasingOption.Linear);
    }

    public void precedant(View view) {
        Intent ite = new Intent(this, DetaileCardiaque.class);
        startActivity(ite);
        overridePendingTransition(R.anim.exit_to_right, R.anim.enter_from_left);
    }

    public void suivant(View view) {
        Intent ite = new Intent(this, DetaileRespiration.class);
        startActivity(ite);
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
                    break;
                case 2:

                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
                    Txt_Cycle.setImageResource(R.drawable.marche);
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
                    Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
                    Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Txt_Cycle.setImageResource(R.drawable.sommeil);
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
            Intent ite = new Intent(this, DetaileCardiaque.class);
            startActivity(ite);
        }
        return false;
    }

}
