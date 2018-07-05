package com.example.user.rafiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 30/03/2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE clients (_id integer PRIMARY KEY autoincrement not null," +
                "nom text not null,prenom text not null,age text not null,payer text not null," +
                "mobile text not null,code text not null,sexe text not null,email text not null,password text not null,image text )";

        String sql2="CREATE TABLE ficheMedicale (_id integer PRIMARY KEY autoincrement not null,"+
                "email text not null,poid text not null,taille text not null,num_scret text,adresse text not null,"+
                "code_postal text not null,ville text not null,sang text)";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String delet_sql="DROP Table clients ";
        String delet_sql2="DROP Table ficheMedicale ";
        sqLiteDatabase.execSQL(delet_sql);
        sqLiteDatabase.execSQL(delet_sql2);

        onCreate(sqLiteDatabase);
    }
}
