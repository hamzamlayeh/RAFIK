package com.example.user.rafiki.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.rafiki.ItemData.Antecedents_ItemData;
import com.example.user.rafiki.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.rafiki.Adapter.Antecedents_Adapter.ViewHolder.TEST_VIDE;

/**
 * Created by user on 29/06/2018.
 */

public class Antecedents_Adapter extends RecyclerView.Adapter<Antecedents_Adapter.ViewHolder> {

public static class ViewHolder extends RecyclerView.ViewHolder{
    EditText e1;
    EditText e2;
    public static int TEST_VIDE=1;
    public ViewHolder(View itemView) {
        super(itemView);

        e1= itemView.findViewById(R.id.acte);
        e2= itemView.findViewById(R.id.date);

    }
}

private Context context;
private List<Antecedents_ItemData> list;

public Antecedents_Adapter(Context context, ArrayList<Antecedents_ItemData> list ){

    this.context=context;
    this.list=list;

}
    @Override
    public Antecedents_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.activity_antecedents_recycler,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Antecedents_Adapter.ViewHolder holder, int position) {
        Antecedents_ItemData itemData = list.get(position);

            holder.e1.setText(itemData.getEdite1());
            holder.e2.setText(itemData.getEdite2());

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
