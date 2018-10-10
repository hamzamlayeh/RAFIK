package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MaladiActivity extends AppCompatActivity {

    EditText Maladi1, Maladi2, Maladi3, Maladi4, Maladi5, Maladi6;
    String M1, M2, M3, M4, M5, M6;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    List<String> listMaladi = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maladi);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        Maladi1 = findViewById(R.id.malade1);
        Maladi2 = findViewById(R.id.malade2);
        Maladi3 = findViewById(R.id.malade3);
        Maladi4 = findViewById(R.id.malade4);
        Maladi5 = findViewById(R.id.malade5);
        Maladi6 = findViewById(R.id.malade6);

        if (ds.getCountMaladi() > 0) {
            listMaladi = ds.getListMaladi();
            Maladi1.setText(listMaladi.get(0));
            Maladi2.setText(listMaladi.get(1));
            Maladi3.setText(listMaladi.get(2));
            Maladi4.setText(listMaladi.get(3));
            Maladi5.setText(listMaladi.get(4));
            Maladi6.setText(listMaladi.get(5));
        }
        Maladi1.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Maladi1.setText("");
                long x = ds.UpdateMaladi("", 1);
                Toast.makeText(MaladiActivity.this, R.string.maladie_sup, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        Maladi2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Maladi2.setText("");
                long x = ds.UpdateMaladi("", 2);
                Toast.makeText(MaladiActivity.this, R.string.maladie_sup, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        Maladi3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Maladi3.setText("");
                long x = ds.UpdateMaladi("", 3);
                Toast.makeText(MaladiActivity.this, R.string.maladie_sup, Toast.LENGTH_LONG).show();

                return false;
            }
        });
        Maladi4.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Maladi4.setText("");
                long x = ds.UpdateMaladi("", 4);
                Toast.makeText(MaladiActivity.this, R.string.maladie_sup, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        Maladi5.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Maladi5.setText("");
                long x = ds.UpdateMaladi("", 5);
                Toast.makeText(MaladiActivity.this, R.string.maladie_sup, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        Maladi6.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Maladi6.setText("");
                long x = ds.UpdateMaladi("", 6);
                Toast.makeText(MaladiActivity.this, R.string.maladie_sup, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void retoure_fiche(View view) {
        M1 = Maladi1.getText().toString();
        M2 = Maladi2.getText().toString();
        M3 = Maladi3.getText().toString();
        M4 = Maladi4.getText().toString();
        M5 = Maladi5.getText().toString();
        M6 = Maladi6.getText().toString();
        listMaladi.add(0, M1);
        listMaladi.add(1, M2);
        listMaladi.add(2, M3);
        listMaladi.add(3, M4);
        listMaladi.add(4, M5);
        listMaladi.add(5, M6);

        if (ds.getCountMaladi() <= 0) {
            int i = 0;
            while (i < listMaladi.size()) {

                long x = ds.addmaladi(listMaladi.get(i));
                i++;
            }
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
            MaladiActivity.this.finish();
        } else {
            int i = 0;
            while (i < listMaladi.size()) {

                long x = ds.UpdateMaladi(listMaladi.get(i), i + 1);
                i++;
            }
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
            MaladiActivity.this.finish();
        }
    }

    public void alert(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" ").setIcon(R.drawable.alert)
                .setMessage(getString(R.string.modifier) + "       " + getString(R.string.cliquer_sur_maladie) + "\n" +
                        getString(R.string.supprimer) + "   " + getString(R.string.appui_long_sur_maladie)
                )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}
