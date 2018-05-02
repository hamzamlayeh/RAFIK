package com.example.user.rafiki;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class E8 extends AppCompatActivity {

    Intent intent;
    Animation animation,animation2;
    ImageView coeur,poumon, batteri;
    TextView bpm,rpm,temps,oxy,niveaubatt;
    int min=0,max=0,moy=0;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e8);
         activity=this;
         Mythred thread=new Mythred();
         thread.start();
        StopThread=true;

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
//        coeur= findViewById(R.id.Coeur);
//        batteri= findViewById(R.id.batterie);
//        bpm = findViewById(R.id.BPM_D);
//        poumon= findViewById(R.id.poumon);
//        rpm = findViewById(R.id.RPM_D);
//        temps = findViewById(R.id.TEMP_D);
//        oxy = findViewById(R.id.oxigen);
//        niveaubatt = findViewById(R.id.NiveauBatt);

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

//                bpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
//                Setvaluesbatement(BLEManager.unsignedToBytes(E7_2.str[2]));
//                rpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
//                temps.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));
//                oxy.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[5])+"%"));
//                niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[6])+"%"));
//                if (E7_2.str[6]==0){
//                    batteri.setImageResource(R.drawable.batt7);
//                }else if (E7_2.str[6]>=1 && E7_2.str[6]<=13){
//                    batteri.setImageResource(R.drawable.batt6);
//
//                }else if (E7_2.str[6]>13 && E7_2.str[6]<=25){
//                    batteri.setImageResource(R.drawable.batt5);
//
//                }else if (E7_2.str[6]>25 && E7_2.str[6]<=38){
//                    batteri.setImageResource(R.drawable.batt4);
//
//                }else if (E7_2.str[6]>38 && E7_2.str[6]<=50){
//                    batteri.setImageResource(R.drawable.batt3);
//
//                }else if (E7_2.str[6]>50 && E7_2.str[6]<=75){
//                    batteri.setImageResource(R.drawable.batt2);
//
//                }else if (E7_2.str[6]>76 && E7_2.str[6]<=100){
//                    batteri.setImageResource(R.drawable.batt1);
//
//                }
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

    static boolean StopThread=true;
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
                    bpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
                    rpm.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
                    temps.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));
                    oxy.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[5])+"%"));
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

      TextView txt1=(TextView) findViewById(R.id.bpm_text1);
      TextView txt2=(TextView) findViewById(R.id.bpm_text2);
      TextView txt3=(TextView) findViewById(R.id.bpm_text3);

        if(max<=x){
            max=x;
            txt1.setText(String.valueOf(max));
            Toast.makeText(this,max+"maxx",Toast.LENGTH_LONG).show();
        }
        if((min<x && min==0)){
            min=x;

            txt3.setText(String.valueOf(min));
            Toast.makeText(this,min+"min",Toast.LENGTH_LONG).show();
        }else if (min<x){
            txt3.setText(String.valueOf(min));
        }else {
            min=x;
            txt3.setText(String.valueOf(min));
        }

        if(max!=0 && min!=0){
          moy=(max+min)/2;
          txt2.setText(String.valueOf(moy));
        }else {
            txt2.setText("00");
        }


//      if(max<=x){
//          max=x;
//          txt1.setText(String.valueOf(max));
//          Toast.makeText(this,max+"maxx",Toast.LENGTH_LONG).show();
//      }else if((min<x && min<max)){
//          min=x;
//          txt3.setText(String.valueOf(min));
//          Toast.makeText(this,min+"min",Toast.LENGTH_LONG).show();
//      }
//      if(max!=0 && min!=0){
//          moy=(max+min)/2;
//          txt2.setText(String.valueOf(moy));
//      }else{
//          txt2.setText("00");
//
//      }
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
        StopThread=false;
        intent = new Intent(this, E9.class);
        startActivity(intent);
    }

    public void exite(View view) {
        StopThread=false;
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//         StopThread=true;
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        StopThread=false;
//
//    }
}
