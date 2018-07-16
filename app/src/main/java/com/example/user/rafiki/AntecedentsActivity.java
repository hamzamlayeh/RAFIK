package com.example.user.rafiki;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Antecedents_Item;

import java.util.ArrayList;
import java.util.List;

public class AntecedentsActivity extends AppCompatActivity {

    MySQLiteOpenHelper helper;
    UserDataSource ds;
    LinearLayout L1, L2, L3, L4, L5;
    EditText Acte1, Acte2, Acte3, Acte4, Acte5;
    EditText Date1, Date2, Date3, Date4, Date5;
    String actes1, actes2, actes3, actes4, actes5, dates1, dates2, dates3, dates4, dates5;
    static int A;
    List<Antecedents_Item> listAntes = new ArrayList<Antecedents_Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedents);
        helper = new MySQLiteOpenHelper(this, "Utilisateur", null);
        ds = new UserDataSource(helper);

        L1 = findViewById(R.id.linear1);
        L2 = findViewById(R.id.linear2);
        L3 = findViewById(R.id.linear3);
        L4 = findViewById(R.id.linear4);
        L5 = findViewById(R.id.linear5);

        Acte1 = findViewById(R.id.acte1);
        Acte2 = findViewById(R.id.acte2);
        Acte3 = findViewById(R.id.acte3);
        Acte4 = findViewById(R.id.acte4);
        Acte5 = findViewById(R.id.acte5);

        Date1 = findViewById(R.id.date1);
        Date2 = findViewById(R.id.date2);
        Date3 = findViewById(R.id.date3);
        Date4 = findViewById(R.id.date4);
        Date5 = findViewById(R.id.date5);

        if (ds.getCountAntece() > 0) {
            listAntes = ds.getListAnte();
            Acte1.setText(listAntes.get(0).getActe());
            Date1.setText(listAntes.get(0).getDate());
            Acte2.setText(listAntes.get(1).getActe());
            Date2.setText(listAntes.get(1).getDate());
            Acte3.setText(listAntes.get(2).getActe());
            Date3.setText(listAntes.get(2).getDate());
            Acte4.setText(listAntes.get(3).getActe());
            Date4.setText(listAntes.get(3).getDate());
            Acte5.setText(listAntes.get(4).getActe());
            Date5.setText(listAntes.get(4).getDate());
        }
        Acte1.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte1.setText("");
                Date1.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 1);
                return false;
            }
        });
        Date1.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte1.setText("");
                Date1.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 1);
                return false;
            }
        });
        Acte2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte2.setText("");
                Date2.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 2);
                return false;
            }
        });
        Date2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte2.setText("");
                Date2.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 2);
                return false;
            }
        });
        Acte3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte3.setText("");
                Date3.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 3);
                return false;
            }
        });
        Date3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte3.setText("");
                Date3.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 3);
                return false;
            }
        });
        Acte4.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte4.setText("");
                Date4.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 4);
                return false;
            }
        });
        Date4.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte4.setText("");
                Date4.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 4);
                return false;
            }
        });
        Acte5.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte5.setText("");
                Date5.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 5);
                return false;
            }
        });
        Date5.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Acte5.setText("");
                Date5.setText("");
                long x = ds.UpdateAnti(new Antecedents_Item("", ""), 5);
                return false;
            }
        });
    }

    public void retoure_fiche(View view) {
        actes1 = Acte1.getText().toString();
        dates1 = Date1.getText().toString();
        actes2 = Acte2.getText().toString();
        dates2 = Date2.getText().toString();
        actes3 = Acte3.getText().toString();
        dates3 = Date3.getText().toString();
        actes4 = Acte4.getText().toString();
        dates4 = Date4.getText().toString();
        actes5 = Acte5.getText().toString();
        dates5 = Date5.getText().toString();

        listAntes.add(0, new Antecedents_Item(actes1, dates1));
        listAntes.add(1, new Antecedents_Item(actes2, dates2));
        listAntes.add(2, new Antecedents_Item(actes3, dates3));
        listAntes.add(3, new Antecedents_Item(actes4, dates4));
        listAntes.add(4, new Antecedents_Item(actes5, dates5));

        if (ds.getCountAntece() <= 0) {
            int i = 0;
            while (i < listAntes.size()) {

                long x = ds.addAnte(listAntes.get(i));
                i++;
            }
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
            AntecedentsActivity.this.finish();
        } else {
            int i = 0;
            while (i < listAntes.size()) {

                long x = ds.UpdateAnti(listAntes.get(i), i + 1);
                i++;
            }
            Intent ite = new Intent(this, Fiche_MedicaleActivity.class);
            startActivity(ite);
            AntecedentsActivity.this.finish();
        }
    }
    public void alert(View view) {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle(" ").setIcon(R.drawable.alert)
                .setMessage("\n" + getString(R.string.ajouter) + "         " + getString(R.string.cliquer_sur_maladie) + "\n" +
                        getString(R.string.modifier) + "       " + getString(R.string.cliquer_sur_maladie) + "\n" +
                        getString(R.string.supprimer) + "   " + getString(R.string.appui_long_sur_maladie)
                )
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}