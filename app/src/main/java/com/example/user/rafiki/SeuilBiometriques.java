package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.SeuilValues;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

public class SeuilBiometriques extends AppCompatActivity {

    MySQLiteOpenHelper helper;
    UserDataSource ds;
    EditText FCmarche_M, FCmarche_X, FCcourse_M, FCcourse_X, FCactivite_M, FCactivite_X, FCsommeil_M, FCsommeil_X;
    EditText FRmarche_M, FRmarche_X, FRcourse_M, FRcourse_X, FRactivite_M, FRactivite_X, FRsommeil_M, FRsommeil_X;
    EditText Tmarche_M, Tmarche_X, Tcourse_M, Tcourse_X, Tactivite_M, Tactivite_X, Tsommeil_M, Tsommeil_X;
    String Cmarche_M, Cmarche_X, Ccourse_M, Ccourse_X, Cactivite_M, Cactivite_X, Csommeil_M, Csommeil_X;
    String Rmarche_M, Rmarche_X, Rcourse_M, Rcourse_X, Ractivite_M, Ractivite_X, Rsommeil_M, Rsommeil_X;
    String Tmarch_M, Tmarch_X, Tcours_M, Tcours_X, Tactivit_M, Tactivit_X, Tsomeil_M, Tsomeil_X;
    List<SeuilValues> list = new ArrayList<SeuilValues>();
    SeuilValues Seuils;
    MKLoader mkLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seuil_biometriques);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        FCmarche_M = findViewById(R.id.FCmarche_min);
        FCmarche_X = findViewById(R.id.FCmarche_max);
        FCcourse_M = findViewById(R.id.FCcourse_min);
        FCcourse_X = findViewById(R.id.FCcourse_max);
        FCactivite_M = findViewById(R.id.FCactivit_min);
        FCactivite_X = findViewById(R.id.FCactivit_max);
        FCsommeil_M = findViewById(R.id.FCsommeil_min);
        FCsommeil_X = findViewById(R.id.FCsommeil_max);

        FRmarche_M = findViewById(R.id.FRmarche_min);
        FRmarche_X = findViewById(R.id.FRmarche_max);
        FRcourse_M = findViewById(R.id.FRcourse_min);
        FRcourse_X = findViewById(R.id.FRcourse_max);
        FRactivite_M = findViewById(R.id.FRactivit_min);
        FRactivite_X = findViewById(R.id.FRactivit_max);
        FRsommeil_M = findViewById(R.id.FRsommeil_min);
        FRsommeil_X = findViewById(R.id.FRsommeil_max);

        Tmarche_M = findViewById(R.id.Tmarche_min);
        Tmarche_X = findViewById(R.id.Tmarche_max);
        Tcourse_M = findViewById(R.id.Tcourse_min);
        Tcourse_X = findViewById(R.id.Tcourse_max);
        Tactivite_M = findViewById(R.id.Tactivit_min);
        Tactivite_X = findViewById(R.id.Tactivit_max);
        Tsommeil_M = findViewById(R.id.Tsommeil_min);
        Tsommeil_X = findViewById(R.id.Tsommeil_max);
        mkLoader = findViewById(R.id.alerr);

        if (ds.getCountSeuils() > 0) {
            list = ds.getListSeuils();
            FCmarche_M.setText(list.get(0).getFCmarche_M());
            FCmarche_X.setText(list.get(0).getFCmarche_X());
            FCcourse_M.setText(list.get(0).getFCcourse_M());
            FCcourse_X.setText(list.get(0).getFCcourse_X());
            FCactivite_M.setText(list.get(0).getFCactivite_M());
            FCactivite_X.setText(list.get(0).getFCactivite_X());
            FCsommeil_M.setText(list.get(0).getFCsommeil_M());
            FCsommeil_X.setText(list.get(0).getFCsommeil_X());

            FRmarche_M.setText(list.get(0).getFRmarche_M());
            FRmarche_X.setText(list.get(0).getFRmarche_X());
            FRcourse_M.setText(list.get(0).getFRcourse_M());
            FRcourse_X.setText(list.get(0).getFRcourse_X());
            FRactivite_M.setText(list.get(0).getFRactivite_M());
            FRactivite_X.setText(list.get(0).getFRactivite_X());
            FRsommeil_M.setText(list.get(0).getFRsommeil_M());
            FRsommeil_X.setText(list.get(0).getFRsommeil_X());

            Tmarche_M.setText(list.get(0).getTmarche_M());
            Tmarche_X.setText(list.get(0).getTmarche_X());
            Tcourse_M.setText(list.get(0).getTcourse_M());
            Tcourse_X.setText(list.get(0).getTcourse_X());
            Tactivite_M.setText(list.get(0).getTactivite_M());
            Tactivite_X.setText(list.get(0).getTactivite_X());
            Tsommeil_M.setText(list.get(0).getTsommeil_M());
            Tsommeil_X.setText(list.get(0).getTsommeil_X());
        }
    }

    public void retoure_fiche(View view) {
        Cmarche_M = FCmarche_M.getText().toString().trim();
        Cmarche_X = FCmarche_X.getText().toString().trim();
        Ccourse_M = FCcourse_M.getText().toString().trim();
        Ccourse_X = FCcourse_X.getText().toString().trim();
        Cactivite_M = FCactivite_M.getText().toString().trim();
        Cactivite_X = FCactivite_X.getText().toString().trim();
        Csommeil_M = FCsommeil_M.getText().toString().trim();
        Csommeil_X = FCsommeil_X.getText().toString().trim();
        Rmarche_M = FRmarche_M.getText().toString().trim();
        Rmarche_X = FRmarche_X.getText().toString().trim();
        Rcourse_M = FRcourse_M.getText().toString().trim();
        Rcourse_X = FRcourse_X.getText().toString().trim();
        Ractivite_M = FRactivite_M.getText().toString().trim();
        Ractivite_X = FRactivite_X.getText().toString().trim();
        Rsommeil_M = FRsommeil_M.getText().toString().trim();
        Rsommeil_X = FRsommeil_X.getText().toString().trim();
        Tmarch_M = Tmarche_M.getText().toString().trim();
        Tmarch_X = Tmarche_X.getText().toString().trim();
        Tcours_M = Tcourse_M.getText().toString().trim();
        Tcours_X = Tcourse_X.getText().toString().trim();
        Tactivit_M = Tactivite_M.getText().toString().trim();
        Tactivit_X = Tactivite_X.getText().toString().trim();
        Tsomeil_M = Tsommeil_M.getText().toString().trim();
        Tsomeil_X = Tsommeil_X.getText().toString().trim();
        if (valider()) {

            Seuils = new SeuilValues(Cmarche_M, Cmarche_X, Ccourse_M, Ccourse_X, Cactivite_M, Cactivite_X, Csommeil_M,
                    Csommeil_X, Rmarche_M, Rmarche_X, Rcourse_M, Rcourse_X, Ractivite_M, Ractivite_X, Rsommeil_M, Rsommeil_X,
                    Tmarch_M, Tmarch_X, Tcours_M, Tcours_X, Tactivit_M, Tactivit_X, Tsomeil_M, Tsomeil_X);

            long ids = ds.UpdateSeuils(Seuils, 1);
            if (ids == -1) {
                Toast.makeText(this, R.string.EreurdanslLinsertion, Toast.LENGTH_LONG).show();
            } else {
                mkLoader.setVisibility(View.VISIBLE);
                Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
                startActivity(ite);
                SeuilBiometriques.this.finish();
            }
        }
    }

    private boolean valider() {

        if (Cmarche_M.isEmpty()) {
            Cmarche_M = "0";
        }
        if (Cmarche_X.isEmpty()) {
            Cmarche_X = "0";
        }
        if (Ccourse_M.isEmpty()) {
            Ccourse_M = "0";
        }
        if (Ccourse_X.isEmpty()) {
            Ccourse_X = "0";
        }
        if (Cactivite_M.isEmpty()) {
            Cactivite_M = "0";
        }
        if (Cactivite_X.isEmpty()) {
            Cactivite_X = "0";
        }
        if (Csommeil_M.isEmpty()) {
            Csommeil_M = "0";
        }
        if (Csommeil_X.isEmpty()) {
            Csommeil_X = "0";
        }

        if (Rmarche_M.isEmpty()) {
            Rmarche_M = "0";
        }
        if (Rmarche_X.isEmpty()) {
            Rmarche_X = "0";
        }
        if (Rcourse_M.isEmpty()) {
            Rcourse_M = "0";
        }
        if (Rcourse_X.isEmpty()) {
            Rcourse_X = "0";
        }
        if (Ractivite_M.isEmpty()) {
            Ractivite_M = "0";
        }
        if (Ractivite_X.isEmpty()) {
            Ractivite_X = "0";
        }
        if (Rsommeil_M.isEmpty()) {
            Rsommeil_M = "0";
        }
        if (Rsommeil_X.isEmpty()) {
            Rsommeil_X = "0";
        }

        if (Tmarch_M.isEmpty()) {
            Tmarch_M = "0";
        }
        if (Tmarch_X.isEmpty()) {
            Tmarch_X = "0";
        }
        if (Tcours_M.isEmpty()) {
            Tcours_M = "0";
        }
        if (Tcours_X.isEmpty()) {
            Tcours_X = "0";
        }
        if (Tactivit_M.isEmpty()) {
            Tactivit_M = "0";
        }
        if (Tactivit_X.isEmpty()) {
            Tactivit_X = "0";
        }
        if (Tsomeil_M.isEmpty()) {
            Tsomeil_M = "0";
        }
        if (Tsomeil_X.isEmpty()) {
            Tsomeil_X = "0";
        }
        return true;
    }
}
