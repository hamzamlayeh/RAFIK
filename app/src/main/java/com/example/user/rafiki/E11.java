package com.example.user.rafiki;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class E11 extends AppCompatActivity {

    LineChart mchart;
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
        ThreadFR thread = new ThreadFR();
        thread.start();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        poumon.startAnimation(animation2);

        mchart = (LineChart) findViewById(R.id.chart1);
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
        leftAxis.setAxisMaximum(65535f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(2f, 2f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

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
                            if (E7_2.str[4] == 0) {
                                if (Paire % 3 == 0) {
                                    Paire++;
                                    indice = 0;
                                    Log.d("trames", Lists.get(0) + "/" + Lists.get(1) + "/" + Lists.get(2) + "/" + Lists.get(3) + "/" + Lists.get(4) + "/" + Lists.get(5)
                                            + "/" + Lists.get(6) + "/" + Lists.get(7) + "/" + Lists.get(8) + "/" + Lists.get(9) + "/" + Lists.get(10) + "/" + Lists.get(11)
                                            + "/" + Lists.get(12) + "/" + Lists.get(13));

                                    ArrayList<Entry> yvalues = new ArrayList<>();

                                    yvalues.add(new Entry((1), Lists.get(0)));
                                    yvalues.add(new Entry((2), Lists.get(1)));
                                    yvalues.add(new Entry((3), Lists.get(2)));
                                    yvalues.add(new Entry((4), Lists.get(3)));
                                    yvalues.add(new Entry((5), Lists.get(4)));
                                    yvalues.add(new Entry((6), Lists.get(5)));
                                    yvalues.add(new Entry((7), Lists.get(6)));
                                    yvalues.add(new Entry((8), Lists.get(7)));
                                    yvalues.add(new Entry((9), Lists.get(8)));
                                    yvalues.add(new Entry((10), Lists.get(9)));
                                    yvalues.add(new Entry((11), Lists.get(10)));
                                    yvalues.add(new Entry((12), Lists.get(11)));
                                    yvalues.add(new Entry((13), Lists.get(12)));
                                    yvalues.add(new Entry((14), Lists.get(13)));

                                    LineDataSet set1 = new LineDataSet(yvalues, "");

                                    set1.setLineWidth(3f);
                                    set1.setDrawValues(false);
                                    set1.setDrawCircles(true);
                                    ArrayList<ILineDataSet> datasets = new ArrayList<>();
                                    datasets.add(set1);
                                    LineData data = new LineData(datasets);
                                    mchart.setData(data);
                                    mchart.animateX(1000, Easing.EasingOption.Linear);
                                    Lists.clear();
                                } else {
                                    Paire++;

                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[5]),
                                            BLEManager.unsignedToBytes(E7_2.str[6]))));
                                    indice++;
                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[7]),
                                            BLEManager.unsignedToBytes(E7_2.str[8]))));
                                    indice++;
                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[9]),
                                            BLEManager.unsignedToBytes(E7_2.str[10]))));
                                    indice++;
                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[11]),
                                            BLEManager.unsignedToBytes(E7_2.str[12]))));
                                    indice++;
                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[13]),
                                            BLEManager.unsignedToBytes(E7_2.str[14]))));
                                    indice++;
                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[15]),
                                            BLEManager.unsignedToBytes(E7_2.str[16]))));
                                    indice++;
                                    Lists.add(indice, BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[17]),
                                            BLEManager.unsignedToBytes(E7_2.str[18]))));
                                    Log.d("size", Lists.size() + "");
                                }
                                rpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));
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
                                Resaux.setImageResource(R.drawable.resaux);

                            } else {
                                rpm.setText("--");
                                niveaubatt.setText("-- %");
                                batteri.setImageResource(R.drawable.batt7);
                                Resaux.setImageResource(R.drawable.resaux2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void re(View view) {
        StopThread = false;
        TrameSop();
        E7_2.str = null;
        Intent intent = new Intent(this, E8.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            StopThread = false;
            E7_2.str = null;
            TrameSop();
            Intent intent = new Intent(this, E8.class);
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
