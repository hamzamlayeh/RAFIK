package com.example.user.rafiki;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by ASUS on 03/02/2018.
 */

public class Compte_pop extends DialogFragment implements View.OnClickListener{

    View view;
    Button ok,anuller;
    DatePicker naisence;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.pop_compte,container,false);
        ok=(Button)view.findViewById(R.id.bt_ok);
        anuller=(Button)view.findViewById(R.id.bt_anuler);
        naisence=(DatePicker)view.findViewById(R.id.datePicker);
        ok.setOnClickListener(this);
        anuller.setOnClickListener(this);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        Button button=(Button)view;
        if(button.getText().toString().equals("Anuler")){
            this.dismiss();
        }else {


            LoginActivity log = (LoginActivity) getActivity();
            //log.setEmail("khaled@gmail.com");
            this.dismiss();
        }
    }
}
