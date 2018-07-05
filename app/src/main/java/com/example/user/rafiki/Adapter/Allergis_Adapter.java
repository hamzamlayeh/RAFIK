package com.example.user.rafiki.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user.rafiki.ItemData.Allergis_ItemData;
import com.example.user.rafiki.ItemData.Antecedents_ItemData;
import com.example.user.rafiki.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 29/06/2018.
 */

public class Allergis_Adapter extends RecyclerView.Adapter<Allergis_Adapter.ViewHolder> {

public static class ViewHolder extends RecyclerView.ViewHolder{
    EditText e1;


    public ViewHolder(View itemView) {
        super(itemView);

        e1= itemView.findViewById(R.id.donne);


    }
}

private Context context;
private List<Allergis_ItemData> list;

public Allergis_Adapter(Context context, ArrayList<Allergis_ItemData> list ){

    this.context=context;
    this.list=list;

}
    @Override
    public Allergis_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.activity_allergies_recycler,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Allergis_Adapter.ViewHolder holder, int position) {
        Allergis_ItemData itemData = list.get(position);

            holder.e1.setText(itemData.getEdite1());


        //        if (!itemData.getEdite1().equals("")&& itemData.getEdite2().equals("")){

//        holder.e1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (holder.e2.getText().length()==0){
//                    TEST_VIDE=1;
//                }else {
//                    TEST_VIDE=0;
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (holder.e2.getText().length()==0){
//                    TEST_VIDE=1;
//                }else {
//                    TEST_VIDE=0;
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (holder.e2.getText().length()==0){
//                    TEST_VIDE=1;
//                }else {
//                    TEST_VIDE=0;
//                }
//            }
//
//        });
//        holder.e1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (holder.e1.getText().length()==0){
//                    TEST_VIDE=1;
//                }else {
//                    TEST_VIDE=0;
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (holder.e1.getText().length()==0){
//                    TEST_VIDE=1;
//                }else {
//                    TEST_VIDE=0;
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (holder.e1.getText().length()==0){
//                    TEST_VIDE=1;
//                }else {
//                    TEST_VIDE=0;
//                }
//            }
//
//        });

//


    }

    @Override
    public int getItemCount() {

        return list.size();
    }



}
