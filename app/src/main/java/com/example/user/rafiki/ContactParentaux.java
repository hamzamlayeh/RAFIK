package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactParentaux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_parentaux);
    }

    public void retoure(View view) {
        Intent ite=new Intent(this,ContactsActivity.class);
        startActivity(ite);
    }
}
