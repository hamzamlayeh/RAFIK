package com.example.user.rafiki;

import android.os.Bundle;
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

public class Calandrier_pop extends DialogFragment implements View.OnClickListener{

    View view;
    Button ok,anuller;
    DatePicker naisence;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.pop_calendrier,container,false);
        ok=(Button)view.findViewById(R.id.bt_ok);
        anuller=(Button)view.findViewById(R.id.bt_anuler);
        naisence=(DatePicker)view.findViewById(R.id.datePicker);
        ok.setOnClickListener(this);
        anuller.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        Button button=(Button)view;
        if(button.getText().toString().equals("ANULER")){
            this.dismiss();
        }else {
            this.dismiss();
            Calendar cal = Calendar.getInstance();
            int x = cal.getWeekYear()- naisence.getYear();
            String age = x + " ans";

            Inscription inscr = (Inscription) getActivity();
            inscr.setage(age);

        }
    }
}
