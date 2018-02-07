package com.example.user.rafiki;

import android.content.Intent;
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
         inscription=new Inscription();
    }

    public void valide_sexe(View view) {
        sexe = (RadioGroup) findViewById(R.id.sexes);
        id_sex = sexe.getCheckedRadioButtonId();
        rd_sexe = (RadioButton) findViewById(id_sex);

//        Bundle bd=new Bundle();
//        bd.putString("sexe",rd_sexe.getText().toString());
//        ite.putExtras(bd);
        String val=rd_sexe.getText().toString();
        inscription.email.setText(val);
        ite=new Intent(this,Inscription.class);
        startActivity(ite);

    }
}
