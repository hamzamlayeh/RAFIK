package com.example.user.rafiki;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class E7_1 extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    boolean testconnexion;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e7_1);
        if (!BLEManager.isBLEEnabled(getApplicationContext())) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            BLEManager.scanBLEGadget();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (BLEManager.connect(getApplicationContext())) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (BLEManager.isDeviceConnected()) {
                                    BLEManager.discoverDeviceServices();
                                    testconnexion = true;
                                    Intent ite = new Intent(E7_1.this, E7_2.class);
                                    ite.putExtra("connexion", testconnexion);
                                    startActivity(ite);
                                }
                            }
                        }, 2000);
                    } else {// false
                        testconnexion = false;
                        Intent ite = new Intent(E7_1.this, E7_2.class);
                        ite.putExtra("connexion", testconnexion);
                        startActivity(ite);
                    }
                }
            }, 2000);
        }
    }
}
