package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.Adapter.Medicament_Adapter;
import com.example.user.rafiki.Helper.RecyclerViewClickListener;
import com.example.user.rafiki.Helper.RecyclerViewTouchListener;
import com.example.user.rafiki.ItemData.Medicament_Item;

import java.util.ArrayList;
import java.util.List;

public class MedicamentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Medicament_Adapter myAdapter;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    ArrayList<Medicament_Item> list = new ArrayList<Medicament_Item>();
    static int Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicaments);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        recyclerView = findViewById(R.id.recycle);

        list = (ArrayList<Medicament_Item>) ds.getMedicament();

        myAdapter = new Medicament_Adapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent ite = new Intent(MedicamentsActivity.this, MedicamentsRenseignerActivity.class);
                Id=list.get(position).get_id();
                ite.putExtra("Id",Id);
                startActivity(ite);
                MedicamentsActivity.this.finish();

            }

            @Override
            public void onLongClick(View view, int position) {
                try {
                    ds.deleteMedica(list.get(position).get_id());
                    list.remove(position);
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }));
    }


    public void alert(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" ").setIcon(R.drawable.alert)
                .setMessage(getString(R.string.modifier) + "       " + getString(R.string.cliquer_sur_medicaments) + "\n" +
                        getString(R.string.supprimer) + "   " + getString(R.string.appui_long_sur_medicaments)
                )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    public void retoure_fiche(View view) {
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
        MedicamentsActivity.this.finish();

    }

    public void medica_resin(View view) {
        Id=-1;
        Intent ite = new Intent(this, MedicamentsRenseignerActivity.class);
        startActivity(ite);
    }
}
