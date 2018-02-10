package com.example.user.rafiki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.Arrays;

public class Liste_payers extends AppCompatActivity {

    String[] items;
    int[] imgs;
    ArrayList<DataItem> listA;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListAdapter listAdapter;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_payers);

        listView = (ListView) findViewById(R.id.listview);
        editText = (EditText) findViewById(R.id.txtsearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {
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
                Toast.makeText(getApplicationContext(),txt.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void searchItem(String textToSearch) {

        for (String item : items)
        {
            if (!item.contains(textToSearch))
            {
                listA.remove(item);
            }
        }
        listAdapter.notifyDataSetChanged();
    }

    public void initList() {

         listA = new ArrayList<DataItem>();

        items = getResources().getStringArray(R.array.Names);
        imgs= new int[]{R.drawable.france,R.drawable.tunisia,R.drawable.turkey,R.drawable.togo};
        listItems = new ArrayList<>(Arrays.asList(items));
        int id=0;
        for(String i : items){
            listA.add(new DataItem(imgs[id],i));
            id++;
        }

         listAdapter = new ListAdapter(listA);
          listView.setAdapter(listAdapter);
//           adapter = new ArrayAdapter<String>(this, R.layout.liste_payer, R.id.txtitem,listItems);
//          listView.setAdapter(adapter);


    }
    class ListAdapter extends BaseAdapter {

        ArrayList<DataItem> list = new ArrayList<DataItem>();

        ListAdapter(ArrayList<DataItem> list2)
        {
            this.list = list2;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
             view = layoutInflater.inflate(R.layout.liste_payer, null);
            TextView txt = (TextView) view.findViewById(R.id.txtitem);
            ImageView img = (ImageView) view.findViewById(R.id.imageitem);

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


