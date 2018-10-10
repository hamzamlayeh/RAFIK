package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.tuyenmonkey.mkloader.MKLoader;

public class ContactsActivity extends AppCompatActivity {

    MKLoader mkLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        mkLoader = findViewById(R.id.alerr);
    }
    public void contacts1(View view) {
        mkLoader.setVisibility(View.VISIBLE);
        Intent ite=new Intent(this,ContactParentaux.class);
        startActivity(ite);
        ContactsActivity.this.finish();
    }

    public void contacts2(View view) {
        mkLoader.setVisibility(View.VISIBLE);
        Intent ite=new Intent(this,ContactMedcin.class);
        startActivity(ite);
        ContactsActivity.this.finish();
    }

    public void contacts3(View view) {
        mkLoader.setVisibility(View.VISIBLE);
        Intent ite=new Intent(this,ContactUrgence.class);
        startActivity(ite);
        ContactsActivity.this.finish();
    }
    public void Precedents(View view) {
        startActivity(new Intent(this,MenuActivity.class));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ite = new Intent(this, MenuActivity.class);
            startActivity(ite);
        }
        return false;
    }
}
