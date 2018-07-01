package com.example.user.rafiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.rafiki.Adapter.Antecedents_Adapter;
import com.example.user.rafiki.ItemData.Antecedents_ItemData;
import com.example.user.rafiki.Helper.RecyclerViewClickListener;
import com.example.user.rafiki.Helper.RecyclerViewTouchListener;

import java.util.ArrayList;

public class AntecedentsActivity extends AppCompatActivity {
    RecyclerView r;
    Antecedents_Adapter myAdapter;
    ArrayList<Antecedents_ItemData> list;
    int i=0,j=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedents);



        r = (RecyclerView) findViewById(R.id.r);
        list = new ArrayList<>();
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(myAdapter);


        r.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), r, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {



            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),  position+ " is long pressed!", Toast.LENGTH_SHORT).show();
                try {


                    list.remove(position);
                    myAdapter.notifyDataSetChanged();

                }catch (Exception e){
                    e.getMessage();
                }
            }
        }));
    }
    public void retoure_fiche(View view) {
        Intent ite=new Intent(this,Fiche_MedicaleActivity.class);
        startActivity(ite);
    }

    public void ajout_linge(View view) {
        if (i<=5) {

            list.add(new Antecedents_ItemData(""+i , ""+i ));
            myAdapter = new Antecedents_Adapter(this, list);
            r.setLayoutManager(new LinearLayoutManager(this));
            r.setAdapter(myAdapter);
            i++;
        }
    }
}
