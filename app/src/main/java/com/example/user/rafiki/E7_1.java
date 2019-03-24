package com.example.user.rafiki;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

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
