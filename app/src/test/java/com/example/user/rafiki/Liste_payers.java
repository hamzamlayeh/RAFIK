package com.example.user.rafiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.rafiki.ItemData.Constante;
import com.example.user.rafiki.ItemData.DataItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Liste_payers extends AppCompatActivity {

    String[] items = new String[199];
    ArrayList<DataItem> listA;
    ListAdapter listAdapter;
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
        initList();
        recherch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    // reset listview
                    initList();
                } else {
                    searchItem(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txt = (TextView) view.findViewById(R.id.txtitem);
                TextView id = (TextView) view.findViewById(R.id.id_image);
                editor = getSharedPreferences("Inscription", MODE_PRIVATE).edit();
                editor.putString("Nom_Pays", txt.getText().toString());
                editor.putString("Id_img", id.getText().toString());
                editor.apply();
                ite = new Intent(Liste_payers.this, Inscription.class);
                startActivity(ite);
            }
        });

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

    public void searchItem(String textToSearch) {

        for (int i = 0; i < listA.size(); i++)
        {
            if (!listA.get(i).nom_payer.contains(textToSearch)) {
                listA.remove(listA.get(i));
            }
            listAdapter.notifyDataSetChanged();
        }

    }

    public void initList() {

        listA = new ArrayList<DataItem>();
        int id = 0;
        for (String i : items) {
            listA.add(new DataItem(String.valueOf(id), Constante.imgs[id], i));
            id++;
        }
        listAdapter = new ListAdapter(listA);
        listView.setAdapter(listAdapter);
    }

    class ListAdapter extends BaseAdapter {

        ArrayList<DataItem> list = new ArrayList<DataItem>();

        ListAdapter(ArrayList<DataItem> list2) {
            this.list = list2;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            view = layoutInflater.inflate(R.layout.liste_payer, null);
            TextView txt = (TextView) view.findViewById(R.id.txtitem);
            TextView id = (TextView) view.findViewById(R.id.id_image);
            ImageView img = (ImageView) view.findViewById(R.id.imageitem);

            id.setText(list.get(i).id);
            txt.setText(list.get(i).nom_payer);
            img.setImageResource(list.get(i).img_payer);


            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i).nom_payer;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
    }
}


