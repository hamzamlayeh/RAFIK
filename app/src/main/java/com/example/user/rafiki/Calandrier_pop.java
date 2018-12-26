package com.example.user.rafiki;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by ASUS on 03/02/2018.
 */

public class Calandrier_pop extends DialogFragment implements View.OnClickListener {

    View view;
    Button ok, anuller;
    DatePicker naisence;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.pop_calendrier, container, false);
        ok = (Button) view.findViewById(R.id.bt_ok);
        anuller = (Button) view.findViewById(R.id.bt_anuler);
        naisence = (DatePicker) view.findViewById(R.id.datePicker);
        ok.setOnClickListener(this);
        anuller.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String age, date;
        Button button = (Button) view;
        if (button.getText().toString().equals(getString(R.string.anuler))) {
            this.dismiss();
        } else {
            Calendar cal = Calendar.getInstance();
            int x = cal.get(Calendar.YEAR) - naisence.getYear();
            int month = naisence.getMonth() + 1;
            date = naisence.getDayOfMonth() + "-" + month + "-" + naisence.getYear();
            age = String.valueOf(x);
            if (Inscription.NUM_PAGE == 1) {
                Inscription inscr = (Inscription) getActivity();
                inscr.setage(age);
                this.dismiss();
            } else if (Inscription.NUM_PAGE == 2) {
                ModifierCompte inscr = (ModifierCompte) getActivity();
                inscr.setage(age);
                this.dismiss();
            } else if (Inscription.NUM_PAGE == 3) {

                MedicamentsRenseignerActivity inscr = (MedicamentsRenseignerActivity) getActivity();
                if (MedicamentsRenseignerActivity.P == 1) {
                    inscr.setage(date);
                } else {
                    inscr.setage2(date);
                }
                this.dismiss();
            }
        }
    }
}
