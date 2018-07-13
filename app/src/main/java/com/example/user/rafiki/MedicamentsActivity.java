package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.rafiki.Adapter.Medicament_Adapter;
import com.example.user.rafiki.ItemData.Medicament_Item;

import java.util.ArrayList;

public class MedicamentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Medicament_Adapter myAdapter;
    ArrayList<Medicament_Item> list = new ArrayList<Medicament_Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicaments);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        list.add(new Medicament_Item("Panadol","2","0","1","13-5-2018"));
        list.add(new Medicament_Item("Doliprane","2","2","2","36"));

        myAdapter = new Medicament_Adapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

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
    public void retoure_fiche(View view) {
        Intent ite=new Intent(this,Fiche_MedicaleActivity.class);
        startActivity(ite);
    }
    public void medica_resin(View view) {
        Intent ite=new Intent(this,MedicamentsRenseignerActivity.class);
        startActivity(ite);
    }
}
