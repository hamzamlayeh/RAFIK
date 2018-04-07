package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStreamReader;

public class E7_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e7_2);
    }

    public void nexte(View view) {
       Intent ite = new Intent(this, E8.class);
        startActivity(ite);
    }
}
