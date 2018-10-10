package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class E7_2 extends AppCompatActivity {

    static byte[] str={0,0,0,0,0,0,0,0,0,0};
    ImageView testcouple;
    TextView textcouple;
    boolean test;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e7_2);
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);

        testcouple = (ImageView) findViewById(R.id.test_couple);
        textcouple = (TextView)findViewById(R.id.bienc);

        Intent ite = getIntent();
        Bundle b = ite.getExtras();

        if (b != null) {
            test = (boolean) b.get("connexion");
            if (test) {
                textcouple.setText(R.string.votre_t_shirt_est_bien_couple);
            } else {
                testcouple.setImageResource(R.drawable.couplageerr);
                textcouple.setText(R.string.votre_t_shirt_pas_couple);
            }
        }

        //Toast.makeText(getApplicationContext(), test + "", Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                //byte[] buffer = {0x01,0x02,0x03,0x4,0x05,0x06,0x07};
                byte[] buffer = {0x02, 0x73, 0x00, 0x74, 0x03, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000);
        //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

    public void nexte(View view) {
        editor = prefs.edit();
        Intent ite=new Intent(E7_2.this,E8.class);
        editor.putBoolean("connexion", test);
        editor.apply();
        startActivity(ite);
    }
}
