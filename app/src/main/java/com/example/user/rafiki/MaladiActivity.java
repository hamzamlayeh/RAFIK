package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.client.json.Json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MaladiActivity extends AppCompatActivity {

    EditText Maladi1, Maladi2, Maladi3, Maladi4, Maladi5, Maladi6;
    String M1, M2, M3, M4, M5, M6;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    List<String> listMaladi = new ArrayList<String>();
    Set set;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maladi);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 2);
        ds = new UserDataSource(helper);

        Maladi1 = findViewById(R.id.malade1);
        Maladi2 = findViewById(R.id.malade2);
        Maladi3 = findViewById(R.id.malade3);
        Maladi4 = findViewById(R.id.malade4);
        Maladi5 = findViewById(R.id.malade5);
        Maladi6 = findViewById(R.id.malade6);

        Toast.makeText(this, ds.getCountMaladi() + "", Toast.LENGTH_SHORT).show();
        if (ds.getCountMaladi()> 0){
            listMaladi = ds.getListMaladi();
            Maladi1.setText(listMaladi.get(0));
            Maladi2.setText(listMaladi.get(1));
            Maladi3.setText(listMaladi.get(2));
            Maladi4.setText(listMaladi.get(3));
            Maladi5.setText(listMaladi.get(4));
            Maladi6.setText(listMaladi.get(5));
        }
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
        }else {
            int i = 0;
            while (i < listMaladi.size()) {

                long x = ds.UpdateMaladi(listMaladi.get(i),i+1);
                i++;
            }
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        }
    }

    public void alert(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" ").setIcon(R.drawable.alert)
                .setMessage("\n" + getString(R.string.ajouter) + "         " + getString(R.string.cliquer_sur_maladie) + "\n" +
                        getString(R.string.modifier) + "       " + getString(R.string.cliquer_sur_maladie) + "\n" +
                        getString(R.string.supprimer) + "   " + getString(R.string.appui_long_sur_maladie)
                )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}
