package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SexeActivity extends AppCompatActivity {

    Intent ite;
    SharedPreferences.Editor editor;
    TextView txt_Homme,txt_Famme;
    RadioButton homme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sexe);
        txt_Homme=findViewById(R.id.txt_homme);
        txt_Famme=findViewById(R.id.txt_famme);

        homme=findViewById(R.id.homme);

        homme.setChecked(true);

           String genre=txt_Homme.getText().toString();
           editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
           editor.putString("sexe",genre);
           editor.apply();

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.famme:
                if (checked){
                    String genre=txt_Famme.getText().toString();
                    editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
                    editor.putString("sexe",genre);
                    editor.apply();
                }
                break;
        }

    }
    public void valide_sexe(View view) {
        ite = new Intent(this, Inscription.class);
        startActivity(ite);
        this.finish();
    }

}
