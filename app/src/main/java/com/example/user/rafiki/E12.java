package com.example.user.rafiki;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class E12 extends AppCompatActivity {

    Intent intent;
    private LineChart mchart;
    static boolean StopThread = true;
    Activity activity;
    List<Integer> listValeur = new ArrayList<>();
    int ReestValeur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e12);

        ProgressBar bar = (ProgressBar) findViewById(R.id.vertical);
        bar.setVisibility(View.INVISIBLE);
//        bar.setProgress(100);
        activity = this;
        StopThread = true;
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x03, 0x74, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
        ThreadTEMP thread = new ThreadTEMP();
        thread.start();
        mchart = (LineChart) findViewById(R.id.chart1);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(true);
        mchart.getAxisRight().setEnabled(false);
        mchart.getXAxis().setEnabled(false);
        mchart.getDescription().setEnabled(false);
        mchart.setDrawBorders(false);
        mchart.setPinchZoom(true);
        mchart.setDrawGridBackground(false);

        YAxis leftAxis = mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(75f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(R.color.left);
        leftAxis.enableGridDashedLine(2f, 2f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

    }

    class ThreadTEMP extends Thread {
        public void run() {
            final TextView temp = findViewById(R.id.tempirature);
            final TextView niveaubatt = findViewById(R.id.NiveauBatt);
            final ImageView batteri = findViewById(R.id.batterie);

            while (StopThread) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            temp.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));
                            niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3]) + "%"));

                            if (E7_2.str[3] == 0) {
                                batteri.setImageResource(R.drawable.batt7);
                            } else if (E7_2.str[3] >= 1 && E7_2.str[3] <= 13) {
                                batteri.setImageResource(R.drawable.batt6);

                            } else if (E7_2.str[3] > 13 && E7_2.str[3] <= 25) {
                                batteri.setImageResource(R.drawable.batt5);

                            } else if (E7_2.str[3] > 25 && E7_2.str[3] <= 38) {
                                batteri.setImageResource(R.drawable.batt4);

                            } else if (E7_2.str[3] > 38 && E7_2.str[3] <= 50) {
                                batteri.setImageResource(R.drawable.batt3);

                            } else if (E7_2.str[3] > 50 && E7_2.str[3] <= 75) {
                                batteri.setImageResource(R.drawable.batt2);

                            } else if (E7_2.str[3] > 76 && E7_2.str[3] <= 100) {
                                batteri.setImageResource(R.drawable.batt1);
                            }

                            if (ReestValeur >= 30) {
                                ReestValeur = 0;
                                listValeur.clear();
                            } else {
                                ReestValeur++;
                                listValeur.add(BLEManager.unsignedToBytes(E7_2.str[2]));
                                Log.d("size", listValeur.size() + "");
                                ArrayList<Entry> yvalues = new ArrayList<>();
                                for (int i = 0; i < listValeur.size(); i++) {
                                    yvalues.add(new Entry((i), listValeur.get(i)));
                                }
                                LineDataSet set1 = new LineDataSet(yvalues, "");

                                set1.setLineWidth(3f);
                                set1.setDrawValues(false);
                                set1.setDrawCircles(true);
                                set1.setColor(R.color.courbe);

                                ArrayList<ILineDataSet> datasets = new ArrayList<>();
                                datasets.add(set1);
                                LineData data = new LineData(datasets);
                                mchart.setData(data);
                                mchart.animateX(1000, Easing.EasingOption.Linear);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void retour(View view) {
        StopThread = false;
        E7_2.str = null;
        TrameSop();
        Intent intent = new Intent(E12.this, E8.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            StopThread = false;
            E7_2.str = null;
            TrameSop();
            Intent intent = new Intent(E12.this, E8.class);
            startActivity(intent);
        }
        return false;
    }

    public void TrameSop() {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x01, 0x74, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}
