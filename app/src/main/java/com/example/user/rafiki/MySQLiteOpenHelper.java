package com.example.user.rafiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 30/03/2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table client
        String sql="CREATE TABLE clients (_id integer PRIMARY KEY autoincrement not null," +
                "nom text not null,prenom text not null,age text not null,payer text not null," +
                "mobile text not null,code text not null,sexe text not null,email text not null,password text not null,image text )";
        //Table FicheMedicale
        String sql2="CREATE TABLE ficheMedicale (_id integer PRIMARY KEY autoincrement not null,"+
                "email text not null,poid text not null,taille text not null,num_scret text,adresse text not null,"+
                "code_postal text not null,ville text not null,sang text)";
        //Table Maladie
        String sql3="CREATE TABLE Maladis (_id integer PRIMARY KEY autoincrement not null,"+
                "NomMaladi text)";
        //Table Antecedents
        String sql4="CREATE TABLE Antecedents (_id integer PRIMARY KEY autoincrement not null,"+
                "acte text not null,date text not null,position integer )";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String delet_sql="DROP Table clients ";
        String delet_sql2="DROP Table ficheMedicale ";
//        String delet_sql3="DROP Table Antecedents ";
//        String delet_sql4="DROP Table Maladis ";
        sqLiteDatabase.execSQL(delet_sql);
        sqLiteDatabase.execSQL(delet_sql2);
//        sqLiteDatabase.execSQL(delet_sql3);
//        sqLiteDatabase.execSQL(delet_sql4);

        onCreate(sqLiteDatabase);
    }
}
