package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Liste_payers extends AppCompatActivity implements TextWatcher {

    String[] items = new String[199];
    ArrayList<DataItem> listA;
    //ListAdapter listAdapter;
    Adapter_Liste_pays myAdapter;
    ListView listView;
    EditText recherch;
    Intent ite;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_payers);

        listView = (ListView) findViewById(R.id.listview);
        recherch = (EditText) findViewById(R.id.txtsearch);
        rempli_nom_pays();

        recherch.addTextChangedListener(this);
        listA=new ArrayList<DataItem>();
        DataItem dataItem;
        int id = 0;
        for(int i=0;i<items.length;i++){

            dataItem=new DataItem(String.valueOf(id),Constante.imgs[i],items[i]);
            listA.add(dataItem);
            id++;
        }
        myAdapter=new Adapter_Liste_pays(this,listA);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txt = (TextView) view.findViewById(R.id.txtitem);
                TextView id = (TextView) view.findViewById(R.id.id_image);
                editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
                editor.putString("Nom_Pays", txt.getText().toString());
                editor.putString("Id_img", id.getText().toString());
                editor.apply();
                Inscription.idc=getIndexPays(txt.getText().toString().toLowerCase());
                ite = new Intent(Liste_payers.this, Inscription.class);
                startActivity(ite);
                Liste_payers.this.finish();
            }
        });

    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        this.myAdapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    public void rempli_nom_pays() {

        try {
            InputStream inputStream = getAssets().open("payes.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                items[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getIndexPays(String countryName)
    {
        boolean exist = false;
        int index = 0;
        int i=0;
        while (exist ==false && i<items.length){
            if(items[i].toLowerCase().equals(countryName))
            {
                exist=true;
                index =i;
            }
            i++;
        }
        return index;
    }

}