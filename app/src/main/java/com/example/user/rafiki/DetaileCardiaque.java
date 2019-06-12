package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Cycle;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DetaileCardiaque extends AppCompatActivity {

    ImageView Etat_Cycle, Cercle;
    TextView Txt_Cycle,Txt_Calorie, Txt_max, Txt_min, Txt_moy;
    GraphView graph;
    SharedPreferences prefs, pref;
    ConstraintLayout constraintLayout;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    ArrayList<Double> list_coure = new ArrayList<>();
    static List<Cycle> Liste_donne = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile_cardiaque);
        Etat_Cycle = findViewById(R.id.etat_cycle);

        Txt_Cycle = findViewById(R.id.txt_etat);
        Txt_Calorie = findViewById(R.id.cal_chifre);
        Txt_max = findViewById(R.id.chifre_max);
        Txt_min = findViewById(R.id.chiffre_min);
        Txt_moy = findViewById(R.id.chifre_moys);
        constraintLayout = findViewById(R.id.constraint);
        graph = findViewById(R.id.graph);
        Cercle = findViewById(R.id.imageView10);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        Test_Donnees();

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(30);
        graph.getViewport().setMaxY(200);

        graph.getViewport().setXAxisBoundsManual(false);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(4);

        graph.getGridLabelRenderer().setHumanRounding(true);
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph.getGridLabelRenderer().setNumVerticalLabels(6);

        graph.getGridLabelRenderer().setHorizontalAxisTitle(getString(R.string.tempsinst));
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);


        String restoredcal = prefs.getString("Calorie", null);
        String fuldate = prefs.getString("Date_Cycle", null);
        if (restoredcal != null) {
            Txt_Calorie.setText(String.valueOf((int) Double.parseDouble(restoredcal)));
        }

        if (fuldate != null && ds.getCountCycle(fuldate) > 0) {
            Liste_donne = ds.getListCycle(fuldate);
            if (Liste_donne.size() > 0) {

                for (Cycle c : Liste_donne) {
                    list_coure.add(c.getFrequenceC());
                }
                Txt_max.setText(String.valueOf(Collections.max(list_coure)));
                Txt_min.setText(String.valueOf(Collections.min(list_coure)));
                Txt_moy.setText(String.valueOf(Math.round(Moyenne_Val(list_coure))));
            }
        }
        if (ds.getCountCycle(fuldate) > 0) {

            int x = 0;
            DataPoint[] points = new DataPoint[Liste_donne.size()];
            for (int i = 0; i < points.length; i++) {

                points[i] = new DataPoint(x, Liste_donne.get(i).getFrequenceC());
                x += 3;
            }
//            int val=0;
//            DataPoint[] points = new DataPoint[50];
//            for (int i = 0; i < points.length; i++) {
//                points[i] = new DataPoint(val, (Math.random()*50+1));
//                val+=5;
//            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
            series.setDrawDataPoints(false);
            series.setDataPointsRadius(7);
            series.setThickness(7);
            series.setColor(Color.CYAN);
            graph.addSeries(series);
        }
    }

    public double Moyenne_Val(ArrayList<Double> list) {
        int total = 0;
        for (double val : list) {
            total += val;
        }
        return total / list.size();
    }

    public void suivant(View view) {
        Intent ite = new Intent(this, DetaileRespiration.class);
        startActivity(ite);
//        overridePendingTransition(R.anim.exit_to_left, R.anim.enter_from_right);
    }

    public void parammetres(View view) {
        Intent ite = new Intent(this, MenuActivity.class);
        startActivity(ite);
    }

    public void Test_Donnees() {
        int Indice = prefs.getInt("Indice", 0);

        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    Etat_Cycle.setImageResource(R.drawable.icon_quotidien);
                    Txt_Cycle.setText(R.string.quotidien);
                    constraintLayout.setVisibility(View.VISIBLE);
                    Cercle.setVisibility(View.GONE);
                    break;
                case 2:

                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
                    Txt_Cycle.setText(R.string.marche);
                    constraintLayout.setVisibility(View.INVISIBLE);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
                    Txt_Cycle.setText(R.string.course_pied);
//                    Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    constraintLayout.setVisibility(View.INVISIBLE);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
                    Txt_Cycle.setText(R.string.cyclisme);
//                    Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    constraintLayout.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Txt_Cycle.setText(R.string.sommeil);
//                    Txt_Cycle.setImageResource(R.drawable.sommeil);
                    constraintLayout.setVisibility(View.VISIBLE);
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
            Intent ite = new Intent(this, ParametresMesures.class);
            startActivity(ite);
        }
        return false;
    }

    public void acueil(View view) {
        Intent ite = new Intent(this, E8.class);
        startActivity(ite);
    }

    public void precedant(View view) {
        Intent ite = new Intent(this, ParametresMesures.class);
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
