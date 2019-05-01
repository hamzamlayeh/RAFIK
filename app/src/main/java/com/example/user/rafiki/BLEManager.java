package com.example.user.rafiki;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.UUID;

public class BLEManager {

    private static BluetoothAdapter mBluetoothAdapter;
    private static BluetoothDevice mBluetoothDevice;
    private static BluetoothGatt bluetoothGatt;
    private static final String GADGET_NAME = "BLE_SPP SERVER";
    private static final long SCAN_PERIOD = 10;
    private static final long INTERVAL_MS = 30;
    private static int scanCycle = 0;
    private static boolean isDeviceFound = false;
    private static boolean isDeviceConnected = false;
    private static BluetoothGattService mSPPBluetoothService;

    /* Texas Instruments */
    private static final String UIID_SERVICE = "F000C0E0-0451-4000-B000-000000000000";
    private static final String UIID_DATA_CHARACTERISTIC = "F000C0E1-0451-4000-B000-000000000000";

    private static BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            if (!isDeviceFound) {
                String deviceName = device.getName();
                if (deviceName != null) {
                    if (deviceName.equals(GADGET_NAME)) {
                        isDeviceFound = true;
                        mBluetoothDevice = device;
                        scanCycle = 0;
                        try {
                            mBluetoothAdapter.stopLeScan(leScanCallback);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                if (scanCycle == SCAN_PERIOD) {
                    isDeviceFound = false;
                    scanCycle = 0;
                    try {
                        mBluetoothAdapter.stopLeScan(leScanCallback);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    scanCycle++;
                }
            }
        }
    };

    private static final BluetoothGattCallback btleGattCallback = new BluetoothGattCallback() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic) {
            // this will get called anytime you perform a read or write characteristic operation
            try {
                byte[] data = {};
                data = characteristic.getValue();
                E7_2.str = data;


            } catch (Exception e) {
                e.printStackTrace();

            }

        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onConnectionStateChange(final BluetoothGatt gatt, final int status, final int newState) {
            // this will get called when a device connects or disconnects
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                isDeviceConnected = true;
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                bluetoothGatt.close();
                isDeviceConnected = false;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onServicesDiscovered(final BluetoothGatt gatt, final int status) {
            // this will get called after the client initiates a 			BluetoothGatt.discoverServices() call
            // List<BluetoothGattService> gattServices = gatt.getServices();
            if (status != BluetoothGatt.GATT_SUCCESS) {
                // Handle the error
                return;
            }
            mSPPBluetoothService = bluetoothGatt.getService(UUID.fromString(UIID_SERVICE));
            BluetoothGattCharacteristic mReadCharacteristic = mSPPBluetoothService.getCharacteristic(UUID.fromString(UIID_DATA_CHARACTERISTIC));
            // Enable notifications for this characteristic locally
            gatt.setCharacteristicNotification(mReadCharacteristic, true);
            BluetoothGattDescriptor desc = mReadCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
            desc.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            gatt.writeDescriptor(desc);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean isBLEEnabled(Context ctx) {
        boolean bResult = true;
        try {
            final BluetoothManager bluetoothManager = (BluetoothManager) ctx.getSystemService(Context.BLUETOOTH_SERVICE);
            mBluetoothAdapter = bluetoothManager.getAdapter();
            if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                // mBluetoothAdapter.enable();
                bResult = false;
            }
        } catch (Exception ex) {
            bResult = false;
        }
        return bResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean scanBLEGadget() {
        boolean bResult = true;
        try {
            mBluetoothAdapter.startLeScan(leScanCallback);

        } catch (Exception ex) {
            mBluetoothAdapter.stopLeScan(leScanCallback);
            bResult = false;
        }
        return bResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean connect(Context ctx) {
        boolean bResult = true;
        try {
            bluetoothGatt = mBluetoothDevice.connectGatt(ctx, false, btleGattCallback);
        } catch (Exception ex) {
            bResult = false;
        }
        return bResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean discoverDeviceServices() {
        boolean bResult = true;
        try {
            bluetoothGatt.discoverServices();
        } catch (Exception ex) {
            bResult = false;
        }
        return bResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean writeData(byte[] buffer) {
        boolean bResult = true;
        try {
            if (mSPPBluetoothService == null) {
                bResult = false;
            } else {
                BluetoothGattCharacteristic mWriteCharacteristic = mSPPBluetoothService.getCharacteristic(UUID.fromString(UIID_DATA_CHARACTERISTIC));
                mWriteCharacteristic.setValue(buffer);
                mWriteCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
                if (!bluetoothGatt.writeCharacteristic(mWriteCharacteristic)) {
                    bResult = false;
                } else {
                    Thread.sleep(INTERVAL_MS);
                }
            }
        } catch (Exception ex) {
            bResult = false;
        }
        return bResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean readData() {
        boolean bResult = true;
        if (mSPPBluetoothService == null) {
            bResult = false;
        } else {
            BluetoothGattCharacteristic mReadCharacteristic = mSPPBluetoothService.getCharacteristic(UUID.fromString(UIID_DATA_CHARACTERISTIC));
            byte[] buffer = mReadCharacteristic.getValue();
            Log.d("BLE", new String(buffer));
        }
        return bResult;
    }


    public static boolean isDeviceConnected() {
        return isDeviceConnected;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean disconnect() {
        boolean bResult = true;
        try {
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
            bluetoothGatt = null;
            isDeviceConnected = false;
        } catch (Exception ex) {
            bResult = false;
        }
        return bResult;
    }

    public static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }

    public static String decToHex(int high, int low) {
        return Integer.toHexString(high) + Integer.toHexString(low);
    }

    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static int TestValeurTrame(int high, int low) {

        if (Integer.toHexString(low).equals("ff")) {
            //return dec;
//            Log.d("tt",((low -high) +1 )* (-1)+"");
            return ((low -high) +1 )* (-1);
        } else {
            return high;
        }
    }

}