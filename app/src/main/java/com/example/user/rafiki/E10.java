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
import android.telephony.SmsManager;
import android.view.View;
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

public class E10 extends AppCompatActivity {

    LineChart mchart;
    static boolean StopThread = true;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e10);

        activity=this;
        ProgressBar bar = (ProgressBar) findViewById(R.id.vertical);
        bar.setProgress(25);
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
        ThreadFC  thread = new E10.ThreadFC ();
        thread.start();
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

    public void re(View view) {
        Intent intent = new Intent(E10.this, E8.class);
        startActivity(intent);
    }
    class ThreadFC extends Thread {
        public void run() {
//            final TextView bpm = findViewById(R.id.BPM_D);
//            final TextView rpm = findViewById(R.id.RPM_D);
//            final TextView temps = findViewById(R.id.TEMP_D);
//            final TextView oxy = findViewById(R.id.oxigen);
//            final TextView niveaubatt = findViewById(R.id.NiveauBatt);
//
//            final ImageView batteri = findViewById(R.id.batterie);

            while (StopThread) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                        } catch (Exception e) {
                            ;
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
}
