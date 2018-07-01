package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Groupe_SangActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView Ab_P, Ab_M, A_P, A_M, B_P, B_M, O_P, O_M;
    RadioButton rd_abP, rd_abM, rd_aP, rd_aM, rd_bP, rd_bM, rd_oP, rd_oM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupe_sang);
        pref = getApplicationContext().getSharedPreferences("Fiche_Medicale", MODE_PRIVATE);
        editor = pref.edit();
        //TextView
        Ab_P = findViewById(R.id.ab_plus);
        Ab_M = findViewById(R.id.ab_moin);
        A_P = findViewById(R.id.a_plus);
        A_M = findViewById(R.id.a_moin);
        B_P = findViewById(R.id.b_plus);
        B_M = findViewById(R.id.b_moin);
        O_P = findViewById(R.id.o_plus);
        O_M = findViewById(R.id.o_moin);

        //Button Radio
        rd_abP = findViewById(R.id.rd_abplus);
        rd_abM = findViewById(R.id.rd_abmoin);
        rd_aP = findViewById(R.id.rd_aplus);
        rd_aM = findViewById(R.id.rd_amoin);
        rd_bP = findViewById(R.id.rd_bplus);
        rd_bM = findViewById(R.id.rd_bmoin);
        rd_oP = findViewById(R.id.rd_oplus);
        rd_oM = findViewById(R.id.rd_omoin);

        rd_abP.setChecked(true);
        editor.putString("G_Sang", Ab_P.getText().toString());
        editor.apply();

    }

    public void getSang(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rd_abplus:
                if (checked) {
                    editor.putString("G_Sang", Ab_P.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_abmoin:
                if (checked) {
                    editor.putString("G_Sang", Ab_M.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_aplus:
                if (checked) {
                    editor.putString("G_Sang", A_P.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_amoin:
                if (checked) {
                    editor.putString("G_Sang", A_M.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_bplus:
                if (checked) {
                    editor.putString("G_Sang", B_P.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_bmoin:
                if (checked) {
                    editor.putString("G_Sang", B_M.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_oplus:
                if (checked) {
                    editor.putString("G_Sang", O_P.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_omoin:
                if (checked) {
                    editor.putString("G_Sang", O_M.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void retoure_fiche(View view) {
        Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
        startActivity(ite);
    }


}
