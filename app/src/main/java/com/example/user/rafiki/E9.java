package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class E9 extends AppCompatActivity {

    //LineChart mchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e9);

//        mchart = (LineChart) findViewById(R.id.chart1);
//        mchart.setDragEnabled(true);
//        mchart.setScaleEnabled(true);
//        mchart.getAxisRight().setEnabled(false);
//        mchart.getAxisLeft().setEnabled(false);
//        mchart.getXAxis().setEnabled(false);
//        mchart.getDescription().setEnabled(false);
//        mchart.setDrawBorders(false);
//        mchart.setPinchZoom(true);
//        mchart.setDrawGridBackground(false);
//
//        YAxis leftAxis=mchart.getAxisLeft();
//        leftAxis.removeAllLimitLines();
//        leftAxis.setAxisMaximum(100f);
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.enableGridDashedLine(2f,2f,0);
//        leftAxis.setDrawLimitLinesBehindData(true);
//
//        ArrayList<Entry> yvalues = new ArrayList<>();
//
//        yvalues.add(new Entry(0,5f));
//        yvalues.add(new Entry(1,5f));
//        yvalues.add(new Entry(2,10f));
//        yvalues.add(new Entry(3,8f));
//        yvalues.add(new Entry(4,10f));
//        yvalues.add(new Entry(5,4f));
//        yvalues.add(new Entry(6,20f));
//        yvalues.add(new Entry(7,5f));
//        yvalues.add(new Entry(8,2f));
//        yvalues.add(new Entry(9,10f));
//        yvalues.add(new Entry(10,50f));
//        yvalues.add(new Entry(11,60f));
//        yvalues.add(new Entry(12,70f));
//        yvalues.add(new Entry(13,60f));
//        yvalues.add(new Entry(14,65f));
//        yvalues.add(new Entry(15,80f));
//        yvalues.add(new Entry(16,60f));
//
//        LineDataSet set1 = new LineDataSet(yvalues,"");
//
//        set1.setLineWidth(0f);
//        set1.setDrawValues(false);
//        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        set1.setDrawFilled(true);
//        set1.setFillDrawable(ContextCompat.getDrawable(this,R.drawable.chart_gradient));
//        set1.setCircleColorHole(Color.BLACK);
//        set1.setCircleColor(Color.BLACK);
//
//        ArrayList<ILineDataSet> datasets=new ArrayList<>();
//        datasets.add(set1);
//        LineData data=new LineData(datasets);
//        mchart.setData(data);
//        mchart.animateX(1400, Easing.EasingOption.Linear);
    }


    public void retour(View view) {
        Intent intent = new Intent(this, E8.class);
        startActivity(intent);
    }
}