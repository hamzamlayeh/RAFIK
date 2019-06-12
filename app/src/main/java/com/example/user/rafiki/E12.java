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
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
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
import java.util.List;

public class E12 extends AppCompatActivity {

    Intent intent;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    double lastXpoint = 0;
    static boolean StopThread = true;
    Activity activity;

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

        graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(50);
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
        series.setColor(getResources().getColor(R.color.txt_hesto_color));
        graph.addSeries(series);

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
                            lastXpoint++;
                            Log.d("trame",BLEManager.unsignedToBytes(E7_2.str[2])+"");
                            series.appendData(new DataPoint(lastXpoint,BLEManager.unsignedToBytes(E7_2.str[2])),true,100);

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

    public void retour(View view) {
        StopThread = false;
        E7_2.str = null;
        Intent intent = new Intent(E12.this, E8.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            StopThread = false;
            E7_2.str = null;
            Intent intent = new Intent(E12.this, E8.class);
            startActivity(intent);
        }
        return false;
    }
}
