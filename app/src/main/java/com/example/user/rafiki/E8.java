package com.example.user.rafiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class E8 extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);


    }

    public void E10(View view) {
        intent = new Intent(this, E10.class);
        startActivity(intent);
    }


    public void E12(View view) {
       intent = new Intent(this, E12.class);
        startActivity(intent);
    }

    public void E11(View view) {
        intent = new Intent(this, E11.class);
        startActivity(intent);
    }

    public void E9(View view) {
        intent = new Intent(this, E9.class);
        startActivity(intent);
    }

    public void exite(View view) {
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
