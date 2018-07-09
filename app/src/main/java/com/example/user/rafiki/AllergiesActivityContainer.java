package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.rafiki.Adapter.Allergis_Adapter;
import com.example.user.rafiki.Helper.RecyclerViewClickListener;
import com.example.user.rafiki.Helper.RecyclerViewTouchListener;
import com.example.user.rafiki.ItemData.Allergis_ItemData;
import com.example.user.rafiki.ItemData.Antecedents_ItemData;

import java.util.ArrayList;

public class AllergiesActivityContainer extends AppCompatActivity {
    RecyclerView r;
    EditText donne;
    TextView NonAllergies;
    Allergis_Adapter myAdapter;
    ArrayList<Allergis_ItemData> list = new ArrayList<Allergis_ItemData>();
    SharedPreferences.Editor editor;
    ImageView ajout;
    int i = 0;
    String Testdonne;
    LinearLayout conteneur;
    static int pos = -1;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies_container);
        donne = findViewById(R.id.donne);
        ajout = findViewById(R.id.ajout);
        conteneur = findViewById(R.id.conteneur);
        r = (RecyclerView) findViewById(R.id.r);

         NonAllergies=findViewById(R.id.NonAllergies);

        String nomAllergies= getIntent().getExtras().getString("allergies");
        NonAllergies.setText(nomAllergies);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null, 2);
        ds = new UserDataSource(helper);

        if (list != null) {
//            list = (ArrayList<Allergis_ItemData>) ds.getListAnte();
        }
        if (list.size() == 5) {
            ajout.setVisibility(View.GONE);
            conteneur.setVisibility(View.GONE);
        } else {
            ajout.setVisibility(View.VISIBLE);
            conteneur.setVisibility(View.VISIBLE);
        }
        myAdapter = new Allergis_Adapter(this, list);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(myAdapter);

        r.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), r, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                TextView donner = view.findViewById(R.id.donne);

                if (list.size() == 3) {
//                    ajout.setVisibility(View.VISIBLE);
                    conteneur.setVisibility(View.VISIBLE);
                }
                donne.setText(donner.getText().toString());
                ajout.setVisibility(View.GONE);
                pos = position;

            }

            @Override
            public void onLongClick(View view, int position) {
                try {

                    list.remove(position);
                    myAdapter.notifyDataSetChanged();
                    if (list.size() < 3) {
                        ajout.setVisibility(View.VISIBLE);
                        conteneur.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }));
    }

    public void ajout_linge(View view) {
        Testdonne = donne.getText().toString();

        if (valider()) {
            if (list.size() < 3) {
                list.add(new Allergis_ItemData(Testdonne));
                myAdapter.notifyDataSetChanged();
                donne.setText("");

                ajout.setVisibility(View.VISIBLE);

//                long x = ds.addAnte(new Antecedents_ItemData(TestActe, TestDate), list.size());
                 if (list.size() >= 3) {
                    ajout.setVisibility(View.GONE);
                    conteneur.setVisibility(View.GONE);
                }
            }

        }
        }

    private boolean valider() {
        boolean valide = true;
        if (Testdonne.isEmpty()) {
            donne.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }

        return valide;
    }

    public void retoure_fiche(View view) {
        if (pos != -1 ) {
            String T1 = donne.getText().toString();


            Allergis_ItemData Data = new Allergis_ItemData(T1);
            list.set(pos, Data);

//            long x = ds.UpdateAnti(T2, T1, pos + 1);
            myAdapter.notifyDataSetChanged();
            donne.setText("");

            pos = -1;
            ajout.setVisibility(View.VISIBLE);

            if (list.size() == 3) {
                ajout.setVisibility(View.GONE);
                conteneur.setVisibility(View.GONE);
            }

        }
    }
}
