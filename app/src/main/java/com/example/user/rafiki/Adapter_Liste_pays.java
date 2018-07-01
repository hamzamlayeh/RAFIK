package com.example.user.rafiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.rafiki.ItemData.DataItem;

import java.util.ArrayList;

public class Adapter_Liste_pays extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<DataItem> originalArray, temparray;
    customFilter cs;

    public Adapter_Liste_pays(Context c, ArrayList<DataItem> originalArray) {
        this.c = c;
        this.originalArray = originalArray;
        this.temparray = originalArray;
    }

    @Override
    public int getCount() {
        return originalArray.size();
    }

    @Override
    public Object getItem(int i) {
        return originalArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.liste_payer, null);

        TextView txt = (TextView) row.findViewById(R.id.txtitem);
        TextView id = (TextView) row.findViewById(R.id.id_image);
        ImageView img = (ImageView) row.findViewById(R.id.imageitem);

        id.setText(originalArray.get(i).id);
        txt.setText(originalArray.get(i).nom_payer);
        img.setImageResource(originalArray.get(i).img_payer);
        return row;
    }

    @Override
    public Filter getFilter() {
        if(cs==null)
        {
            cs=new customFilter();
        }
        return cs;
    }

    class customFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults Results = new FilterResults();

            if (charSequence != null && charSequence.length() > 0) {
                charSequence = charSequence.toString().toUpperCase();
                ArrayList<DataItem> filtres = new ArrayList<>();

                for (int i = 0; i < temparray.size(); i++) {
                    if (temparray.get(i).getNom_payer().toUpperCase().contains(charSequence)) {
                        DataItem singleRow = new DataItem(temparray.get(i).getId(),temparray.get(i).getImg_payer(),temparray.get(i).getNom_payer());
                        filtres.add(singleRow);

                    }
                }
                Results.count = filtres.size();
                Results.values = filtres;

            } else {
                Results.count = temparray.size();
                Results.values = temparray;
            }

            return Results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            originalArray = (ArrayList<DataItem>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
