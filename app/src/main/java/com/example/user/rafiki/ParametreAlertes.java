package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Alerts;

import java.util.ArrayList;
import java.util.List;

public class ParametreAlertes extends AppCompatActivity {

    EditText SMS2, SMS3, SMS4;
    String sms2, sms3, sms4;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    List<Alerts> list = new ArrayList<Alerts>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre_alertes);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        SMS2 = findViewById(R.id.sms_N2);
        SMS3 = findViewById(R.id.sms_N3);
        SMS4 = findViewById(R.id.sms_N4);

        if (ds.getCountAlerts() > 0) {
            list = ds.getListAlerts();
            SMS2.setText(list.get(0).getSMSNiveau2());
            SMS3.setText(list.get(0).getSMSNiveau3());
            SMS4.setText(list.get(0).getSMSNiveau4());
        }
    }

    public void registrer(View view) {
        sms2 = SMS2.getText().toString();
        sms3 = SMS3.getText().toString();
        sms4 = SMS4.getText().toString();

        if (valider()) {
            Alerts alerts=new Alerts(sms2,sms3,sms4);
            long ids = ds.UpdateAlert(alerts, 1);
            if (ids==-1){
                Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
            }else {
                startActivity(new Intent(this,MenuActivity.class));
            }
        }
    }

    private boolean valider() {

        boolean valide = true;
        if (sms2.isEmpty() ) {
            SMS2.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (sms3.isEmpty() ) {
            SMS3.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (sms4.isEmpty() ) {
            SMS4.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        return valide;
    }
//    public void vocal(View view) {
//        Intent ite=new Intent(this,EnregistrementVocal.class);
//        startActivity(ite);
//    }
}
