package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Verif_code_mailActivity extends AppCompatActivity {

    Intent ite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_code_mail);
    }

    public void test(View view) {
        ite=new Intent(this,E8.class);
        startActivity(ite);
    }
}
