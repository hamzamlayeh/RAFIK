package com.example.user.rafiki;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AllergiesActivityContainer extends AppCompatActivity {

    EditText donne1,donne2,donne3;
    TextView NonAllergies;
      SharedPreferences.Editor editor;

    String Testdonne2,Testdonne1,Testdonne3;


    MySQLiteOpenHelper helper;
    UserDataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies_container);
        donne1 = findViewById(R.id.donne1);
        donne2 = findViewById(R.id.donne2);
        donne3 = findViewById(R.id.donne3);


        NonAllergies = findViewById(R.id.NonAllergies);

        String nomAllergies = getIntent().getExtras().getString("allergies");
        NonAllergies.setText(nomAllergies);



    }


    private boolean valider() {
        boolean valide = true;
        Testdonne1= donne1.getText().toString();
        Testdonne2= donne2.getText().toString();
        Testdonne3= donne3.getText().toString();
        if (Testdonne1.isEmpty()) {
            donne1.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }if (Testdonne2.isEmpty()) {
            donne2.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }if (Testdonne3.isEmpty()) {
            donne3.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }

        return valide;
    }

    public void retoure_fiche(View view) {
        valider();

}}
