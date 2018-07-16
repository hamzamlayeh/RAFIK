package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AllergiesActivity extends AppCompatActivity {
    TextView insectes,medicaments,animaux,aliments,respiratoire,pollen,autre;
    String data;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        insectes=  findViewById(R.id.T1);
        medicaments=  findViewById(R.id.T2);
        animaux=  findViewById(R.id.T3);
        aliments=  findViewById(R.id.T4);
        respiratoire=  findViewById(R.id.T5);
        pollen=  findViewById(R.id.T6);
        autre=  findViewById(R.id.T7);
    }
    public void retoure_fiche(View view) {
        Intent ite=new Intent(this,Fiche_MedicaleActivity.class);
        startActivity(ite);
        AllergiesActivity.this.finish();
    }

    public void insectes(View view) {

        data=insectes.getText().toString();
        if (!ds.verifId_allergie(1)){
            ds.addallergie(1);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",1);
        startActivity(ite);
    }

    public void medicaments(View view) {
        data=medicaments.getText().toString();
        if (!ds.verifId_allergie(2)){
            ds.addallergie(2);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",2);
        startActivity(ite);
    }

    public void animaux(View view) {
        data=animaux.getText().toString();
        if (!ds.verifId_allergie(3)){
            ds.addallergie(3);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",3);
        startActivity(ite);
    }

    public void aliments(View view) {
        data=aliments.getText().toString();
        if (!ds.verifId_allergie(4)){
            ds.addallergie(4);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",4);
        startActivity(ite);
    }

    public void respiratoire(View view) {
        data=respiratoire.getText().toString();
        if (!ds.verifId_allergie(5)){
            ds.addallergie(5);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",5);
        startActivity(ite);
    }

    public void pollen(View view) {
        data=pollen.getText().toString();
        if (!ds.verifId_allergie(6)){
            ds.addallergie(6);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",6);
        startActivity(ite);
    }

    public void autre(View view) {
        data=autre.getText().toString();
        if (!ds.verifId_allergie(7)){
            ds.addallergie(7);
        }
        Intent ite=new Intent(this,AllergiesActivityContainer.class);
        ite.putExtra("allergies",data);
        ite.putExtra("Id",7);
        startActivity(ite);
    }
}
