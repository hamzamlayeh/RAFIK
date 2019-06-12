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

public class E11 extends AppCompatActivity {

    //LineChart mchart;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    double lastXpoint = 0;
    static boolean StopThread = true;
    Activity activity;
    Animation animation, animation2;
    ImageView poumon;
    int Paire = 1, indice = 0;
    ArrayList<Integer> Lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e11);

        poumon = findViewById(R.id.imageView4);
//        ProgressBar bar = (ProgressBar) findViewById(R.id.vertical);
//        bar.setProgress(72);
        activity = this;
        StopThread = true;
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x00, 0x7B, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
        ThreadFR thread = new ThreadFR();
        thread.start();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        poumon.startAnimation(animation2);

        graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(70);
        graph.getGridLabelRenderer().setHumanRounding(true);
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getString(R.string.tempsinst));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.CYAN);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(false);
        series.setDataPointsRadius(7);
        series.setThickness(7);
        series.setColor(getResources().getColor(R.color.courbe_resp));
        graph.addSeries(series);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                poumon.startAnimation(animation);

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
                poumon.startAnimation(animation2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    class ThreadFR extends Thread {
        public void run() {
            final TextView rpm = findViewById(R.id.RPM);
            final TextView niveaubatt = findViewById(R.id.NiveauBatt);
            final ImageView batteri = findViewById(R.id.batterie);
            final ImageView Resaux = findViewById(R.id.icone_resaux);

            while (StopThread) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d("respiration:", BLEManager.unsignedToBytes(E7_2.str[2]) + "/" +
                                    "/" + BLEManager.unsignedToBytes(E7_2.str[3]) + "/" +
                                    "/" + BLEManager.unsignedToBytes(E7_2.str[4]));

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
//                                Lists.add(0, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[5]),
//                                        BLEManager.unsignedToBytes(E7_2.str[6])));
//                                Lists.add(1, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[7]),
//                                        BLEManager.unsignedToBytes(E7_2.str[8])));
//                                Lists.add(2, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[9]),
//                                        BLEManager.unsignedToBytes(E7_2.str[10])));
//                                Lists.add(3, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[11]),
//                                        BLEManager.unsignedToBytes(E7_2.str[12])));
//                                Lists.add(4, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[13]),
//                                        BLEManager.unsignedToBytes(E7_2.str[14])));
//                                Lists.add(5, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[15]),
//                                        BLEManager.unsignedToBytes(E7_2.str[16])));
//                                Lists.add(6, BLEManager.TestValeurTrame(BLEManager.unsignedToBytes(E7_2.str[17]),
//                                        BLEManager.unsignedToBytes(E7_2.str[18])));
//                                Log.d("trames", Lists.get(0) + "/" + Lists.get(1) + "/" + Lists.get(2) + "/" + Lists.get(3) + "/" + Lists.get(4) + "/" + Lists.get(5)
//                                        + "/" + Lists.get(6) + "/ size: " + Lists.size());
//                                Log.d("tram:",  Integer.toHexString(E7_2.str[5]) + "/" + Integer.toHexString(E7_2.str[6]) + "/" + Integer.toHexString(E7_2.str[7])
//                                        + "/" + Integer.toHexString(E7_2.str[8]) + "/" +Integer.toHexString(E7_2.str[9]) + "/" + Integer.toHexString(E7_2.str[10])
//                                        + "/" + Integer.toHexString(E7_2.str[11]) + "/" + Integer.toHexString(E7_2.str[12])+ "/" + Integer.toHexString(E7_2.str[13])
//                                        + "/" + Integer.toHexString(E7_2.str[14])+ "/" + Integer.toHexString(E7_2.str[15])
//                                        + "/" + Integer.toHexString(E7_2.str[16])+ "/" + Integer.toHexString(E7_2.str[17])+ "/" + Integer.toHexString(E7_2.str[18]));
//                                for (int i = 0; i < Lists.size(); i++) {
//                                    series.appendData(new DataPoint(lastXpoint,Lists.get(i)),true,100);
//                                    lastXpoint++;
//                                }
//                                Lists.clear();
                                series.appendData(new DataPoint(lastXpoint, BLEManager.unsignedToBytes(E7_2.str[2])), true, 100);
                                lastXpoint++;
//                                if (Paire % 3 == 0) {
//                                    Paire++;
//                                    indice = 0;
//                                    Log.d("trames", Lists.get(0) + "/" + Lists.get(1) + "/" + Lists.get(2) + "/" + Lists.get(3) + "/" + Lists.get(4) + "/" + Lists.get(5)
//                                            + "/" + Lists.get(6) + "/" + Lists.get(7) + "/" + Lists.get(8) + "/" + Lists.get(9) + "/" + Lists.get(10) + "/" + Lists.get(11)
//                                            + "/" + Lists.get(12) + "/" + Lists.get(13));
//
//                                    ArrayList<Entry> yvalues = new ArrayList<>();
//
//                                    yvalues.add(new Entry((1), Lists.get(0)));
//                                    yvalues.add(new Entry((2), Lists.get(1)));
//                                    yvalues.add(new Entry((3), Lists.get(2)));
//                                    yvalues.add(new Entry((4), Lists.get(3)));
//                                    yvalues.add(new Entry((5), Lists.get(4)));
//                                    yvalues.add(new Entry((6), Lists.get(5)));
//                                    yvalues.add(new Entry((7), Lists.get(6)));
//                                    yvalues.add(new Entry((8), Lists.get(7)));
//                                    yvalues.add(new Entry((9), Lists.get(8)));
//                                    yvalues.add(new Entry((10), Lists.get(9)));
//                                    yvalues.add(new Entry((11), Lists.get(10)));
//                                    yvalues.add(new Entry((12), Lists.get(11)));
//                                    yvalues.add(new Entry((13), Lists.get(12)));
//                                    yvalues.add(new Entry((14), Lists.get(13)));
//
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
//                                    Log.d("size", Lists.size() + "");
//                                }
                                rpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));

                                Resaux.setImageResource(R.drawable.resaux);

                            } else {
                                rpm.setText("--");
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
        //TrameSop();
        E7_2.str = null;
        Intent intent = new Intent(this, E8.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            StopThread = false;
            E7_2.str = null;
//            TrameSop();
            Intent intent = new Intent(this, E8.class);
            startActivity(intent);
        }
        return false;
    }

//    public void TrameSop() {
//        new Handler().postDelayed(new Runnable() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
//            public void run() {
//                byte[] buffer = {0x02, 0x73, 0x01, 0x74, 0x03, 0x0A};
//                BLEManager.writeData(buffer);
//                try {
//                    Thread.sleep(1000);
//                    BLEManager.readData();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 1000);
//    }

}
