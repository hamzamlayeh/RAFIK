package com.example.user.rafiki;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class E7 extends AppCompatActivity {
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e7);

      bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
   }
    public void next(View view) {
//        if (!bluetoothAdapter.isEnabled()) {
//            Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBlueTooth, REQUEST_CODE_ENABLE_BLUETOOTH);
//        }else{
//        }
        Intent ite=new Intent(this,E7_1.class);
        startActivity(ite);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE_ENABLE_BLUETOOTH)
            return;
        if (resultCode == RESULT_OK) {
            Intent ite=new Intent(this,E7_1.class);
            startActivity(ite);
        } else {
            E7.this.finish();
        }
    }

}
