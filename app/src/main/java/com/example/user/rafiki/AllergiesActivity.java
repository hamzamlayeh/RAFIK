package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.tuyenmonkey.mkloader.MKLoader;

public class AllergiesActivity extends AppCompatActivity {
    TextView insectes, medicaments, animaux, aliments, respiratoire, pollen, autre;
    String data;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    CheckBox rd_1, rd_2, rd_3, rd_4, rd_5, rd_6, rd_7;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    MKLoader mkLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        pref = getApplicationContext().getSharedPreferences("allergies", MODE_PRIVATE);
        editor = pref.edit();

        mkLoader = findViewById(R.id.alerr);
        rd_1 = findViewById(R.id.rd_1);
        rd_2 = findViewById(R.id.rd_2);
        rd_3 = findViewById(R.id.rd_3);
        rd_4 = findViewById(R.id.rd_4);
        rd_5 = findViewById(R.id.rd_5);
        rd_6 = findViewById(R.id.rd_6);
        rd_7 = findViewById(R.id.rd_7);

        rd_1.setChecked(pref.getBoolean("ch1", false));
        rd_2.setChecked(pref.getBoolean("ch2", false));
        rd_3.setChecked(pref.getBoolean("ch3", false));
        rd_4.setChecked(pref.getBoolean("ch4", false));
        rd_5.setChecked(pref.getBoolean("ch5", false));
        rd_6.setChecked(pref.getBoolean("ch6", false));
        rd_7.setChecked(pref.getBoolean("ch7", false));

        insectes = findViewById(R.id.T1);
        medicaments = findViewById(R.id.T2);
        animaux = findViewById(R.id.T3);
        aliments = findViewById(R.id.T4);
        respiratoire = findViewById(R.id.T5);
        pollen = findViewById(R.id.T6);
        autre = findViewById(R.id.T7);
    }

    public void retoure_fiche(View view) {
        mkLoader.setVisibility(View.VISIBLE);
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
        AllergiesActivity.this.finish();
    }

    public void r1(View view) {
        editor.putBoolean("ch1", rd_1.isChecked());
        editor.apply();
        if (!rd_1.isChecked()) {
            ds.Updateallergie("", "", "", 1);
        } else {
            data = insectes.getText().toString();
            if (!ds.verifId_allergie(1)) {
                ds.addallergie(1);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 1);
            startActivity(ite);
        }
    }

    public void r2(View view) {
        editor.putBoolean("ch2", rd_2.isChecked());
        editor.apply();

        if (!rd_2.isChecked()) {
            ds.Updateallergie("", "", "", 2);
        } else {
            data = medicaments.getText().toString();
            if (!ds.verifId_allergie(2)) {
                ds.addallergie(2);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 2);
            startActivity(ite);
        }
    }

    public void r3(View view) {
        editor.putBoolean("ch3", rd_3.isChecked());
        editor.apply();
        if (!rd_3.isChecked()) {
            ds.Updateallergie("", "", "", 3);
        } else {
            data = animaux.getText().toString();
            if (!ds.verifId_allergie(3)) {
                ds.addallergie(3);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 3);
            startActivity(ite);
        }
    }

    public void r4(View view) {
        editor.putBoolean("ch4", rd_4.isChecked());
        editor.apply();
        if (!rd_4.isChecked()) {
            ds.Updateallergie("", "", "", 4);
        } else {
            data = aliments.getText().toString();
            if (!ds.verifId_allergie(4)) {
                ds.addallergie(4);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 4);
            startActivity(ite);
        }
    }

    public void r5(View view) {
        editor.putBoolean("ch5", rd_5.isChecked());
        editor.apply();
        if (!rd_5.isChecked()) {
            ds.Updateallergie("", "", "", 5);
        } else {
            data = respiratoire.getText().toString();
            if (!ds.verifId_allergie(5)) {
                ds.addallergie(5);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 5);
            startActivity(ite);
        }
    }

    public void r6(View view) {
        editor.putBoolean("ch6", rd_6.isChecked());
        editor.apply();
        if (!rd_6.isChecked()) {
            ds.Updateallergie("", "", "", 6);
        } else {
            data = pollen.getText().toString();
            if (!ds.verifId_allergie(6)) {
                ds.addallergie(6);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 6);
            startActivity(ite);
        }
    }

    public void r7(View view) {
        editor.putBoolean("ch7", rd_7.isChecked());
        editor.apply();
        if (!rd_7.isChecked()) {
            ds.Updateallergie("", "", "", 7);
        } else {
            data = autre.getText().toString();
            if (!ds.verifId_allergie(7)) {
                ds.addallergie(7);
            }
            Intent ite = new Intent(this, AllergiesActivityContainer.class);
            ite.putExtra("allergies", data);
            ite.putExtra("Id", 7);
            startActivity(ite);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
        }
        return false;
    }
}
