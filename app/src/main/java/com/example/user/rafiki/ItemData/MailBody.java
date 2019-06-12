package com.example.user.rafiki.ItemData;

import android.content.Context;

import com.example.user.rafiki.R;

public class MailBody {

    public static String getBody(String password, Context context) {
        String str;

        str = "<html><body><center><img src='https://i.goopics.net/ODgEK.png' style=\"width: 30%;\"></center>" +
                "<h3 style='color:#3F51B5;text-align:center;'>"+context.getString(R.string.Q1)+"</h3>" +
                "<h3 style='color:#3F51B5;text-align:center;'>"+context.getString(R.string.ligne2)+"</h3>" +
                "<br><p>"+context.getString(R.string.ligne3)+"<br><b>" +password+"</b></p> "+
                "<p>"+context.getString(R.string.ligne4)+"<br>" +
                context.getString(R.string.ligne5)+"</p><br>" +
                "<p>"+context.getString(R.string.ligne6_merci)+"<br>"+context.getString(R.string.ligne6)+" </p><br>" +
                "<p>"+context.getString(R.string.ligne7)+"</p>" +
                "</body></html>";
        return str;
    }
}
