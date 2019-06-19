package com.example.user.rafiki;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Alerts;
import com.example.user.rafiki.ItemData.Contacts_Medecins;
import com.example.user.rafiki.ItemData.Contacts_Parentaux;
import com.example.user.rafiki.ItemData.SeuilValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E8 extends AppCompatActivity {

    final static int MY_PERMISSIONS_REQUEST = 1;
    Intent intent;
    MySQLiteOpenHelper helper;
    UserDataSource ds;
    Animation animation, animation2;
    ImageView coeur, poumon, Resaux;
    int FC = 0, FR = 0, T = 0, W = 0, N = 0, TempAlert = 0, CompteurMIN_MAX = 0;
    Activity activity;
    List<SeuilValues> list = new ArrayList<SeuilValues>();
    List<Alerts> listAlert = new ArrayList<Alerts>();
    List<Contacts_Parentaux> listFamilia = new ArrayList<Contacts_Parentaux>();
    List<Contacts_Medecins> listMedcin = new ArrayList<Contacts_Medecins>();
    List<Integer> ListeBPM = new ArrayList<Integer>();
    List<Integer> ListeRPM = new ArrayList<Integer>();
    List<Integer> ListeTemp = new ArrayList<Integer>();
    static boolean StopThread = true;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String niveauxBat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        checkSmsPermission();
        activity = this;
        StopThread = true;
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x00, 0x74, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
        Mythred thread = new Mythred();
        thread.start();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        coeur = findViewById(R.id.Coeur);
        poumon = findViewById(R.id.poumon);
        Resaux = findViewById(R.id.icone_resaux);
        coeur.startAnimation(animation2);
        poumon.startAnimation(animation2);

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                coeur.startAnimation(animation);
                poumon.startAnimation(animation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coeur.startAnimation(animation2);
                poumon.startAnimation(animation2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST);
            }
        } else {
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) ==
                            PackageManager.PERMISSION_GRANTED) {

                    }
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST);
                }
                return;
            }
        }
    }

    public void parammetres(View view) {
        TrameSop();
        E7_2.str = null;
        StopThread = false;
        Intent ite = new Intent(this, MenuActivity.class);
        startActivity(ite);
    }

    public void Cycles(View view) {
        StopThread = false;
        TrameSop();
        Intent ite = new Intent(this, CycleActivity.class);
        startActivity(ite);
        E7_2.str = null;

    }

    public void historique(View view) {
        StopThread = false;
        TrameSop();
        E7_2.str = null;
        Intent ite = new Intent(this, HistoriqueActivity.class);
        startActivity(ite);
    }

    class Mythred extends Thread {
        public void run() {
            final TextView bpm = findViewById(R.id.BPM_D);
            final TextView rpm = findViewById(R.id.RPM_D);
            final TextView temps = findViewById(R.id.TEMP_D);
            final TextView oxy = findViewById(R.id.oxigen);
            final TextView niveaubatt = findViewById(R.id.NiveauBatt);
            final ImageView batteri = findViewById(R.id.batterie);

            final TextView txt9 = (TextView) findViewById(R.id.text_temp1);
            final TextView txt8 = (TextView) findViewById(R.id.text_temp2);
            final TextView txt7 = (TextView) findViewById(R.id.text_temp3);
            final TextView txt6 = (TextView) findViewById(R.id.rpm_text1);
            final TextView txt5 = (TextView) findViewById(R.id.rpm_text2);
            final TextView txt4 = (TextView) findViewById(R.id.rpm_text3);
            final TextView txt3 = (TextView) findViewById(R.id.bpm_text1);
            final TextView txt2 = (TextView) findViewById(R.id.bpm_text2);
            final TextView txt1 = (TextView) findViewById(R.id.bpm_text3);

            while (StopThread) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[6]) + "%"));
                            editor.putInt("NiveauBat", BLEManager.unsignedToBytes(E7_2.str[6]));
                            editor.putInt("Electrode", E7_2.str[8]);
                            editor.apply();
                            if (E7_2.str[6] == 0) {
                                batteri.setImageResource(R.drawable.batt7);
                            } else if (E7_2.str[6] >= 1 && E7_2.str[6] <= 13) {
                                batteri.setImageResource(R.drawable.batt6);

                            } else if (E7_2.str[6] > 13 && E7_2.str[6] <= 25) {
                                batteri.setImageResource(R.drawable.batt5);

                            } else if (E7_2.str[6] > 25 && E7_2.str[6] <= 38) {
                                batteri.setImageResource(R.drawable.batt4);

                            } else if (E7_2.str[6] > 38 && E7_2.str[6] <= 50) {
                                batteri.setImageResource(R.drawable.batt3);

                            } else if (E7_2.str[6] > 50 && E7_2.str[6] <= 75) {
                                batteri.setImageResource(R.drawable.batt2);

                            } else if (E7_2.str[6] > 76 && E7_2.str[6] <= 100) {
                                batteri.setImageResource(R.drawable.batt1);
                            }
                            if (E7_2.str[8] == 0) {
//                                Alerts(E7_2.str[7]);
                                bpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
                                rpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
                                temps.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));
                                oxy.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[5]) + "%"));
                                Resaux.setImageResource(R.drawable.resaux);

                                if (CompteurMIN_MAX >= 60) {
                                    CompteurMIN_MAX = 0;
                                    ListeBPM.clear();
                                    ListeRPM.clear();
                                    ListeTemp.clear();
                                } else {
                                    Setvaluesbatement(BLEManager.unsignedToBytes(E7_2.str[2]));
                                    Setvaluespoumon(BLEManager.unsignedToBytes(E7_2.str[3]));
                                    Setvaluestempirature(BLEManager.unsignedToBytes(E7_2.str[4]));
                                    CompteurMIN_MAX++;
//                                    Log.d("compteur",CompteurMIN_MAX+"");
                                }

                                Log.d("trameInst", BLEManager.unsignedToBytes(E7_2.str[2]) + "/" + BLEManager.unsignedToBytes(E7_2.str[3]) + "/" + BLEManager.unsignedToBytes(E7_2.str[4]) + "/" + BLEManager.unsignedToBytes(E7_2.str[5]) + "/" + BLEManager.unsignedToBytes(E7_2.str[6]) + "/" + BLEManager.unsignedToBytes(E7_2.str[7]));

                                if (E7_2.str[7] == 1) {
                                    String num = "53302222";
                                    String msg = "ATTENTION : Choc détécté!";
                                    SmsManager sms = SmsManager.getDefault();
                                    sms.sendTextMessage(num, null, msg, null, null);
                                    E7_2.str[7] = 0;
                                }
//                                Test_Les_Seuil(BLEManager.unsignedToBytes(E7_2.str[2]), BLEManager.unsignedToBytes(E7_2.str[3]),
//                                        BLEManager.unsignedToBytes(E7_2.str[4]));
//                                if (TempAlert >= 0 && TempAlert <= 300000) {
//                                    listAlert = ds.getListAlerts();
//                                    if (((FC == 1 || FR == 1 || T == 1) && W == 0) && N <= 2) {
////           Toast.makeText(activity, "alert 1", Toast.LENGTH_SHORT).show();
//                                        Notification(activity);
//                                    }
//                                    if (ds.getCountParentaux() > 0) {
//
//                                        listFamilia = ds.getListParentaux();
//                                        if (((FC == 1 || FR == 1 || T == 1) && W == 0) && N > 2) {
////                Toast.makeText(activity, "alert 2", Toast.LENGTH_SHORT).show();
//                                            Notification(activity);
//                                            SmsManager sms = SmsManager.getDefault();
//                                            sms.sendTextMessage(listFamilia.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//                                            if (listFamilia.size() == 2)
//                                                sms.sendTextMessage(listFamilia.get(1).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//                                            if (listFamilia.size() == 3)
//                                                sms.sendTextMessage(listFamilia.get(2).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//                                        }
//                                        if (W == 1 && N <= 2) {
////              Toast.makeText(activity, "aler 2", Toast.LENGTH_SHORT).show();
//                                            Notification(activity);
//                                            SmsManager sms = SmsManager.getDefault();
//                                            sms.sendTextMessage(listFamilia.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//                                            if (listFamilia.size() == 2)
//                                                sms.sendTextMessage(listFamilia.get(1).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//                                            if (listFamilia.size() == 3)
//                                                sms.sendTextMessage(listFamilia.get(2).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//                                        }
//                                    }
//                                    if (ds.getCountParentaux() > 0 && ds.getCountMedecins() > 0) {
//                                        listMedcin = ds.getListMedecins();
//                                        if (W == 1 && N > 2) {
////                Toast.makeText(activity, "aler 3", Toast.LENGTH_SHORT).show();
//                                            Notification(activity);
//                                            SmsManager sms = SmsManager.getDefault();
//                                            sms.sendTextMessage(listMedcin.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau3(), null, null);
//                                        }
//                                        if (W == 1 && N > 2 && E7_2.str[7] == 1) {
////                Toast.makeText(activity, "aler 4", Toast.LENGTH_SHORT).show();
//                                            SmsManager sms = SmsManager.getDefault();
//                                            sms.sendTextMessage(listMedcin.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau4(), null, null);
//                                        }
//                                    }
//                                    TempAlert++;
//                                } else {
//                                    N = 0;
//                                    TempAlert = 0;
////                                    Log.d("ttttttt", TempAlert + "");
//                                }

                            } else {
                                bpm.setText("--");
                                rpm.setText("--");
                                temps.setText("--");
                                oxy.setText("--");
                                txt1.setText("--");
                                txt2.setText("--");
                                txt3.setText("--");
                                txt4.setText("--");
                                txt5.setText("--");
                                txt6.setText("--");
                                txt7.setText("--");
                                txt8.setText("--");
                                txt9.setText("--");
                                Resaux.setImageResource(R.drawable.resaux2);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Setvaluesbatement(int x) {

        TextView txt3 = findViewById(R.id.bpm_text1);
        TextView txt2 = findViewById(R.id.bpm_text2);
        TextView txt1 = findViewById(R.id.bpm_text3);
        ListeBPM.add(x);
        txt1.setText(String.valueOf(Collections.max(ListeBPM)));
        txt2.setText(String.valueOf(Math.round(Moyenne_Val(ListeBPM))));
        txt3.setText(String.valueOf(Collections.min(ListeBPM)));
//        Log.d("size", ListeBPM.size() + "//");

    }

    public void Setvaluespoumon(int x) {

        TextView txt3 = (TextView) findViewById(R.id.rpm_text1);
        TextView txt2 = (TextView) findViewById(R.id.rpm_text2);
        TextView txt1 = (TextView) findViewById(R.id.rpm_text3);
        ListeRPM.add(x);
        txt1.setText(String.valueOf(Collections.max(ListeRPM)));
        txt2.setText(String.valueOf(Math.round(Moyenne_Val(ListeRPM))));
        txt3.setText(String.valueOf(Collections.min(ListeRPM)));

    }

    public void Setvaluestempirature(int x) {

        TextView txt3 = (TextView) findViewById(R.id.text_temp1);
        TextView txt2 = (TextView) findViewById(R.id.text_temp2);
        TextView txt1 = (TextView) findViewById(R.id.text_temp3);
        ListeTemp.add(x);
        txt1.setText(String.valueOf(Collections.max(ListeTemp)));
        txt2.setText(String.valueOf(Math.round(Moyenne_Val(ListeTemp))));
        txt3.setText(String.valueOf(Collections.min(ListeTemp)));

    }

    public float Moyenne_Val(List<Integer> list) {
        int total = 0;
        for (int val : list) {
            total += val;
        }
        return total / list.size();
    }

    public void Test_Les_Seuil(int FCinst, int FRinst, int Tinst) {

        list = ds.getListSeuils();
        //Fréquence Cardiaque FC
        if (Collections.max(ListeBPM) >= Integer.parseInt(list.get(0).getFCactivite_X())
                || FCinst >= Integer.parseInt(list.get(0).getFCactivite_X())) {
            FC = 1;
            N += 1;
            System.out.print(Collections.max(ListeBPM));
        } else {
            FC = 0;
        }
        if (Collections.min(ListeBPM) <= Integer.parseInt(list.get(0).getFCactivite_M())
                || FCinst <= Integer.parseInt(list.get(0).getFCactivite_M())) {
            FC = 1;
            N += 1;
        } else {
            FC = 0;
        }
        //Fréquence Respiratoire FR
        if (Collections.max(ListeRPM) >= Integer.parseInt(list.get(0).getFRactivite_X())
                || FRinst >= Integer.parseInt(list.get(0).getFRactivite_X())) {
            FR = 1;
            N += 1;
        } else {
            FR = 0;
        }
        if (Collections.min(ListeRPM) <= Integer.parseInt(list.get(0).getFRactivite_M())
                || FRinst <= Integer.parseInt(list.get(0).getFRactivite_M())) {
            FR = 1;
            N += 1;
        } else {
            FR = 0;
        }
        //Température T°:
        if (Collections.max(ListeTemp) >= Integer.parseInt(list.get(0).getTactivite_X())
                || Tinst >= Integer.parseInt(list.get(0).getTactivite_X())) {
            T = 1;
            N += 1;
        } else {
            T = 0;
        }
        if (Collections.min(ListeTemp) <= Integer.parseInt(list.get(0).getTactivite_M())
                || Tinst <= Integer.parseInt(list.get(0).getTactivite_M())) {
            T = 1;
            N += 1;
        } else {
            T = 0;
        }
        W = FC * FR * T;
        //Toast.makeText(activity, W + "alert 2" + FC + "//" + FR + "//" + T + "//" + N, Toast.LENGTH_SHORT).show();

    }

//    public void Alerts(int Chute) {
//        listAlert = ds.getListAlerts();
//        if (((FC == 1 || FR == 1 || T == 1) && W == 0) && N <= 2) {
////           Toast.makeText(activity, "alert 1", Toast.LENGTH_SHORT).show();
//            Notification(activity);
//        }
//        if (ds.getCountParentaux() > 0) {
//
//            listFamilia = ds.getListParentaux();
//            if (((FC == 1 || FR == 1 || T == 1) && W == 0) && N > 2) {
////                Toast.makeText(activity, "alert 2", Toast.LENGTH_SHORT).show();
//                Notification(activity);
//                SmsManager sms = SmsManager.getDefault();
//                sms.sendTextMessage(listFamilia.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//            }
//            if (W == 1 && N <= 2) {
////              Toast.makeText(activity, "aler 2", Toast.LENGTH_SHORT).show();
//                Notification(activity);
//                SmsManager sms = SmsManager.getDefault();
//                sms.sendTextMessage(listFamilia.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau2(), null, null);
//            }
//        }
//        if (ds.getCountParentaux() > 0 && ds.getCountMedecins() > 0) {
//            listMedcin = ds.getListMedecins();
//            if (W == 1 && N > 2) {
////                Toast.makeText(activity, "aler 3", Toast.LENGTH_SHORT).show();
//                Notification(activity);
//                SmsManager sms = SmsManager.getDefault();
//                sms.sendTextMessage(listMedcin.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau3(), null, null);
//            }
//            if (W == 1 && N > 2 && Chute == 1) {
////                Toast.makeText(activity, "aler 4", Toast.LENGTH_SHORT).show();
//                SmsManager sms = SmsManager.getDefault();
//                sms.sendTextMessage(listMedcin.get(0).getMobile(), null, listAlert.get(0).getSMSNiveau4(), null, null);
//            }
//        }
//        N = 0;
//    }

    public void Notification(Context context) {


        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";

        int NOTIFICATION_ID = 1;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(true);

            if (notificationManager != null) {

                notificationManager.createNotificationChannel(mChannel);
            }

        }
        Intent resultIntent = new Intent(context, E8.class);
        android.support.v4.app.TaskStackBuilder stackBuilder = android.support.v4.app.TaskStackBuilder.create(context);
        stackBuilder.addParentStack(E8.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        @SuppressLint("ResourceAsColor")
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(getString(R.string.title_notification))
                .setContentText(getString(R.string.text_notif))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.text_notif)))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setColor((android.R.color.holo_red_dark));


        if (notificationManager != null) {

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    public void E10(View view) {
        StopThread = false;
        E7_2.str = null;
//        TrameSop();
        intent = new Intent(this, E10.class);
        startActivity(intent);
    }

    public void E12(View view) {
        StopThread = false;
        E7_2.str = null;
//        TrameSop();
        intent = new Intent(this, E12.class);
        startActivity(intent);
    }

    public void E11(View view) {
        StopThread = false;
        E7_2.str = null;
//        TrameSop();
        intent = new Intent(this, E11.class);
        startActivity(intent);
    }

    public void E9(View view) {
//        StopThread=false;
//        intent = new Intent(this, E9.class);
//        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exite(View view) {
        StopThread = false;
        TrameSop();
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finishAffinity();

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onResume() {
        super.onResume();
        StopThread = true;
        Mythred thread = new Mythred();
        thread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        StopThread = false;

    }

    public void TrameSop() {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x01, 0x74, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

}
