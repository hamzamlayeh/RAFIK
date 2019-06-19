package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class E7_2 extends AppCompatActivity {

    static byte[] str = {};
    ImageView testcouple;
    TextView textcouple;
    Button buttonGo;
    boolean test;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e7_2);
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);

        testcouple = findViewById(R.id.test_couple);
        textcouple = findViewById(R.id.bienc);
        buttonGo = findViewById(R.id.button2);

        Intent ite = getIntent();
        Bundle b = ite.getExtras();

        if (b != null) {
            test = (boolean) b.get("connexion");
            if (test) {
                textcouple.setText(R.string.votre_t_shirt_est_bien_couple);
                buttonGo.setVisibility(View.VISIBLE);
            } else {
                testcouple.setImageResource(R.drawable.couplageerr);
                textcouple.setText(R.string.votre_t_shirt_pas_couple);
                buttonGo.setVisibility(View.VISIBLE);
            }
        }
    }

//    public void nexte(View view) {
//        editor = prefs.edit();
//        Intent ite = new Intent(E7_2.this, E8.class);
//        editor.putBoolean("connexion", test);
//        editor.apply();
//        startActivity(ite);
//    }

    public void Go(View view) {
        editor = prefs.edit();
        Intent ite = new Intent(E7_2.this, E8.class);
        editor.putBoolean("connexion", test);
        editor.apply();
        startActivity(ite);
    }
}
