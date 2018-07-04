package com.example.user.rafiki;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.rafiki.Adapter.Antecedents_Adapter;
import com.example.user.rafiki.ItemData.Antecedents_ItemData;
import com.example.user.rafiki.Helper.RecyclerViewClickListener;
import com.example.user.rafiki.Helper.RecyclerViewTouchListener;

import java.util.ArrayList;

import static com.example.user.rafiki.Adapter.Antecedents_Adapter.ViewHolder.TEST_VIDE;

public class AntecedentsActivity extends AppCompatActivity {
    RecyclerView r;
    EditText date,acte;
    Antecedents_Adapter myAdapter;
    ArrayList<Antecedents_ItemData> list;
    ImageView ajout;
    int i=0;
    String TestDate,TestActe;
    LinearLayout conteneur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedents);
        date=findViewById(R.id.date);
        acte=findViewById(R.id.acte);
        ajout=findViewById(R.id.ajout);
        conteneur=findViewById(R.id.conteneur);

        list = new ArrayList<>();
        r = (RecyclerView) findViewById(R.id.r);
        myAdapter = new Antecedents_Adapter(this, list);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(myAdapter);
        if(list.size()>=5) {
            ajout.setVisibility(View.VISIBLE);

        }





        r.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), r, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {



            }

            @Override
            public void onLongClick(View view, int position) {
                try {

                    list.remove(position);
                    myAdapter.notifyDataSetChanged();
                    if(list.size()>=4) {
                        ajout.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }));
    }
    public void retoure_fiche(View view) {


   }

    public void ajout_linge(View view) {


        TestDate=date.getText().toString();
        TestActe=acte.getText().toString();

        if(valider()){
            if(list.size()<=5) {
                list.add(new Antecedents_ItemData(TestDate, TestActe));
                myAdapter.notifyDataSetChanged();
                date.setText("");
                acte.setText("");
                ajout.setVisibility(View.VISIBLE);
                Toast.makeText(this, ""+list.size(), Toast.LENGTH_SHORT).show();
                if(list.size()>=5){
                    ajout.setVisibility(View.GONE);
                    conteneur.setVisibility(View.GONE);

                }
            }

        }



//        for (i=0;i<list.size();i++) {

//
//                    myAdapter.notifyDataSetChanged();
//                    myAdapter = new Antecedents_Adapter(this, list);
//                    r.setLayoutManager(new LinearLayoutManager(this));
//                    r.setAdapter(myAdapter);
//                    i++;
           //     }




    }

    @TargetApi(16)
    private boolean valider() {
        boolean valide = true;
        if (TestDate.isEmpty()) {
            date.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
        if (TestActe.isEmpty()) {
            acte.setError(getString(R.string.champs_obligatoir));
            valide = false;
        }
    return valide;
    }
}

