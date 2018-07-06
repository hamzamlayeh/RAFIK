package com.example.user.rafiki;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.rafiki.Adapter.Antecedents_Adapter;
import com.example.user.rafiki.ItemData.Antecedents_ItemData;
import com.example.user.rafiki.Helper.RecyclerViewClickListener;
import com.example.user.rafiki.Helper.RecyclerViewTouchListener;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.user.rafiki.Adapter.Antecedents_Adapter.ViewHolder.TEST_VIDE;

public class AntecedentsActivity extends AppCompatActivity {
    RecyclerView r;
    EditText date, acte;
    Antecedents_Adapter myAdapter;
    ArrayList<Antecedents_ItemData> list = new ArrayList<Antecedents_ItemData>();
    SharedPreferences.Editor editor;
    SharedPreferences pref;
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

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 2);
        ds = new UserDataSource(helper);
        if (list!=null) {
            list = (ArrayList<Antecedents_ItemData>) ds.getListAnte();
        }
        if (list.size() ==5) {
            ajout.setVisibility(View.GONE);
            conteneur.setVisibility(View.GONE);


        }else {
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
                if (list.size() ==5) {
                    ajout.setVisibility(View.VISIBLE);
                    conteneur.setVisibility(View.VISIBLE);
                }

                date.setText(date1.getText().toString());
                acte.setText(acte1.getText().toString());

                pos = position;

            }

            @Override
            public void onLongClick(View view, int position) {
                try {

                    list.remove(position);
                    ds.deleteAnti(position+1);
                    myAdapter.notifyDataSetChanged();
                    if (list.size() <5) {
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
            list.set(pos, Data);
            long x = ds.UpdateAnti(T2, T1, pos + 1);
            myAdapter.notifyDataSetChanged();
            date.setText("");
            acte.setText("");
            pos = -1;

            if (list.size() ==5) {
                ajout.setVisibility(View.GONE);
                conteneur.setVisibility(View.GONE);
            }
        }

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

    @TargetApi(16)
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


}

