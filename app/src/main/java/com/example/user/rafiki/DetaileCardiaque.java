package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.rafiki.ItemData.Cycle;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DetaileCardiaque extends AppCompatActivity {

    ImageView Etat_Cycle, Resaux, Txt_Cycle, Cercle;
    TextView Txt_Calorie, Txt_max, Txt_min, Txt_moy;
    LineChart mchart;
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
        Resaux = findViewById(R.id.imageView29);
        Txt_Cycle = findViewById(R.id.txt_etat);
        Txt_Calorie = findViewById(R.id.cal_chifre);
        Txt_max = findViewById(R.id.chifre_max);
        Txt_min = findViewById(R.id.chiffre_min);
        Txt_moy = findViewById(R.id.chifre_moys);
        mchart = findViewById(R.id.chart1);
        constraintLayout = findViewById(R.id.constraint);
        Cercle = findViewById(R.id.imageView10);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
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

        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        String restoredcal = prefs.getString("Calorie", null);
        String fuldate = prefs.getString("Date_Cycle", null);
        if (restoredcal != null) {
            Txt_Calorie.setText(format.format(Double.valueOf(restoredcal)));
        }
        if (fuldate != null) {
            Liste_donne = ds.getListCycle(fuldate);
        }
        if (Liste_donne.size() > 0) {

            for (Cycle c : Liste_donne) {
                list_coure.add(c.getFrequenceC());
            }
        }

        YAxis leftAxis = mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(500f);
        leftAxis.setAxisMinimum(-500f);
        leftAxis.enableGridDashedLine(2f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        ArrayList<Entry> yvalues = new ArrayList<>();
        float x = 0f;
        for (int i = 0; i < Liste_donne.size(); i++) {
            yvalues.add(new Entry(x, (float) Liste_donne.get(i).getFrequenceC()));
            System.out.println(Liste_donne.get(i).getFrequenceC());
            x = x + 5f;
        }

        LineDataSet set1 = new LineDataSet(yvalues, "");

        set1.setLineWidth(2f);
        set1.setDrawValues(false);

        set1.setDrawCircles(false);

        ArrayList<ILineDataSet> datasets = new ArrayList<>();
        datasets.add(set1);
        LineData data = new LineData(datasets);
        mchart.setData(data);
        mchart.animateX(1400, Easing.EasingOption.Linear);

        Txt_max.setText(String.valueOf(Collections.max(list_coure)));
        Txt_min.setText(String.valueOf(Collections.min(list_coure)));
        Txt_moy.setText(String.valueOf((Collections.min(list_coure) + Collections.max(list_coure)) / 2));
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
                    constraintLayout.setVisibility(View.VISIBLE);
                    Cercle.setVisibility(View.GONE);
                    break;
                case 2:

                    Etat_Cycle.setImageResource(R.drawable.icon_marche);
                    Txt_Cycle.setImageResource(R.drawable.marche);
                    constraintLayout.setVisibility(View.INVISIBLE);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Etat_Cycle.setImageResource(R.drawable.icone_course);
                    Txt_Cycle.setImageResource(R.drawable.course_a_pied);
                    constraintLayout.setVisibility(View.INVISIBLE);
                    Cercle.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Etat_Cycle.setImageResource(R.drawable.icone_cycle);
                    Txt_Cycle.setImageResource(R.drawable.cyclisme);
                    constraintLayout.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    Etat_Cycle.setImageResource(R.drawable.icon_sommeil);
                    Txt_Cycle.setImageResource(R.drawable.sommeil);
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

    public void historique(View view) {
    }
}
