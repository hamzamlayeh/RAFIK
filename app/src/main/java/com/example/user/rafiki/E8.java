package com.example.user.rafiki;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class E8 extends AppCompatActivity {

    final static int MY_PERMISSIONS_REQUEST = 1;
    Intent intent;
    Animation animation,animation2;
    ImageView coeur,poumon;
    int minBat=0,maxBat=0,moyBat=0,minPoum=0,maxPoum=0,moyPoum=0,minTemp=0,maxTemp=0,moyTemp=0;
    int minOxy=0,maxOxy=0,moyOxy=0;
    Activity activity;
    static boolean StopThread=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);
        checkSmsPermission();
         activity=this;
         Mythred thread=new Mythred();
         thread.start();
        StopThread=true;

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
         coeur= findViewById(R.id.Coeur);
         poumon= findViewById(R.id.poumon);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
        Intent ite=new Intent(this,MenuActivity.class);
        startActivity(ite);
    }

    class Mythred extends Thread {
            public void run(){
                final TextView bpm = findViewById(R.id.BPM_D);
                final TextView rpm = findViewById(R.id.RPM_D);
                final TextView temps = findViewById(R.id.TEMP_D);
                final TextView oxy = findViewById(R.id.oxigen);
                final TextView niveaubatt = findViewById(R.id.NiveauBatt);

                final ImageView batteri= findViewById(R.id.batterie);


                while (StopThread){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                bpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
                                Setvaluesbatement(BLEManager.unsignedToBytes(E7_2.str[2]));
                                rpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
                                Setvaluespoumon(BLEManager.unsignedToBytes(E7_2.str[3]));
                                temps.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));
                                Setvaluestempirature(BLEManager.unsignedToBytes(E7_2.str[4]));
                                oxy.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[5])+"%"));
                                Setvaluesoxygene(BLEManager.unsignedToBytes(E7_2.str[5]));
                                niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[6])+"%"));

                                if (E7_2.str[6]==0){
                                    batteri.setImageResource(R.drawable.batt7);
                                }else if (E7_2.str[6]>=1 && E7_2.str[6]<=13){
                                    batteri.setImageResource(R.drawable.batt6);

                                }else if (E7_2.str[6]>13 && E7_2.str[6]<=25){
                                    batteri.setImageResource(R.drawable.batt5);

                                }else if (E7_2.str[6]>25 && E7_2.str[6]<=38){
                                    batteri.setImageResource(R.drawable.batt4);

                                }else if (E7_2.str[6]>38 && E7_2.str[6]<=50){
                                    batteri.setImageResource(R.drawable.batt3);

                                }else if (E7_2.str[6]>50 && E7_2.str[6]<=75){
                                    batteri.setImageResource(R.drawable.batt2);

                                }else if (E7_2.str[6]>76 && E7_2.str[6]<=100){
                                    batteri.setImageResource(R.drawable.batt1);

                                }
                                if(E7_2.str[7]==1){
                                    String num = "52845265";
                                    String msg = "ATTENTION : Choc détécté!";
                                    SmsManager sms = SmsManager.getDefault();
                                    sms.sendTextMessage(num, null, msg, null, null);
                                    E7_2.str[7]=0;
                                }
                            }catch (Exception e){
                                ;
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
    public void Setvaluesbatement(int x){

      TextView txt3=(TextView) findViewById(R.id.bpm_text1);
      TextView txt2=(TextView) findViewById(R.id.bpm_text2);
      TextView txt1=(TextView) findViewById(R.id.bpm_text3);

        if(maxBat<=x){
            maxBat=x;
            txt1.setText(String.valueOf(maxBat));
        }
        if((minBat<x && minBat==0)){
            minBat=x;

            txt3.setText(String.valueOf(minBat));
        }else if (minBat<x){
            txt3.setText(String.valueOf(minBat));
        }else {
            minBat=x;
            txt3.setText(String.valueOf(minBat));
        }

        if(maxBat!=0 && minBat!=0){
            moyBat=(maxBat+minBat)/2;
          txt2.setText(String.valueOf(moyBat));
        }else {
            txt2.setText("00");
        }
        }
    public void Setvaluespoumon(int x){

        TextView txt3=(TextView) findViewById(R.id.rpm_text1);
        TextView txt2=(TextView) findViewById(R.id.rpm_text2);
        TextView txt1=(TextView) findViewById(R.id.rpm_text3);

        if(maxPoum<=x){
            maxPoum=x;
            txt1.setText(String.valueOf(maxPoum));
        }
        if((minPoum<x && minPoum==0)){
            minPoum=x;
            txt3.setText(String.valueOf(minPoum));
        }else if (minPoum<x){
            txt3.setText(String.valueOf(minPoum));
        }else {
            minPoum=x;
            txt3.setText(String.valueOf(minPoum));
        }

        if(maxPoum!=0 && minPoum!=0){
            moyPoum=(maxPoum+minPoum)/2;
            txt2.setText(String.valueOf(moyPoum));
        }else {
            txt2.setText("00");
        }
    }
    public void Setvaluestempirature(int x){

        TextView txt3=(TextView) findViewById(R.id.text_temp1);
        TextView txt2=(TextView) findViewById(R.id.text_temp2);
        TextView txt1=(TextView) findViewById(R.id.text_temp3);

        if(maxTemp<=x){
            maxTemp=x;
            txt1.setText(String.valueOf(maxTemp));
        }
        if((minTemp<x && minTemp==0)){
            minTemp=x;
            txt3.setText(String.valueOf(minTemp));
        }else if (minTemp<x){
            txt3.setText(String.valueOf(minTemp));
        }else {
            minTemp=x;
            txt3.setText(String.valueOf(minTemp));
        }

        if(maxTemp!=0 && minTemp!=0){
            moyTemp=(maxTemp+minTemp)/2;
            txt2.setText(String.valueOf(moyTemp));
        }else {
            txt2.setText("00");
        }
    }
    public void Setvaluesoxygene(int x){

        TextView txt3=(TextView) findViewById(R.id.text_oxigen1);
        TextView txt2=(TextView) findViewById(R.id.text_oxigen2);
        TextView txt1=(TextView) findViewById(R.id.text_oxigen3);

        if(maxOxy<=x){
            maxOxy=x;
            txt1.setText(String.valueOf(maxOxy)+"%");
        }
        if((minOxy<x && minOxy==0)){
            minOxy=x;
            txt3.setText(String.valueOf(minOxy)+"%");
        }else if (minOxy<x){
            txt3.setText(String.valueOf(minOxy)+"%");
        }else {
            minOxy=x;
            txt3.setText(String.valueOf(minOxy)+"%");
        }

        if(maxOxy!=0 && minOxy!=0){
            moyOxy=(maxOxy+minOxy)/2;
            txt2.setText(String.valueOf(moyOxy)+"%");
        }else {
            txt2.setText("00%");
        }
    }

    public void E10(View view) {
        StopThread=false;
        intent = new Intent(this, E10.class);
        startActivity(intent);
    }


    public void E12(View view) {
        StopThread=false;
        intent = new Intent(this, E12.class);
        startActivity(intent);
    }

    public void E11(View view) {
        StopThread=false;
        intent = new Intent(this, E11.class);
        startActivity(intent);
    }

    public void E9(View view) {
//        StopThread=false;
//        intent = new Intent(this, E9.class);
//        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exite(View view) {
        StopThread=false;
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
    public void onResume(){
        super.onResume();
         StopThread=true;
        Mythred thread=new Mythred();
        thread.start();
    }
    @Override
    public void onPause() {
        super.onPause();
        StopThread=false;

    }
}
