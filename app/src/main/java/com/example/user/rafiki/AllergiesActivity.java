package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AllergiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
    }
    public void retoure_fiche(View view) {
        Intent ite=new Intent(this,Fiche_MedicaleActivity.class);
        startActivity(ite);
    }
}
