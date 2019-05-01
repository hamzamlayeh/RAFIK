package com.example.user.rafiki;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.user.rafiki.ItemData.Cycle;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ParametresMesures extends AppCompatActivity {

    static int UNITE_SECONDE = 1000, UNITE_MENUTE = 60000, UNITE_HEURE = 3600000;
    static boolean StopThread = true;
    ImageView Img_etat, Resaux, batteri;
    TextView textHaute, textBas, niveaubatt, textECG, textPoumon, textTemp, textCal, textDist, textVitesse;
    LinearLayout LinearDistance;
    Chronometer chronometer;
    ToggleButton btn_P_R;
    Button stop, declancher, Img_lock;
    int Indice, Poids, Nbr_pas;
    long lastPause;
    double Duree_en_munite, Calorie;
    Boolean DECLANCHER = false;
    String Chrono, Date_cycle, FullDate_cycle, Time_cycle;
    SharedPreferences prefs, pref;
    SharedPreferences.Editor editor;
    Activity activity;
    MySQLiteOpenHelper helper;
    UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_mesures);
        activity = this;
        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        Indice = prefs.getInt("Indice", 0);

        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        Img_etat = findViewById(R.id.imageView10);
        Img_lock = findViewById(R.id.bt_clock);
        Resaux = findViewById(R.id.imageView29);
        textHaute = findViewById(R.id.Signes);
        textBas = findViewById(R.id.BIOMETRIQUE);
        LinearDistance = findViewById(R.id.linearDistance);
        chronometer = findViewById(R.id.simpleChronometer);
        btn_P_R = findViewById(R.id.button3);
        stop = findViewById(R.id.button5);
        declancher = findViewById(R.id.declancher);
        niveaubatt = findViewById(R.id.NiveauBatt);
        batteri = findViewById(R.id.batterie);
        stop.setClickable(false);
        Test_Donnees();
        String restoredemail = pref.getString("Email", null);
        int restoredbat = pref.getInt("NiveauBat", -1);
        int restoredelctrod = pref.getInt("Electrode", -1);
        if (restoredbat != -1) {
            niveaubatt.setText(String.valueOf(String.format("%s%%", restoredbat)));
            if (restoredbat == 0) {
                batteri.setImageResource(R.drawable.batt7);
            } else if (restoredbat >= 1 && restoredbat <= 13) {
                batteri.setImageResource(R.drawable.batt6);

            } else if (restoredbat > 13 && restoredbat <= 25) {
                batteri.setImageResource(R.drawable.batt5);

            } else if (restoredbat > 25 && restoredbat <= 38) {
                batteri.setImageResource(R.drawable.batt4);

            } else if (restoredbat > 38 && restoredbat <= 50) {
                batteri.setImageResource(R.drawable.batt3);

            } else if (restoredbat > 50 && restoredbat <= 75) {
                batteri.setImageResource(R.drawable.batt2);

            } else if (restoredbat > 76 && restoredbat <= 100) {
                batteri.setImageResource(R.drawable.batt1);
            }
        }
        if (restoredelctrod != -1) {
            if (restoredelctrod == 0) {
                Resaux.setImageResource(R.drawable.resaux);
            } else {
                Resaux.setImageResource(R.drawable.resaux2);
            }
        }
        if (restoredemail != null) {
            Poids = Integer.parseInt(ds.getPoid(restoredemail));
        } else {
            Poids = 0;
        }
    }

    public void declancher(View view) {
        boolean value = pref.getBoolean("connexion", false);
        if (value) {
            E7_2.str = null;
            StopThread = true;
            //EnvoiaTrame();
            EnvoiaTrameByCycle();
            Mythred0 thread0 = new Mythred0();
            thread0.start();
            DECLANCHER = true;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            SimpleDateFormat FullDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
            SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            Calendar date = Calendar.getInstance();
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            btn_P_R.setClickable(true);
            declancher.setClickable(false);
            stop.setClickable(true);
            Date_cycle = simpleDateFormat.format(date.getTime());
            FullDate_cycle = FullDateFormat.format(date.getTime());
            Time_cycle = TimeFormat.format(date.getTime());
        } else {
            Toast.makeText(activity, R.string.test_carte_cycle, Toast.LENGTH_SHORT).show();
        }
    }

    public void Stop(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" " + getString(R.string.finir_activity))
                .setIcon(R.drawable.alert)
                .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TrameSop();
                        StopThread = false;
                        if (E7_2.str != null) {
                            E7_2.str = null;
                            DECLANCHER = false;
                            chronometer.stop();
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            lastPause = 0;
                            btn_P_R.setText(R.string.pause);
                            Img_lock.setBackgroundResource(R.drawable.lock_close);
                            btn_P_R.setBackgroundResource(R.drawable.button_rudus);
                            btn_P_R.setChecked(false);
                            btn_P_R.setClickable(false);
                            declancher.setClickable(true);

                            if (ds.getCountCycle(FullDate_cycle) > 0) {
                                editor = prefs.edit();
                                editor.putString("Calorie", String.valueOf(Calorie));
                                editor.putString("Nbr_Pas", String.valueOf(Nbr_pas));
                                editor.putString("Duree_Minute", String.valueOf(Duree_en_munite));
                                editor.putString("Chronomaitre", Chrono);
                                editor.putString("Date_Cycle", FullDate_cycle);
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(), DetaileCardiaque.class));
                            }
                        } else {
                            //Toast.makeText(activity, "tab vide", Toast.LENGTH_SHORT).show();
                        }
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
            btn_P_R.setBackgroundResource(R.color.bg_btn_ok);
            lastPause = SystemClock.elapsedRealtime();
            chronometer.stop();
        } else {
            //en reprendre
            StopThread = true;
//            EnvoiaTrame();
            EnvoiaTrameByCycle();
            Mythred0 thread0 = new Mythred0();
            thread0.start();
            chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
            chronometer.start();
            Img_lock.setBackgroundResource(R.drawable.lock_close);
            btn_P_R.setText(R.string.pause);
            btn_P_R.setBackgroundResource(R.color.color_btnpause);
        }
    }

    public void Test_Donnees() {
//        boolean value = pref.getBoolean("connexion", false);
//        if (value) {
//            Resaux.setImageResource(R.drawable.resaux);
//        } else {
//            Resaux.setImageResource(R.drawable.resaux2);
//        }
        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    textBas.setVisibility(View.INVISIBLE);
                    LinearDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.quotidien);
                    Img_etat.setImageResource(R.drawable.icon_quotidien);
                    break;
                case 2:
                    textBas.setVisibility(View.INVISIBLE);
                    LinearDistance.setVisibility(View.VISIBLE);
                    textHaute.setText(R.string.marche);
                    Img_etat.setImageResource(R.drawable.icon_marche);
                    break;
                case 3:
                    textBas.setVisibility(View.VISIBLE);
                    LinearDistance.setVisibility(View.VISIBLE);
                    textHaute.setText(R.string.course);
                    textBas.setText(R.string.a_pied);
                    Img_etat.setImageResource(R.drawable.icone_course);
                    break;
                case 4:
                    textBas.setVisibility(View.INVISIBLE);
                    LinearDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.cyclisme);
                    Img_etat.setImageResource(R.drawable.icone_cycle);
                    break;
                case 5:
                    textBas.setVisibility(View.INVISIBLE);
                    LinearDistance.setVisibility(View.GONE);
                    textHaute.setText(R.string.sommeil);
                    Img_etat.setImageResource(R.drawable.icon_sommeil);
                    break;
            }
        }
    }

    public void parammetres(View view) {
        if (DECLANCHER) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            alt.setTitle(" " + getString(R.string.finir_activity))
                    .setIcon(R.drawable.alert)
                    .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TrameSop();
                            StopThread = false;
                            E7_2.str = null;
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        }
                    }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        } else {
            StopThread = false;
            E7_2.str = null;
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exite(View view) {
        if (DECLANCHER) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            alt.setTitle(" " + getString(R.string.finir_activity))
                    .setIcon(R.drawable.alert)
                    .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TrameSop();
                            StopThread = false;
                            E7_2.str = null;
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            startActivity(intent);
                            finishAffinity();
                        }
                    }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        } else {
            StopThread = false;
            E7_2.str = null;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (DECLANCHER) {
                AlertDialog.Builder alt = new AlertDialog.Builder(this);
                alt.setTitle(" " + getString(R.string.finir_activity))
                        .setIcon(R.drawable.alert)
                        .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                        .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                TrameSop();
                                StopThread = false;
                                E7_2.str = null;
                                startActivity(new Intent(getApplicationContext(), CycleActivity.class));
                            }
                        }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            } else {
                StopThread = false;
                E7_2.str = null;
                startActivity(new Intent(getApplicationContext(), CycleActivity.class));
            }
        }
        return false;
    }

    public void acueil(View view) {
        if (DECLANCHER) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            alt.setTitle(" " + getString(R.string.finir_activity))
                    .setIcon(R.drawable.alert)
                    .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TrameSop();
                            StopThread = false;
                            E7_2.str = null;
                            startActivity(new Intent(getApplicationContext(), E8.class));
                        }
                    }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        } else {
            StopThread = false;
            E7_2.str = null;
            startActivity(new Intent(getApplicationContext(), E8.class));
        }
    }

    public void Cycle(View view) {
        if (DECLANCHER) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            alt.setTitle(" " + getString(R.string.finir_activity))
                    .setIcon(R.drawable.alert)
                    .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            StopThread = false;
                            E7_2.str = null;
                            startActivity(new Intent(getApplicationContext(), CycleActivity.class));
                        }
                    }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        } else {
            StopThread = false;
            E7_2.str = null;
            startActivity(new Intent(getApplicationContext(), CycleActivity.class));
        }
    }

    public void historique(View view) {
        if (DECLANCHER) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            alt.setTitle(" " + getString(R.string.finir_activity))
                    .setIcon(R.drawable.alert)
                    .setMessage("\n " + getString(R.string.etes_vous_sure_de_vouloir))
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TrameSop();
                            StopThread = false;
                            E7_2.str = null;
                            startActivity(new Intent(getApplicationContext(), HistoriqueActivity.class));
                        }
                    }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        } else {
            TrameSop();
            StopThread = false;
            E7_2.str = null;
            startActivity(new Intent(getApplicationContext(), HistoriqueActivity.class));
        }
    }

    class Mythred0 extends Thread {
        public void run() {
            textECG = findViewById(R.id.chiffreECG);
            textPoumon = findViewById(R.id.chiffrePoumon);
            textTemp = findViewById(R.id.chiffreTemp);
            textCal = findViewById(R.id.chiffrecalorie);
            textDist = findViewById(R.id.chiffreDistance);
            textVitesse = findViewById(R.id.chiffrevittesse);

            while (StopThread) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            NumberFormat format = NumberFormat.getInstance();
                            format.setMaximumFractionDigits(2);
                            SimpleDateFormat dateFormat;
                            try {
                                Chrono = String.valueOf(chronometer.getText());
                                if (Chrono.length() > 5) {
                                    dateFormat = new SimpleDateFormat("hh:mm:ss");
//                                    System.out.println("A==" + Chrono);
                                } else {
                                    dateFormat = new SimpleDateFormat("mm:ss");
//                                    System.out.println("B==" + Chrono);
                                }
                                Date date = dateFormat.parse(Chrono);
                                Calendar cl = Calendar.getInstance();
                                cl.setTime(date);
                                double seconde = cl.get(Calendar.SECOND) * UNITE_SECONDE;
                                double munite = cl.get(Calendar.MINUTE) * UNITE_MENUTE;
                                double huere = cl.get(Calendar.HOUR_OF_DAY) * UNITE_HEURE;

                                Duree_en_munite = (huere + seconde + munite) / UNITE_MENUTE;
//                                System.out.println(Duree_en_munite);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[7]) + "%"));

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
                            if (E7_2.str[8] == 0) {
//                                Log.d("time", Chrono = String.valueOf(chronometer.getText()));
                                Log.d("trame", BLEManager.unsignedToBytes(E7_2.str[2]) + "/" + BLEManager.unsignedToBytes(E7_2.str[3])
                                        + "/" + BLEManager.unsignedToBytes(E7_2.str[4]) + "/" + BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[5]),
                                        BLEManager.unsignedToBytes(E7_2.str[6]))) + "/" + BLEManager.unsignedToBytes(E7_2.str[7]) + "/" +
                                        BLEManager.unsignedToBytes(E7_2.str[8]));
                                Resaux.setImageResource(R.drawable.resaux);
                                textECG.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
                                textPoumon.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
                                textTemp.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));

                            } else {
                                textECG.setText("--");
                                textPoumon.setText("--");
                                textTemp.setText("--");
                                Resaux.setImageResource(R.drawable.resaux2);
                            }
                            Nbr_pas = BLEManager.hexToInt(BLEManager.decToHex(BLEManager.unsignedToBytes(E7_2.str[5]),
                                    BLEManager.unsignedToBytes(E7_2.str[6])));
                            Log.i("nbpas",(BLEManager.unsignedToBytes(E7_2.str[6])*255)+BLEManager.unsignedToBytes(E7_2.str[5])+"");
//                            Log.i("NombrePas",Nbr_pas+"//"+Nbr_pas/3);
                            Log.i("PasBrut",Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[5]))+"/"+Integer.toHexString(BLEManager.unsignedToBytes(E7_2.str[6])));
                            if (Indice != 0) {
                                switch (Indice) {
                                    case 1:
                                        if (E7_2.str[8] == 0) {
                                            Calorie = (2 * 3.5 * Poids / 200) * Duree_en_munite;
                                            textCal.setText(String.valueOf(Math.round(Calorie)));
                                        } else {
                                            textCal.setText("--");
                                        }
                                        break;
                                    case 2:
                                        if (E7_2.str[8] == 0) {
                                            double DistanceM = (double) Nbr_pas / 1600;
                                            textDist.setText(format.format(DistanceM));
                                            textVitesse.setText(format.format((int) DistanceM / (Duree_en_munite / 60)));
                                            Calorie = (2 * 3.5 * Poids / 200) * Duree_en_munite;
                                            textCal.setText(String.valueOf((int) Calorie));
                                        } else {
                                            textDist.setText("--");
                                            textVitesse.setText("--");
                                            textCal.setText("--");
                                        }
                                        break;
                                    case 3:
                                        if (E7_2.str[8] == 0) {
                                            double DistanceC = (double) Nbr_pas / 1250;
                                            double vitesseC = DistanceC / (Duree_en_munite / 60);
                                            textDist.setText(format.format(DistanceC));
                                            textVitesse.setText(format.format((int) vitesseC));

                                            if (vitesseC <= 10) {
                                                Calorie = (8 * 3.5 * Poids / 200) * Duree_en_munite;
                                                textCal.setText(String.valueOf(Math.round(Calorie)));
                                            } else {
                                                Calorie = (14 * 3.5 * Poids / 200) * Duree_en_munite;
                                                textCal.setText(String.valueOf(Math.round(Calorie)));
                                            }
                                        } else {
                                            textDist.setText("--");
                                            textVitesse.setText("--");
                                            textCal.setText("--");
                                        }
                                        break;
                                    case 4:
                                        if (E7_2.str[8] == 0) {
                                            Calorie = (4 * 3.5 * Poids / 200) * Duree_en_munite;
                                            textCal.setText(String.valueOf(Math.round(Calorie)));
                                        } else {
                                            textCal.setText("--");
                                        }
                                        break;
                                    case 5:
                                        if (E7_2.str[8] == 0) {
                                            Calorie = (1 * 3.5 * Poids / 200) * Duree_en_munite;
                                            textCal.setText(String.valueOf(Math.round(Calorie)));
                                        } else {
                                            textCal.setText("--");
                                        }
                                        break;
                                }
                            }

                            if (E7_2.str[8] == 0) {
                                Cycle cycle = new Cycle(FullDate_cycle, Date_cycle, Time_cycle, BLEManager.unsignedToBytes(E7_2.str[2]), BLEManager.unsignedToBytes(E7_2.str[3]), BLEManager.unsignedToBytes(E7_2.str[4]), Nbr_pas, Math.round(Calorie), Indice);
                                ds.addCycle(cycle);
                            }
                            //EnvoiaTrame();
                            EnvoiaTrameByCycle();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
        }, 2000);
    }

    public void EnvoiaTrameByCycle() {
        if (Indice != 0) {
            switch (Indice) {
                case 1:
                    EnvoiaTrame((byte) 0x74);
                    break;
                case 2:
                    EnvoiaTrame((byte) 0x76);
                    break;
                case 3:
                    EnvoiaTrame((byte) 0x75);
                    break;
                case 4:
                    EnvoiaTrame((byte) 0x77);
                    break;
                case 5:
                    EnvoiaTrame((byte) 0x78);
                    break;
            }
        }
    }

    public void EnvoiaTrame(final byte valeur) {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            public void run() {
                byte[] buffer = {0x02, 0x73, 0x02, valeur, 0x03, 0x0A};
                BLEManager.writeData(buffer);
                try {
                    Thread.sleep(1000);
                    BLEManager.readData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }
//    private File writeToFile(String nomFicher) {
//        File chemin = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//        return new File(chemin, nomFicher);
//    }

//    @NonNull
//    private List<String> Lireficher(File file) {
//        List<String> result = new ArrayList<String>();
//        StringBuilder objBuffer = new StringBuilder();
//        try {
//            FileInputStream objFile = new FileInputStream(file);
//            InputStreamReader objReader = new InputStreamReader(objFile);
//            BufferedReader objBufferReader = new BufferedReader(objReader);
//            String strLine;
//            while ((strLine = objBufferReader.readLine()) != null) {
//                objBuffer.append(strLine);
//                objBuffer.append("\n");
//                result.add(strLine);
//
//            }
//            objFile.close();
//            objBufferReader.close();
////            Toast.makeText(activity, objBuffer.toString()+"", Toast.LENGTH_SHORT).show();
//        } catch (FileNotFoundException objError) {
//            Toast.makeText(this, "Fichier non trouvé\n" + objError.toString(), Toast.LENGTH_LONG).show();
//        } catch (IOException objError) {
//            Toast.makeText(this, "Erreur\n" + objError.toString(), Toast.LENGTH_LONG).show();
//        }
//        return result;
//    }
}
