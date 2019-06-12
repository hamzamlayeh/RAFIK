package com.example.user.rafiki;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Random;

public class E10 extends AppCompatActivity {

    //    LineChart mchart;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    double lastXpoint = 0;
    Random rd = new Random();
    static boolean StopThread = true;
    Activity activity;
    Animation animation, animation2;
    ImageView coeur;
    int Compteur = 0;
    int Paire = 1, indice = 0;
    ArrayList<Integer> Lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e10);

        coeur = findViewById(R.id.respiration);
//        ProgressBar bar = (ProgressBar) findViewById(R.id.vertical);
//        bar.setProgress(25);
        activity = this;
        StopThread = true;
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x7C, 0x00, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
        ThreadFC thread = new E10.ThreadFC();
        thread.start();
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        coeur.startAnimation(animation2);
        graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-255);
        graph.getViewport().setMaxY(255);
        graph.getGridLabelRenderer().setHumanRounding(true);
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getString(R.string.tempsinst));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.CYAN);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(false);
        series.setDataPointsRadius(5);
        series.setThickness(5);
        series.setColor(Color.RED);
        graph.addSeries(series);

//        mchart = (LineChart) findViewById(R.id.chart1);
//        mchart.setDragEnabled(true);
//        mchart.setScaleEnabled(true);
//        mchart.getAxisRight().setEnabled(false);
//        mchart.getAxisLeft().setEnabled(true);
//        XAxis xl = mchart.getXAxis();
//        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setDrawAxisLine(true);
//        xl.setDrawGridLines(false);
//        //xl.setGranularity(5f);
//        xl.setTextColor(R.color.courbe);
//        mchart.getDescription().setEnabled(false);
//        mchart.setDrawBorders(false);
//        mchart.setPinchZoom(true);
//        mchart.setDrawGridBackground(false);
//
//        YAxis leftAxis = mchart.getAxisLeft();
//        leftAxis.removeAllLimitLines();
//        leftAxis.setAxisMaximum(255f);
//        leftAxis.setAxisMinimum(-255f);
//        leftAxis.enableGridDashedLine(2f, 2f, 0);
//        leftAxis.setDrawLimitLinesBehindData(true);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                coeur.startAnimation(animation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coeur.startAnimation(animation2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    class ThreadFC extends Thread {
        public void run() {
            final TextView bpm = findViewById(R.id.BPM);
            final TextView niveaubatt = findViewById(R.id.NiveauBatt);
            final ImageView batteri = findViewById(R.id.batterie);
            final ImageView Resaux = findViewById(R.id.icone_resaux);

            while (StopThread) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d("nutre:", BLEManager.unsignedToBytes(E7_2.str[3]) + "/" + BLEManager.unsignedToBytes(E7_2.str[4]) +
                                    "/" + BLEManager.unsignedToBytes(E7_2.str[2]));
                            Log.d("h/l:", ""+BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[5]), BLEManager.unsignedToBytes(E7_2.str[6])));
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
                            if (E7_2.str[4] == 0) {

                                //if (Compteur <= 214) {
                                // Log.d("compteur", Compteur + "");
//                                    Lists.add(0, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[5]),
//                                            BLEManager.unsignedToBytes(E7_2.str[6])));
//                                    Lists.add(1, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[7]),
//                                            BLEManager.unsignedToBytes(E7_2.str[8])));
//                                    Lists.add(2, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[9]),
//                                            BLEManager.unsignedToBytes(E7_2.str[10])));
//                                    Lists.add(3, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[11]),
//                                            BLEManager.unsignedToBytes(E7_2.str[12])));
//                                    Lists.add(4, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[13]),
//                                            BLEManager.unsignedToBytes(E7_2.str[14])));
//                                    Lists.add(5, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[15]),
//                                            BLEManager.unsignedToBytes(E7_2.str[16])));
//                                    Lists.add(6, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[17]),
//                                            BLEManager.unsignedToBytes(E7_2.str[18])));
//                                Log.d("trames", Lists.get(0) + "/" + Lists.get(1) + "/" + Lists.get(2) + "/" + Lists.get(3) + "/" + Lists.get(4) + "/" + Lists.get(5)
//                                        + "/" + Lists.get(6) + "/ size: " + Lists.size());
//                                Log.d("tram:",  Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[5])) + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[6])) + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[7]))
//                                        + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[8])) + "/" +Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[9])) + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[10]))
//                                        + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[11])) + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[12]))+ "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[13]))
//                                        + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[14]))+ "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[15]))
//                                        + "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[16]))+ "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[17]))+ "/" + Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[18])));
//                                Log.d("nutre:", BLEManager.unsignedToBytes(E7_2.str[5]) + "/" + BLEManager.unsignedToBytes(E7_2.str[6]) + "/" +E7_2.str[7]+ "/" + E7_2.str[8] + "/" +E7_2.str[9] + "/" + E7_2.str[10]
//                                        + "/" + E7_2.str[11] + "/" + E7_2.str[12]+ "/" + E7_2.str[13]
//                                        + "/" +E7_2.str[14]+ "/" + E7_2.str[15]
//                                        + "/" + E7_2.str[16]+ "/" + E7_2.str[17]+ "/" + E7_2.str[18]);
                                //Compteur++;
//                                series.appendData(new DataPoint(lastXpoint, BLEManager.unsignedToBytes(E7_2.str[2])), true, 100);
//                                lastXpoint++;
                                series.appendData(new DataPoint(lastXpoint, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[5]),
                                        BLEManager.unsignedToBytes(E7_2.str[6]))), true, 100);
                                lastXpoint++;
                                //} else {
//                                    for (int i = 0; i < Lists.size(); i++) {
//                                        series.appendData(new DataPoint(lastXpoint,Lists.get(i)),true,100);
//                                        lastXpoint++;
//                                    }
//                                    int x = 0;
//                                    DataPoint[] points = new DataPoint[Lists.size()];
//                                    for (int i = 0; i < Lists.size(); i++) {
//
//                                        points[i] = new DataPoint(x, Lists.get(i));
//                                        x++;
//                                    }
//                                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
//                                    series.setDrawDataPoints(true);
//                                    series.setDataPointsRadius(7);
//                                    series.setThickness(7);
//                                    series.setColor(Color.CYAN);
//                                    graph.addSeries(series);
//                                    series.resetData(new DataPoint[0]);
                                //Compteur=0;
                                //Lists.clear();
                                //}

//                                Lists.clear();
//                                if (Paire % 3 == 0) {
//                                    Paire++;
//                                    indice = 0;
//                                    Log.d("trames", Lists.get(0) + "/" + Lists.get(1) + "/" + Lists.get(2) + "/" + Lists.get(3) + "/" + Lists.get(4) + "/" + Lists.get(5)
//                                            + "/" + Lists.get(6) + "/" + Lists.get(7) + "/" + Lists.get(8) + "/" + Lists.get(9) + "/" + Lists.get(10) + "/" + Lists.get(11)
//                                            + "/" + Lists.get(12) + "/" + Lists.get(13));
//
//                                    ArrayList<Entry> yvalues = new ArrayList<>();
//                                    for (int i = 0; i < Lists.size(); i++) {
//                                        yvalues.add(new Entry(i, Lists.get(i)));
//                                    }

//                                    LineDataSet set1 = new LineDataSet(yvalues, "");
//
//                                    set1.setLineWidth(3f);
//                                    set1.setDrawValues(false);
//                                    set1.setDrawCircles(true);
//                                    ArrayList<ILineDataSet> datasets = new ArrayList<>();
//                                    datasets.add(set1);
//                                    LineData data = new LineData(datasets);
//                                    mchart.setData(data);
//                                    mchart.animateX(1000, Easing.EasingOption.Linear);
//                                    Lists.clear();
//                                } else {
//                                    Paire++;
//
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[5]),
//                                            BLEManager.unsignedToBytes(E7_2.str[6])));
//                                    indice++;
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[7]),
//                                            BLEManager.unsignedToBytes(E7_2.str[8])));
//                                    indice++;
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[9]),
//                                            BLEManager.unsignedToBytes(E7_2.str[10])));
//                                    indice++;
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[11]),
//                                            BLEManager.unsignedToBytes(E7_2.str[12])));
//                                    indice++;
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[13]),
//                                            BLEManager.unsignedToBytes(E7_2.str[14])));
//                                    indice++;
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[15]),
//                                            BLEManager.unsignedToBytes(E7_2.str[16])));
//                                    indice++;
//                                    Lists.add(indice, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[17]),
//                                            BLEManager.unsignedToBytes(E7_2.str[18])));
//                                }

                                bpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));

                                Resaux.setImageResource(R.drawable.resaux);

                            } else {
                                bpm.setText("--");
                                niveaubatt.setText("-- %");
                                Resaux.setImageResource(R.drawable.resaux2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void re(View view) {
        StopThread = false;
        E7_2.str = null;
        Intent intent = new Intent(E10.this, E8.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            StopThread = false;
            E7_2.str = null;
            Intent intent = new Intent(E10.this, E8.class);
            startActivity(intent);
        }
        return false;
    }

}
