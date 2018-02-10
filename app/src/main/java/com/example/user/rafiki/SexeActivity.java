package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SexeActivity extends AppCompatActivity {

    Intent ite;
    RadioGroup sexe;
    RadioButton rd_sexe;
    int id_sex;
    Inscription inscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sexe);



    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.homme:
                if (checked){

                    SharedPreferences.Editor editor = getSharedPreferences("sexe", MODE_PRIVATE).edit();
                    editor.putString("sexe","Masculin");
                    editor.apply();

                }
                  break;
            case R.id.famme:
                if (checked){
                    SharedPreferences.Editor editor = getSharedPreferences("sexe", MODE_PRIVATE).edit();
                    editor.putString("sexe","Fiminin");
                    editor.apply();



                }
                    break;
        }

    }
    public void valide_sexe(View view) {

        ite = new Intent(this, Inscription.class);
        startActivity(ite);
    }

}
