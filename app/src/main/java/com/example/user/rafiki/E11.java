package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e11);

        ProgressBar bar =(ProgressBar)findViewById(R.id.vertical);
        bar.setProgress(72);
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

        YAxis leftAxis=mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(32f);
        leftAxis.setAxisMinimum(16f);
        leftAxis.enableGridDashedLine(2f,2f,0);
        leftAxis.setDrawLimitLinesBehindData(true);

        ArrayList<Entry> yvalues = new ArrayList<>();
        for (int i=0;i<=50;i++){
            float val=(float) (Math.random()*(28-21)+21);
            yvalues.add(new Entry((i/3f),val));
        }

        LineDataSet set1 = new LineDataSet(yvalues,"");

        set1.setLineWidth(2f);
        set1.setDrawValues(false);
        set1.setDrawCircles(false);
        set1.setColor(R.color.courbe);

        ArrayList<ILineDataSet> datasets=new ArrayList<>();
        datasets.add(set1);
        LineData data=new LineData(datasets);
        mchart.setData(data);
        mchart.animateX(1400, Easing.EasingOption.Linear);
    }


    public void re(View view) {
        Intent intent = new Intent(this, E8.class);
        startActivity(intent);
    }

    public void alert(View view) {
        AlertDialog.Builder alt= new AlertDialog.Builder(this);
        alt.setTitle(" "+" "+getString(R.string.notes_conseils))
                .setIcon(R.drawable.alert)
                .setMessage("\n "+" "+" "+" "+" "+" - dernier alert\n \n"+
                        " "+" "+" "+" "+" "+" - khaled  \n \n"+
                        " "+" "+" "+" "+" "+" - khaled"
                )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}
