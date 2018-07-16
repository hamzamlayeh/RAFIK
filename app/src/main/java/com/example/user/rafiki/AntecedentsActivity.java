package com.example.user.rafiki;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

public class AntecedentsActivity extends AppCompatActivity {
    static int P;
    EditText act1, acte2, acte3, acte4, acte5, date1, date2, date3, date4, date5;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    final Calendar c = Calendar.getInstance();
    int y= c.get(Calendar.YEAR);
    int m= c.get(Calendar.MONTH);
    int day= c.get(Calendar.DAY_OF_MONTH);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedents);
        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        date3 = findViewById(R.id.date3);
        date4 = findViewById(R.id.date4);
        date5 = findViewById(R.id.date5);



    }

    public void retoure_fiche(View view) {
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
    }

    public void get_date1(View view) {

        DatePickerDialog.OnDateSetListener dpd =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int s = month+1;
                String a = dayOfMonth+"/"+s+"/"+year;
                date1.setText(a);
            }
        };

        DatePickerDialog d =new DatePickerDialog(this,dpd,y,m,day);
        d.show();
    }

    public void get_date2(View view) {
        DatePickerDialog.OnDateSetListener dpd =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int s = month+1;
                String a = dayOfMonth+"/"+s+"/"+year;
                date2.setText(a);
            }
        };

        DatePickerDialog d =new DatePickerDialog(this,dpd,y,m,day);
        d.show();
    }

    public void get_date3(View view) {
        DatePickerDialog.OnDateSetListener dpd =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int s = month+1;
                String a = dayOfMonth+"/"+s+"/"+year;
                date3.setText(a);
            }
        };

        DatePickerDialog d =new DatePickerDialog(this,dpd,y,m,day);
        d.show();
    }

    public void get_date4(View view) {
        DatePickerDialog.OnDateSetListener dpd =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int s = month+1;
                String a = dayOfMonth+"/"+s+"/"+year;
                date4.setText(a);
            }
        };

        DatePickerDialog d =new DatePickerDialog(this,dpd,y,m,day);
        d.show();
    }

    public void get_date5(View view) {
        DatePickerDialog.OnDateSetListener dpd =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int s = month+1;
                String a = dayOfMonth+"/"+s+"/"+year;
                date5.setText(a);
            }
        };

        DatePickerDialog d =new DatePickerDialog(this,dpd,y,m,day);
        d.show();
    }

}