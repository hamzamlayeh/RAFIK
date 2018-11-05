package com.example.user.rafiki;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

public class ParametresMesures extends AppCompatActivity {

    static boolean StopThread = true;
    ImageView Img_etat, Resaux, batteri;
    TextView textHaute, textBas, niveaubatt, textECG, textPoumon, textTemp, textCal, textDist;
    Chronometer chronometer;
    ToggleButton btn_P_R;
    Button stop, declancher,Img_lock;
    ConstraintLayout constraintDistance;
    int Indice;
    long lastPause;
    SharedPreferences prefs, pref;
    Activity activity;
    File file;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_mesures);

        activity = this;

        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        Indice = prefs.getInt("Indice", 0);
        Img_etat = findViewById(R.id.imageView10);
        Img_lock = findViewById(R.id.bt_clock);
        Resaux = findViewById(R.id.imageView29);
        textHaute = findViewById(R.id.Signes);
        textBas = findViewById(R.id.BIOMETRIQUE);
        constraintDistance = findViewById(R.id.constraint_distance);
        chronometer = findViewById(R.id.simpleChronometer);
        btn_P_R = findViewById(R.id.button3);
        stop = findViewById(R.id.button5);
        declancher = findViewById(R.id.declancher);

        Test_Donnees();
    }

    public void declancher(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        btn_P_R.setClickable(true);
        declancher.setClickable(false);
        stop.setClickable(true);
        Calendar date = Calendar.getInstance();
        String donnerdate = String.valueOf(date.getTimeInMillis());
        file = writeToFile(donnerdate + ".csv");
        StopThread = true;
        Mythred0 thread0 = new Mythred0();
        thread0.start();
        EnvoiaTrame();
    }

    public void Stop(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" " + getString(R.string.finir_activity))
                .setIcon(R.drawable.alert)
                .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir) +
                        getString(R.string.mettre_fin_a_votre_activité)
                )
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StopThread = false;
                        chronometer.stop();
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        lastPause = 0;
                        btn_P_R.setText(R.string.pause);
                        Img_lock.setBackgroundResource(R.drawable.lock_close);
                        btn_P_R.setBackgroundResource(R.drawable.button_rudus);
                        btn_P_R.setChecked(false);
                        btn_P_R.setClickable(false);
                        declancher.setClickable(true);
                        startActivity(new Intent(getApplicationContext(), DetaileCardiaque.class));

                    }
                }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }

    public void pause_rep(View view) {

        if (btn_P_R.isChecked()) {
            //en pause
            StopThread = false;
            btn_P_R.setText(R.string.reprendre);
            Img_lock.setBackgroundResource(R.drawable.lock_open);
            btn_P_R.setBackgroundResource(R.drawable.button_rudus4);
            lastPause = SystemClock.elapsedRealtime();
            chronometer.stop();
        } else {
            //en reprendre
            StopThread = true;
            thread.start();
            Mythred0 thread0 = new Mythred0();
            thread0.start();
            chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
            chronometer.start();
            Img_lock.setBackgroundResource(R.drawable.lock_close);
            btn_P_R.setText(R.string.pause);
            btn_P_R.setBackgroundResource(R.drawable.button_rudus);
        }
    }

    public void Test_Donnees() {
        boolean value = pref.getBoolean("connexion", false);
        if (value) {
            Resaux.setImageResource(R.drawable.resaux);
        } else {
            Resaux.setImageResource(R.drawable.resaux2);
        }
        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.quotidien);
                    Img_etat.setImageResource(R.drawable.icon_quotidien);
                    break;
                case 2:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.VISIBLE);
                    textHaute.setText(R.string.marche);
                    Img_etat.setImageResource(R.drawable.icon_marche);
                    break;
                case 3:
                    textBas.setVisibility(View.VISIBLE);
                    constraintDistance.setVisibility(View.VISIBLE);
                    textHaute.setText(R.string.course);
                    textBas.setText(R.string.a_pied);
                    Img_etat.setImageResource(R.drawable.icone_course);
                    break;
                case 4:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.cyclisme);
                    Img_etat.setImageResource(R.drawable.icone_cycle);
                    break;
                case 5:
                    textBas.setVisibility(View.INVISIBLE);
                    constraintDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.sommeil);
                    Img_etat.setImageResource(R.drawable.icon_sommeil);
                    break;
            }
        }
    }

    public void EnvoiaTrame() {
         thread = new Thread() {
            @Override
            public void run() {
                while (StopThread) {
                    try {
                        runOnUiThread(new Runnable() {
                            byte[] buffer;

                            @Override
                            public void run() {
                                if (Indice != 0) {
                                    switch (Indice) {
                                        case 1:
                                            buffer = new byte[]{0x02, 0x73, 0x02, 0x01, 0x03, 0x03, 0x0A};
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                                BLEManager.writeData(buffer);
                                                BLEManager.readData();
                                            }
                                            break;
                                        case 2:
                                            buffer = new byte[]{0x02, 0x73, 0x02, 0x02, 0x03, 0x03, 0x0A};
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                                BLEManager.writeData(buffer);
                                                BLEManager.readData();
                                            }
                                            break;
                                        case 3:
                                            buffer = new byte[]{0x02, 0x73, 0x02, 0x03, 0x03, 0x03, 0x0A};
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                                BLEManager.writeData(buffer);
                                                BLEManager.readData();
                                            }
                                            break;
                                        case 4:
                                            buffer = new byte[]{0x02, 0x73, 0x02, 0x04, 0x03, 0x03, 0x0A};
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                                BLEManager.writeData(buffer);
                                                BLEManager.readData();
                                            }
                                            break;
                                        case 5:
                                            buffer = new byte[]{0x02, 0x73, 0x02, 0x05, 0x03, 0x03, 0x0A};
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                                BLEManager.writeData(buffer);
                                                BLEManager.readData();
                                            }
                                            break;
                                    }
                                }
                            }
                        });
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void parammetres(View view) {
        StopThread = false;
        Intent ite = new Intent(this, MenuActivity.class);
        startActivity(ite);
        E7_2.str = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exite(View view) {
        StopThread = false;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            StopThread = false;
            Intent ite = new Intent(this, CycleActivity.class);
            startActivity(ite);
        }
        return false;
    }

    public void acueil(View view) {
        StopThread = false;
        Intent ite = new Intent(this, CycleActivity.class);
        startActivity(ite);
        E7_2.str = null;
    }

    class Mythred0 extends Thread {
        public void run() {
            textECG = findViewById(R.id.chiffreECG);
            textPoumon = findViewById(R.id.chiffrePoumon);
            textTemp = findViewById(R.id.chiffreTemp);
            textCal = findViewById(R.id.chiffrecalorie);
            textDist = findViewById(R.id.chiffreDistance);
            niveaubatt = findViewById(R.id.NiveauBatt);
            batteri = findViewById(R.id.batterie);
            while (StopThread) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            textECG.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
                            textPoumon.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
                            textTemp.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));
                            textCal.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[5])));
                            niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[7]) + "%"));

                            textDist.setText(String.valueOf(BLEManager.unsignedToBytes((byte) (E7_2.str[5] + E7_2.str[6]))));
                            if (E7_2.str[7] == 0) {
                                batteri.setImageResource(R.drawable.batt7);
                            } else if (E7_2.str[7] >= 1 && E7_2.str[7] <= 13) {
                                batteri.setImageResource(R.drawable.batt6);

                            } else if (E7_2.str[7] > 13 && E7_2.str[7] <= 25) {
                                batteri.setImageResource(R.drawable.batt5);

                            } else if (E7_2.str[7] > 25 && E7_2.str[7] <= 38) {
                                batteri.setImageResource(R.drawable.batt4);

                            } else if (E7_2.str[7] > 38 && E7_2.str[7] <= 50) {
                                batteri.setImageResource(R.drawable.batt3);

                            } else if (E7_2.str[7] > 50 && E7_2.str[7] <= 75) {
                                batteri.setImageResource(R.drawable.batt2);

                            } else if (E7_2.str[7] > 76 && E7_2.str[7] <= 100) {
                                batteri.setImageResource(R.drawable.batt1);

                            }
                            String line = String.format("%s ; %s ; %s\n", String.valueOf(E7_2.str[2]), String.valueOf(E7_2.str[3]), String.valueOf(E7_2.str[4]));
//                              String heder = String.format("%s ; %s ; %s\n", "coeur","poumon","tenpirateur");
                            FileWriter filewriter = new FileWriter(file, true);
                            filewriter.write(line);
                            filewriter.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File writeToFile(String nomFicher) {
        File chemin = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        return new File(chemin, nomFicher);
    }
}
