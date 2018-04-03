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

public class E10 extends AppCompatActivity {

    LineChart mchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_e10);

        //ProgressBar bar = (ProgressBar) findViewById(R.id.vertical);
        //bar.setProgress(25);

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
        ;
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

    public void alert(View view) {
        AlertDialog.Builder alt= new AlertDialog.Builder(this);
        alt.setTitle(" "+" "+getString(R.string.note_consiel))
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
