package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.rafiki.Adapter.Antecedents_Adapter;
import com.example.user.rafiki.Helper.RecyclerViewClickListener;
import com.example.user.rafiki.Helper.RecyclerViewTouchListener;
import com.example.user.rafiki.ItemData.Antecedents_ItemData;

import java.util.ArrayList;

public class AntecedentsActivity extends AppCompatActivity {
    RecyclerView r;
    EditText date, acte;
    Antecedents_Adapter myAdapter;
    ArrayList<Antecedents_ItemData> list = new ArrayList<Antecedents_ItemData>();
    SharedPreferences.Editor editor;
    ImageView ajout;
    int i = 0;
    String TestDate, TestActe;
    LinearLayout conteneur;
    static int pos = -1;
    MySQLiteOpenHelper helper;
    UserDataSource ds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedents);
        date = findViewById(R.id.date);
        acte = findViewById(R.id.acte);
        ajout = findViewById(R.id.ajout);
        conteneur = findViewById(R.id.conteneur);
        r = (RecyclerView) findViewById(R.id.r);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        if (list != null) {
            list = (ArrayList<Antecedents_ItemData>) ds.getListAnte();
        }
        if (list.size() == 5) {
            ajout.setVisibility(View.GONE);
            conteneur.setVisibility(View.GONE);
        } else {
            ajout.setVisibility(View.VISIBLE);
            conteneur.setVisibility(View.VISIBLE);
        }
        myAdapter = new Antecedents_Adapter(this, list);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(myAdapter);

        r.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), r, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                EditText acte1 = view.findViewById(R.id.acte);
                EditText date1 = view.findViewById(R.id.date);
                if (list.size() == 5) {
                  //  ajout.setVisibility(View.VISIBLE);
                    conteneur.setVisibility(View.VISIBLE);
                }
                date.setText(date1.getText().toString());
                acte.setText(acte1.getText().toString());
                ajout.setVisibility(View.GONE);
                pos = position;

            }

            @Override
            public void onLongClick(View view, int position) {
                try {

                    list.remove(position);
                    ds.deleteAnti(position );
                    myAdapter.notifyDataSetChanged();
                    if (list.size() < 5) {
                        ajout.setVisibility(View.VISIBLE);
                        conteneur.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }));
    }

    public void retoure_fiche(View view) {
        if (pos != -1) {
            String T1 = date.getText().toString();
            String T2 = acte.getText().toString();

            Antecedents_ItemData Data = new Antecedents_ItemData(T2, T1);
            list.set(pos+1, Data);
            long x = ds.UpdateAnti(T2, T1, pos + 1);
            myAdapter.notifyDataSetChanged();
            date.setText("");
            acte.setText("");
            pos = -1;
            ajout.setVisibility(View.VISIBLE);
            if (list.size() == 5) {
                ajout.setVisibility(View.GONE);
                conteneur.setVisibility(View.GONE);
            }
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
        }
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
    }

    public void ajout_linge(View view) {

        TestDate = date.getText().toString();
        TestActe = acte.getText().toString();

        if (valider()) {
            if (list.size() <= 5) {
                list.add(new Antecedents_ItemData(TestActe, TestDate));
                myAdapter.notifyDataSetChanged();
                date.setText("");
                acte.setText("");
                ajout.setVisibility(View.VISIBLE);

                long x = ds.addAnte(new Antecedents_ItemData(TestActe, TestDate), list.size());
                if (list.size() >= 5) {
                    ajout.setVisibility(View.GONE);
                    conteneur.setVisibility(View.GONE);
                }
            }

        }
    }

    private boolean valider() {
        boolean valide = true;
        if (TestDate.isEmpty()) {
            date.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (TestActe.isEmpty()) {
            acte.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        return valide;
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