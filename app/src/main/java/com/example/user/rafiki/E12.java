package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class E12 extends AppCompatActivity {

    Intent intent;
    private LineChart mchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e12);

        ProgressBar bar =(ProgressBar)findViewById(R.id.vertical);
        bar.setVisibility(View.INVISIBLE);
//        bar.setProgress(100);

        mchart = (LineChart) findViewById(R.id.chart1);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(true);
        mchart.getAxisRight().setEnabled(false);
        mchart.getXAxis().setEnabled(false);
        mchart.getDescription().setEnabled(false);
        mchart.setDrawBorders(false);
        mchart.setPinchZoom(true);
        mchart.setDrawGridBackground(false);

        YAxis leftAxis=mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(50f);
        leftAxis.setAxisMinimum(20f);
        leftAxis.setTextColor(R.color.left);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);

        ArrayList<Entry> yvalues = new ArrayList<>();
        for (int i=0;i<=50;i++){
            float val=(float) (Math.random()*(40-35)+35);
            yvalues.add(new Entry((i+5),val));
        }
//        yvalues.add(new Entry(0,37f));
//        yvalues.add(new Entry(1,38f));
//        yvalues.add(new Entry(2,38f));
//        yvalues.add(new Entry(3,36f));
//        yvalues.add(new Entry(4,35f));
//        yvalues.add(new Entry(5,36f));
//        yvalues.add(new Entry(6,36f));
//        yvalues.add(new Entry(7,37.5f));
//        yvalues.add(new Entry(8,37.5f));
//        yvalues.add(new Entry(9,37f));
//        yvalues.add(new Entry(10,36.9f));
//        yvalues.add(new Entry(11,36.5f));

        LineDataSet set1 = new LineDataSet(yvalues,"");

        set1.setLineWidth(2f);
        set1.setHighlightEnabled(false);
        set1.setDrawValues(false);

        //set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set1.setCircleColorHole(R.color.chart);



        ArrayList<ILineDataSet> datasets=new ArrayList<>();
        datasets.add(set1);
        LineData data=new LineData(datasets);
        mchart.setData(data);
        mchart.animateX(1400, Easing.EasingOption.Linear);
    }

    public void retour(View view) {
        Intent intent = new Intent(E12.this, E8.class);
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
