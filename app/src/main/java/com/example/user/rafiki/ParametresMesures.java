package com.example.user.rafiki;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.user.rafiki.ItemData.Donnes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParametresMesures extends AppCompatActivity {

    static int UNITE_SECONDE = 1000, UNITE_MENUTE = 60000, UNITE_HEURE = 3600000;
    static boolean StopThread = true;
    ImageView Img_etat, Resaux, batteri;
    TextView textHaute, textBas, niveaubatt, textECG, textPoumon, textTemp, textCal, textDist;
    Chronometer chronometer;
    ToggleButton btn_P_R;
    Button stop, declancher, Img_lock;
    ConstraintLayout constraintDistance;
    int Indice, Poids;
    long lastPause;
    double Nbr_pas, Duree_en_munite, Calorie;
    String Chrono;
    SharedPreferences prefs, pref, pref2;
    SharedPreferences.Editor editor;
    Activity activity;
    File file;
    Thread thread;
    static String[] temp = new String[0];
    static List<Donnes> Liste_donne = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_mesures);

        activity = this;
        prefs = getSharedPreferences("Cycle", MODE_PRIVATE);
        pref = getSharedPreferences("Inscription", MODE_PRIVATE);
        pref2 = getApplicationContext().getSharedPreferences("Fiche_Medicale", MODE_PRIVATE);
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
        String restoredpoid = pref2.getString("Poid", null);
        if (restoredpoid != null) {
            Poids = Integer.parseInt(restoredpoid);
        } else {
            Poids = 0;
        }
        Toast.makeText(activity, Poids + "", Toast.LENGTH_SHORT).show();
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
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
//                        try {
//                            Date date = dateFormat.parse(String.valueOf(chronometer.getText()));
//                            Calendar cl=Calendar.getInstance();
//                            cl.setTime(date);
//                            Duree_en_munite=((cl.get(Calendar.SECOND)*UNITE_SECONDE)+(cl.get(Calendar.MINUTE)*UNITE_MENUTE)+
//                                    (cl.get(Calendar.HOUR)*UNITE_HEURE))/UNITE_MENUTE;
//                            System.out.println(Duree_en_munite);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

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

                        for (int i = 0; i < Lireficher(file).size(); i++) {
                            String[] donnes = Lireficher(file).get(i).split(";");
                            String couer = donnes[0];
                            String poumon = donnes[1];
                            String temp = donnes[2];
                            Donnes item = new Donnes(couer, poumon, temp);
                            Liste_donne.add(item);
                            if (i == Lireficher(file).size() - 1) {
                                editor = prefs.edit();
                                editor.putString("Calorie", String.valueOf(Calorie));
                                editor.putString("Nbr_Pas", String.valueOf(Nbr_pas));
                                editor.putString("Duree_Minute", String.valueOf(Duree_en_munite));
                                editor.putString("Chronomaitre", Chrono);
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(), DetaileCardiaque.class));
                            }
                        }
//                        long base=chronometer.getBase();
//                      Toast.makeText(activity, ""+base, Toast.LENGTH_SHORT).show();
//                        System.out.println(Liste_donne.get(0).getCoeur());
//                        System.out.println(Liste_donne.get(0).getPoumon());
//                        System.out.println(Liste_donne.get(0).getTempirateur());
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
                            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                            NumberFormat format = NumberFormat.getInstance();
                            format.setMaximumFractionDigits(2);
                            try {
                                Chrono = String.valueOf(chronometer.getText());
                                Date date = dateFormat.parse(Chrono);
                                Calendar cl = Calendar.getInstance();
                                cl.setTime(date);
                                double seconde = cl.get(Calendar.SECOND) * UNITE_SECONDE;
                                double munite = cl.get(Calendar.MINUTE) * UNITE_MENUTE;
                                double huere = cl.get(Calendar.HOUR_OF_DAY);

                                Duree_en_munite = (seconde + munite) / UNITE_MENUTE;
                                System.out.println((seconde + munite) / UNITE_MENUTE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            textECG.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[2])));//batement de coeur
                            textPoumon.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[3])));
                            textTemp.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[4])));
                            niveaubatt.setText(String.valueOf(BLEManager.unsignedToBytes(E7_2.str[7]) + "%"));
                            Nbr_pas = BLEManager.unsignedToBytes((byte) (E7_2.str[5] + E7_2.str[6]));
                            if (Indice != 0) {
                                switch (Indice) {
                                    case 1:
                                        Calorie = (2 * 3.5 * Poids / 200) * Duree_en_munite;
                                        textCal.setText(format.format(Calorie));
                                        break;
                                    case 2:
                                        textDist.setText(format.format(Nbr_pas / 1600));
                                        Calorie = (2 * 3.5 * Poids / 200) * Duree_en_munite;
                                        textCal.setText(format.format(Calorie));
                                        break;
                                    case 3:
                                        textDist.setText(format.format(Nbr_pas / 1250));
                                        if (Nbr_pas <= 12) {
                                            Calorie = (8 * 3.5 * Poids / 200) * Duree_en_munite;
                                            textCal.setText(format.format(Calorie));
                                        } else {
                                            Calorie = (14 * 3.5 * Poids / 200) * Duree_en_munite;
                                            textCal.setText(format.format(Calorie));
                                        }
                                        break;
                                    case 4:
                                        Calorie = (4 * 3.5 * Poids / 200) * Duree_en_munite;
                                        textCal.setText(format.format(Calorie));
                                        break;
                                    case 5:
                                        Calorie = (1 * 3.5 * Poids / 200) * Duree_en_munite;
                                        textCal.setText(format.format(Calorie));
                                        break;
                                }
                            }
//                            textDist.setText(String.valueOf(BLEManager.unsignedToBytes((byte) (E7_2.str[5] + E7_2.str[6]))));

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

    @NonNull
    private List<String> Lireficher(File file) {
        List<String> result = new ArrayList<String>();
        StringBuilder objBuffer = new StringBuilder();
        try {
            FileInputStream objFile = new FileInputStream(file);
            InputStreamReader objReader = new InputStreamReader(objFile);
            BufferedReader objBufferReader = new BufferedReader(objReader);
            String strLine;
            while ((strLine = objBufferReader.readLine()) != null) {
                objBuffer.append(strLine);
                objBuffer.append("\n");
                result.add(strLine);

            }
            objFile.close();
            objBufferReader.close();
//            Toast.makeText(activity, objBuffer.toString()+"", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException objError) {
            Toast.makeText(this, "Fichier non trouvé\n" + objError.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException objError) {
            Toast.makeText(this, "Erreur\n" + objError.toString(), Toast.LENGTH_LONG).show();
        }
        return result;
    }
}
