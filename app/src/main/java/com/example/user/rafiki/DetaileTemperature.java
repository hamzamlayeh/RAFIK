package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Collections;

import static com.example.user.rafiki.DetaileCardiaque.Liste_donne;

public class DetaileTemperature extends AppCompatActivity {

    ImageView Etat_Cycle, Suivant, Cercle;
    GraphView graph;
    TextView Txt_max, Txt_min, Txt_moy,Txt_Cycle;
    SharedPreferences prefs, pref;
    ArrayList<Double> list_temp = new ArrayList<>();
    MySQLiteOpenHelper helper;
    UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile_temperature);

        Etat_Cycle = findViewById(R.id.etat_cycle);
        Txt_Cycle = findViewById(R.id.txt_etat);
        Txt_max = findViewById(R.id.chifre_max);
        Txt_min = findViewById(R.id.chiffre_min);
        Txt_moy = findViewById(R.id.chifre_moys);
        Suivant = findViewById(R.id.suvi);
        Cercle = findViewById(R.id.imageView10);
        graph = findViewById(R.id.graph);

        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        Test_Donnees();

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(60);

        graph.getViewport().setXAxisBoundsManual(true);
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
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);

        String fuldate = prefs.getString("Date_Cycle", null);
        if (fuldate != null && ds.getCountCycle(fuldate) > 0) {
            if (Liste_donne.size() > 0) {

                for (Cycle c : Liste_donne) {
                    list_temp.add(c.getTempirateur());
                }
            }
        }

        if (ds.getCountCycle(fuldate) > 0) {
//            float x = 0f;
//            for (int i = 0; i < DetaileCardiaque.Liste_donne.size(); i++) {
//                yvalues.add(new Entry(x, (float) DetaileCardiaque.Liste_donne.get(i).getTempirateur()));
//                x = x + 5f;
//            }
            int x = 0;
            DataPoint[] points = new DataPoint[Liste_donne.size()];
            for (int i = 0; i < points.length; i++) {

                points[i] = new DataPoint(x, Liste_donne.get(i).getTempirateur());
                x += 3;
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
            series.setDrawDataPoints(false);
            series.setDataPointsRadius(7);
            series.setThickness(7);
            series.setColor(Color.CYAN);
            graph.addSeries(series);
        }

        Txt_max.setText(String.valueOf(Collections.max(list_temp)));
        Txt_min.setText(String.valueOf(Collections.min(list_temp)));
        Txt_moy.setText(String.valueOf(Math.round(Moyenne_Val(list_temp))));
    }

    public double Moyenne_Val(ArrayList<Double> list) {
        int total = 0;
        for (double val : list) {
            total += val;
        }
        return total / list.size();
    }

    public void precedant(View view) {
        Intent ite = new Intent(this, DetaileRespiration.class);
        startActivity(ite);
       // overridePendingTransition(R.anim.exit_to_right, R.anim.enter_from_left);
    }

    public void suivant(View view) {
        Intent ite = new Intent(this, DetaileActivity.class);
        startActivity(ite);
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
                    //Txt_Cycle.setImageResource(R.drawable.quotidien);
                    Txt_Cycle.setText(R.string.quotidien);
                    Suivant.setVisibility(View.INVISIBLE);
                    Cercle.setVisibility(View.GONE);
                    break;
                case 2:

                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
                    //Txt_Cycle.setImageResource(R.drawable.marche);
                    Txt_Cycle.setText(R.string.marche);
                    Suivant.setVisibility(View.VISIBLE);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
                    //Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    Txt_Cycle.setText(R.string.course_pied);
                    Suivant.setVisibility(View.VISIBLE);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
                    //Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    Txt_Cycle.setText(R.string.cyclisme);
                    Suivant.setVisibility(View.INVISIBLE);
                    Cercle.setVisibility(View.GONE);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Txt_Cycle.setText(R.string.sommeil);
                   // Txt_Cycle.setImageResource(R.drawable.sommeil);
                    Suivant.setVisibility(View.INVISIBLE);
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
            Intent ite = new Intent(this, DetaileRespiration.class);
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
