package com.example.user.rafiki;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
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

public class E7_1 extends AppCompatActivity {

    private static final int  REQUEST_ENABLE_BT = 1;
    final static int MY_PERMISSIONS_REQUEST= 2;
    boolean testconnexion;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e7_1);
        checkFineLocationPermission();
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
                                    testconnexion=true;
                                    Intent ite=new Intent(E7_1.this,E7_2.class);
                                    ite.putExtra("connexion", testconnexion);
                                    startActivity(ite);
                                }
                            }
                        }, 2000);
                    }else {// false
                        testconnexion=false;
                        Intent ite=new Intent(E7_1.this,E7_2.class);
                        ite.putExtra("connexion", testconnexion);
                        startActivity(ite);
                    }
                }
            }, 2000);
        }
    }

    public void checkFineLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(E7_1.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(E7_1.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ;
                ActivityCompat.requestPermissions(E7_1.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
