package com.example.user.rafiki.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.user.rafiki.ItemData.Medicament_Item;
import com.example.user.rafiki.R;

import java.util.ArrayList;
import java.util.List;

public class Medicament_Adapter extends RecyclerView.Adapter<Medicament_Adapter.ViewHolder> {
    private Context context;
    private List<Medicament_Item> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom_midica, date, nb_matin, nb_midi, nb_soire, lundi, mardi, mercredi, jeudi, vendredi;
        TextView samedi, dimanche;

        public ViewHolder(View itemView) {
            super(itemView);

            nom_midica = itemView.findViewById(R.id.nom);
            date = itemView.findViewById(R.id.date);
            nb_matin = itemView.findViewById(R.id.nb_matin);
            nb_midi = itemView.findViewById(R.id.nb_midi);
            nb_soire = itemView.findViewById(R.id.nb_soire);
            lundi = itemView.findViewById(R.id.lundi);
            mardi = itemView.findViewById(R.id.mardi);
            mercredi = itemView.findViewById(R.id.mercredi);
            jeudi = itemView.findViewById(R.id.jeudi);
            vendredi = itemView.findViewById(R.id.vendredi);
            samedi = itemView.findViewById(R.id.samedi);
            dimanche = itemView.findViewById(R.id.dimanche);

        }
    }
    public Medicament_Adapter(Context context, ArrayList<Medicament_Item> list ){

        this.context=context;
        this.list=list;

    }
    @NonNull
    @Override
    public Medicament_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.medicament_recycle_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Medicament_Adapter.ViewHolder holder, int position) {

        Medicament_Item itemData = list.get(position);

        holder.nom_midica.setText(itemData.getNom_medica());
        holder.date.setText(itemData.getDate_fin());
        holder.nb_matin.setText(itemData.getNb_matin());
        holder.nb_midi.setText(itemData.getNb_midi());
        holder.nb_soire.setText(itemData.getNb_soire());
        holder.lundi.setTextColor(itemData.getColor_lu());
        holder.mardi.setTextColor(itemData.getColor_ma());
        holder.mercredi.setTextColor(itemData.getColor_me());
        holder.jeudi.setTextColor(itemData.getColor_ju());
        holder.vendredi.setTextColor(itemData.getColor_ve());
        holder.samedi.setTextColor(itemData.getColor_sa());
        holder.dimanche.setTextColor(itemData.getColor_di());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
